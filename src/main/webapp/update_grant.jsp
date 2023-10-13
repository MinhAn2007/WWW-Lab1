<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/13/2023
  Time: 2:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.fit.iuh.lab1.models.Role" %>
<%@ page import="vn.edu.fit.iuh.lab1.repositories.RoleRepository" %>
<%@ page import="java.util.Optional" %>
<%@ page import="vn.edu.fit.iuh.lab1.repositories.GrantAccessRepository" %>
<%@ page import="vn.edu.fit.iuh.lab1.models.GrantAccess" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Role</title>
    <link rel="stylesheet" type="text/css" href="login.css">

    <style>

        body {
            background-color: #f0f0f0;
            font-family: Arial, sans-serif;
        }

        h1 {
            color: #333;
        }

        input,
        label {
            margin-bottom: 10px;
        }

        select option {
            background-color: #f0f0f0;
            color: #333;
        }

    </style>
</head>
<body>
<div class="container">
    <h2>Update Role</h2>
    <form method="post" action="ControlServlet?action=updateGrant" style="text-align: center">
        <%  GrantAccessRepository grantAccessRepository = new GrantAccessRepository();%>
        <%   Optional<GrantAccess> optionalGrant = grantAccessRepository.findGrant(request.getParameter("role_id"),request.getParameter("account_id")); %>
        <%   GrantAccess grantAccess = optionalGrant.orElseThrow(() -> new IllegalStateException("Account not found")); %>

        <% System.out.println(grantAccess); %>
        <label >Role ID:</label>
        <input type="text" name="role_id" value="<%= grantAccess.getRole_id() %>" readonly>
        <br>
        <label >Account ID:</label>
        <input type="text" name="account_id" value="<%= grantAccess.getAccount_id() %>" readonly>
        <br>
        <label >Note:</label>
        <input type="text" name="note" value="<%= grantAccess.getNote() %>">
        <br>
        <label >Status:</label>
        <select name="status">
            <option value="1">Enable</option>
            <option value="0">Disable</option>
        </select>
        <br>
        <br>
        <input type="submit" value="Update">
    </form>
</div>
</body>
</html>

