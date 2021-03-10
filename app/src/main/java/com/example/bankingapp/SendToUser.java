package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SendToUser extends AppCompatActivity {
     List<Model> modelList = new ArrayList<>();
     RecyclerView recyclerView;
     RecyclerView.LayoutManager layoutManager;
     CustomAdapterSend adapterSend;

     String phoneNumber,name,currentAmount,transferAmount,remainingAmount;
     String selectedNumber,selectedName,selectedAmount,selectedTransferAmount;
     String date;

     SendToUser sendToUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_user);

        sendToUser = this;
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle("All Users");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-YYYY, hh:mm a");
        date = simpleDateFormat.format(calendar.getTime());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            phoneNumber = bundle.getString("phoneNumber");
            name = bundle.getString("name");
            currentAmount = bundle.getString("CurrentAmount");
            transferAmount = bundle.getString("transferAmount");
            showData(phoneNumber);
        }
    }

    private void showData(String phoneNumber) {
        modelList.clear();
        Cursor cursor = new MySQLDatabase(this).readUserSelectedData(phoneNumber);
        while(cursor.moveToNext()){
            String balanceDB = cursor.getString(2);
            Double balance = Double.parseDouble(balanceDB);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            String price = nf.format(balance);

            Model model = new Model(cursor.getString(0),cursor.getString(1),price);
            modelList.add(model);

        }
        adapterSend = new CustomAdapterSend(SendToUser.this,modelList);
        recyclerView.setAdapter(adapterSend);
    }
    public void selectUser(int position){
        selectedNumber = modelList.get(position).getPhoneNo();
        Cursor cursor = new MySQLDatabase(this).readByPhone(selectedNumber);

        while(cursor.moveToNext()){
            selectedName = cursor.getString(1);
            selectedAmount = cursor.getString(2);
            Double selectUserBalance = Double.parseDouble(selectedAmount);
            Double selectUserTransferBalance = Double.parseDouble(transferAmount);
            final Double selectUserRemainingAmount = selectUserBalance + selectUserTransferBalance;


                    new MySQLDatabase(sendToUser).insertTransferData(date,name,selectedName,transferAmount,"Successful");
                    new MySQLDatabase(sendToUser).updateAmount(selectedNumber,selectUserRemainingAmount.toString());
                    calculateAmount();
                    Toast.makeText(sendToUser, "Transaction Successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SendToUser.this,UserList.class));
                    finish();
        }
    }


    private void calculateAmount() {
        Double dCurrentAmount = Double.parseDouble(currentAmount);
        Double dTransferAmount = Double.parseDouble(transferAmount);
        Double dRemainingAmount = dCurrentAmount - dTransferAmount;
        remainingAmount = dRemainingAmount.toString();
        new MySQLDatabase(this).updateAmount(phoneNumber,remainingAmount);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(sendToUser);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new MySQLDatabase(sendToUser).insertTransferData(date, name, "Not selected", transferAmount, "Failed");
                        Toast.makeText(sendToUser, "Transaction Cancelled!", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(sendToUser, UserList.class));
                        finish();
                    }
                }).setNegativeButton("No", null);
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}