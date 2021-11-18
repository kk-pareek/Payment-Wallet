package userInterface;

import java.util.Scanner;

import static userInterface.UserInterface.*;

public class TakeUserInput {
    public static void handleUserInput() {
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
