package vn.edu.fit.iuh.lab1.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries(
        value = @NamedQuery(name = "Account.findAll", query = "select a from Account a where a.status=1")
)
public class Account {


    @Id
    private String account_id;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private int status;

    @OneToMany(mappedBy = "account")
    private List<GrantAccess> listGrant;

    public Account() {

    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Account(String account_id, String fullName, String password, String email, String phone, int status) {
        this.account_id = account_id;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id='" + account_id + '\'' +
                ", full_name='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}


