package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static database.Operation.theApp;

public class OperationUtils {
    public static HashMap<String, String> getIdPasswordMap() {
        HashMap<String, String> idPasswordMap = new HashMap<>();
        DatabaseConnection theApp = new DatabaseConnection();
        String qry = "select * from accountDetails";
        try {
            ResultSet theResultSet = theApp.theStatement.executeQuery(qry);
            while (theResultSet.next()) {
                idPasswordMap.put(theResultSet.getString("accountNumber"), theResultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Issue with getting list of all account numbers:" + e.getMessage());
        }
        return idPasswordMap;
    }

    public static ArrayList<String> getListOfAllAccountNumbers() {
        DatabaseConnection theApp = new DatabaseConnection();
        ArrayList<String> accountNumbers = new ArrayList<>();
        String qry = "select accountNumber from accountDetails";
        try {
            ResultSet theResultSet = theApp.theStatement.executeQuery(qry);
            while (theResultSet.next()) {
                accountNumbers.add(theResultSet.getString("accountNumber"));
            }
        } catch (SQLException e) {
            System.out.println("Issue with getting list of all account numbers:" + e.getMessage());
        }
        return accountNumbers;
    }

    public static String getRandomAccountNumber() {
        Random random = new Random();
        ArrayList<String> accountNumbers = getListOfAllAccountNumbers();
        Integer accountNumber;
        while (true) {
            accountNumber = random.nextInt(900000) + 100000;
            if (!accountNumbers.contains(accountNumber.toString()))
                break;
        }
        return accountNumber.toString();
    }

    public static boolean isUserValid(String accountNumber, String password) {
        for (HashMap.Entry<String, String> entry : getIdPasswordMap().entrySet()) {
            if (entry.getKey().compareTo(accountNumber) == 0 && entry.getValue().compareTo(password) == 0)
                return true;
        }
        return false;
    }

    public static void executeQuery(String query) {
        try {
            if (theApp.theStatement.executeUpdate(query) > 0)
                System.out.println("Done!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
