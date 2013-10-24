/*
 *  project Java1week3 (Actually Week 4)
 * 
 * package com.agvinsant.java1week3
 * 
 * @author Adam Vinsant
 * 
 * date Oct 15, 2013 / Oct 22, 2013
 * 
 */
package com.agvinsant.java1week3;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.agvinsant.webClass.WebClass;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;


public class MainActivity extends Activity {

	Context context;
	Resources res;
	String[] genreName;
	TextView connectedView;
	Spinner viewSpinner;
	TextView jsonView;
	String trackName;
	String artistName;
	String albumName;
	String trackSite;
	String trackPreview;
	String headString;
	String tPreview;
	public static URL finalURL;
	int pos;
	Button webButton;

	
	ArrayList<String> artistNameList = new ArrayList<String>();
	ArrayList<String> albumNameList = new ArrayList<String>();
	ArrayList<String> trackSiteList = new ArrayList<String>();
	ArrayList<String> trackNameList = new ArrayList<String>();
	ArrayList<String> trackPreviewList = new ArrayList<String>();
	
	Boolean connected = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// setting the context
		context = this;
		res = getResources();
		
		// setting song array and head string
		genreName = res.getStringArray(R.array.genreArray);
		headString = res.getString(R.string.header);
		
		// setting the content view from layout xml 
		setContentView(R.layout.layout);
		
		//setting additional text views
		TextView headView = (TextView) findViewById(R.id.headView);
		headView.setText(headString);
		
		//spinner adapter
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, genreName);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//creating the spinner
		viewSpinner = (Spinner) findViewById(R.id.spinner1);
		viewSpinner.setAdapter(spinnerAdapter);
		
		//spinner onClick function
		viewSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}

		});
		
		// setting the spinner position variable
		 pos = viewSpinner.getSelectedItemPosition();

		// setting the jsonView from layout
		jsonView = (TextView) findViewById(R.id.jsonView);
		
		// Creating button from resource layout
		Button mb = (Button) findViewById(R.id.button1);
		mb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Get selected song info
				String arName = artistNameList.get(pos).toString();
				String alName = albumNameList.get(pos).toString();  
				String tSite = trackSiteList.get(pos).toString();
				String tName = trackNameList.get(pos).toString();
				
				// setting items into the jsonView and formating for style
				jsonView.setText("Song Name:  " + tName + "\r\n" + "\r\n" + "Artist Name:   " +arName+ "\r\n"+ "\r\n"+"Album Name:   "+alName+ "\r\n" +"\r\n"+ "Song Website:   " +tSite);
				
				webButton.setVisibility(View.VISIBLE);
			}
		});
		
	   tPreview = trackPreviewList.get(pos).toString();
		
		// button to switch to webView in Chrome
		webButton = (Button) findViewById(R.id.button2);
		webButton.setVisibility(View.INVISIBLE);
		webButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// setting the web view for the Listen Now button... 	This will send the user to the preview page for the selected song.
				WebView webView = (WebView) findViewById(R.id.webView);
				webView.loadUrl(tPreview);
			}

		});
		
		// creating the connection view to show the device's connection status
		connectedView = (TextView) findViewById(R.id.connectionView);
		
		//Detecting network settings
		connected = WebClass.getConnectionStatus(context);
		if(connected){
			Log.i("Network Connection", WebClass.getConnectionType(context));
			
			connectedView.setText("Network Connection: " + WebClass.getConnectionType(context)+"\n");
			
			// calling the getSongInfo function 
			getSongInfo();
		}
		else{
				connectedView.setText(""+ WebClass.getConnectionType(context)+"\n");
				
				// alert box shows if there is not an internet connection. Informs user that stored data will be used
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("No Internet Connection Detected. Check your connection and try again.")
				       .setCancelable(false)
				       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                dialog.cancel();
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();
		}
}	
	// FINISH onCreate................
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected boolean selectionAvailable(int selection) {
		if(artistNameList == null || artistNameList.size() < (selection + 1))return false;
		if(albumNameList == null || albumNameList.size() < (selection + 1))return false;
		if(trackSiteList == null || trackSiteList.size() < (selection + 1))return false;

		return true;
	}
	
	//get URL... creating the URL that is sent to request data from the API
		private void getSongInfo(){
			
			Log.i("getSongInfo", "hit function");
			
			String baseURL = "https://itunes.apple.com/search?term=";
			
			try{
				finalURL = new URL(baseURL+"nine+inch+nails+the+fragile&limit=6");
				songRequest sr = new songRequest();
				sr.execute(finalURL);
				
				Log.i("getSongInfo", "hit function");
			} catch (MalformedURLException e){
				Log.e("BAD URL", "MALFORMED URL");
				finalURL = null;
			}
		}
		
		//get data from URL
		private class songRequest extends AsyncTask<URL, Void, String>{
			@Override
			protected String doInBackground(URL... urls){
				String response = "";
				
					response = WebClass.getURLStringResponse(finalURL);
					
					Log.i("songRequest", response);
					return response;
				
				
			}
			
			//get data and add to arrays.
			@Override
			protected void onPostExecute(String result){
				Log.i("URL RESPONSE", result);
				
		try {
						
						Log.i("TRYING JSON", "trying json");
						
						JSONObject mainJSON = new JSONObject(result);
			
						JSONArray jsonResult = mainJSON.getJSONArray("results");
							
						int n = jsonResult.length();
						for (int i = 0; i<n; i++ ){	
							
							JSONObject child = jsonResult.getJSONObject(i);
							// getting info from JSONArray tags						
							artistName= child.getString("artistName");
							albumName = child.getString("collectionName");
							trackSite= child.getString("trackViewUrl");
							trackName = child.getString("trackName");
							trackPreview = child.getString("trackPreview");
							
							//populating the array lists
							artistNameList.add(artistName);
							albumNameList.add(albumName);  
							trackSiteList.add(trackSite);
							trackNameList.add(trackName);
							trackPreviewList.add(trackPreview);
					}
			
					} catch (JSONException e) {
						Log.e("JSONException", "ERROR", e);
						e.printStackTrace();
					}
			}	
		}


}
