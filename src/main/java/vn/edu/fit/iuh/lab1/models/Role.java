package vn.edu.fit.iuh.lab1.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries(
        value = @NamedQuery(name = "Role.findAll", query = "select r from Role r where r.status=1")
)
public class Role {
    @Id
    private String role_id;
    private String roleName;
    private String description;
    private int status;
    @OneToMany(mappedBy = "role")
    private List<GrantAccess> listGrant;

    public Role(String role_id, String roleName, String description, int status, List<GrantAccess> listGrant) {
        this.role_id = role_id;
        this.roleName = roleName;
        this.description = description;
        this.status = status;
        this.listGrant = listGrant;
    }

    public Role() {
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<GrantAccess> getListGrant() {
        return listGrant;
    }

    public void setListGrant(List<GrantAccess> listGrant) {
        this.listGrant = listGrant;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + role_id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", listGrant=" + listGrant +
                '}';
    }
}
