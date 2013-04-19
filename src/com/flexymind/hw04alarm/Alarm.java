package com.flexymind.hw04alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Alarm extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		
		boolean close_prmssn = false;
		
		Intent intent6 = getIntent();
        String close_permission = intent6.getStringExtra("close");
        close_prmssn = Boolean.parseBoolean(close_permission);
        if (close_prmssn==true) finish();
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.alarm);
	    
	    Intent intent = new Intent(this, AudioService.class);
		startService(intent);
	  }
	
	public void onSnooze(View view) {
		Intent intent = new Intent(this, AudioService.class);
		stopService(intent);
		Intent intent2 = new Intent(this, Flexymind_hw_04_alarmActivity.class);
		intent2.putExtra("togglestate","true");
		startActivity(intent2);
		//finish();
	}
	
	public void onAlarmOff(View view) {
		Intent intent = new Intent(this, AudioService.class);
		stopService(intent);
		Intent intent2 = new Intent(this, Flexymind_hw_04_alarmActivity.class);
		intent2.putExtra("togglestate","false");
		startActivity(intent2);
		//finish();
	}
	
	@Override
	 public void onBackPressed() { // method for exit confirmation
		finish();
        Intent intent3 = new Intent(Intent.ACTION_MAIN); 
        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
        intent3.addCategory(Intent.CATEGORY_HOME); 
        intent3.putExtra("close","true");
        startActivity(intent3);
        Intent intent4 = new Intent(this, AudioService.class);
		stopService(intent4);
	}

}
