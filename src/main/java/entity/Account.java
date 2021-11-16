package entity;

import database.DatabaseConnection;

import static database.Operations.createAccount;

public class Account {
    private String accountNumber;
    private String name;
    private String password;
    private Double balance;

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }


    public Account(String accountNumber, String name, String password, Double balance) {
        createAccount(accountNumber, name, password, balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
