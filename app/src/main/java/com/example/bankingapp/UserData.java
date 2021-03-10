package com.example.bankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class UserData extends AppCompatActivity {
    String phoneNumber;
    Double newBalance;
    EditText amountEDT;
    TextView name,phonenumber,email,accountNmbr,ifscCode,balance;
    Button transferBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data);

        getSupportActionBar().setTitle("User Details");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.nametxt2);
        phonenumber = findViewById(R.id.numbertxt2);
        email = findViewById(R.id.emailtxt2);
        amountEDT = findViewById(R.id.amountedt);
        accountNmbr = findViewById(R.id.accounttxt2);
        ifscCode = findViewById(R.id.ifsctxt2);
        balance = findViewById(R.id.blnctxt2);
        transferBtn = findViewById(R.id.transferbtn);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            phoneNumber = bundle.getString("phoneNumber");
            showData(phoneNumber);
        }
        transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = amountEDT.getText().toString();
                if (amount.equals("")){
                    Toast.makeText(UserData.this, "Enter Amount First!", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        double amnt = Double.parseDouble(amount);
                        String str = balance.getText().toString();
                        String newAmount = "";
                        for(int i=0;i<str.length();i++){
                            if(str.charAt(i) != ',')
                                newAmount += str.charAt(i);
                        }
                        double bal = Double.parseDouble(newAmount);
                        if (amnt > bal){
                            Toast.makeText(UserData.this, "Not Enough balance ", Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent = new Intent(UserData.this,SendToUser.class);
                            intent.putExtra("phoneNumber",phonenumber.getText().toString());
                            intent.putExtra("name",name.getText().toString());
                            intent.putExtra("CurrentAmount",newBalance.toString());
                            intent.putExtra("transferAmount",amountEDT.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                        Toast.makeText(UserData.this, "Enter valid amount!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
    private void showData(String phonenmbr){
        Cursor cursor = new MySQLDatabase(this).readByPhone(phonenmbr);
        while (cursor.moveToNext()){
            String DBBalance = cursor.getString(2);
            newBalance = Double.parseDouble(DBBalance);
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(newBalance);

            phonenumber.setText(cursor.getString(0));
            name.setText(cursor.getString(1));
            email.setText(cursor.getString(3));
            balance.setText(price);
            accountNmbr.setText(cursor.getString(4));
            ifscCode.setText(cursor.getString(5));

        }
    }


}
