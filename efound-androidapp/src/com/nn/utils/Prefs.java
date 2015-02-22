package com.nn.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Prefs {
	private SharedPreferences _prefs = null;
	private Editor _editor = null;

	public Prefs(Context context) {
		this._prefs = context.getSharedPreferences("PREFS_iFound",
				Context.MODE_PRIVATE);
		this._editor = this._prefs.edit();
	}

	public String getValue(String key, String defaultvalue) {
		if (this._prefs == null) {
			return "Unknown";
		}
		return this._prefs.getString(key, defaultvalue);
	}

	public void setValue(String key, String value) {
		if (this._editor == null) {
			return;
		}
		this._editor.putString(key, value);
	}

	public void setBoolean(String key, Boolean value) {
		if (this._editor == null) {
			return;
		}
		this._editor.putBoolean(key, value);
	}

	public Boolean getBoolean(String key, Boolean defaultvalue) {
		if (this._prefs == null) {
			return false;
		}
		Boolean getBool = this._prefs.getBoolean(key, defaultvalue);
		return getBool;
	}

	public void save() {
		if (this._editor == null) {
			return;
		}
		this._editor.commit();
	}

}
