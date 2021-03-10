package com.example.bankingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapterSend extends RecyclerView.Adapter<ViewHolder> {

    SendToUser sendUser;
    List<Model> modelList;


    public CustomAdapterSend(SendToUser sendToUser, List<Model> modelList) {
        this.modelList = modelList;
        this.sendUser = sendToUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                sendUser.selectUser(position);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Name.setText(modelList.get(position).getName());
        holder.Phone.setText(modelList.get(position).getPhoneNo());
        holder.Balance.setText(modelList.get(position).getBalance());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

}
