package com.example.bankingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {

    List<Model> modelList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapterList adapter;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_list);

        getSupportActionBar().setTitle("All Users");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        showData();
    }
    public void showData(){
        modelList.clear();
        Cursor cursor = new MySQLDatabase(this).readAllData();
        while(cursor.moveToNext()){
            String DBBalance = cursor.getString(2);
            Double balance = Double.parseDouble(DBBalance);
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(true);
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setMinimumFractionDigits(2);
            String price = numberFormat.format(balance);

            Model model = new Model(cursor.getString(0),cursor.getString(1),price);
            modelList.add(model);
        }
        adapter = new CustomAdapterList(UserList.this,modelList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showData();
    }

    public void nextActivity(int position){
        phoneNumber = modelList.get(position).getPhoneNo();
        Intent intent = new Intent(UserList.this,UserData.class);
        intent.putExtra("phoneNumber",phoneNumber);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UserList.this,MainActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}