package vn.edu.fit.iuh.lab1.repositories;


import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.models.Status;
import vn.edu.fit.iuh.lab1.repositories.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    String querry = "delete * from mydb.account where  account_id like ? ";

//    public Account getById(String id) throws SQLException, ClassNotFoundException {
//        Account account = new Account();
//        Connection con;
//        con = ConnectDB.getInstance().getConnection();
//        String querry = "Select * from Account where  account_id like '?'";
//        PreparedStatement statement = null;
//        statement = con.prepareStatement(querry);
//        statement.setString(1,id);
//        ResultSet rs = statement.executeQuery();
//        statement.executeQuery();
//        while (rs.next()) {
//            Status status = Status.fromStatus(rs.getInt("status"));
//            account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), status);
//            return account;
//            }
//        return null;
//    }
    public List<Account> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Account> listAccount = new ArrayList<>();
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        String querry = "Select * from Account";
        PreparedStatement statement = null;
        statement = con.prepareStatement(querry);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Status status = Status.fromStatus(rs.getInt("status"));
            Account account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), status);
            listAccount.add(account);
        }
        return listAccount;
    }

    public boolean create(Account account) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
        String querry = "INSERT INTO mydb.account values  (?, ?, ?, ?, ?, ?)\n" +
                "\n";
        statement = con.prepareStatement(querry);
        statement.setString(1,account.getAccountId());
        statement.setString(2,account.getFullName());
        statement.setString(3,account.getPassWord());
        statement.setString(4,account.getEmail());
        statement.setString(5,account.getPhone());
        statement.setInt(6,account.getStatus().getStage());
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
            String querry = "delete from mydb.account where  account_id = ? ";
            statement = con.prepareStatement(querry);
            statement.setString(1,id);
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean update(Account account) throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            //update
            String query = "UPDATE mydb.account SET full_name = ?, password = ?, email = ?, phone = ?, status = ? WHERE account_id = ?";
            statement = con.prepareStatement(query);
            statement.setString(1,account.getFullName());
            statement.setString(2,account.getPassWord());
            statement.setString(3,account.getEmail());
            statement.setString(4,account.getPhone());
            statement.setInt(5,account.getStatus().getStage());
            statement.setString(6,account.getAccountId());
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


}