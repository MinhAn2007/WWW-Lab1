package vn.edu.fit.iuh.lab1.models;

import jakarta.persistence.*;

@Entity
public class GrantAccess {
    @Id
    private String role_id;

    @Id
    private String account_id;

    @Column(name = "is_grant")
    private Grant isGrant;

    private String note;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    public GrantAccess(String role_id, String account_id, Grant isGrant, String note, Account account, Role role) {
        this.role_id = role_id;
        this.account_id = account_id;
        this.isGrant = isGrant;
        this.note = note;
        this.account = account;
        this.role = role;
    }

    public GrantAccess(String role_id, String account_id, Grant isGrant, String note) {
        this.role_id = role_id;
        this.account_id = account_id;
        this.isGrant = isGrant;
        this.note = note;
    }

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

    public Grant isGrant() {
        return isGrant;
    }

    public void setGrant(Grant grant) {
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

    public GrantAccess() {
    }
}
