package com.placella.bmi;

import android.app.Activity;
import android.app.AlertDialog;
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
				// Start the activity
				self.startActivity(i);
			} catch (ActivityNotFoundException e) {
				// Raise on activity not found
				Toast message = Toast.makeText(self, "Browser not found.", Toast.LENGTH_SHORT);
				message.show();
			}
        } else if (item.getItemId() == R.id.about) {
        	new AlertDialog
	    	    .Builder(self)
		    	.setMessage(R.string.about_text)
		    	.setPositiveButton(R.string.ok, null)
		    	.create()
		    	.show();
        }
    }
}