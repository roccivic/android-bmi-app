package com.placella.bmi;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This class displays a list of links to web sites
 * containing useful information about BMI
 *
 * @author Rouslan Placella
 */
public class LinkList extends ListActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final String[] LINKS = getResources().getStringArray(R.array.urls);
		final String[] LABELS = getResources().getStringArray(R.array.labels);
		setListAdapter(
			new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				LABELS
			)
		);
		// Attach the click event listener
		getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View b, int c, long d) {
				try {
					startActivity(
						new Intent(
							Intent.ACTION_VIEW,
							Uri.parse(LINKS[c])
						)
					);
				} catch (Exception e) {
					// Invalid array index
					// The number of labels doesn't match
					// the number of URLs in the XML file 
				}
			}
		});
	}
}
