package com.nn.services;

public interface OnServiceResponse {
	public void onSuccess(String message);

	public void onFailure(String message);
}
