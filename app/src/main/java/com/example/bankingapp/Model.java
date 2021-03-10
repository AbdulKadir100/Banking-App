package com.example.bankingapp;

public class Model {
    private String phoneNo, name, balance, nameFrom, nameTo, date, transactionStatus;


    public Model(String phoneNo, String name, String balance) {
        this.phoneNo = phoneNo;
        this.name = name;
        this.balance = balance;
    }
    //"DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)";

    public Model( String nameFrom, String nameTo, String balance,String date, String transactionStatus) {
        this.balance = balance;
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
        this.date = date;
        this.transactionStatus = transactionStatus;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
