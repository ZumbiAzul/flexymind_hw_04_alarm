package com.flexymind.hw04alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class AudioService extends Service {
	
	MediaPlayer mMediaPlayer = null;
	
	public void onCreate() {
		super.onCreate();
	}
	
	public int onStartCommand(Intent intent, int flags, int startID) {
		playMusic();
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
	
	void playMusic() {
		Toast.makeText(getApplicationContext(), "AudioService created", Toast.LENGTH_LONG).show();
		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
		mediaPlayer.start(); // no need to call prepare(); create() does that for you       
	}
	
}
