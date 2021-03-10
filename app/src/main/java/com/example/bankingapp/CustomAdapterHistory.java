package com.example.bankingapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapterHistory extends RecyclerView.Adapter<ViewHolder> {
    HistoryList historyList;
    List<Model> modelList;
    TextView transactionStatus;

    public CustomAdapterHistory(HistoryList userList, List<Model> modelList) {
        this.modelList = modelList;
        this.historyList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list,parent,false);
        ViewHolder holder = new ViewHolder(ItemView);
        holder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameFrom.setText(modelList.get(position).getNameFrom());
        holder.Balance.setText(modelList.get(position).getBalance());
        holder.nameTo.setText(modelList.get(position).getNameTo());
        holder.date.setText(modelList.get(position).getDate());
        holder.transaction.setText(modelList.get(position).getTransactionStatus());

        if(modelList.get(position).getTransactionStatus().equals("Failed"))
            holder.transaction.setTextColor(Color.parseColor("#f40404"));
        else
            holder.transaction.setTextColor(Color.parseColor("#4bb543"));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
