<%@ page import="java.util.List" %>
<%@ page import="vn.edu.fit.iuh.lab1.models.Account" %>
<%@ page import="vn.edu.fit.iuh.lab1.models.Role" %>


<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>
<style>
    body {
        font-family: Arial, Helvetica, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    .container {
        width: 80%;
        height: 100%;
        margin: 20px auto;
        padding: 20px;
        background: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
        color: #333;
        font-size: 24px;
        margin-bottom: 20px;
    }

    h3 {
        color: #333;
        font-size: 20px;
        margin-top: 20px;
    }

    label {
        display: block;
        font-weight: bold;
        margin-top: 10px;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    button {
        background: #007BFF;
        color: #fff;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 3px;
    }

    button:hover {
        background: #0056b3;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }

    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ccc;
    }

    th {
        background: #f0f0f0;
    }

    .error-message {
        color: red;
        font-weight: bold;
    }

    a {
        text-decoration: none;
        color: #007BFF;
    }

    a:hover {
        text-decoration: underline;
    }
</style>

<div class="container">
    <h2>Welcome to Admin Dashboard</h2>
    <button type="button" onclick="location.href='ControlServlet?action=listRoleId'">Role</button>
    <button type="button" onclick="location.href='ControlServlet?action=getPermisson'">Permission</button>
    <button type="button" onclick="location.href='ControlServlet?action=getLogs'">Logs</button>
    <h3>Account List</h3>
    <button type="button" onclick="location.href='add_account.jsp'">Add Account</button>
    <table>
        <tr>
            <th>Username</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Status</th>
            <th>Role</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <% List<Account> listAcc = (List) request.getAttribute("listAcc"); %>
        <% for (int i = 0; i < listAcc.size(); i++) {%>
        <tr>
            <td><%= listAcc.get(i).getAccount_id()%>
            </td>
            <td><%= listAcc.get(i).getFullName()%>
            </td>
            <td><%= listAcc.get(i).getEmail()%>
            </td>
            <td><%= listAcc.get(i).getEmail()%>
            </td>
            <td>
                <% int status = listAcc.get(i).getStatus(); %>
                <% if (status == 1) { %>
                Active
                <% } else if (status == -1) { %>
                Delete
                <% } %>
            </td>

            <td><a href="ControlServlet?action=listRoleOfAcc&account_id=<%= listAcc.get(i).getAccount_id()%>">Show
                Roles</a></td>
            <td>
                <a href="update_acc.jsp?account_id=<%= listAcc.get(i).getAccount_id() %>">Update</a>
            </td>
            <td>
                <form method="post"
                      action="ControlServlet?action=deleteAcc&&account_id=<%= listAcc.get(i).getAccount_id()%>">
                    <button type="submit">Delete</button>
                </form>
            </td>
            <% } %>

        </tr>
    </table>
    <br>
    <% List<Role> listRole = (List) request.getAttribute("listRole"); %>
    <h2>List Role</h2>
    <button type="button" onclick="location.href='add_role.jsp'">Add Role</button>
    <table>
        <tr>
            <th>Role ID</th>
            <th>Role Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <% for (int i = 0; i < listRole.size(); i++) { %>
        <tr>
            <td><%=listRole.get(i).getRole_id()%></td>
            <td><%=listRole.get(i).getRoleName()%></td>
            <td><%=listRole.get(i).getDescription()%></td>
            <td>
                <% int status = listRole.get(i).getStatus(); %>
                <% if (status == 1) { %>
                Enable
                <% } else if (status == -1) { %>
                Disable
                <% } %>
            </td>
            <td><a href="update_role.jsp?role_id=<%= listRole.get(i).getRole_id() %>">Update</a></td>
            <td>  <form method="post"
                        action="ControlServlet?action=deleteRole&&role_id=<%= listRole.get(i).getRole_id()%>">
                <button type="submit">Delete</button>
            </form></td>

        </tr>

        <% } %>
    </table>
    <br><br>
    <button type="button" onclick="location.href='ControlServlet?action=logout'">Logout</button>

</div>


</body>
</html>
