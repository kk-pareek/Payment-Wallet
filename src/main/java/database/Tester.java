package database;


import entity.Account;

import java.time.LocalDate;

public class Tester {
    public static void main(String[] args) {
//        System.out.println(Utils.getListOfAllAccountNumbers());
//        System.out.println(Utils.getRandomAccountNumber());
//        createAccount("234311", "test User", "123456", 10000d);
//        balanceEnquiry("234311");
//        System.out.println(getIdPasswordMap());
//        printStatement("000001");

        Account theAccount = new Account();
        String qry2 = "insert into transactionDetails values('" + theAccount.getAccountNumber() + "', '" + LocalDate.now() + "', " + theAccount.getAmountToBeTransacted() + ", 'debit')";
        System.out.println(qry2);
    }
}
