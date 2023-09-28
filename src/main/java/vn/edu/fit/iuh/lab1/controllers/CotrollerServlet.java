package vn.edu.fit.iuh.lab1.controllers;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import vn.edu.fit.iuh.lab1.models.GrantAccess;
import vn.edu.fit.iuh.lab1.models.Role;
import vn.edu.fit.iuh.lab1.models.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mariadb.jdbc.Connection;
import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.repositories.AccountRepository;
import vn.edu.fit.iuh.lab1.repositories.RoleRepository;

@WebServlet(name = "CotrollerServlet", value = "/ControlServlet")
public class CotrollerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        AccountRepository accountRepository = new AccountRepository();
        if (action.equalsIgnoreCase("login")) {

            String id = request.getParameter("username");
            String pw = request.getParameter("password");
            if (accountRepository.login(id, pw).isPresent()) {
                if (true) {
                    List<Account> listAcc = accountRepository.getAllAcc();
                    request.setAttribute("listAcc",listAcc);
                    String destination = "dashboard.jsp";
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                    requestDispatcher.forward(request, response);
                }
             else {
                Optional<Account> optionalAccount = accountRepository.findbyId(id);
                Account acc = optionalAccount.orElseThrow(() -> new IllegalStateException("Account not found"));
                request.setAttribute("acc", acc);
                String destination = "account.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            }
        } else {
            out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Account is not exist');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }
    }
}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        AccountRepository accountRepository = new AccountRepository();
        if(action.equalsIgnoreCase("login")) {
        }
    }

}

