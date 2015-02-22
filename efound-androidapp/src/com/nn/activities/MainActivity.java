package com.nn.activities;

import java.io.ByteArrayOutputStream;

import org.ndeftools.Message;
import org.ndeftools.Record;
import org.ndeftools.UnsupportedRecord;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Window;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.nn.pojo.TagInfo;
import com.nn.services.OnServiceResponse;
import com.nn.services.WebElements;
import com.nn.services.WebService;
import com.nn.utils.Utils;
import com.skjolberg.nfc.NfcReader;
import com.skjolberg.nfc.NfcTag;
import com.skjolberg.nfc.util.activity.NfcExternalDetectorActivity;

public class MainActivity extends NfcExternalDetectorActivity implements
		OnServiceResponse {

	private static final String TAG = MainActivity.class.getName();

	protected Boolean service = null;
	protected Boolean reader = null;
	protected Boolean tag = null;

	private NdefFormatable ndefFormatable;
	private Ndef ndef;
	private Context mContext;
	private AsyncHttpClient mClient;
	public static TagInfo tagInfo;
	// private RelativeLayout relative1, relative2, relative3;
	private ProgressDialog mDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mContext = this;
		init();
		initializeExternalNfc();

		setDetecting(true);
		// apply listeners
		// relative1.setOnClickListener(this);
		// relative2.setOnClickListener(this);
		// relative3.setOnClickListener(this);
	}

	private void init() {
		mDialog = new ProgressDialog(mContext);
		mDialog.setMessage("Checking Records");
		mDialog.setCancelable(false);
		// relative1 = (RelativeLayout) findViewById(R.id.relative1);
		// relative2 = (RelativeLayout) findViewById(R.id.relative2);
		// relative3 = (RelativeLayout) findViewById(R.id.relative3);

	}

	// @Override
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.relative1:
	// startActivity(new Intent(mContext, ViewDataActivity.class)
	// .putExtra("type", "stolen"));
	// break;
	// case R.id.relative2:
	// startActivity(new Intent(mContext, ViewDataActivity.class)
	// .putExtra("type", "fake"));
	// break;
	// case R.id.relative3:
	// startActivity(new Intent(mContext, ViewDataActivity.class)
	// .putExtra("type", "approve"));
	// break;
	//
	// }
	// }

	@SuppressLint("NewApi")
	public void onNfcIntentDetected(Intent intent, String action) {

		setTagPresent(true);

		if (intent.hasExtra(NfcAdapter.EXTRA_ID)) {
			byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
			String tag_id = toHexString(id);
			if (Utils.isConnectingToInternet(mContext)) {
				mDialog.show();

				WebService mWebService = new WebService(mContext);
				mWebService.Get(WebElements.TAG_INFO_URL + tag_id);
			} else {
				Utils.showToast(mContext, getString(R.string.no_internet));
			}

		} else {
			Log.d(TAG, "No tag id");

			// setTagId(getString(R.string.tagIdNone));
		}

		if (intent.hasExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)) {

			Parcelable[] messages = intent
					.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
			if (messages != null) {
				Log.d(TAG, "NDEF message");

				NdefMessage[] ndefMessages = new NdefMessage[messages.length];
				for (int i = 0; i < messages.length; i++) {
					ndefMessages[i] = (NdefMessage) messages[i];
				}

				// read as much as possible
				Message message = new Message();
				for (int i = 0; i < messages.length; i++) {
					NdefMessage ndefMessage = (NdefMessage) messages[i];

					for (NdefRecord ndefRecord : ndefMessage.getRecords()) {

						Record record;
						try {
							record = Record.parse(ndefRecord);

							Log.d(TAG, "NDEF record "
									+ record.getClass().getName());
						} catch (FormatException e) {
							// if the record is unsupported or corrupted, keep
							// as unsupported record
							record = UnsupportedRecord.parse(ndefRecord);
						}

						message.add(record);
					}
				}
				// showRecords(message);
			} else {
				// hideRecords();
			}
		} else {
			Log.d(TAG, "No NDEF message");

			// hideRecords();
		}

		// setTagType(getString(R.string.tagTypeNone));
		if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {

			Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

			try {
				String[] techList = tag.getTechList();

				for (String tech : techList) {
					Log.d(TAG, "Tech " + tech);

					if (tech.equals(android.nfc.tech.MifareUltralight.class
							.getName())) {

						// setTagType(getString(R.string.tagTypeMifareUltralight));

						MifareUltralight mifareUltralight = MifareUltralight
								.get(tag);
						if (mifareUltralight == null) {
							throw new IllegalArgumentException(
									"No Mifare Ultralight");
						}
						try {
							mifareUltralight.connect();

							int offset = 4;
							int length;

							int type = mifareUltralight.getType();
							switch (type) {
							case MifareUltralight.TYPE_ULTRALIGHT: {
								length = 12;

								break;
							}
							case MifareUltralight.TYPE_ULTRALIGHT_C: {
								length = 36;

								break;
							}
							default:
								throw new IllegalArgumentException(
										"Unknown mifare ultralight tag " + type);
							}

							int readLength = 4;

							ByteArrayOutputStream bout = new ByteArrayOutputStream();

							for (int i = offset; i < offset + length; i += readLength) {
								bout.write(mifareUltralight.readPages(i));
							}

							byte[] buffer = bout.toByteArray();

							StringBuilder builder = new StringBuilder();
							for (int k = 0; k < buffer.length; k += readLength) {
								builder.append((offset + k) + " "
										+ toHexString(buffer, k, readLength));
								builder.append('\n');
							}

							Log.d(TAG, builder.toString());

							mifareUltralight.close();
						} catch (Exception e) {
							Log.d(TAG, "Problem processing tag technology", e);
						}
					} else if (tech.equals(android.nfc.tech.NfcA.class
							.getName())) {
						Log.d(TAG, "Ignore " + tech);
					} else if (tech.equals(android.nfc.tech.NfcB.class
							.getName())) {
						Log.d(TAG, "Ignore " + tech);
					} else if (tech.equals(android.nfc.tech.NfcF.class
							.getName())) {
						Log.d(TAG, "Ignore " + tech);
					} else if (tech.equals(android.nfc.tech.NfcV.class
							.getName())) {
						Log.d(TAG, "Ignore " + tech);
					} else if (tech.equals(android.nfc.tech.IsoDep.class
							.getName())) {
						android.nfc.tech.IsoDep isoDep = IsoDep.get(tag);

						boolean hostCardEmulation = intent.getBooleanExtra(
								NfcTag.EXTRA_HOST_CARD_EMULATION, false);
					}
				}
			} catch (Exception e) {
				Log.d(TAG, "Problem processing tag technology", e);
			}
		}
	}

	// if (hostCardEmulation) {
	// // setTagType(getString(R.string.tagTypeHostCardEmulation));
	//
	// Log.d(TAG, "Got " + isoDep.getClass().getName()
	// + " for HCE");
	// }
	// SharedPreferences prefs = PreferenceManager
	// .getDefaultSharedPreferences(this);
	//
	// boolean autoSelectIsoApplication = prefs
	// .getBoolean(
	// PreferencesActivity.PREFERENCE_HOST_CARD_EMULATION_AUTO_SELECT_ISO_APPLICATION,
	// true);

	// if (autoSelectIsoApplication) {
	// isoDep.connect();
	//
	// // attempt to select demo HCE application using
	// // iso adpu
	// String isoApplicationString = prefs
	// .getString(
	// PreferencesActivity.PREFERENCE_HOST_CARD_EMULATION_ISO_APPLICATION_ID,
	// null);
	//
	// // clean whitespace
	// isoApplicationString = isoApplicationString
	// .replaceAll("\\s", "");

	// try {
	// byte[] key = hexStringToByteArray(isoApplicationString);
	//
	// // send ISO select application.
	// // All commands starting with 0x00 are
	// // passed through without ADPU wrapping for
	// // HCE
	// CommandAPDU command = new CommandAPDU(0x00,
	// 0xA4, 0x04, 00, key);
	//
	// Log.d(TAG, "Send request "
	// + toHexString(command.getBytes()));
	//
	// byte[] responseBytes = isoDep
	// .transceive(command.getBytes());
	//
	// Log.d(TAG, "Got response "
	// + toHexString(responseBytes));
	//
	// ResponseAPDU response = new ResponseAPDU(
	// responseBytes);
	//
	// if (response.getSW1() == 0x91
	// && response.getSW2() == 0x00) {
	// Log.d(TAG, "Selected HCE application "
	// + isoApplicationString);
	//
	// // issue command which now should be
	// // routed to the same HCE client
	// // pretend to select application of
	// // desfire card
	//
	// DesfireReader reader = new DesfireReader(
	// isoDep);
	// reader.selectApplication(0x00112233);
	//
	// Log.d(TAG,
	// "Selected application using desfire select application command");
	// } else if (response.getSW1() == 0x82
	// && response.getSW2() == 0x6A) {
	// Log.d(TAG, "HCE application "
	// + isoApplicationString
	// + " not found on remote device");
	// } else {
	// Log.d(TAG,
	// "Unknown error selecting HCE application "
	// + isoApplicationString);
	// }
	// } catch (Exception e) {
	// Log.w(TAG, "Unable to decode HEX string "
	// + isoApplicationString
	// + " into binary data", e);
	// }
	// isoDep.close();
	//
	// }
	//
	// } else {
	// // setTagType(getString(R.string.tagTypeDesfire));
	//
	// Log.d(TAG, "Got " + isoDep.getClass().getName());
	//
	// isoDep.connect();
	//
	// DesfireReader reader = new DesfireReader(isoDep);
	//
	// VersionInfo versionInfo = reader.getVersionInfo();
	//
	// Log.d(TAG,
	// "Got version info - hardware version "
	// + versionInfo.getHardwareVersion()
	// + " / software version "
	// + versionInfo.getSoftwareVersion());
	//
	// isoDep.close();
	// }
	//
	// } else if (tech.equals(android.nfc.tech.MifareClassic.class
	// .getName())) {
	// android.nfc.tech.MifareClassic mifareClassic = MifareClassic
	// .get(tag);
	//
	// // setTagType(getString(R.string.tagTypeMifareClassic));
	//
	// Log.d(TAG, "Got " + mifareClassic.getClass().getName());
	// } else if (tech.equals(android.nfc.tech.Ndef.class
	// .getName())) {
	// this.ndef = Ndef.get(tag);
	//
	// Log.d(TAG, "Got " + ndef.getClass().getName());
	//
	// } else if (tech
	// .equals(android.nfc.tech.NdefFormatable.class
	// .getName())) {
	//
	// this.ndefFormatable = NdefFormatable.get(tag);
	//
	// Log.d(TAG, "Got " + ndefFormatable.getClass().getName());
	//
	// }
	//

	// } catch (Exception e) {
	// Log.d(TAG, "Problem processing tag technology", e);
	// }
	// }
	//
	// invalidateOptionsMenu();
	// }

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	protected void onExternalNfcIntentDetected(Intent intent, String action) {
		// default to same as native NFC
		onNfcIntentDetected(intent, action);
	}

	@Override
	protected void onExternalNfcReaderClosed(Intent intent) {
		if (intent.hasExtra(NfcReader.EXTRA_READER_STATUS_CODE)) {
			Log.d(TAG,
					"Disconnect status code "
							+ intent.getIntExtra(
									NfcReader.EXTRA_READER_STATUS_CODE, -1));
		}

		if (intent.hasExtra(NfcReader.EXTRA_READER_STATUS_MESSAGE)) {
			Log.d(TAG,
					"Disconnect status message "
							+ intent.getCharSequenceExtra(NfcReader.EXTRA_READER_STATUS_MESSAGE));
		}

		setReaderOpen(false);
	}

	/**
	 * 
	 * NFC feature was found and is currently enabled
	 * 
	 */

	@Override
	protected void onNfcStateEnabled() {
		// toast(getString(R.string.nfcAvailableEnabled));
	}

	/**
	 * 
	 * NFC feature was found but is currently disabled
	 * 
	 */

	@Override
	protected void onNfcStateDisabled() {
		// toast(getString(R.string.nfcAvailableDisabled));
	}

	/**
	 * 
	 * NFC setting changed since last check. For example, the user enabled NFC
	 * in the wireless settings.
	 * 
	 */

	@Override
	protected void onNfcStateChange(boolean enabled) {
		// if (enabled) {
		// toast(getString(R.string.nfcAvailableEnabled));
		// } else {
		// toast(getString(R.string.nfcAvailableDisabled));
		// }
	}

	/**
	 * 
	 * This device does not have NFC hardware
	 * 
	 */

	@Override
	protected void onNfcFeatureNotFound() {
		// toast(getString(R.string.noNfcMessage));
	}

	@Override
	protected void onNfcTagLost(Intent intent) {
		setTagPresent(false);

		// invalidateOptionsMenu();
	}

	protected void onExternalNfcTagLost(Intent intent) {
		// default to same as native NFC
		this.ndef = null;
		this.ndefFormatable = null;

		onNfcTagLost(intent);
	}

	public void setTagPresent(final boolean present) {
		this.tag = present;

		// invalidateOptionsMenu();

		// if(present) {
		// setTextViewText(R.id.tagStatus, R.string.tagStatusPresent);
		//
		// setViewVisibility(R.id.tagIdRow, View.VISIBLE);
		// setViewVisibility(R.id.tagTypeRow, View.VISIBLE);
		// } else {
		// setTextViewText(R.id.tagStatus, R.string.tagStatusAbsent);
		//
		// setViewVisibility(R.id.tagIdRow, View.GONE);
		// setViewVisibility(R.id.tagTypeRow, View.GONE);
		//
		// hideRecords();
		// }

		// invalidateOptionsMenu();
	}

	public void setReaderOpen(final boolean open) {
		this.reader = open;
		// if (open) {
		// setTextViewText(R.id.readerStatus, R.string.readerStatusOpen);
		//
		// setViewVisibility(R.id.tagStatusRow, View.VISIBLE);
		// setViewVisibility(R.id.tagIdRow, View.GONE);
		// setViewVisibility(R.id.tagTypeRow, View.GONE);
		// } else {
		// setTextViewText(R.id.readerStatus, R.string.readerStatusClosed);
		//
		// setViewVisibility(R.id.tagStatusRow, View.GONE);
		// setViewVisibility(R.id.tagIdRow, View.GONE);
		// setViewVisibility(R.id.tagTypeRow, View.GONE);
		// }
		//
		// invalidateOptionsMenu();
	}

	@Override
	protected void onExternalNfcServiceStopped(Intent intent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onExternalNfcServiceStarted(Intent intent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onExternalNfcReaderOpened(Intent intent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess(String message) {
		// TODO Auto-generated method stub
		if (message != null && !message.equals("")) {
			mDialog.dismiss();
			Gson gson = new Gson();

			tagInfo = gson.fromJson(message, TagInfo.class);
			if (tagInfo != null) {
				startActivity(new Intent(mContext, ViewDataActivity.class)
						.putExtra("user", "guest"));
			}
		} else {
			mDialog.dismiss();
			Utils.showToast(mContext, "NOT_EXIST");
		}
	}

	@Override
	public void onFailure(String message) {
		// TODO Auto-generated method stub
		mDialog.dismiss();
		Utils.showToast(mContext, getString(R.string.error_occurred));
	}
}
