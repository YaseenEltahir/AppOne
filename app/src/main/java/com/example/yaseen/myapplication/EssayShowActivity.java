package com.example.yaseen.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EssayShowActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay_show);
        pdfView = findViewById(R.id.pdfView);
        //new HttpPostAsyncTask().execute(getString(R.string.ServerAddress)+getString(R.string.api_files));
       new HttpPostAsyncTask().execute(getString(R.string.ServerAddress)+"api/files?file_name=My CV_1547157677.pdf");
        //new HttpPostAsyncTask().execute(getString(R.string.ServerAddress)+"api/files");
    }

    public class HttpPostAsyncTask extends AsyncTask<String, Void, InputStream> {
        InputStream inputStream = null;

        @Override
        protected InputStream doInBackground(String... params) {

            try {
                // This is getting the url from the string we passed in
                URL url = new URL(params[0]);
                // Create the urlConnection
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //httpURLConnection.setReadTimeout(10000);
                //httpURLConnection.setConnectTimeout(15000);
                //httpURLConnection.setDoInput(true);
                //httpURLConnection.setDoOutput(true);
                Log.e("pin",getIntent().getStringExtra("file_name"));
                httpURLConnection.setRequestProperty("file_name",getIntent().getStringExtra("file_name"));

                int statusCode = httpURLConnection.getResponseCode();
                Log.e(getString(R.string.Tag),String.valueOf(statusCode));

                if (statusCode == 200) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                }

            } catch (Exception e) {
                Log.e(getString(R.string.Tag), e.toString());
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {

            pdfView.fromStream(inputStream).load();

        }
    }
}
