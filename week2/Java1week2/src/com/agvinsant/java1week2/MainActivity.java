/*
 *  project Java1week2
 * 
 * package com.agvinsant.java1week2
 * 
 * @author Adam Vinsant
 * 
 * date Oct 8, 2013
 * 
 */
package com.agvinsant.java1week2;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends Activity {

	// Global variables
	LinearLayout ll;
	String[] compList;
	int[] compPrices;
	TextView compInfo;
	TextView compPrice;
	TextView resInfo;
	Resources res;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// setting resources variable
		res = getResources();
		
		// setting the main layout view
			ll = new LinearLayout(this);
			ll.setOrientation(LinearLayout.VERTICAL);
			//setting the params
			LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			ll.setLayoutParams(lp);
			
			// creating and setting the top text line
			TextView topLine = new TextView(this);
			topLine.setText("Apple Computer Starting Prices");
			ll.addView(topLine);
			
			// creating some space between text views
			TextView blank  = new TextView(this);
			blank.setText(" ");
			ll.addView(blank);
			
			// creating the instruction line
			TextView instructions = new TextView(this);
			instructions.setText("Click a model to see starting price.");
			ll.addView(instructions);
			
			// creating an array adapter for the listview
			
			compList = res.getStringArray(R.array.compArray);
			compPrices = res.getIntArray(R.array.priceArray);
			
			// listView adapter
			ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, compList);
			
			// creating the list
			ListView list = new ListView(this);
			list.setAdapter(listAdapter);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			list.setLayoutParams(lp);
			ll.addView(list);
			
		
			
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,
						long id) {
					
					
						// Pulling Info in from JSON...No file, supposed to be using enum
						String listText = compList[position].toString();
						int priceText = compPrices[position];
						
						// setting results using resource arrays
						resInfo.setText("Computer Type: " + listText + "\r\n" + "Starting Price: $" + priceText + "\r\n");
						
						// setting results with json data
						compInfo.setText("This data would be pulled from JSON data:" + "\r\n" + JSON.readJSON(listText));

					
				}
			});
			
			// creating some space betwen text views
					TextView blank2  = new TextView(this);
					blank2.setText(" ");
					ll.addView(blank2);
					
			// this view shows results pulled from resources 
			resInfo = new TextView(this);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			resInfo.setLayoutParams(lp);
			ll.addView(resInfo);
			
			// computer type results
			compInfo = new TextView(this);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			compInfo.setLayoutParams(lp);
			ll.addView(compInfo);
			
			// setting the content view
			setContentView(ll);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
