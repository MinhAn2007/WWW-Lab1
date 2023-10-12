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
    <form method="post" action="ControlServlet?action=updateRole" style="text-align: center">
        <%  RoleRepository roleRepository = new RoleRepository();%>
        <%   Optional<Role> optionalAccount = roleRepository.findbyId(request.getParameter("role_id")); %>
        <%   Role role = optionalAccount.orElseThrow(() -> new IllegalStateException("Account not found")); %>

        <% System.out.println(role); %>
        <label >Role ID:</label>
        <input type="text" name="role_id" value="<%= role.getRole_id() %>" readonly>
        <br>
        <label >Role Name:</label>
        <input type="text" name="role_name" value="<%= role.getRoleName() %>">
        <br>
        <label >Description:</label>
        <input type="text" name="description" value="<%= role.getDescription() %>">
        <br>
        <label >Status:</label>
        <select name="status">
            <option value="1">Enable</option>
            <option value="-1">Disable</option>
        </select>
        <br>
        <br>
        <input type="submit" value="Update">
    </form>
</div>
</body>
</html>

