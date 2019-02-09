package com.example.yaseen.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Random;

public class EssayDBListActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView listView;
    DBHelper mydb;
    ArrayList<String> array_list;

    ArrayList<String> files_names_array_list=new ArrayList<>();
    ArrayList<String> essays_titles_array_list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay_db_list);
        mydb = new DBHelper(this);

//        showNotification(this);

//        Log.e(getString(R.string.Tag),String.valueOf(mydb.essaysNumberOfRows()));
        if (mydb.isDataBaseEmpty()) {

//            mydb.insertEssay("أيام في أوروبا", "DaysInEurope.pdf", "أيام في أوروبا");
            mydb.insertEssay("30 وسيلة لتأديب الطفل", "30 وسيلة لتأديب الطفل.pdf", "30 وسيلة لتأديب الطفل");
            mydb.insertEssay("افكار دعوية", "افكار دعوية.pdf", "افكار دعوية");
            mydb.insertEssay("التربية الايمانية", "التربية الايمانية.pdf", "التربية الايمانية");
            mydb.insertEssay("التربية القرانية", "التربية القرانية.pdf", "التربية القرانية");
            mydb.insertEssay("الشعور بالانتماء", "الشعور بالانتماء.pdf", "الشعور بالانتماء");
            mydb.insertEssay("المكافئات", "المكافئات.pdf", "المكافئات");
            mydb.insertEssay("فن العقاب", "فن العقاب.pdf", "فن العقاب");
            mydb.insertEssay("كيف تعاقب طفلك", "كيف تعاقب طفلك.pdf", "كيف تعاقب طفلك");
            mydb.insertEssay("لماذا نضرب ابناءنا", "لماذا نضرب ابناءنا.pdf", "لماذا نضرب ابناءنا");
            mydb.insertEssay("وحب الرسول", "وحب الرسول.pdf", "وحب الرسول");
            mydb.insertEssay("وحب الله عز وجل", "وحب الله عز وجل.pdf", "وحب الله عز وجل");
            Log.e("pin","inserted");
        }
        showNotification(this);
        array_list = mydb.getAllEssaysTitles();
//        Log.d(getString(R.string.Tag),array_list.toString());
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);
//arrayAdapter.notifyDataSetChanged();
        listView = findViewById(R.id.listView1);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Cursor rs = mydb.getEssayData(id_To_Search);
                rs.moveToFirst();

                String file_name = rs.getString(rs.getColumnIndex(DBHelper.ESSAYS_COLUMN_FILE_NAME));

                Intent intent = new Intent(getApplicationContext(), PDFFromAssets.class).putExtra("file_name", file_name);
//                Intent intent = new Intent(getApplicationContext(),DisplayContactActivity.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                array_list.clear();
                String [] words=newText.split("\\W+");
                 array_list.addAll(mydb.getLikeEssays(words));
//                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
    private int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
    private void showNotification(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            files_names_array_list = mydb.getAllEssaysFilesNames();
            essays_titles_array_list = mydb.getAllEssaysTitles();
//Log.e("pin",String.valueOf(files_names_array_list.size()));
            int selected_essay_index=getRandomNumber(0,files_names_array_list.size()-1);
            String selected_essay_title=essays_titles_array_list.get(selected_essay_index);
            String selected_file_name=files_names_array_list.get(selected_essay_index);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setSmallIcon(R.drawable.app_icon);

            mBuilder.setContentTitle(selected_essay_title);
            mBuilder.setContentText(selected_file_name);


//        Intent resultIntent = new Intent(this, ResultActivity.class);
            Intent resultIntent = new Intent(context, PDFFromAssets.class).putExtra("file_name", selected_file_name);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(), DisplayContactActivity.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public boolean onKeyDown(int keycode, KeyEvent event) {
//        if (keycode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(true);
//        }
//        return super.onKeyDown(keycode, event);
//    }
}
