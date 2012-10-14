package com.placella.bmi;

import com.placella.bmi.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DataInput extends Activity {
	private DataInput self = this;
	private int height = 0;
	private int weight = 0;
	private boolean imperial; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        
        imperial = (boolean) getIntent().getBooleanExtra("imperial", false);

        if (! imperial) {
        	TextView t = (TextView) findViewById(R.id.height_text);
            t.setText(R.string.metric_your_height);
        	t = (TextView) findViewById(R.id.weight_text);
            t.setText(R.string.metric_your_weight);
        }
        
        final Button button = (Button) findViewById(R.id.calculate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
	            EditText[] inputs = {
	                (EditText) findViewById(R.id.height),
	                (EditText) findViewById(R.id.weight)
            	};
	            TextView[] labels = {
		            (TextView) findViewById(R.id.height_text),
		            (TextView) findViewById(R.id.weight_text)
	            };
	            int colors[] = {
            		getResources().getColor(R.color.normal),
            		getResources().getColor(R.color.error)
	            };
        		try {
        			height = Integer.parseInt(inputs[0].getText().toString());
        		} catch (Exception e) {}
        		try {
        			weight = Integer.parseInt(inputs[1].getText().toString());
        		} catch (Exception e) {}
        		labels[0].setTextColor(colors[0]);
        		labels[1].setTextColor(colors[0]);
            	if (height <= 0 || weight <= 0) {
            		errorDialog(R.string.invalid);
            		if (height <= 0) {
            			labels[0].setTextColor(colors[1]);
            		}
            		if (weight <= 0) {
            			labels[1].setTextColor(colors[1]);
            		}
            	} else {
            		Button button = (Button) findViewById(R.id.calculate);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
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
                    });            		
            	}
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
