package com.flexymind.hw04alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AudioService extends Service {
	
	public void onCreate() {
		super.onCreate();
	}
	
	public int onStartCommand(Intent intent, int flags, int startID) {
		someTask();
		return super.onStartCommand(intent, flags, startID);
	}
	
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	void someTask() {
		Toast.makeText(getApplicationContext(), "AudioService created", Toast.LENGTH_LONG).show();
	}

}
