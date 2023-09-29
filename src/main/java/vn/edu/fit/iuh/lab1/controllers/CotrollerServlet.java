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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RoleRepository roleRepository = new RoleRepository();
        AccountRepository accountRepository = new AccountRepository();

        if (action.equalsIgnoreCase("listRole")) {
            List<String> listRole = roleRepository.getName();
            request.setAttribute("listRole", listRole);
            String destination = "role.jsp";
            RequestDispatcher requestDis = request.getRequestDispatcher(destination);
            requestDis.forward(request, response);
        }
        else if(action.equalsIgnoreCase("listAccByRole")){
            String role_id = request.getParameter("role_id");
            List<Account> listAcc = accountRepository.getAccByRole(role_id);
            request.setAttribute("listAccByRole",listAcc);
            String destination = "account_role.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
            requestDispatcher.forward(request, response);
        }
        else if(action.equalsIgnoreCase("listRoleOfAcc")){
            String account_id = request.getParameter("account_id");
            List<Role> roleList = roleRepository.getRoleOfAcc(account_id);
            request.setAttribute("listRoleOfAcc",roleList);
            String destination = "role_of_account.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
            requestDispatcher.forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        AccountRepository accountRepository = new AccountRepository();
        RoleRepository roleRepository = new RoleRepository();
        if (action.equalsIgnoreCase("login")) {

            String id = request.getParameter("username");
            String pw = request.getParameter("password");
            if (accountRepository.login(id, pw).isPresent()) {
                Optional<Account> optionalAccount = accountRepository.findbyId(id);
                Account acc = optionalAccount.orElseThrow(() -> new IllegalStateException("Account not found"));

                if (roleRepository.checkRole(id)) {
                    List<Account> listAcc = accountRepository.getAllAcc();
                    request.setAttribute("listAcc",listAcc);
                    String destination = "dashboard.jsp";
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                    requestDispatcher.forward(request, response);
                }
                else {
                    request.setAttribute("acc", acc);
                    String destination = "account.jsp";
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                    try {
                        requestDispatcher.forward(request, response);
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else {
                out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Account is not exist');");
                out.println("location='index.jsp';");
                out.println("</script>");

            }

    }
}}

