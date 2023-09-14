package vn.edu.fit.iuh.lab1.models;

public class GrantAccess {
    private Account account;
    private  Role role;
    private  Grant grant;
    private String note;

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

    public Grant getGrant() {
        return grant;
    }

    public void setGrant(Grant grant) {
        this.grant = grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public GrantAccess(Account account, Role role, Grant grant, String note) {
        this.account = account;
        this.role = role;
        this.grant = grant;
        this.note = note;
    }

    public GrantAccess() {
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "account=" + account +
                ", role=" + role +
                ", grant=" + grant +
                ", note='" + note + '\'' +
                '}';
    }
}
