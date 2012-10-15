package com.placella.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Output extends Activity {
	private double height = 0;
	private double weight = 0;
	private boolean imperial = false; 
	private double bmi = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        
        height = getIntent().getDoubleExtra("height", 0);
        weight = (int) getIntent().getIntExtra("weight", 0);
        imperial = (boolean) getIntent().getBooleanExtra("imperial", false);
        bmi = (weight / (height*height));
        
        if (imperial) {
        	bmi *= 703;
        }
        
     /*   System.out.println(" height :" + height);
        System.out.println(" weight :" + weight);
        System.out.println(" bmi :" + bmi);
        System.out.println(" imperial :" + (imperial ? "true" : "false"));*/
        
        TextView result = (TextView) findViewById(R.id.result);
        result.setText(
        	String.format(
    			result.getText().toString(),
    			bmi
    		)
        );
        
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
	        ImageView bar = (ImageView) findViewById(R.id.bar);
	        int width = bar.getWidth();
	        int offset = 0;
	        if (bmi > 32.5) {
	        	offset = width;
	        } else if (bmi > 17.5) {
	        	offset = (int)((bmi - 17.5) / (32.5 - 17.5) * width);
	        }
	        ImageView marker = (ImageView) findViewById(R.id.marker);
	        marker.setPadding(offset, 0, 0, 0);
		}
	}

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	MenuHandler.onOptionsItemSelected(item, this);
		return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);		
	    outState.putDouble("height", height);
	    outState.putDouble("weight", weight);
	    outState.putBoolean("imperial", imperial);
	    outState.putDouble("bmi", bmi);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    height = savedInstanceState.getDouble("height");
	    weight = savedInstanceState.getDouble("weight");
	    imperial = savedInstanceState.getBoolean("imperial");
	    bmi = savedInstanceState.getDouble("bmi");
    }
}
