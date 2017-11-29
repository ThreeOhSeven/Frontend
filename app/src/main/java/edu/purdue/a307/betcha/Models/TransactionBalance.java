package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/18/17.
 */

public class TransactionBalance {
    String result;
    int current_balance;


    public TransactionBalance() {};

    public TransactionBalance(String result, int current_balance) {
        this.result = result;
        this.current_balance = current_balance;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(int current_balance) {
        this.current_balance = current_balance;
    }
}
