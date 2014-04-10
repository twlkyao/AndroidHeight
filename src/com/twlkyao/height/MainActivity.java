/**
 * ���ߣ���ʿ��
 * ���ڣ�2013.2.23
 * ���ܣ���ݸ�ĸ���Ԥ�⺢�����
 */
package com.twlkyao.height;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	RadioGroup radioGroup;
	RadioButton radio1;
	RadioButton radio2;
	EditText father_height;
	EditText mother_height;
	TextView result;
	Button button;
	TextView help;
	
	final float feFaFactor = 0.46f;	// The factor of girl to father.
	final float feMoFactor = 0.5f;	// The factor of girl to mother.
	final float maFaFactor = 0.54f;	// The factor of boy to father.
	final float maMoFactor = 0.54f;	// The factor of boy to mother.
	float faFactor = maFaFactor;	// The default factor to father.
	float moFactor = maMoFactor;	// The default factor to mother.
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        setListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0,1,1,R.string.about);
    	menu.add(0,2,2,R.string.exit);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId() == 1) {			// About.
    		Toast.makeText(getApplicationContext(), R.string.about_info, Toast.LENGTH_LONG).show();
    	}
    	else if(item.getItemId() == 2) {	// Help.
    		finish();
    	}
    	return super.onOptionsItemSelected(item);
    }
    // Find the views.
    public void findViews() {
    	radioGroup = (RadioGroup) findViewById(R.id.radioGroup);		// The RadioGroup.
    	radio1 = (RadioButton) findViewById(R.id.radio1);				// The RadioButton of boy.
    	radio2 = (RadioButton) findViewById(R.id.radio2);				// The RadioButton of girl.
    	father_height = (EditText) findViewById(R.id.father_height);	// The EditText of father's height.
    	mother_height = (EditText) findViewById(R.id.mother_height);	// The EditText of mother's height.
    	result = (TextView) findViewById(R.id.result);					// The TextView of baby's height.
    	button = (Button) findViewById(R.id.button);					// The Button of calculate.
    	help = (TextView) findViewById(R.id.help);						// The TextView of help.
    }
    
    // Set listener.
    public void setListeners() {
    	button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int faHeight;	// The height of father.
				int moHeight;	// The height of mother.
				float rHeight;	// The height of the baby.
				DecimalFormat df=new DecimalFormat("0.0");	// The format of the height.
				
				String faHeightString = father_height.getText().toString();
				String moHeightString = mother_height.getText().toString();
				
				if(!faHeightString.equals("") && !moHeightString.equals("")) {
					faHeight = Integer.parseInt(faHeightString);	// Parse the string of fatehr height to int.
					moHeight = Integer.parseInt(moHeightString);	// Parse the string of mother height to int.
					
					// Set RadioGroup checked change listener.
					radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
						
						public void onCheckedChanged(RadioGroup group, int checkedId) {
							// TODO Auto-generated method stub
							if(radio1.getId() == checkedId) {		// Boy.
								faFactor = maFaFactor;				// Factor of boy to father.
								moFactor = maMoFactor;				// Factor of boy to mother.
							}
							if(radio2.getId() == checkedId) {		// Girl.
								faFactor = feFaFactor;				// Factor of girl to father.
								moFactor = feMoFactor;				// Factor of girl to mother.
							}
						}
					});
					
					// Calculate the height of the baby.
					rHeight = faHeight * faFactor + moHeight * moFactor;
					result.setText(df.format(rHeight));
				} else {
					Toast.makeText(getApplicationContext(),
							R.string.empty, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
