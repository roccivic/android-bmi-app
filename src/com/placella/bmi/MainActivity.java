package com.placella.bmi;

import com.placella.bmi.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.*;
import android.view.*;
import android.widget.Button;

/**
 * This class represents main activity
 * Here it is possible to choose the desired units
 * of measurement to be used in the calculations
 *
 * @author Rouslan Placella
 */
public class MainActivity extends Activity {
    private int imperialHeight;
    private int imperialWeight;
    private int metricHeight;
    private int metricWeight;
    private final int IMPERIAL = 0;
    private final int METRIC = 1;
    private SharedPreferences prefs;

    /**
     * Called when the activity is starting
     * Displays the buttons for selecting the unit
     * of measurement and binds click events to them
     *
     * @param savedInstanceState This bundle may contain saved data
     * @override
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Draw the layout elements on screen
        setContentView(R.layout.activity_main);
        // Retrieve data from persistent storage
        prefs = getSharedPreferences("bmi_values", 0);
        imperialHeight = prefs.getInt("imperialHeight", 0);
        imperialWeight = prefs.getInt("imperialWeight", 0);
        metricHeight = prefs.getInt("metricHeight", 0);
        metricWeight = prefs.getInt("metricWeight", 0);
        // Bind click events
        Button button = (Button) findViewById(R.id.choose_imperial);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleClick(true, imperialHeight, imperialWeight);
            }
        });
        button = (Button) findViewById(R.id.choose_metric);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleClick(false, metricHeight, metricWeight);
            }
        });

    }

    /**
     * Called when the user click on a button on the screen
     *
     * @param imperial Whether to use imperial units
     * @param height A previously saved value for the height
     * @param weight A previously saved value for the weight
     */
    private void handleClick(boolean imperial, int height, int weight) {
        // Create a bundle of data to be passed to the next activity
        Bundle b = new Bundle();
        b.putBoolean("imperial", imperial);
        b.putInt("height", height);
        b.putInt("weight", weight);
        Intent intent = new Intent(this, DataInput.class);
        intent.putExtras(b);
        // Start the next activity and expect
        // to be called back when it finishes executing
        startActivityForResult(intent, imperial ? IMPERIAL : METRIC);
    }

    /**
     * Called when the child activity has finished executing
     * Saves any data the child activity may have passed back
     *
     * @override
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == IMPERIAL) {
            imperialHeight = intent.getIntExtra("height", 0);
            imperialWeight = intent.getIntExtra("weight", 0);
        } else {
            metricHeight = intent.getIntExtra("height", 0);
            metricWeight = intent.getIntExtra("weight", 0);
        }
    }

    /**
     * Displays the options menu
     *
     * @param menu The options menu in which to place the items
     * @override
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * Called whenever an item in the options
     * menu is selected. Here we leave the dedicated
     * MenuHandler class take care of the request
     *
     * @param item The menu item that was selected
     * @override
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuHandler.onOptionsItemSelected(item, this);
        return super.onOptionsItemSelected(item);
    }

    /**
     * Saves the state of the activity to the shared preferences
     * in order to remember the values that the user has previously
     * used between sessions
     *
     * @override
     */
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("imperialHeight", imperialHeight);
        editor.putInt("imperialWeight", imperialWeight);
        editor.putInt("metricHeight", metricHeight);
        editor.putInt("metricWeight", metricWeight);
        editor.commit();
    }

    /**
     * Saves the state of the activity
     *
     * @param outState Bundle in which to save data
     * @override
     */
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("imperialHeight", imperialHeight);
        outState.putInt("imperialWeight", imperialWeight);
        outState.putInt("metricHeight", metricHeight);
        outState.putInt("metricWeight", metricWeight);
    }

    /**
     * Restores the state of the activity
     *
     * @param outState The data supplied in onSaveInstanceState()
     * @override
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imperialHeight = savedInstanceState.getInt("imperialHeight");
        imperialWeight = savedInstanceState.getInt("imperialWeight");
        metricHeight = savedInstanceState.getInt("metricHeight");
        metricWeight = savedInstanceState.getInt("metricWeight");
    }
}
