package com.flexymind.hw04alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class Flexymind_hw_04_alarmActivity extends Activity {
	
	TimePicker timePckr;
	ToggleButton tglBtn;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        timePckr = (TimePicker)findViewById(R.id.timePicker1);
        tglBtn = (ToggleButton)findViewById(R.id.toggleButton1);
        timePckr.setCurrentHour(9);
        timePckr.setCurrentMinute(0);
    }
    
    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();
        
        if (on) {
        	Intent intent = new Intent(this, Alarm.class);
        	startActivity(intent);
        } else {
        	timePckr.setCurrentHour(9);
            timePckr.setCurrentMinute(0);
        }
    }
}