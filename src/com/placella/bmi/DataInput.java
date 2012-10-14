package com.placella.bmi;

import com.placella.bmi.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataInput extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        
        boolean imperial = (boolean) getIntent().getBooleanExtra("imperial", false);

        if (! imperial) {
        	TextView t = (TextView) findViewById(R.id.height_text);
            t.setText(R.string.metric_your_height);
        	t = (TextView) findViewById(R.id.weight_text);
            t.setText(R.string.metric_your_weight);
        }
        
        final Button button = (Button) findViewById(R.id.calculate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	errorDialog(R.string.invalid);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void errorDialog(int message) {
    	new AlertDialog
    	    .Builder(this)
	    	.setMessage(message)
	    	.setPositiveButton(R.string.ok, null)
	    	.create()
	    	.show();
    }
}
