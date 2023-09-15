package vn.edu.fit.iuh.lab1.repositories;

import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.models.Role;
import vn.edu.fit.iuh.lab1.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<Role> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Role> listRole = new ArrayList<>();
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        String querry = "Select * from Role";
        PreparedStatement statement = null;
        statement = con.prepareStatement(querry);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Status status = Status.fromStatus(rs.getInt("status"));
            Role role = new Role(rs.getString("role_id"), rs.getString("role_name"), rs.getString("description"), status);
            listRole.add(role);
        }
        return listRole;
    }

    public boolean create(Role role) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            String querry = "INSERT INTO mydb.role values  (?, ?, ?, ?)\n" +
                    "\n";
            statement = con.prepareStatement(querry);
            statement.setString(1,role.getRoleId());
            statement.setString(2,role.getRoleName());
            statement.setString(3,role.getDescription());
            statement.setInt(4,role.getStatus().getStage());
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            String querry = "delete from mydb.role where  role_id = ? ";
            statement = con.prepareStatement(querry);
            statement.setString(1,id);
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean update(Role role) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            //update
            String query = "UPDATE mydb.role SET role_name = ?, description = ?,  status = ? WHERE role_id = ?";
            statement = con.prepareStatement(query);
            statement.setString(1,role.getRoleName());
            statement.setString(2,role.getDescription());
            statement.setInt(3,role.getStatus().getStage());
            statement.setString(4,role.getRoleId());
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
