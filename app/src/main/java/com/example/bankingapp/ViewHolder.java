package com.example.bankingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView Name,Phone,Balance,Rupees,Slash,
    nameFrom,nameTo,transaction,date;
    View view;
    ImageView phoneImage,arrowImage;

    private ViewHolder.ClickListener mClicklistner;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;

        Name = view.findViewById(R.id.name);
        Phone = view.findViewById(R.id.phone);
        Balance = view.findViewById(R.id.balance);
        Rupees = view.findViewById(R.id.rupee);
        Slash = view.findViewById(R.id.slash);
        nameFrom = view.findViewById(R.id.nameFrom);
        nameTo = view.findViewById(R.id.nameTo);
        transaction = view.findViewById(R.id.transaction);
        date = view.findViewById(R.id.date);

        phoneImage = view.findViewById(R.id.phoneImg);
        arrowImage = view.findViewById(R.id.arrow);

        view.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClicklistner.onItemClick(view,getAdapterPosition());
            }
        });

    }

    public interface ClickListener {
        void onItemClick(View view,int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClicklistner = clickListener;
    }
}
