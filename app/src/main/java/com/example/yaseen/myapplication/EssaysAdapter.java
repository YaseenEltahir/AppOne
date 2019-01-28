package com.example.yaseen.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yaseen.myapplication.Objects.Essay;

import java.util.List;

public class EssaysAdapter extends RecyclerView.Adapter<EssaysAdapter.MyViewHolder> {
    private List<Essay> essaysList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView essay_title, date;

        public MyViewHolder(View view) {
            super(view);
            essay_title =  view.findViewById(R.id.essay_title);
            date =  view.findViewById(R.id.date);
        }
    }


    public EssaysAdapter(List<Essay> essaysList, Context context) {
        this.essaysList = essaysList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.essay_list_raw, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Essay essay = essaysList.get(position);
        holder.essay_title.setText(essay.getEssay_title());
        holder.essay_title.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,EssayShowActivity.class).putExtra("file_name",essay.getFile_name()));

            }
        });
        holder.date.setText(essay.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return essaysList.size();
    }
}
