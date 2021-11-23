package entity;

public class Account {
    private String accountNumber;
    private String name;
    private String password;
    private Double balance;
    private Double amountToBeTransacted;
    private String beneficiaryAccountNumber;

    public Account(String accountNumber, String name, String password, Double balance, Double amountToBeTransacted, String beneficiaryAccountNumber) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.amountToBeTransacted = amountToBeTransacted;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public Account() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAmountToBeTransacted() {
        return amountToBeTransacted;
    }

    public void setAmountToBeTransacted(Double amountToBeTransacted) {
        this.amountToBeTransacted = amountToBeTransacted;
    }
}
