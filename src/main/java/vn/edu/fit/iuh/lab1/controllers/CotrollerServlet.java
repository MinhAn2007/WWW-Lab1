package vn.edu.fit.iuh.lab1.controllers;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

import vn.edu.fit.iuh.lab1.models.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mariadb.jdbc.Connection;
import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.repositories.AccountRepository;
//
//import jakarta.servlet.annotation.WebServlet;
//
//
//
//public class CotrollerServlet {
//}

@WebServlet(name = "CotrollerServlet", value = "/ControlServlet")
public class CotrollerServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        AccountRepository acc = new AccountRepository();
        if (action.equals("displayList")) {
            try {
                List<Account> accounts = acc.getAll();

                // Tạo một chuỗi HTML để hiển thị danh sách tài khoản
                StringBuilder htmlBuilder = new StringBuilder();
                htmlBuilder.append("<html><body><h1>Danh sách tài khoản:</h1>");

                for (Account account : accounts) {
                    htmlBuilder.append("<p>").append(account.getFullName()).append("</p>");
                }

                htmlBuilder.append("</body></html>");

                // Thiết lập Content-Type và gửi chuỗi HTML đến PrintWriter
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(htmlBuilder.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (action.equals("add")) {
            String accountId = request.getParameter("account_id");
            String fullName = request.getParameter("full_name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String selectedStatus = request.getParameter("status");
            Status status;

// Map the String value to the enum
            switch (selectedStatus) {
                case "ACTIVE":
                    status = Status.ACTIVE;
                    break;
                case "DEACTIVE":
                    status = Status.DEACTIVE;
                    break;
                case "DELETE":
                    status = Status.DELETE;
                    break;
                default:
                    // Handle the case where the parameter doesn't match any enum value
                    status = null; // or provide a default value
                    break;
            }
            boolean add = false;
            try {
                add = acc.create(new Account(accountId, fullName, password, email, phone, status));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (add) {
                StringBuilder htmlBuilder = new StringBuilder();
                htmlBuilder.append("<p>").append(accountId + fullName + password + email + phone + status + selectedStatus).append("</p>");
                PrintWriter out = response.getWriter();
                out.println(htmlBuilder.toString());
            } else {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>Add không thành công!</h1>");
                out.println("<p>Dữ liệu không được thêm vào cơ sở dữ liệu.</p>");
                out.println("</body></html>");

            }
        }
        if (action.equals("delete")) {

            String accountId = request.getParameter("account_id3");

            boolean delete;
            try {
                delete = acc.delete(accountId);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (delete) {
                StringBuilder htmlBuilder = new StringBuilder();
                htmlBuilder.append("<p> Đã xóa ").append(accountId).append("</p>");
                PrintWriter out = response.getWriter();
                out.println(htmlBuilder.toString());
            } else {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>Xóa không thành công!</h1>");
                out.println("<p>Không có trong CSDL.</p>");
                out.println("</body></html>");

            }

        }
        if(action.equals("update")){
            String accountId2 = request.getParameter("account_id");
            String fullName2 = request.getParameter("full_name");
            String password2 = request.getParameter("password");
            String email2 = request.getParameter("email");
            String phone2 = request.getParameter("phone");
            String selectedStatus2 = request.getParameter("status");
            Status status2;

// Map the String value to the enum
            switch (selectedStatus2) {
                case "ACTIVE":
                    status2 = Status.ACTIVE;
                    break;
                case "DEACTIVE":
                    status2 = Status.DEACTIVE;
                    break;
                case "DELETE":
                    status2 = Status.DELETE;
                    break;
                default:
                    // Handle the case where the parameter doesn't match any enum value
                    status2 = null; // or provide a default value
                    break;
            }
            boolean update = false;
            try {
                update = acc.update(new Account(accountId2, fullName2, password2, email2, phone2, status2));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (update) {
                StringBuilder htmlBuilder = new StringBuilder();
                htmlBuilder.append("<p>Đã update").append(accountId2 + fullName2 + password2 + email2 + phone2  + selectedStatus2).append("</p>");
                PrintWriter out = response.getWriter();
                out.println(htmlBuilder.toString());
            } else {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>update không thành công!</h1>");
                out.println("<p>Dữ liệu không được thêm vào cơ sở dữ liệu.</p>");
                out.println("</body></html>");

            }
        }
    }
    }

