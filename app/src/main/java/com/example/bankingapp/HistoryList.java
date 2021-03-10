package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryList extends AppCompatActivity {


    List<Model> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapterHistory adapter;
    TextView historyEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        getSupportActionBar().setTitle("Transaction History");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        historyEmpty = findViewById(R.id.historyTxt);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        showData();
    }

    public void showData(){
        modelList.clear();
        Cursor cursor = new MySQLDatabase(this).readTransferData();
        while(cursor.moveToNext()){
            String DBBalance = cursor.getString(4);
            Double balance = Double.parseDouble(DBBalance);
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(true);
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setMinimumFractionDigits(2);
            String price = numberFormat.format(balance);

            //"DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)";
            Model model = new Model(cursor.getString(2),cursor.getString(3),
                    price,cursor.getString(1),cursor.getString(5));
            modelList.add(model);
        }
        adapter = new CustomAdapterHistory(HistoryList.this,modelList);
        recyclerView.setAdapter(adapter);

        if(modelList.size() == 0)
            historyEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}