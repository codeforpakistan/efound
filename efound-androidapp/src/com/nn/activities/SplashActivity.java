package com.nn.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity {
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		mContext = this;
		Thread background = new Thread() {
			public void run() {

				try {
					// Thread will sleep for 5 seconds
					sleep(2 * 1000);

					// After 5 seconds redirect to another intent
					startActivity(new Intent(mContext, LoginActivity.class));
					finish();

					// Remove activity
					finish();

				} catch (Exception e) {

				}
			}
		};
		background.start();
	}

}
