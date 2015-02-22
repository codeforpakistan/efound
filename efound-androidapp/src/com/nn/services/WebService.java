package com.nn.services;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class WebService implements OnServiceResponse {

	/**
	 * static Singleton instance
	 */
	private static WebService instance;
	private AsyncHttpClient mClient;
	private OnServiceResponse mServiceResponse;

	/**
	 * Private constructor for singleton
	 */
	public WebService() {
	}

	public WebService(Context context) {
		this.mServiceResponse = (OnServiceResponse) context;
	}

	/**
	 * Static getter method for retrieving the singleton instance
	 */
	public static WebService getInstance() {
		if (instance == null) {
			instance = new WebService();
		}
		return instance;
	}

	public void Get(String url) {
		url = getAbsoluteUrl(url);
		mClient = new AsyncHttpClient();
		mClient.setTimeout(WebElements.TIMEOUT);
		mClient.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
				mServiceResponse.onSuccess(content);
			}

			@Override
			public void onFailure(int statusCode, Throwable error,
					String content) {
				super.onFailure(statusCode, error, content);
				mServiceResponse.onFailure(content);
			}
		});
	}

	public void Put(Context context, JSONObject jsonObject) {

		// url = getAbsoluteUrl(url);
		mClient = new AsyncHttpClient();
		mClient.setTimeout(WebElements.TIMEOUT);
		StringEntity entity = null;
		String str = jsonObject.toString();

		try {
			entity = new StringEntity(str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mClient.put(context, WebElements.BASE_URL, entity,
				WebElements.CONTENT_TYPE, new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String content) {
						super.onSuccess(content);
						mServiceResponse.onSuccess(content);
					}

					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {
						super.onFailure(statusCode, error, content);
						mServiceResponse.onFailure(content);
					}
				});
	}

	@Override
	public void onSuccess(String message) {
		mServiceResponse.onSuccess(message);
	}

	@Override
	public void onFailure(String message) {
		mServiceResponse.onFailure(message);

	}

	private String getAbsoluteUrl(String url) {
		url = WebElements.BASE_URL + url;
		return url;
	}

}
