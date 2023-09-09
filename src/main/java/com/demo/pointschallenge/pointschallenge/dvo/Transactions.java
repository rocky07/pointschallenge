package com.demo.pointschallenge.pointschallenge.dvo;

import java.util.Date;

public class Transactions {
    private String customerName;
    private double transacationAmount;    
    private String transactionDate;

    public Transactions(String name,double amount,String date){
        this.customerName=name;
        this.transacationAmount=amount;
        this.transactionDate=date;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public double getTransacationAmount() {
        return transacationAmount;
    }
    public void setTransacationAmount(double transacationAmount) {
        this.transacationAmount = transacationAmount;
    }
    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
    
}
