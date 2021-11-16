package userInterface;

import entity.Account;

import java.util.Random;
import java.util.Scanner;

import static database.Operations.*;
import static database.Utils.*;

public class UserInterface {
    public static void showMainMenu() {
        while(true) {
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
        System.out.println("\n******Welcome to balance enquiry portal******\n");
        System.out.println("Please enter your account number:");
        Scanner sc = new Scanner(System.in);
        String accountNumber = sc.nextLine();
        String password;
        if(getListOfAllAccountNumbers().contains(accountNumber)) {
            System.out.println("Please Enter your password:");
            password = sc.nextLine();
            if(isUserValid(accountNumber, password))
                balanceEnquiry(accountNumber);
            else
                System.out.println("Incorrect Password!!");
        }
        else {
            System.out.println("Sorry! this account doesn't exist.");
        }
    }
    
    public static void handleUiForMoneyDeposit() {
        System.out.println("\n******Welcome to Money Deposit Portal******\n");
        System.out.println("Please enter your account number:");
        Scanner sc = new Scanner(System.in);
        String accountNumber = sc.nextLine();
        String password;
        if(getListOfAllAccountNumbers().contains(accountNumber)) {
            System.out.println("Please Enter your password:");
            password = sc.nextLine();
            if(isUserValid(accountNumber, password)) {
                System.out.println("Enter the amount you want to deposit:");
                Double amountToBeAdded = sc.nextDouble();
                moneyDeposit(accountNumber, amountToBeAdded);
            }
            else
                System.out.println("Incorrect Password!!");
        }
        else {
            System.out.println("Sorry! this account doesn't exist.");
        }
    }

    public static void handleUiForMoneyWithdrawal() {
        System.out.println("\n******Welcome to Money Withdrawal Portal******\n");
        System.out.println("Please enter your account number:");
        Scanner sc = new Scanner(System.in);
        String accountNumber = sc.nextLine();
        String password;
        if(getListOfAllAccountNumbers().contains(accountNumber)) {
            System.out.println("Please Enter your password:");
            password = sc.nextLine();
            if(isUserValid(accountNumber, password)) {
                System.out.println("Enter the amount you want to withdraw:");
                Double amountToBeAdded = sc.nextDouble();
                amountToBeAdded *= -1;
                moneyDeposit(accountNumber, amountToBeAdded);
            }
            else
                System.out.println("Incorrect Password!!");
        }
        else {
            System.out.println("Sorry! this account doesn't exist.");
        }
    }

    public static void handleUiForMoneyTransfer() {
        System.out.println("\n******Welcome to Money Transfer Portal******\n");
        System.out.println("Please enter your account number:");
        Scanner sc = new Scanner(System.in);
        String accountNumber = sc.nextLine();
        String password;
        if(getListOfAllAccountNumbers().contains(accountNumber)) {
            System.out.println("Please Enter your password:");
            password = sc.nextLine();
            if(isUserValid(accountNumber, password)) {
                System.out.println("Enter the beneficiary account number:");
                String beneficiaryAccountNumber = sc.nextLine();
                if(getListOfAllAccountNumbers().contains(beneficiaryAccountNumber)) {
                    System.out.println("Enter the amount you want to transfer:");
                    Double amountToBeTransfered = sc.nextDouble();
                    moneyDeposit(beneficiaryAccountNumber, amountToBeTransfered);
                    amountToBeTransfered *= -1;
                    moneyDeposit(accountNumber, amountToBeTransfered);
                }
                else {
                    System.out.println("Sorry! Beneficiary account doesn't exist.");
                }
            }
            else
                System.out.println("Incorrect Password!!");
        }
        else {
            System.out.println("Sorry! this account doesn't exist.");
        }
    }

    public static void handleUiForPrintStatement() {
        System.out.println("******Account STATEMENT******");
        System.out.println("Please enter your account number:");
        Scanner sc = new Scanner(System.in);
        String accountNumber = sc.nextLine();
        String password;
        if(getListOfAllAccountNumbers().contains(accountNumber)) {
            System.out.println("Please Enter your password:");
            password = sc.nextLine();
            if(isUserValid(accountNumber, password)) {
               printStatement(accountNumber);
            }
            else
                System.out.println("Incorrect Password!!");
        }
        else {
            System.out.println("Sorry! this account doesn't exist.");
        }
    }
}
