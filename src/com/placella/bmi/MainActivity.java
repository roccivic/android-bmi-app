package com.placella.bmi;

import com.placella.bmi.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private MainActivity self = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button) findViewById(R.id.choose_imperial);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Bundle b = new Bundle();
            	b.putBoolean("imperial", true);
            	Intent i = new Intent(self, DataInput.class);
            	i.putExtras(b);
            	startActivity(i);
            }
        });
        button = (Button) findViewById(R.id.choose_metric);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Bundle b = new Bundle();
            	b.putBoolean("imperial", false);
            	Intent i = new Intent(self, DataInput.class);
            	i.putExtras(b);
            	startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
