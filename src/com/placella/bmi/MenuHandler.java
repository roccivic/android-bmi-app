package com.placella.bmi;

import android.app.*;
import android.content.*;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuHandler {
    public static void onOptionsItemSelected(MenuItem item, Activity self) {
        if (item.getItemId() == R.id.source_code) {
			String url = "https://github.com/roccivic/android-bmi-app";
			Intent i = new Intent(Intent.ACTION_VIEW);
			Uri u = Uri.parse(url);
			i.setData(u);
			try {
				self.startActivity(i);
			} catch (ActivityNotFoundException e) {
				Toast message = Toast.makeText(self, "Browser not found.", Toast.LENGTH_SHORT);
				message.show();
			}
        } else if (item.getItemId() == R.id.about) {
        	new MessageDialog(self, R.string.about_text).show();
        }
    }
}
