package com.flexymind.hw04alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Alarm extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.alarm);
	    
	    Intent intent = new Intent(this, AudioService.class);
		startService(intent);
	  }
	
	public void onSnooze(View view) {
		Intent intent = new Intent(this, AudioService.class);
		stopService(intent);
		finish();
	}
	
	public void onAlarmOff(View view) {
		Intent intent = new Intent(this, AudioService.class);
		stopService(intent);
		finish();
	}

}
