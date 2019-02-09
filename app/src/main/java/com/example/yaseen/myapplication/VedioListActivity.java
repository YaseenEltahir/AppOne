package com.example.yaseen.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VedioListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio_list);

        // Get ListView object from xml
        final ListView listView = findViewById(R.id.listView);

        // Defined Array values to show in ListView
        String[] values = new String[] { "(ولقد آتيناك سبعاً من المثاني والقرآن العظيم)",
                "(وَمَن قُتِلَ مَظْلُومًا فَقَدْ جَعَلْنَا لِوَلِيِّهِ سُلْطَاناُ)",
                "اعلم يا هذا أن ساعد الله أشد من ساعدك !"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                String VIDEO_ID = "ZTev4airr-U";
                String VIDEO_ID = "0tGpmcGvVTg";

                if(position==0)
                    startActivity(new Intent(VedioListActivity.this,YoutubeActivity.class).putExtra("vedioId",VIDEO_ID));
                else if(position==1)
                    startActivity(new Intent(VedioListActivity.this,YoutubeActivity.class).putExtra("vedioId","hu-wGTNvjro"));
                else
                    startActivity(new Intent(VedioListActivity.this,YoutubeActivity.class).putExtra("vedioId","BxBYKqkxbMI"));

                // ListView Clicked item index
//                int itemPosition     = position;

                // ListView Clicked item value
//                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//                        .show();

            }

        });
    }

}
