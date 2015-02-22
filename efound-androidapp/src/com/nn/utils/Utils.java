package com.nn.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class Utils {
	// public static String BASE_URL =
	// "http://10.99.9.58/projects/stealert/api";
	// public static int TIMEOUT = 1000 * 10;
	// public static final String CONTENT_TYPE = "application/json";

	public static void hideKeyBoard(Context con, View view) {
		InputMethodManager imm = (InputMethodManager) con
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
	}

	public static void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static boolean isConnectingToInternet(Context cnt) {
		ConnectivityManager connectivityManager = (ConnectivityManager) cnt
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
}
