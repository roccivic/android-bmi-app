package com.placella.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
