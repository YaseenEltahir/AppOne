package com.example.yaseen.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yaseen.myapplication.Objects.Essay;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EssaysActivity extends AppCompatActivity {

    private List<Essay> essaysList;
    private RecyclerView recyclerView;
    private EssaysAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essays);

        essaysList = new ArrayList<>();
        //final TextView mTextView = (TextView) findViewById(R.id.text);
// ...
        recyclerView=findViewById(R.id.recycler_view);
        mAdapter = new EssaysAdapter(essaysList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.ServerAddress)+"api/essays";

        final Gson gson = new Gson();

        final Type category = new TypeToken<List<Essay>>() {
        }.getType();


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.e(getString(R.string.Tag), response);
                        essaysList.addAll((Collection<? extends Essay>) gson.fromJson(response, category));
                        List<Essay> str = gson.fromJson(response, category);
                        mAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getString(R.string.Tag), error.toString());
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                1000*60,
               5,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
}
