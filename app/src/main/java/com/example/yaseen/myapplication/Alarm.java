package com.example.yaseen.myapplication;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;


public class Alarm extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
//        Intent intentone = new Intent(context.getApplicationContext(), UssdCallActivity.class);
//        intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intentone);

        showNotification(context);
        Log.e("pin","alarm");
        wl.release();

    }
    private void showNotification(Context context){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setSmallIcon(R.drawable.ic_launcher);

            mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Hi, This is Android Notification Detail!");

//        Intent resultIntent = new Intent(this, ResultActivity.class);
            Intent resultIntent = new Intent(context, PDFFromAssets.class).putExtra("file_name", "DaysInEurope.pdf");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(PDFFromAssets.class);

// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = null;

            resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
            mNotificationManager.notify(1, mBuilder.build());

        }

    }
    public void setAlarm(Context context)
    {
        int chosenHour = 21;
        int chosenMinute = 24;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        Calendar current_cal=Calendar.getInstance();
        current_cal.setTimeInMillis(System.currentTimeMillis());


        //Toast.makeText(context,chosenHour+" "+chosenMinute, Toast.LENGTH_SHORT).show(); // For example
        cal.set(Calendar.HOUR_OF_DAY, chosenHour);
        cal.set(Calendar.MINUTE, chosenMinute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if(cal.getTime().before(current_cal.getTime()))
        {
            int cal_day=cal.get(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH,cal_day+1);
        }

        AlarmManager am =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),1000*5,pi);
//        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),86400000,pi);

        //DataClass.setServiceState2(context,1);
        /*if(DataClass.getServiceState(context))
        Toast.makeText(context,"service is running",Toast.LENGTH_LONG).show();
        else
        Toast.makeText(context,"service is off",Toast.LENGTH_LONG).show();
*/


    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);




        //DataClass.setServiceState2(context,0);
        /*if(DataClass.getServiceState(context))
            Toast.makeText(context,"service is running",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context,"service is off",Toast.LENGTH_LONG).show();
*/
    }


}
