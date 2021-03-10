package com.example.bankingapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapterList extends RecyclerView.Adapter<ViewHolder> {

    UserList userList;
    List<Model> modelList;


    public CustomAdapterList(UserList userList, List<Model> modelList) {
        this.modelList = modelList;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist,parent,false);
        ViewHolder holder = new ViewHolder(ItemView);
        holder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("Position", "onItemClick: "+position);
                userList.nextActivity(position);
            }
        });
        return holder;

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
