package com.placella.bmi;

import android.app.*;
import android.content.*;
import android.net.Uri;
import android.view.MenuItem;

/**
 * Contains a static function that handles
 * user choices in the options menu
 * Used in every activity since the context
 * menu in this application does not change
 *
 * @author Rouslan Placella
 */
public class MenuHandler {
    public static void onOptionsItemSelected(MenuItem item, Activity self) {
        if (item.getItemId() == R.id.source_code) {
            // The user chose the "Get Source Code" option
            // Create a new intent
            String url = "https://github.com/roccivic/android-bmi-app";
            Intent i = new Intent(Intent.ACTION_VIEW);
            Uri u = Uri.parse(url);
            i.setData(u);
            // Request to navigate to the web page
            self.startActivity(i);
        } else if (item.getItemId() == R.id.about) {
            // Show the about dialog
            new MessageDialog(self, R.string.about_text).show();
        }
    }
}
