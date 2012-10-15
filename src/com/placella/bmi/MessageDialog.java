package com.placella.bmi;

import android.app.*;

public class MessageDialog {
	private AlertDialog.Builder builder;
	public MessageDialog(Activity self, int message) {
		builder = new AlertDialog.Builder(self);
		builder.setPositiveButton(R.string.ok, null);
		builder.setMessage(message);
	}
	public void show() {
		builder.create().show();
	}
}
