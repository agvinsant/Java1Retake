package com.avinsant.java1project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MainActivity extends Activity {
    //setting the variables for the main layout and params.
	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	EditText et;
	TextView results;
	LinearLayout form;
	Boolean special;
	Spinner itemSpinner;
	String[] itemName;
	Context context;
	Resources res;
	String no;
	int no2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        context = this;
        res = getResources();
        
        // Setting the linear layout variable. 
        ll = new LinearLayout(this);
        // setting the orientation to be vertical or portrait. 
        ll.setOrientation(LinearLayout.VERTICAL);
        // setting the layout params and variable for the view
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // setting the params inside the layout
        ll.setLayoutParams(lp);
        
       
        // Creating a text area
        TextView tv = new TextView(context);
        // putting static text in the text area
        tv.setText("Check item prices and specials.");
        // putting the text view into the layout
        ll.addView(tv);
        
        // creating the form layout (nested layout)
        form = new LinearLayout(context);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        form.setLayoutParams(lp);
        form.setOrientation(LinearLayout.HORIZONTAL);
        
        
        // creating the text editor
        et = new EditText(context);
        // setting hint text
        et.setHint("number of items");
        //this will get a string  
        no=et.getText().toString(); 
       //this will get a no from the string
        no2=Integer.parseInt(no);              
        // setting into the view
        form.addView(et);
        
        // setting a button
        Button b = new Button(context);
        // Putting text into the button
        b.setText("OK");
        // adding the button to the view
        form.addView(b);
        
        // creating a click event for the button
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// setting the float variables for the denominations
			}
        });
        
        	// initializing the itemName string
        	itemName = res.getStringArray(R.array.itemArray);
        
        	//spinner adapter
      		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, itemName);
      		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      		
      		//creating the spinner
      		itemSpinner = new Spinner(context);
      		itemSpinner.setAdapter(spinnerAdapter);
      		
      		//spinner onClick function
      		itemSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

      			@Override
      			public void onItemSelected(AdapterView<?> parent, View view,
      					int position, long id) {
      				Toast.makeText(context, "You selected " + itemName[position], Toast.LENGTH_LONG).show();
      			}

      			@Override
      			public void onNothingSelected(AdapterView<?> arg0) {
      				// TODO Auto-generated method stub

      			}

      		});
      		
        
       // populating the form layout
        form.addView(et);
        form.addView(b);
        
        // adding the form field layout to the main layout or view
        ll.addView(form);
        
        // adding a text view for the results of the conversion
        results = new TextView(context);
        ll.addView(results);
        
        // Setting the main content view into the application. 
        setContentView(ll);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
