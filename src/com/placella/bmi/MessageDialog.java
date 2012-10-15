package com.placella.bmi;

import android.app.*;

/**
 * A class to show the user a simple
 * pop-up dialog with a message
 *
 * @author Rouslan Placella
 */
public class MessageDialog {
    private AlertDialog.Builder builder;
    /**
     * Creates a new dialog builder and sets the message
     *
     * @param self Context for the dialog
     * @param message The string to display
     */
    public MessageDialog(Activity self, int message) {
        builder = new AlertDialog.Builder(self);
        builder.setPositiveButton(R.string.ok, null);
        builder.setMessage(message);
    }
    /**
     * Shows the dialog
     */
    public void show() {
        builder.create().show();
    }
}
