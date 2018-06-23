package com.example.yaseen.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EssayListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay_list);

        // Get ListView object from xml
        final ListView listView = findViewById(R.id.listView);

        // Defined Array values to show in ListView
        String[] values = new String[] { "المقالة الأولى",
                "المقالة الثانية",
                "المقالة الثالثة"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String VIDEO_ID = "ZTev4airr-U";

                if(position==0)
                    startActivity(new Intent(EssayListActivity.this,EssayActivity.class).putExtra("vedioId",VIDEO_ID));

            }

        });
    }

}
