package com.nn.activities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nn.adapters.ListProductsAdapter;
import com.nn.pojo.TagInfo;
import com.nn.services.OnServiceResponse;
import com.nn.services.WebElements;
import com.nn.services.WebService;
import com.nn.utils.Prefs;
import com.nn.utils.Utils;

@SuppressLint("NewApi")
public class ListProductsActivity extends Activity implements OnClickListener,
		OnServiceResponse {

	private Context mContext;
	private ListView mListView;
	private TextView mTxtViewEmpty;
	private List<TagInfo> mProductList;
	private ListProductsAdapter mListProductsAdapter;
	public static TagInfo tagInfo;
	private Button mBtnScan, mBtnLogout;
	private List<TagInfo> mListTagedProducts;
	private ProgressDialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_products);

		mContext = this;
		mListView = (ListView) findViewById(R.id.list_products);
		mTxtViewEmpty = (TextView) findViewById(R.id.textEmpty);
		mProductList = new ArrayList<TagInfo>();
		mBtnScan = (Button) findViewById(R.id.btnScan);
		mBtnLogout = (Button) findViewById(R.id.btnLogout);
		mDialog = new ProgressDialog(mContext);
		mDialog.setMessage("Requesting Data");
		mDialog.setCancelable(false);

		mBtnScan.setOnClickListener(this);
		mBtnLogout.setOnClickListener(this);
		if (getIntent().getExtras() != null) {
			if (LoginActivity.mListTagedProducts != null
					&& LoginActivity.mListTagedProducts.size() > 0) {
				mProductList = LoginActivity.mListTagedProducts;
				mListProductsAdapter = new ListProductsAdapter(
						ListProductsActivity.this, mProductList);
				if (mListProductsAdapter != null) {
					mListView.setAdapter(mListProductsAdapter);
					mListView.setVisibility(View.VISIBLE);
					mTxtViewEmpty.setVisibility(View.GONE);
				}
			} else {
				mListView.setVisibility(View.GONE);
				mTxtViewEmpty.setVisibility(View.VISIBLE);
			}
		} else {
			mListTagedProducts = new ArrayList<TagInfo>();
			Prefs sharedPrefs = new Prefs(mContext);
			String username = sharedPrefs.getValue("cnic", "");
			String password = sharedPrefs.getValue("password", "");

			if (username.equals("") || password.equals("")) {
				Utils.showToast(mContext, getString(R.string.error_occurred));
				finish();
				return;
			} else {
				// JSONObject jsonObject = new JSONObject();
				// try {
				// jsonObject.put("username", username);
				// jsonObject.put("password", password);
				// } catch (JSONException e) {
				// e.printStackTrace();
				// }
				mDialog.show();
				WebService webServie = new WebService(mContext);
				webServie.Get(WebElements.CNIC_URL + username
						+ WebElements.PASSWORD_URL + password);
				// webServie.Put(mContext, jsonObject);
			}
		}
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				tagInfo = mProductList.get(arg2);
				startActivity(new Intent(mContext, ViewDataActivity.class)
						.putExtra("user", "yes"));
				finish();
			}
		});

	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
				.setMessage("Are you sure you want to logout?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnScan:
			startActivity(new Intent(mContext, MainActivity.class));
			break;
		case R.id.btnLogout:
			new AlertDialog.Builder(this)
					.setMessage("Are you sure you want to logout?")
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
									finish();
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();
								}
							}).setIcon(android.R.drawable.ic_dialog_alert)
					.show();
			break;
		}
	}

	@Override
	public void onSuccess(String message) {
		// TODO Auto-generated method stub
		mDialog.dismiss();
		if (message == null || message.equals("")) {
			Utils.showToast(mContext, getString(R.string.error_occurred));
			finish();
		} else {
			Type type = new TypeToken<List<TagInfo>>() {
			}.getType();
			Gson gson = new Gson();
			mListTagedProducts = gson.fromJson(message, type);
			if (mListTagedProducts.size() > 0) {
				mProductList = mListTagedProducts;
				mListProductsAdapter = new ListProductsAdapter(
						(Activity) mContext, mProductList);
				mListView.setAdapter(mListProductsAdapter);
			}
		}

	}

	@Override
	public void onFailure(String message) {
		// TODO Auto-generated method stub
		mDialog.dismiss();
		Utils.showToast(mContext, getString(R.string.error_occurred));
		finish();
	}

}
