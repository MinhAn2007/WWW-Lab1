package vn.edu.fit.iuh.lab1.models;

public class Account {

    private String accountId;
    private String fullName;
    private String passWord;
    private String email;
    private String phone;
    private Status status ;


    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Account(String accountId, String fullName, String passWord, String email, String phone) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.passWord = passWord;
        this.email = email;
        this.phone = phone;
    }

    public Account() {
    }

    public Account(String accountId, String fullName, String passWord, String email, String phone, Status status) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.passWord = passWord;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }
}
