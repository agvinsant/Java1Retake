/*
 *  project Java1week3
 * 
 * package com.agvinsant.java1week3
 * 
 * @author Adam Vinsant
 * 
 * date Oct 15, 2013
 * 
 */
package com.agvinsant.java1week3;


import com.agvinsant.lib.BasicLayout;
import com.agvinsant.webClass.WebClass;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {

	Context context;
	Resources res;
	TextView connectedView;
	
	Boolean connected = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// setting the context
		context = this;
		res = getResources();
		
		
		// setting the linear layout
		LinearLayout ll = new LinearLayout(this);
		LinearLayout ml = BasicLayout.layoutWithButton(this, "Search");
		ll.setOrientation(LinearLayout.VERTICAL);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);

		// Creating button from BasicLayout class
		Button mb = (Button) ml.findViewById(1);
		
		connectedView = new TextView(context);
		
		TextView headView = new TextView(context);
		headView.setText("Select a song from the list to see the info");
		
		
		//Detecting network settings
		connected = WebClass.getConnectionStatus(context);
		if(connected){
			Log.i("Network Connection", WebClass.getConnectionType(context));
			
			connectedView.setText("Network Connection: " + WebClass.getConnectionType(context)+"\n");
			
			// calling the getSongInfo function 
			//getSongInfo();
		}
		else{
				connectedView.setText(""+ WebClass.getConnectionType(context)+"\n");
		}

				
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
