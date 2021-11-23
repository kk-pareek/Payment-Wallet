package database;

import entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Operation {
    static DatabaseConnection theApp = new DatabaseConnection();

    public static void createAccount(Account theAccount) {
        String qry = "insert into accountDetails values(?, ?, ?, ?)";
        try {
            theApp.thePreparedStatement = theApp.dbCon.prepareStatement(qry);
            theApp.thePreparedStatement.setString(1, theAccount.getAccountNumber());
            theApp.thePreparedStatement.setString(2, theAccount.getName());
            theApp.thePreparedStatement.setDouble(3, theAccount.getBalance());
            theApp.thePreparedStatement.setString(4, theAccount.getPassword());
            if (theApp.thePreparedStatement.executeUpdate() > 0) {
                System.out.println("Account Created Successfully.");
                System.out.println("Please note your account number as: " + theAccount.getAccountNumber());
            }
        } catch (SQLException e) {
            System.out.println("Error in preparing statement: " + e.getMessage());
        }
    }

    public static void balanceEnquiry(Account theAccount) {
        String qry = "select balance, name from accountDetails where accountNumber=" + theAccount.getAccountNumber();
        try {
            ResultSet theResultSet = theApp.theStatement.executeQuery(qry);
            while (theResultSet.next()) {
                System.out.println("Hi " + theResultSet.getString("name") + "!!");
                System.out.println("Your account balance is: " + theResultSet.getString("balance"));
            }
        } catch (SQLException e) {
            System.out.println("Issues with balance Enquiry: " + e.getMessage());
        }
    }

    public static void moneyDeposit(Account theAccount) {
        String queryToUpdateAccountBalance = "update accountDetails set balance = balance + " + theAccount.getAmountToBeTransacted() + " where accountNumber = " + theAccount.getAccountNumber();
        String queryToUpdateTransactionTable = "insert into transactionDetails values('" + theAccount.getAccountNumber() + "', '" + LocalDate.now() + "', " + theAccount.getAmountToBeTransacted() + ", 'Credit')";
        try {
            if (theApp.theStatement.executeUpdate(queryToUpdateAccountBalance) > 0)
                System.out.println("Balance Updated!!");
            if (theApp.theStatement.executeUpdate(queryToUpdateTransactionTable) > 0)
                System.out.println("Transaction Updated!!");
        } catch (SQLException e) {
            System.out.println("Issues with money Deposit: " + e.getMessage());
        }
    }

    public static void moneyDepositToBeneficiaryAccount(Account theAccount) {
        String queryToUpdateAccountBalance = "update accountDetails set balance = balance + " + theAccount.getAmountToBeTransacted() + " where accountNumber = " + theAccount.getBeneficiaryAccountNumber();
        String queryToUpdateTransactionTable = "insert into transactionDetails values('" + theAccount.getBeneficiaryAccountNumber() + "', '" + LocalDate.now() + "', " + theAccount.getAmountToBeTransacted() + ", 'Credit')";
        try {
            if (theApp.theStatement.executeUpdate(queryToUpdateAccountBalance) > 0)
                System.out.println("Balance Updated!!");
            if (theApp.theStatement.executeUpdate(queryToUpdateTransactionTable) > 0)
                System.out.println("Transaction Updated!!");
        } catch (SQLException e) {
            System.out.println("Issues with money Deposit: " + e.getMessage());
        }
    }

    public static void moneyWithdraw(Account theAccount) {
        String queryToUpdateAccountBalance =  "update accountDetails set balance = balance - " + theAccount.getAmountToBeTransacted() + " where accountNumber = " + theAccount.getAccountNumber();
        String queryToUpdateTransactionTable = "insert into transactionDetails values('" + theAccount.getAccountNumber() + "', '" + LocalDate.now() + "', " + -1d*theAccount.getAmountToBeTransacted() + ", 'Debit')";
        try {
            if (theApp.theStatement.executeUpdate(queryToUpdateAccountBalance) > 0)
                System.out.println("Balance Updated!!");
            if (theApp.theStatement.executeUpdate(queryToUpdateTransactionTable) > 0)
                System.out.println("Transaction Updated!!");
        } catch (SQLException e) {
            System.out.println("Issues with money Deposit: " + e.getMessage());
        }
    }

    public static void moneyTransfer(Account theAccount) {
        moneyWithdraw(theAccount);
        moneyDepositToBeneficiaryAccount(theAccount);
    }

    public static void printStatement(Account theAccount) {
        String qry = "select * from transactionDetails where accountNumber = " + theAccount.getAccountNumber() + " limit 1000";
        try {
            ResultSet theResultSet = theApp.theStatement.executeQuery(qry);
            System.out.println("DATE\t\tAMOUNT\tTRANSACTION_TYPE");
            while (theResultSet.next()) {
                System.out.println(theResultSet.getString("date") + "\t" +
                        theResultSet.getString("amount") + "\t\t" +
                        theResultSet.getString("type"));
            }
        } catch (SQLException e) {
            System.out.println("Issues with balance Enquiry: " + e.getMessage());
        }
    }
}
