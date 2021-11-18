package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Operations {
    static DatabaseConnection theApp = new DatabaseConnection();

    public static void createAccount(String accountNumber, String name, String password, Double balance) {
        String qry = "insert into accountDetails values(?, ?, ?, ?)";
        try {
            theApp.thePreparedStatement = theApp.dbCon.prepareStatement(qry);
            theApp.thePreparedStatement.setString(1, accountNumber);
            theApp.thePreparedStatement.setString(2, name);
            theApp.thePreparedStatement.setDouble(3, balance);
            theApp.thePreparedStatement.setString(4, password);
            if (theApp.thePreparedStatement.executeUpdate() > 0) {
                System.out.println("Account Created Successfully.");
                System.out.println("Please note your account number as: " + accountNumber);
            }
        } catch (SQLException e) {
            System.out.println("Error in preparing statement: " + e.getMessage());
        }
    }

    public static void balanceEnquiry(String accountNumber) {
        String qry = "select balance, name from accountDetails where accountNumber=" + accountNumber;
        try {
            ResultSet theResultSet = theApp.theStatement.executeQuery(qry);
            while (theResultSet.next()) {
                System.out.println("Hi " +
                        theResultSet.getString("name") +
                        "!!\n" +
                        "Your account balance is: " + theResultSet.getString("balance"));
            }
        } catch (SQLException e) {
            System.out.println("Issues with balance Enquiry: " + e.getMessage());
        }
    }

    public static void moneyDeposit(String accountNumber, Double amountToBeAdded) {
        LocalDate date = LocalDate.now();
        String type;
        if (amountToBeAdded < 0) type = "debit";
        else type = "credit";
        String qry = "update accountDetails set balance = balance + " + amountToBeAdded + " where accountNumber = " + accountNumber;
        String qry2 = "insert into transactionDetails values('" + accountNumber + "', '" + date + "', " + amountToBeAdded + ", '" + type + "')";
        try {
            if (theApp.theStatement.executeUpdate(qry) > 0)
                System.out.println("Balance Updated!!");
            if (theApp.theStatement.executeUpdate(qry2) > 0)
                System.out.println("Database Updated!!");
        } catch (SQLException e) {
            System.out.println("Issues with money Deposit: " + e.getMessage());
        }
    }

    public static void printStatement(String accountNumber) {
        String qry = "select * from transactionDetails where accountNumber = " + accountNumber + " limit 10";
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
