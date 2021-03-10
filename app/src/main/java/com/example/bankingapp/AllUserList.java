package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AllUserList extends AppCompatActivity {

    List<Model> showAllModel = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapterList adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_list);
    }
}