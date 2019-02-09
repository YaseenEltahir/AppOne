package com.example.yaseen.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;


public class MainService extends Service {
    //private static final String TAG="com.example.application.AnsarAlsunaDonations";
    static Alarm alarm = new Alarm();
    public MainService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //DataClass.setServiceState(this,true);

        alarm.setAlarm(this);
//        Toast.makeText(this,"Alarm is to be triggered Every Day at "+ ""
//               ,Toast.LENGTH_LONG).show();


        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        alarm.cancelAlarm(this);
        //DataClass.setServiceState(this,false);

//        Intent intentone = new Intent(this.getApplicationContext(), ServiceCloseActivity.class);
//        intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        this.startActivity(intentone);

        Toast.makeText(this, "Service Stop", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {
       // alarm.setAlarm(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
      return null;
    }
}
