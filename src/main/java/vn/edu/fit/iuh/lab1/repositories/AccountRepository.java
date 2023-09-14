package vn.edu.fit.iuh.lab1.repositories;


import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.repositories.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    public void getAll() throws SQLException, ClassNotFoundException {
        Connection con;
        con = ConnectDB.getInstance().getConnection();
        if(con !=null){
            System.out.println("connect");
    }
        else System.out.println("can't");
    }

}
