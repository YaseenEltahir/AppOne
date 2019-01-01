package com.example.yaseen.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EssayActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay);
        webView=findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/html_sample.html");

//        TextView essayTextView=findViewById(R.id.essayTextView);
//        essayTextView.setText(readFile("text.txt"));
//        essayTextView.setText(readFile("Sample.rtf"));

    }
    private String readFile(String fileName){

        StringBuilder result= new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                new InputStreamReader(getAssets().open(fileName)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                result.append(mLine);
                result.append("\n");
               //process line
               //...
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                 try {
                     reader.close();
                 } catch (IOException e) {
                     //log the exception
                 }
            }
        }
        return result.toString();
    }
}
