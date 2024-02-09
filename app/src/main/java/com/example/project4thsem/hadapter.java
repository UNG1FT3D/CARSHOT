package com.example.project4thsem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class hadapter extends RecyclerView.Adapter<hadapter.MyViewHolder>{
    Context context;
    ArrayList<historyDetail> historyDetail;
    public hadapter(Context context, ArrayList<historyDetail> historyDetail) {
        this.context = context;
        this.historyDetail = historyDetail;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.history_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        historyDetail hDetail=historyDetail.get(position);
        holder.date.setText(hDetail.date);
        holder.time.setText(hDetail.time);
        holder.type.setText(hDetail.type);
        holder.plan.setText(hDetail.plan);
    }

    @Override
    public int getItemCount() {
        return historyDetail.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date,time,type,plan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.Date);
            time=itemView.findViewById(R.id.Time);
            type=itemView.findViewById(R.id.service_type);
            plan=itemView.findViewById(R.id.plan_type);
        }
    }
}