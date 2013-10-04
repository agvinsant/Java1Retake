/*
 *  project Java1Project
 * 
 * package com.avinsant.java1project
 * 
 * @author Adam Vinsant
 * 
 * date Oct 4, 2013
 * 
 */
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
	LinearLayout.LayoutParams fp;
	EditText et;
	TextView results;
	LinearLayout form;
	Boolean special;
	Context context;
	Resources res;
	TextView outfits;
	String editText;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        context = this;
        res = getResources();
        special = false;
        
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
        tv.setText("Check outfit prices and specials.");
        // putting the text view into the layout
        ll.addView(tv);
        
        // creating the form layout (nested layout)
        form = new LinearLayout(context);
        fp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        form.setLayoutParams(fp);
        form.setOrientation(LinearLayout.HORIZONTAL);
        
        
        // creating the text editor
        et = new EditText(context);
        // setting hint text
        et.setHint("Number of Outfits");
        // setting into the view
        form.addView(et);
        
        outfits = new TextView(context);
       
        
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
				String total= res.getString(R.string.total);
				String discount = res.getString(R.string.discount);

				// setting ints from resources
				int price1= res.getInteger(R.integer.shirtPrice);
				int price2= res.getInteger(R.integer.jeanPrice);
				int price3= res.getInteger(R.integer.shoePrice);
				int totalP= res.getInteger(R.integer.totalPrice);
				int disP= totalP-20;
				
				
				// setting the total with number of items from edit text
			    editText = et.getText().toString();
				// setting the editText to an integer
				int finalPrice = Integer.parseInt(editText);
				// calculations... final non discounted price
				int finalTotal = totalP * finalPrice;
				// setting discounted price
				int finalDisTotal = disP * finalPrice;
					// setting the results text view
					results.setText(item1 + ": $" + price1 + "\r\n" + 
							item2 + ": $" + price2 + "\r\n" + 
							item3 + ": $" + price3 + "\r\n" +
							total + ": $" + totalP + "\r\n" + "\r\n" +
							"You chose " + finalPrice + " outfits" + "\r\n" + "\r\n"
							);
							if (finalPrice>=3){
								special = true;
								if(special){
									
									results.append(discount + ": $" + disP + "\r\n" + "Final Price: $" + finalDisTotal);
									
								}
							}else{
								results.append("Final Price: $" + finalTotal);
							}
				
			}
        });
        
        // Button Created to run while loop, really has no place in the app but added for requirements
        Button cart= new Button(context);
        cart.setText("Run Loop");
        cart.setLayoutParams(fp);
        cart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			int i = 0;
				while(i<5){
					// loop should output 4
					outfits.setText("Simply for show: " + i  + "\r\n" + "This shows the final output of a while loop added in my code all for show.");
					i++;
				}
				
			}
		});
        
        
        
        
        // adding the form field layout to the main layout or view
        ll.addView(form);
        
        // adding a text view for the results of the conversion
        results = new TextView(context);
        ll.addView(results);
        ll.addView(cart);
        ll.addView(outfits);
        
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
