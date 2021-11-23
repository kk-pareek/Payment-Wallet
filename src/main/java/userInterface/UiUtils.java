package userInterface;

import entity.Account;

import java.util.Scanner;

import static database.Operation.*;
import static database.Operation.printStatement;
import static database.OperationUtils.*;
import static userInterface.UserInterface.*;

public class UiUtils {
    static Account theAccount = new Account();
    public static String handleUiForLogIn() {
        System.out.println("\n******Welcome to LogIn portal******\n");
        String accountNumber = takeStringInput("your account number.");
        if (isAccountValid(accountNumber)) {
            String password = takeStringInput("your password:");
            if (isUserValid(accountNumber, password)) {
                theAccount.setAccountNumber(accountNumber);
                return accountNumber;
            }
            else {
                System.out.println("Incorrect Password!!");
                return "";
            }
        }
        System.out.println("Account doesn't exist.");
        return "";
    }

    public static void handleUiForCreateAccount() {
        theAccount.setAccountNumber(getRandomAccountNumber());
        theAccount.setPassword(takeStringInput("password for your account"));
        theAccount.setBalance(0d);
        theAccount.setName(takeStringInput("your name"));
        createAccount(theAccount);
    }

    public static void handleUiForBalanceEnquiry(Account theAccount) {
        balanceEnquiry(theAccount);
    }

    public static void handleUiForMoneyDeposit(Account theAccount) {
            theAccount.setAmountToBeTransacted(takeDoubleInput("amount you want to deposit"));
            moneyDeposit(theAccount);
    }

    public static void handleUiForMoneyWithdrawal(Account theAccount) {
        theAccount.setAmountToBeTransacted(takeDoubleInput("amount you want to withdraw"));
        moneyWithdraw(theAccount);
    }

    public static void handleUiForMoneyTransfer(Account theAccount) {
        String beneficiaryAccountNumber = takeStringInput("Beneficiary Account Number");
        if (isAccountValid(beneficiaryAccountNumber)) {
            theAccount.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
            theAccount.setAmountToBeTransacted(takeDoubleInput("amount you want to transfer"));
            moneyTransfer(theAccount);
        } else System.out.println("Beneficiary Account doesn't exist!!");
    }

    public static void handleUiForPrintStatement(Account theAccount) {
        printStatement(theAccount);
    }

    public static void handleUiForExit() {
        System.out.println("Thank you for banking with us.");
        System.exit(0);
    }

    public static boolean isAccountValid(String accountNumber) {
        return getListOfAllAccountNumbers().contains(accountNumber);
    }

    public static String takeStringInput(String var) {
        System.out.println("Please enter the " + var + ":");
        return new Scanner(System.in).nextLine();
    }

    public static Double takeDoubleInput(String var) {
        System.out.println("Please enter the " + var + ":");
        return new Scanner(System.in).nextDouble();
    }
}
