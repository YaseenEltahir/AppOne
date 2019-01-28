package com.example.yaseen.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDFFromAssets extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdffrom_assets);
        pdfView = findViewById(R.id.pdfView2);
        String file_name=getIntent().getExtras().getString("file_name");
        pdfView.fromAsset(file_name).load();
    }
}
