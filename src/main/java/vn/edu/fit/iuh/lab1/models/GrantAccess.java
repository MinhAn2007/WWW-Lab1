package vn.edu.fit.iuh.lab1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class GrantAccess {
    @Id
    private String role_id;
    @Id
    private  String account_id;
    @Column(name = "is_grant")
    private boolean isGrant;
    private String note;

    @ManyToOne
    private Account account;
    @ManyToOne
    private Role role;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public boolean isGrant() {
        return isGrant;
    }

    public void setGrant(boolean grant) {
        isGrant = grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public GrantAccess(String role_id, String account_id, boolean isGrant, String note, Account account, Role role) {
        this.role_id = role_id;
        this.account_id = account_id;
        this.isGrant = isGrant;
        this.note = note;
        this.account = account;
        this.role = role;
    }

    public GrantAccess() {
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "role_id='" + role_id + '\'' +
                ", account_id='" + account_id + '\'' +
                ", isGrant=" + isGrant +
                ", note='" + note + '\'' +
                ", account=" + account +
                ", role=" + role +
                '}';
    }
}
