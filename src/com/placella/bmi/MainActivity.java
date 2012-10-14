package com.placella.bmi;

import com.placella.bmi.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private int imperialHeight = 0;
	private int imperialWeight = 0;
	private int metricHeight = 0;
	private int metricWeight = 0;
	private final int IMPERIAL = 0;
	private final int METRIC = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.choose_imperial);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	handleClick(true, imperialHeight, imperialWeight, IMPERIAL);
            }
        });
        button = (Button) findViewById(R.id.choose_metric);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	handleClick(false, metricHeight, metricWeight, METRIC);
            }
        });
    }
    
    private void handleClick(boolean imperial, int height, int weight, int requestCode) {
    	Bundle b = new Bundle();
    	b.putBoolean("imperial", imperial);
    	b.putInt("height", height);
    	b.putInt("weight", weight);
    	Intent intent = new Intent(this, DataInput.class);
    	intent.putExtras(b);
    	startActivityForResult(intent, requestCode);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	if (requestCode == IMPERIAL) {
    		imperialHeight = intent.getIntExtra("height", 0);
    		imperialWeight = intent.getIntExtra("weight", 0);
    	} else {
    		metricHeight = intent.getIntExtra("height", 0);
    		metricWeight = intent.getIntExtra("weight", 0);
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
