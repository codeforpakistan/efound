package com.nn.activities;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.nn.pojo.TagInfo;
import com.nn.services.OnServiceResponse;
import com.nn.services.WebElements;
import com.nn.services.WebService;
import com.nn.utils.Utils;

@SuppressLint("NewApi")
public class ViewDataActivity extends Activity implements View.OnClickListener,
		OnServiceResponse {

	private Context mContext;
	private String tagNo;
	private Button mBtnConfirm;
	private Button mBtnCancel;
	private LinearLayout mMainLinear;
	private Button mBtnStolen, mBtnFound;
	private AsyncHttpClient mClient;
	private ProgressDialog mDialog;
	private Animation myFadeInAnimation;
	private String user = "";

	private TextView mTxtProName, mTxtCategory, mTxtType, mTxtMadeBy,
			mTxtModel, mTxtMakeYear, mTxtPurchased, mTxtDeatils, mTxtFullName,
			mTxtCNIC, mTxtEmail, mTxtContact, mTxtAddress;
	private LinearLayout mlinear1, mlinear2, mlinear3, mlinear4, mlinear5,
			mlinear6, mlinear7, mlinear8, mlinear9, mlinear10, mlinear11,
			mlinear12, mLinear13;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view_data);

		mContext = this;
		myFadeInAnimation = AnimationUtils.loadAnimation(mContext,
				R.anim.animation);
		mDialog = new ProgressDialog(mContext);
		mDialog.setMessage("Processing...");
		mDialog.setCancelable(false);

		mBtnFound = (Button) findViewById(R.id.btnFound);
		mBtnStolen = (Button) findViewById(R.id.btnStolen);

		mTxtProName = (TextView) findViewById(R.id.txtName);
		mTxtCategory = (TextView) findViewById(R.id.txtTypeCategory);
		mTxtType = (TextView) findViewById(R.id.txtType);
		mTxtMadeBy = (TextView) findViewById(R.id.txtManufacturer);
		mTxtModel = (TextView) findViewById(R.id.txtModel);
		mTxtMakeYear = (TextView) findViewById(R.id.txtMakeYear);
		mTxtPurchased = (TextView) findViewById(R.id.txtPurchased);
		mTxtDeatils = (TextView) findViewById(R.id.txtDetails);
		mTxtFullName = (TextView) findViewById(R.id.txtFullName);
		mTxtCNIC = (TextView) findViewById(R.id.txtCNIC);
		mTxtEmail = (TextView) findViewById(R.id.txtEmail);
		mTxtContact = (TextView) findViewById(R.id.txtContact);
		mTxtAddress = (TextView) findViewById(R.id.txtAddress);

		// Apply Listeners
		mBtnStolen.setOnClickListener(this);
		mBtnFound.setOnClickListener(this);

		// Status Layout
		mMainLinear = (LinearLayout) findViewById(R.id.statusLinear);

		// LinearLayout
		mlinear1 = (LinearLayout) findViewById(R.id.linear1);
		mlinear2 = (LinearLayout) findViewById(R.id.linear2);
		mlinear3 = (LinearLayout) findViewById(R.id.linear3);
		mlinear4 = (LinearLayout) findViewById(R.id.linear4);
		mlinear5 = (LinearLayout) findViewById(R.id.linear5);
		mlinear6 = (LinearLayout) findViewById(R.id.linear6);
		mlinear7 = (LinearLayout) findViewById(R.id.linear7);
		mlinear8 = (LinearLayout) findViewById(R.id.linear8);
		mlinear9 = (LinearLayout) findViewById(R.id.linear9);
		mlinear10 = (LinearLayout) findViewById(R.id.linear10);
		mlinear11 = (LinearLayout) findViewById(R.id.linear11);
		mlinear12 = (LinearLayout) findViewById(R.id.linear12);
		mLinear13 = (LinearLayout) findViewById(R.id.linear13);
		mBtnFound.setOnClickListener(this);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			// String type = bundle.getString("type", "");
			user = bundle.getString("user", "");

			if (user.equals("guest")) {
				TagInfo tagInfo = MainActivity.tagInfo;
				if (tagInfo != null) {
					if (!tagInfo.getEfprName().equals("")) {
						mTxtProName.setText(tagInfo.getEfprName());
					} else {
						mlinear1.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfprCategory().equals("")) {
						mTxtCategory.setText(tagInfo.getEfprCategory());
					} else {
						mlinear2.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfprType().equals("")) {
						mTxtType.setText(tagInfo.getEfprType());
					} else {
						mlinear3.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfprMadeby().equals("")) {
						mTxtMadeBy.setText(tagInfo.getEfprMadeby());
					} else {
						mlinear4.setVisibility(View.GONE);
					}

					if (!tagInfo.getEfprModel().equals("")) {
						mTxtModel.setText(tagInfo.getEfprModel());
					} else {
						mlinear5.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfprMakeyear().equals("")) {
						mTxtMakeYear.setText(tagInfo.getEfprMakeyear());
					} else {
						mlinear6.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfprPurchased().equals("")) {
						mTxtPurchased.setText(tagInfo.getEfprPurchased());
					} else {
						mlinear7.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfusFullname().equals("")) {
						mTxtFullName.setText(tagInfo.getEfusFullname());
					} else {
						mlinear8.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfusCnic().equals("")) {
						mTxtCNIC.setText(tagInfo.getEfusCnic());
					} else {
						mlinear9.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfusEmail().equals("")) {
						mTxtEmail.setText(tagInfo.getEfusEmail());
					} else {
						mlinear10.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfusContact().equals("")) {
						mTxtContact.setText(tagInfo.getEfusContact());
					} else {
						mlinear11.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfusAddress().equals("")) {
						mTxtAddress.setText(tagInfo.getEfusAddress());
					} else {
						mlinear12.setVisibility(View.GONE);
					}
					if (!tagInfo.getEfprDetails().equals("")) {
						mTxtDeatils.setText(tagInfo.getEfprDetails());
					} else {
						mLinear13.setVisibility(View.GONE);
					}

					tagNo = tagInfo.getEfprNfcid();
					if (tagInfo.getEfprStatus().equals("stolen")) {
						mMainLinear.setBackground(getResources().getDrawable(
								R.drawable.stolen));
						mMainLinear.startAnimation(myFadeInAnimation);
					} else {
						mMainLinear.setBackground(null);
					}
				}
			} else {
				if (ListProductsActivity.tagInfo != null) {
					TagInfo tagInfo = ListProductsActivity.tagInfo;
					if (tagInfo != null) {
						if (!tagInfo.getEfprName().equals("")) {
							mTxtProName.setText(tagInfo.getEfprName());
						} else {
							mlinear1.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfprCategory().equals("")) {
							mTxtCategory.setText(tagInfo.getEfprCategory());
						} else {
							mlinear2.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfprType().equals("")) {
							mTxtType.setText(tagInfo.getEfprType());
						} else {
							mlinear3.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfprMadeby().equals("")) {
							mTxtMadeBy.setText(tagInfo.getEfprMadeby());
						} else {
							mlinear4.setVisibility(View.GONE);
						}

						if (!tagInfo.getEfprModel().equals("")) {
							mTxtModel.setText(tagInfo.getEfprModel());
						} else {
							mlinear5.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfprMakeyear().equals("")) {
							mTxtMakeYear.setText(tagInfo.getEfprMakeyear());
						} else {
							mlinear6.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfprPurchased().equals("")) {
							mTxtPurchased.setText(tagInfo.getEfprPurchased());
						} else {
							mlinear7.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfusFullname().equals("")) {
							mTxtFullName.setText(tagInfo.getEfusFullname());
						} else {
							mlinear8.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfusCnic().equals("")) {
							mTxtCNIC.setText(tagInfo.getEfusCnic());
						} else {
							mlinear9.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfusEmail().equals("")) {
							mTxtEmail.setText(tagInfo.getEfusEmail());
						} else {
							mlinear10.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfusContact().equals("")) {
							mTxtContact.setText(tagInfo.getEfusContact());
						} else {
							mlinear11.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfusAddress().equals("")) {
							mTxtAddress.setText(tagInfo.getEfusAddress());
						} else {
							mlinear12.setVisibility(View.GONE);
						}
						if (!tagInfo.getEfprDetails().equals("")) {
							mTxtDeatils.setText(tagInfo.getEfprDetails());
						} else {
							mLinear13.setVisibility(View.GONE);
						}

						tagNo = tagInfo.getEfprNfcid();
						if (tagInfo.getEfprStatus().equals("stolen")) {
							mMainLinear.setBackground(getResources()
									.getDrawable(R.drawable.stolen));
							mMainLinear.startAnimation(myFadeInAnimation);
						} else {
							mMainLinear.setBackground(null);
						}
						if (tagInfo.getEfprStatus().equals("stolen")) {
							mBtnStolen.setVisibility(View.GONE);
							mBtnFound.setVisibility(View.VISIBLE);
						} else {
							mBtnStolen.setVisibility(View.VISIBLE);
							mBtnFound.setVisibility(View.GONE);
						}
					}
				}
			}
			// if (type.equals("stolen")) {
			// mMainLinear.setBackground(getResources().getDrawable(
			// R.drawable.stolen));
			// if (!user.equals("")) {
			// mBtnFound.setVisibility(View.VISIBLE);
			// }
			// } else if (type.equals("fake")) {
			// mMainLinear.setBackground(getResources().getDrawable(
			// R.drawable.fake));
			// if (!user.equals("")) {
			// mBtnStolen.setVisibility(View.VISIBLE);
			// }
			// }
		}
		// String reg_no = bundle.getString("NFC_ID", "");
		// String reg_Date = bundle.getString("Reg_Date", "");
		// String owner_name = bundle.getString("Owner_Name", "");
		// String address = bundle.getString("Address", "");
		// String class_of_vehicle = bundle.getString("Class_Of_Vehicle", "");
		// String type_body = bundle.getString("Type_Body", "");
		// String marker_name = bundle.getString("Marker_Name", "");
		// String manufacturer_year = bundle
		// .getString("Manufacturer_Year", "");
		// String chesis_no = bundle.getString("Chesis_No", "");
		// String engine_no = bundle.getString("Engine_No", "");

		// mTxtNFCID.setText("0X004567");
		// mTxtType.setText("Table");
		// mTxtName.setText("IKEA-TBL-02");
		// mTxtColor.setText("Wood");
		// mTxtManufacturer.setText("IKEA");
		// mTxtMakeYear.setText("2015");
		// mTxtPurchasedDate.setText("February 16, 2015");
		// mTxtOwner.setText("Shahid Nauman");
		// mTxtDetails.setText("Foldable Smartspace");
		// mMainLinear
		// .setBackground(getResources().getDrawable(R.drawable.stolen));
		// mMainLinear.startAnimation(myFadeInAnimation);
		// }

	}

	@Override
	public void onClick(View v) {
		if (!Utils.isConnectingToInternet(mContext)) {
			Utils.showToast(mContext, getString(R.string.no_internet));
			return;
		}
		switch (v.getId()) {
		case R.id.btnFound:
			callWebservice(tagNo, WebElements.STATUS_DEFAULT);
			break;
		case R.id.btnStolen:
			callWebservice(tagNo, WebElements.STATUS_STOLEN);
		}

	}

	private void callWebservice(String tagId, String status) {
		mDialog.show();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("tagid", tagId);
			jsonObject.put("status", status);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebService mWebService = new WebService(mContext);
		mWebService.Get(WebElements.TAG_INFO_URL + tagId
				+ WebElements.TAG_STATUS_URL + status);
		// mWebService.Put(mContext, jsonObject);
		// mClient.setTimeout(10000);
		// StringEntity entity = null;
		// String str = jsonObject.toString();
		//
		// try {
		// entity = new StringEntity(str);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// mClient.put(mContext, WebElements.BASE_URL, entity,
		// WebElements.CONTENT_TYPE, new AsyncHttpResponseHandler() {
		//
		// @Override
		// public void onSuccess(String content) {
		// super.onSuccess(content);
		// Utils.showToast(mContext,
		// getString(R.string.status_successful));
		// finish();
		// }
		//
		// @Override
		// public void onFailure(int statusCode, Throwable error,
		// String content) {
		// super.onFailure(statusCode, error, content);
		// Utils.showToast(mContext,
		// getString(R.string.error_occurred));
		// }
		// });
	}

	@Override
	public void onSuccess(String message) {
		mDialog.dismiss();
		Utils.showToast(mContext, getString(R.string.status_successful));
		if (user.equals("yes")) {
			startActivity(new Intent(mContext, ListProductsActivity.class));
			finish();
		} else {
			finish();
		}
	}

	@Override
	public void onFailure(String message) {
		mDialog.dismiss();
		Utils.showToast(mContext, getString(R.string.error_occurred));
	}

	@Override
	public void onBackPressed() {
		if (mDialog.isShowing()) {
			mDialog.dismiss();
		}
		if (user.equals("yes")) {
			startActivity(new Intent(mContext, ListProductsActivity.class));
			finish();
		} else {
			finish();
		}
	}

}
