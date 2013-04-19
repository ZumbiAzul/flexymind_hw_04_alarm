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
        
        TimerTask taaask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(doTask);
            }
        };
        
        if (state==true) {
        	timer = new Timer("DigitalClock");
        	Calendar currentTime = Calendar.getInstance();
        	int chh = currentTime.get(Calendar.HOUR_OF_DAY);
            int cmm = currentTime.get(Calendar.MINUTE);
            int css = currentTime.get(Calendar.SECOND);
            int cms = currentTime.get(Calendar.MILLISECOND);
        	
            int hh = timePckr.getCurrentHour();
            int mm = timePckr.getCurrentMinute();
            int current_ms = ((chh*60+cmm)*60+css)*1000+cms;
            int alarm_ms = (hh*60+mm+1)*60*1000;
            int diff_time = alarm_ms-current_ms;
        	
        	timer.schedule(taaask,diff_time);
        }
        
        
        Intent intent3 = getIntent();
        String close_permission = intent3.getStringExtra("close");
        close_prmssn = Boolean.parseBoolean(close_permission);
        if (close_prmssn==true) finish();
    }
    
    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();
        timer = new Timer("DigitalClock");
        Calendar currentTime = Calendar.getInstance();
        
        int hh = timePckr.getCurrentHour();
        int mm = timePckr.getCurrentMinute();
        int chh = currentTime.get(Calendar.HOUR_OF_DAY);
        int cmm = currentTime.get(Calendar.MINUTE);
        int css = currentTime.get(Calendar.SECOND);
        int cms = currentTime.get(Calendar.MILLISECOND);
        
        int current_ms = ((chh*60+cmm)*60+css)*1000+cms;
        int alarm_ms = (hh*60+mm)*60*1000;
        int diff_time = alarm_ms-current_ms;
        
        if (diff_time<0) diff_time=24*60*60*1000-Math.abs(alarm_ms-current_ms);
        
        TimerTask taaask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(doTask);
            }
        };
        
        if (on) {
        	timer.schedule(taaask,diff_time);
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