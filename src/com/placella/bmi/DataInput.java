package com.placella.bmi;

import com.placella.bmi.R;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

/**
 * This class represents the activity
 * where the user inputs the data
 * to be processed
 *
 * @author Rouslan Placella
 */
public class DataInput extends Activity {
    private DataInput self = this;
    private int height = 0;
    private int weight = 0;
    /**
     * Whether we are using imperial measurements
     */
    private boolean imperial = false;
    /**
     * Called when the activity is starting
     * Prints saved values into the input boxes and sets up
     * the callback for the "Calculate" button click event
     *
     * @param savedInstanceState This bundle may contain saved data
     * @override
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Draw the layout elements on screen
        setContentView(R.layout.activity_input);
        // Retrieve any saved elements
        imperial = (boolean) getIntent().getBooleanExtra("imperial", false);
        height = getIntent().getIntExtra("height", 0);
        weight = (int) getIntent().getIntExtra("weight", 0);
        // Print the values into the text boxes
        EditText[] inputs = {
            (EditText) findViewById(R.id.height),
            (EditText) findViewById(R.id.weight)
        };
        inputs[0].setText(Integer.toString(height));
        inputs[1].setText(Integer.toString(weight));
        // Select the text in the first text box so
        // that the user can start typing right away
        inputs[0].selectAll();

        if (! imperial) {
            // Change the text of the labels in we are in metric mode
            TextView t = (TextView) findViewById(R.id.height_text);
            t.setText(R.string.metric_your_height);
            t = (TextView) findViewById(R.id.weight_text);
            t.setText(R.string.metric_your_weight);
        }
        // Save the current state
        update(height, weight);
        // Bind event handler to the button click event
        Button button = (Button) findViewById(R.id.calculate);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * This function will retrieve the user supplied values,
             * validate them and if the validation passes it will call
             * the next activity, otherwise it will show an error message
             */
            public void onClick(View v) {
                int[] values = getValues();
                height = values[0];
                weight = values[1];
                TextView[] labels = {
                    (TextView) findViewById(R.id.height_text),
                    (TextView) findViewById(R.id.weight_text)
                };
                int colors[] = {
                    getResources().getColor(R.color.normal),
                    getResources().getColor(R.color.error)
                };
                // Reset the colour of the labels
                labels[0].setTextColor(colors[0]);
                labels[1].setTextColor(colors[0]);
                if (height <= 0 || weight <= 0) {
                    // Validation failed, show an error message
                    new MessageDialog(self, R.string.invalid).show();
                    // Highlight the labels of all invalid fields
                    if (height <= 0) {
                        labels[0].setTextColor(colors[1]);
                    }
                    if (weight <= 0) {
                        labels[1].setTextColor(colors[1]);
                    }
                } else {
                    // Save the current state
                    update(height, weight);
                    // Prepare the data to be passed
                    // to the next activity and start it
                    Bundle b = new Bundle();
                    b.putBoolean("imperial", imperial);
                    if (! imperial) {
                        b.putDouble("height", (double)height / 100);
                    } else {
                        b.putDouble("height", (double)height);
                    }
                    b.putInt("weight", weight);
                    Intent i = new Intent(self, Output.class);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });
    }

    /**
     * Saves the current input data
     * when the user releases a key
     *
     * @param keyCode The value in event.getKeyCode()
     * @param event   Description of the key event
     * @override
     */
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        int[] values = getValues();
        update(values[0], values[1]);
        return super.onKeyUp(keyCode, event);
    }

    /**
     * Saves the current input data in order to later
     * pass it on to the calling activity
     *
     * @param h Height
     * @param w Weight
     */
    private void update(int h, int w) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("height", h);
        returnIntent.putExtra("weight", w);
        returnIntent.putExtra("imperial", imperial);
        setResult(0, returnIntent);
    }

    /**
     * Tell the calling activity that we have finished
     * executing and pass along some data back
     *
     * @override
     */
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
     * Retrieves the input data from the text boxes
     *
     * @return [ height, weight ]
     */
    private int[] getValues() {
        EditText input;
        int[] retval = new int[2];
        // Default to 0 in the case of any parsing errors
        try {
            input = (EditText) findViewById(R.id.height);
            retval[0] = Integer.parseInt(input.getText().toString());
        } catch (Exception e) {
            retval[0] = 0;
        }
        try {
            input = (EditText) findViewById(R.id.weight);
            retval[1] = Integer.parseInt(input.getText().toString());
        } catch (Exception e) {
            retval[1] = 0;
        }
        return retval;
    }

    /**
     * Saves the state of the activity
     *
     * @param outState Bundle in which to save data
     * @override
     */
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("height", height);
        outState.putInt("weight", weight);
        outState.putBoolean("imperial", imperial);
    }

    /**
     * Restores the state of the activity
     *
     * @param outState The data supplied in onSaveInstanceState()
     * @override
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        height = savedInstanceState.getInt("height");
        weight = savedInstanceState.getInt("weight");
        imperial = savedInstanceState.getBoolean("imperial");
    }
}
