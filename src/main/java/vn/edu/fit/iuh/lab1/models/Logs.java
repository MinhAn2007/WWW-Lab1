package vn.edu.fit.iuh.lab1.models;

import jakarta.persistence.*;


import java.util.Date;
import java.util.List;
@Entity
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String account_id;
    private Date loginTime;
    private Date logoutTime;
    private String note;
    public Logs() {
    }

    public Logs(long id, String account_id, Date loginTime, Date logoutTime, String note) {
        this.id = id;
        this.account_id = account_id;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.note = note;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
