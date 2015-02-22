package com.nn.activities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nn.pojo.TagInfo;
import com.nn.services.OnServiceResponse;
import com.nn.services.WebElements;
import com.nn.services.WebService;
import com.nn.utils.Prefs;
import com.nn.utils.Utils;

public class LoginActivity extends Activity implements OnClickListener,
		OnServiceResponse {

	private EditText mEdtTxtUsername, mEdtTxtPassword;
	private Button mBtnLogin, mBtnGuest;
	private Context mContext;
	private ProgressDialog mDialog;
	public static List<TagInfo> mListTagedProducts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		mContext = this;

		mListTagedProducts = new ArrayList<TagInfo>();
		mEdtTxtUsername = (EditText) findViewById(R.id.edtTxtUsername);
		mEdtTxtPassword = (EditText) findViewById(R.id.edtTxtPassword);
		mBtnLogin = (Button) findViewById(R.id.btnLogin);
		mBtnGuest = (Button) findViewById(R.id.btnGuest);

		mDialog = new ProgressDialog(mContext);
		mDialog.setMessage("Authenticating");
		mDialog.setCancelable(false);
		mBtnLogin.setOnClickListener(this);
		mBtnGuest.setOnClickListener(this);

		 mEdtTxtUsername.setText("1430160469949");
		 mEdtTxtPassword.setText("efound");
	}

	@Override
	public void onClick(View v) {
		Utils.hideKeyBoard(mContext, v);
		switch (v.getId()) {
		case R.id.btnGuest:
			startActivity(new Intent(mContext, MainActivity.class));
			break;
		case R.id.btnLogin:
			// startActivity(new Intent(mContext, ListProductsActivity.class));
			if (mEdtTxtUsername.getText().toString().length() == 0) {
				Utils.showToast(mContext, "Enter Username");
			} else if (mEdtTxtPassword.getText().toString().length() == 0) {
				Utils.showToast(mContext, "Enter Password");
			} else {
				if (Utils.isConnectingToInternet(mContext)) {
					mDialog.show();
					String username = mEdtTxtUsername.getText().toString();
					String password = mEdtTxtPassword.getText().toString();
					// JSONObject jsonObject = new JSONObject();
					// try {
					// jsonObject.put("username", username);
					// jsonObject.put("password", password);
					// } catch (JSONException e) {
					// e.printStackTrace();
					// }
					WebService webServie = new WebService(mContext);
					webServie.Get(WebElements.CNIC_URL + username
							+ WebElements.PASSWORD_URL + password);

				} else {
					Utils.showToast(mContext, getString(R.string.no_internet));
				}
			}
			break;
		}

	}

	@Override
	public void onSuccess(String message) {
		// TODO Auto-generated method stub
		if (message == null || message.equals("")) {
			Utils.showToast(mContext, getString(R.string.error_occurred));
			return;
		}
		mDialog.dismiss();
		Type type = new TypeToken<List<TagInfo>>() {
		}.getType();
		Gson gson = new Gson();
		mListTagedProducts = gson.fromJson(message, type);
		startActivity(new Intent(mContext, ListProductsActivity.class)
				.putExtra("login", "login"));
		Prefs sharedPref = new Prefs(mContext);
		sharedPref.setValue("cnic", mEdtTxtUsername.getText().toString());
		sharedPref.setValue("password", mEdtTxtPassword.getText().toString());
		sharedPref.save();
		mEdtTxtUsername.setText("");
		mEdtTxtPassword.setText("");
		mDialog.dismiss();
	}

	@Override
	public void onFailure(String message) {
		mDialog.dismiss();
		Utils.showToast(mContext, getString(R.string.error_occurred));
//		mEdtTxtUsername.setText("");
//		mEdtTxtPassword.setText("");
	}
}
