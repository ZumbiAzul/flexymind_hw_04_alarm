package com.flexymind.hw04alarm;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class Flexymind_hw_04_alarmActivity extends Activity {
	
	TimePicker timePckr;
	ToggleButton tglBtn;
	Timer timer;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        boolean close_prmssn = false;
        
        timePckr = (TimePicker)findViewById(R.id.timePicker1);
        tglBtn = (ToggleButton)findViewById(R.id.toggleButton1);
        
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        timePckr.setCurrentHour(hour);
        timePckr.setCurrentMinute(minute);
        
        Intent intent2 = getIntent();
        String tglstate = intent2.getStringExtra("togglestate");
        boolean state = Boolean.parseBoolean(tglstate);
        tglBtn.setChecked(state);
        
        Intent intent3 = getIntent();
        String close_permission = intent3.getStringExtra("close");
        close_prmssn = Boolean.parseBoolean(close_permission);
        if (close_prmssn==true) finish();
    }
    
    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();
        timer = new Timer("DigitalClock");
        
        TimerTask taaask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(doTask);
            }
        };
        
        if (on) {
        	timer.schedule(taaask, 1000);
        } else {
        	timePckr.setCurrentHour(9);
            timePckr.setCurrentMinute(0);
        }       
    }
    
    final Runnable doTask = new Runnable() {
        public void run() {
           onAlarm();
        }
    };
    
    public void onAlarm() {
    	Intent intent = new Intent(this, Alarm.class);
    	startActivity(intent);
    }
    
    @Override
	public void onBackPressed() { // method for exit confirmation
	   finish();
       Intent intent5 = new Intent(Intent.ACTION_MAIN); 
       intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
       intent5.addCategory(Intent.CATEGORY_HOME); 
       intent5.putExtra("close","true");
       startActivity(intent5);
	}
    
}