package userInterface;

import java.util.Scanner;

import static userInterface.UiUtils.*;

public class UserInterface {
    public static void startExecution() {
        while (true) {
            System.out.println("\n*****Welcome to the payment wallet*****\n");
            System.out.println("\nPlease opt from the below menu:\n" +
                    "1. Log In.\n" +
                    "2. Sign Up.\n" +
                    "3. Exit.\n");

            int userChoicePreLogIn = new Scanner(System.in).nextInt();
            switch (userChoicePreLogIn) {
                case 1:
                    String accountNumber = handleUiForLogIn();
                    if (accountNumber.length() > 0) {
                        while (true) {
                            theAccount.setAccountNumber(accountNumber);
                            System.out.println("\n*****Welcome to the payment wallet*****\n");
                            System.out.println("\nPlease opt from the below menu:\n" +
                                    "1. Show Balance.\n" +
                                    "2. Deposit Amount.\n" +
                                    "3. Withdraw Amount.\n" +
                                    "4. Fund Transfer.\n" +
                                    "5. Print Statement.\n" +
                                    "6. Exit.\n");

                            int userChoicePostLogin = new Scanner(System.in).nextInt();
                            switch (userChoicePostLogin) {
                                case 1:
                                    handleUiForBalanceEnquiry(theAccount);
                                    break;
                                case 2:
                                    handleUiForMoneyDeposit(theAccount);
                                    break;
                                case 3:
                                    handleUiForMoneyWithdrawal(theAccount);
                                    break;
                                case 4:
                                    handleUiForMoneyTransfer(theAccount);
                                    break;
                                case 5:
                                    handleUiForPrintStatement(theAccount);
                                    break;
                                case 6:
                                    handleUiForExit();
                                    break;
                                default:
                                    System.out.println("Invalid input!!");
                            }
                        }
                    }
                    break;
                case 2:
                    handleUiForCreateAccount();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input!!");
            }
        }
    }
}
