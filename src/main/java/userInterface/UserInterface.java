package userInterface;

import entity.Account;

import java.util.Random;
import java.util.Scanner;

import static database.Operations.*;
import static database.Utils.*;

public class UserInterface {
    public static void showMainMenu() {
        while (true) {
            System.out.println("\n*****Welcome to the payment wallet*****\n");
            System.out.println("\nPlease opt from the below menu:\n" +
                    "1. Create Account.\n" +
                    "2. Show Balance.\n" +
                    "3. Deposit Amount.\n" +
                    "4. Withdraw Amount.\n" +
                    "5. Fund Transfer.\n" +
                    "6. Print Statement.\n" +
                    "7. Exit.\n");

            Scanner sc = new Scanner(System.in);
            int userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    handleUiForCreateAccount();
                    break;

                case 2:
                    handleUiForBalanceEnquiry();
                    break;

                case 3:
                    handleUiForMoneyDeposit();
                    break;

                case 4:
                    handleUiForMoneyWithdrawal();
                    break;

                case 5:
                    handleUiForMoneyTransfer();
                    break;

                case 6:
                    handleUiForPrintStatement();
                    break;

                case 7:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid request!!");
            }
        }
    }

    public static void handleUiForCreateAccount() {
        System.out.println("\n*****Welcome to account creation portal*****\n");
        System.out.println("Please Enter your name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("Please Enter password for your account:");
        String password = sc.nextLine();
        String accountNumber = getRandomAccountNumber();
        new Account(accountNumber, name, password, 0d);
    }

    public static void handleUiForBalanceEnquiry() {
        String accountNumber = handleUiForLogin("BALANCE ENQUIRY");
        if (accountNumber.length() > 0)
            balanceEnquiry(accountNumber);
    }

    public static void handleUiForMoneyDeposit() {
        String accountNumber = handleUiForLogin("MONEY DEPOSIT");
        if (accountNumber.length() > 0) {
            System.out.println("Enter the amount you want to deposit:");
            Double amountToBeAdded = new Scanner(System.in).nextDouble();
            moneyDeposit(accountNumber, amountToBeAdded);
        }
    }

    public static void handleUiForMoneyWithdrawal() {
        String accountNumber = handleUiForLogin("MONEY WITHDRAWAL");
        if (accountNumber.length() > 0) {
            System.out.println("Enter the amount you want to withdraw:");
            Double amountToBeAdded = -1d * new Scanner(System.in).nextDouble();
            moneyDeposit(accountNumber, amountToBeAdded);
        }
    }

    public static void handleUiForMoneyTransfer() {
        String accountNumber = handleUiForLogin("MONEY TRANSFER");
        String beneficiaryAccount = takeStringInput("Beneficiary Account Number");
        if (accountNumber.length() > 0 && isAccountValid(beneficiaryAccount)) {
            Double amountToBeTransferred = takeDoubleInput("Amount You Want To Transfer");
            moneyDeposit(beneficiaryAccount, amountToBeTransferred);
            moneyDeposit(accountNumber, -1d * amountToBeTransferred);
        }
    }

    public static void handleUiForPrintStatement() {
        String accountNumber = handleUiForLogin("ACCOUNT STATEMENT");
        if (accountNumber.length() > 0)
            printStatement(accountNumber);
    }

    public static boolean isAccountValid(String accountNumber) {
        if (getListOfAllAccountNumbers().contains(accountNumber))
            return true;
        return false;
    }

    public static String takeStringInput(String var) {
        System.out.println("Please enter the " + var + ":");
        return new Scanner(System.in).nextLine();
    }

    public static Double takeDoubleInput(String var) {
        System.out.println("Please enter the " + var + ":");
        return new Scanner(System.in).nextDouble();
    }

    public static String handleUiForLogin(String portal) {
        System.out.println("\n******Welcome to " + portal + " portal******\n");
        System.out.println("Please enter your account number:");
        String accountNumber = new Scanner(System.in).nextLine();
        String password;

        if (isAccountValid(accountNumber)) {
            System.out.println("Please Enter your password:");
            password = new Scanner(System.in).nextLine();
            if (isUserValid(accountNumber, password))
                return accountNumber;
            else {
                System.out.println("Incorrect Password!!");
                return "";
            }
        }
        System.out.println("Account doesn't exist.");
        return "";
    }
}
