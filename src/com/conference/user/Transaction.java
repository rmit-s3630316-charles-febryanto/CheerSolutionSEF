package com.conference.user;

import com.conference.company.Product;
import javafx.collections.ObservableList;

import java.sql.Date;

public class Transaction {
    private String transactionId;
    private ObservableList<Product> products;
    private double total;
    private Date date;

    public Transaction(String transactionId, ObservableList<Product> products, double total, Date date) {
        String transactionIdBuilder = "";
        String transactionIdCheck;
        if(transactionId.length() > 10) {
           transactionIdCheck = transactionId.substring(0, 10);
        } else if(transactionId.length() < 10) {
            int lengthAdd = 10-transactionId.length();
            String newTransactionId = transactionId;
            for(int i=0; i<lengthAdd; i++) {
                newTransactionId += 0;
            }
           transactionIdCheck = newTransactionId;
        } else {
            transactionIdCheck = transactionId;
        }

        for(int i=0; i<transactionIdCheck.length(); i++) {
            try {
                Integer.parseInt(transactionIdCheck.charAt(i) + "");
                // if success
                transactionIdBuilder += transactionIdCheck.charAt(i);
            } catch (NumberFormatException e) {
                // catch exception
                transactionIdBuilder += 0;
            }
        }

        this.transactionId = transactionIdBuilder;

        if(total >= 0) {
            this.total = total;
        } else {
            this.total = 0;
        }

        this.products = products;
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }
}
