package vn.edu.fit.iuh.lab1.controllers;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Date;
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
import vn.edu.fit.iuh.lab1.repositories.LogRepository;
import vn.edu.fit.iuh.lab1.repositories.RoleRepository;

import static java.lang.Integer.parseInt;

@WebServlet(name = "CotrollerServlet", value = "/ControlServlet")
public class CotrollerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RoleRepository roleRepository = new RoleRepository();
        AccountRepository accountRepository = new AccountRepository();
        LogRepository logRepository = new LogRepository();
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        PrintWriter out = response.getWriter();
        List<String> listRoleForGrant = roleRepository.getId();
        List<String> listAccForGrant = accountRepository.getName();
        request.setAttribute("listRoleForGrant", listRoleForGrant);
        request.setAttribute("listAccForGrant", listAccForGrant);
        if (action.equalsIgnoreCase("listRoleId")) {
            List<String> listRole = roleRepository.getId();
            request.setAttribute("listRoleId", listRole);
            String destination = "role.jsp";
            RequestDispatcher requestDis = request.getRequestDispatcher(destination);
            requestDis.forward(request, response);
        } else if (action.equalsIgnoreCase("listAccByRole")) {
            String role_id = request.getParameter("role_id");
            List<Account> listAcc = accountRepository.getAccByRole(role_id);
            request.setAttribute("listAccByRole", listAcc);
            String destination = "account_role.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
            requestDispatcher.forward(request, response);
        } else if (action.equalsIgnoreCase("listRoleOfAcc")) {
            String account_id = request.getParameter("account_id");
            List<Role> roleList = roleRepository.getRoleOfAcc(account_id);
            request.setAttribute("listRoleOfAcc", roleList);
            String destination = "role_of_account.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
            requestDispatcher.forward(request, response);
        } else if (action.equalsIgnoreCase("getGrant")) {
            String destination = "add_role_for_acc.jsp";
            RequestDispatcher requestDis = request.getRequestDispatcher(destination);
            requestDis.forward(request, response);
        } else if (action.equalsIgnoreCase("getLogs")) {
            List<Logs> Listlogs = logRepository.getAllLogs();
            request.setAttribute("Listlogs", Listlogs);
            String destination = "log.jsp";
            RequestDispatcher requestDis = request.getRequestDispatcher(destination);
            requestDis.forward(request, response);
        } else if (action.equalsIgnoreCase("logout")) {
            Logs logs = new Logs();
            Date loginTime = (Date) request.getSession().getAttribute("loginTime");
            String id = request.getSession().getAttribute("id").toString();
            request.getSession().invalidate();
            logs.setAccount_id(id);
            logs.setLoginTime(loginTime);
            Date logout = new Date();
            logs.setLogoutTime(logout);
            logs.setNote("Account : " + id + "\r" + "Time login : " + loginTime + "\r" + "Time logout : " + logout + "\r");
            if(logRepository.logLogout(logs))
                response.sendRedirect("index.jsp");
            else {
                out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Something error here !!!!!!')");
                out.println("window.history.back()");
                out.println("</script>");
            }
        }
        else if (action.equalsIgnoreCase("getPermisson")) {
            List<GrantAccess> grantAccesses = grantAccessRepository.getAll();
            request.setAttribute("listGrants", grantAccesses);
            String destination = "permisson.jsp";
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
        LogRepository logRepository = new LogRepository();
        if (action.equalsIgnoreCase("login")) {
            String id = request.getParameter("username");
            request.getSession().setAttribute("id", id);
            String pw = request.getParameter("password");
            Date loginTime = new Date();
            request.getSession().setAttribute("loginTime", loginTime);

            // Lưu log vào cơ sở dữ liệu hoặc tệp log
            if (accountRepository.login(id, pw).isPresent()) {
                Optional<Account> optionalAccount = accountRepository.findbyId(id);
                Account acc = optionalAccount.orElseThrow(() -> new IllegalStateException("Account not found"));

                if (roleRepository.checkRole(id)) {
                    List<Account> listAcc = accountRepository.getAllAcc();
                    request.setAttribute("listAcc", listAcc);
                    List<Role> listRole = roleRepository.getAll();
                    request.setAttribute("listRole", listRole);
                    String destination = "dashboard.jsp";
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("acc", acc);
                    String destination = "account.jsp";
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                    try {
                        requestDispatcher.forward(request, response);
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else {
                out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Account is not exist');");
                out.println("location='index.jsp';");
                out.println("</script>");

            }


        } else if (action.equalsIgnoreCase("addGrant")) {
//            INSERT INTO mydb.grantaccess
//                    (ACCOUNT_ID, ROLE_ID, is_grant, NOTE, ACCOUNT_ACCOUNT_ID, ROLE_ROLE_ID)
//            VALUES('', '', 0, NULL, NULL, NULL);

            String role = request.getParameter("selectedRole");
            String acc = request.getParameter("selectedAcc");
            String description = request.getParameter("description");
            boolean add = grantAccessRepository.insert(new GrantAccess(role, acc, Grant.ENABLED, description));
            List<GrantAccess> grantAccesses = grantAccessRepository.getAll();
            request.setAttribute("listGrants", grantAccesses);
            if (add) {
                request.setAttribute("successMessage", "Add Grant Success for " + acc + " role " + role);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("permisson.jsp");
                requestDispatcher.forward(request, response);
            }else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Add Grant UnSuccess for " + acc + " role " + role + " ');");
                out.println("window.history.back()");
                out.println("</script>");
            }

        } else if (action.equalsIgnoreCase("deleteAcc")) {
            String account_id = request.getParameter("account_id");
            boolean delete = accountRepository.delete(account_id, -1);
            List<Account> listAcc = accountRepository.getAllAcc();
            request.setAttribute("listAcc", listAcc);
            List<Role> listRole = roleRepository.getAll();
            request.setAttribute("listRole", listRole);
            if (delete) {
                String destination = "dashboard.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error while deleting Account!')");
                out.println("window.history.back();");
                out.println("</script>");
            }
        } else if (action.equalsIgnoreCase("updateAcc")) {
            String accountId = request.getParameter("account_id");
            String fullName = request.getParameter("full_name");
            String passWord = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String status = request.getParameter("status");
            boolean update = accountRepository.update(new Account(accountId, fullName, passWord, email, phone, parseInt(status)));
            List<Account> listAcc = accountRepository.getAllAcc();
            request.setAttribute("listAcc", listAcc);
            List<Role> listRole = roleRepository.getAll();
            request.setAttribute("listRole", listRole);
            if (update) {
                String destination = "dashboard.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ERROR!@!");
                out.println("</script>");
            }

        } else if (action.equalsIgnoreCase("addAcc")) {
            String accountId = request.getParameter("account_id");
            String fullName = request.getParameter("full_name");
            String passWord = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String status = request.getParameter("status");
            boolean add = accountRepository.insert(new Account(accountId, fullName, passWord, email, phone, parseInt(status)));
            List<Account> listAcc = accountRepository.getAllAcc();
            request.setAttribute("listAcc", listAcc);
            List<Role> listRole = roleRepository.getAll();
            request.setAttribute("listRole", listRole);
            if (add) {
                String destination = "dashboard.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error while adding Account!')");
                out.println("window.history.back();");
                out.println("</script>");
            }

        } else if (action.equalsIgnoreCase("addRole")) {
            String role_id = request.getParameter("role_id");
            String role_name = request.getParameter("role_name");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            boolean add = roleRepository.insert(new Role(role_id, role_name, description, parseInt(status)));
            List<Account> listAcc = accountRepository.getAllAcc();
            request.setAttribute("listAcc", listAcc);
            List<Role> listRole = roleRepository.getAll();
            request.setAttribute("listRole", listRole);
            if (add) {
                String destination = "dashboard.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error while adding role!')");
                out.println("window.history.back();");
                out.println("</script>");
            }

        } else if (action.equalsIgnoreCase("updateRole")) {
            String role_id = request.getParameter("role_id");
            String role_name = request.getParameter("role_name");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            boolean update = roleRepository.update(new Role(role_id, role_name, description, parseInt(status)));
            List<Account> listAcc = accountRepository.getAllAcc();
            request.setAttribute("listAcc", listAcc);
            List<Role> listRole = roleRepository.getAll();
            request.setAttribute("listRole", listRole);
            if (update) {
                String destination = "dashboard.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error while deleting role!')");
                out.println("window.history.back();");
                out.println("</script>");
            }

        } else if (action.equalsIgnoreCase("deleteRole")) {
            String role_id = request.getParameter("role_id");
            boolean delete = roleRepository.delete(role_id, -1);
            List<Account> listAcc = accountRepository.getAllAcc();
            request.setAttribute("listAcc", listAcc);
            List<Role> listRole = roleRepository.getAll();
            request.setAttribute("listRole", listRole);
            if (delete) {
                String destination = "dashboard.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error while deleting Account!')");
                out.println("window.history.back();");
                out.println("</script>");
            }
        }
        else if (action.equalsIgnoreCase("deleteGrant")) {
            String account_id = request.getParameter("account_id");
            String role_id = request.getParameter("role_id");
            boolean delete = grantAccessRepository.delete(role_id,account_id,Grant.DISABLED);
            List<GrantAccess> grantAccesses = grantAccessRepository.getAll();
            request.setAttribute("listGrants", grantAccesses);
            if (delete) {
                String destination = "permisson.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error while deleting Account!')");
                out.println("window.history.back();");
                out.println("</script>");
            }
        } else if (action.equalsIgnoreCase("updateGrant")) {
            String accountId = request.getParameter("account_id");
            String role_id = request.getParameter("role_id");
            String note = request.getParameter("note");
            String status = request.getParameter("status");
            boolean update = grantAccessRepository.update(new GrantAccess(role_id, accountId,Grant.fromGrant(parseInt(status)),note));
            List<GrantAccess> grantAccesses = grantAccessRepository.getAll();
            request.setAttribute("listGrants", grantAccesses);
            if (update) {
                String destination = "permisson.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ERROR!@!");
                out.println("</script>");
            }

        }else if (action.equalsIgnoreCase("deleteLog")) {
            Long log_id = Long.parseLong(request.getParameter("log_id"));
            boolean delete = logRepository.delete(log_id,"Remove");
            List<Logs> logsList = logRepository.getAllLogs();
            request.setAttribute("Listlogs", logsList);
            if (delete) {
                String destination = "log.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error while deleting Log!')");
                out.println("window.history.back();");
                out.println("</script>");
            }
        } else if (action.equalsIgnoreCase("updateLog")) {
            Long log_id = Long.parseLong(request.getParameter("log_id"));
            String note = request.getParameter("note");
            boolean update = logRepository.delete(log_id,note);
            List<Logs> logsList = logRepository.getAllLogs();
            request.setAttribute("Listlogs", logsList);
            if (update) {
                String destination = "log.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
                requestDispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error while deleting Log!')");
                out.println("window.history.back();");
                out.println("</script>");
            }

        }
    }

}

