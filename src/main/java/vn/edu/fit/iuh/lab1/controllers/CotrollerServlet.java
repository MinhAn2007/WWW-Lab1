package vn.edu.fit.iuh.lab1.controllers;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mariadb.jdbc.Connection;
import vn.edu.fit.iuh.lab1.repositories.AccountRepository;
//
//import jakarta.servlet.annotation.WebServlet;
//
//
//
//public class CotrollerServlet {
//}

@WebServlet(name = "CotrollerServlet", value = "/servlet")
public class CotrollerServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountRepository acc = new AccountRepository();
        try {
            acc.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }}