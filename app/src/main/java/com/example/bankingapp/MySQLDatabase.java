package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;
import static java.sql.Types.DECIMAL;
import static java.sql.Types.VARCHAR;

public class MySQLDatabase extends SQLiteOpenHelper {
    private String userTable = "User";
    private String transactionTable = "TransactionTable";

    public MySQLDatabase(@Nullable Context context) {
        super(context, "MyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + userTable + "(phoneNumber INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)";
        String query2 = "create table " + transactionTable + " (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)";
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL("insert into "+userTable+" values(9834798643,'Nanji bhai',13457.00,'nanzi@gmail.com','175463276844','PUNB0246600')");
        db.execSQL("insert into "+userTable+" values(2547836535,'Tiwari',40000.00,'tiwari40@gmail.com','387465874384','PUNB0246600')");
        db.execSQL("insert into "+userTable+" values(9836535435,'Munna bhai',20324.00,'munna1@gmail.com','3687457365784','PUNB0246600')");
        db.execSQL("insert into "+userTable+" values(8972673654,'Devi prashad',20567.01,'devi50@gmail.com','98734865753','SBI0246600')");
        db.execSQL("insert into "+userTable+" values(7842674756,'Anuradha',20458.48,'anuradha1c@gmail.com','7835483556','SBI76546')");
        db.execSQL("insert into "+userTable+" values(9727537546,'Ghanshyam',76746.26,'shyam2@gmail.com','8763587644','AXIS037674')");
        db.execSQL("insert into "+userTable+" values(9186467545,'Babu bhai',6749.00,'babu@gmail.com','9836458743','ICICI03264564')");
        db.execSQL("insert into "+userTable+" values(8945645657,'Totla seth',49057.00,'seth40@gmail.com','89346587434','UNION456654')");
        db.execSQL("insert into "+userTable+" values(9019874685,'Kachra seth',150.00,'kachra150@gmail.com','8346857434','CA9787453')");
        db.execSQL("insert into "+userTable+" values(7864547657,'Raju',300.00,'raju1-2@gmail.com','836487353','RBI034786')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + userTable);
        db.execSQL("DROP TABLE IF EXISTS " + transactionTable);
        onCreate(db);
    }

    public Cursor readByPhone(String phone) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from  " + userTable + " where phoneNumber =" + phone;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + userTable, null);
        return cursor;
    }

    public Cursor readUserSelectedData(String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "
                + userTable + " except select * from "
                + userTable + " where phoneNumber =" + phone, null);
        return cursor;
    }

    public void updateAmount(String phone, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update  " + userTable + " set balance = " + amount + " where phoneNumber = " + phone);
    }


    public Cursor readTransferData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + transactionTable , null);
        return cursor;
    }

    public boolean insertTransferData(String date, String fromName, String toName, String amount, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", fromName);
        contentValues.put("TONAME", toName);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(transactionTable, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}


