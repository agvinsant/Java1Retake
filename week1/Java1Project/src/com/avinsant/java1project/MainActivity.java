package com.avinsant.java1project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    //setting the variables for the main layout and params.
	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	EditText et1;
	EditText et2;
	EditText et3;
	TextView results;
	LinearLayout form;
	Boolean special;
	Context context;
	Resources res;
	String no1;
	String no2;
	String no3;
	int finalString1;
	int finalString2;
	int finalString3;
	
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
        et1 = new EditText(context);
        et2 = new EditText(context);
        et3 = new EditText(context);
        // setting hint text
        et1.setHint("# shirts");
        et2.setHint("# jeans");
        et3.setHint("# shoes");
        //this will get a string  
        no1=et1.getText().toString(); 
        no2=et2.getText().toString(); 
        no3=et3.getText().toString(); 
       //this will get a no from the string
        finalString1=Integer.parseInt(no1); 
        finalString2=Integer.parseInt(no2); 
        finalString3=Integer.parseInt(no3); 
        // setting into the view
        form.addView(et1);
        form.addView(et2);
        form.addView(et3);
        
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
				// setting strings from resources
				String item1= res.getString(R.string.shirts);
				String item2= res.getString(R.string.jeans);
				String item3= res.getString(R.string.shoes);
				
				// setting ints from resources
				int price1= res.getInteger(R.integer.shirtPrice);
				int price2= res.getInteger(R.integer.jeanPrice);
				int price3= res.getInteger(R.integer.shoePrice);
				
				// setting the total with number of items from edit text
				
				results.setText(item1 + ": $" + price1 + "\r\n" + 
						item2 + ": $" + price2 + "\r\n" + 
						item3 + ": $" + price3 + "\r\n"
				
						);
			}
        });
        
        
        	
        
       // populating the form layout
        form.addView(et1);
        form.addView(et2);
        form.addView(et3);
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
