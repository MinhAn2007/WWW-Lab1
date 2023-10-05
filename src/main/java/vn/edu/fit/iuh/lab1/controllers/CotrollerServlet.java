package vn.edu.fit.iuh.lab1.controllers;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import vn.edu.fit.iuh.lab1.models.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mariadb.jdbc.Connection;
import vn.edu.fit.iuh.lab1.repositories.AccountRepository;
import vn.edu.fit.iuh.lab1.repositories.GrantAccessRepository;
import vn.edu.fit.iuh.lab1.repositories.RoleRepository;

@WebServlet(name = "CotrollerServlet", value = "/ControlServlet")
public class CotrollerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RoleRepository roleRepository = new RoleRepository();
        AccountRepository accountRepository = new AccountRepository();
        PrintWriter out = response.getWriter();
        List<String> listRoleForGrant = roleRepository.getName();
        List<String> listAccForGrant = accountRepository.getName();
        request.setAttribute("listRoleForGrant", listRoleForGrant);
        request.setAttribute("listAccForGrant", listAccForGrant);
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
        else if(action.equalsIgnoreCase("getGrant")){

            String destination = "add_role_for_acc.jsp";
            RequestDispatcher requestDis = request.getRequestDispatcher(destination);
            requestDis.forward(request, response);
        }


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        AccountRepository accountRepository = new AccountRepository();
        RoleRepository roleRepository = new RoleRepository();
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
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
        else if (action.equalsIgnoreCase("addGrant")) {
//            INSERT INTO mydb.grantaccess
//                    (ACCOUNT_ID, ROLE_ID, is_grant, NOTE, ACCOUNT_ACCOUNT_ID, ROLE_ROLE_ID)
//            VALUES('', '', 0, NULL, NULL, NULL);

            String role = request.getParameter("selectedRole");
            String acc = request.getParameter("selectedAcc");
            String description = request.getParameter("description");
            boolean add = grantAccessRepository.insert(new GrantAccess(role,acc,Grant.ENABLED,description));
            if(add){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Add Grant success for " + acc+ " role " + role+" ');");
                out.println("window.history.back()");
                out.println("window.location.reload()");
                out.println("</script>");
            }
        else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Add Grant UnSuccess for " + acc+ " role " + role+" ');");
                out.println("window.history.back()");
                out.println("window.location.reload()");
                out.println("</script>");
            }
        }


    }}

