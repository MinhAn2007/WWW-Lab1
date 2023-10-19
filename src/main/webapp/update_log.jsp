<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 10/14/2023
  Time: 1:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.fit.iuh.lab1.models.Role" %>
<%@ page import="vn.edu.fit.iuh.lab1.repositories.RoleRepository" %>
<%@ page import="java.util.Optional" %>
<%@ page import="vn.edu.fit.iuh.lab1.repositories.LogRepository" %>
<%@ page import="vn.edu.fit.iuh.lab1.models.Logs" %>
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
    <h2>Update Log</h2>
    <form method="post" action="ControlServlet?action=updateLog" style="text-align: center">
        <%  LogRepository logRepository = new LogRepository();%>
        <%   Optional<Logs> optionalLogs = logRepository.findbyId((Long.parseLong(request.getParameter("log_id")))); %>
        <%   Logs logs = optionalLogs.orElseThrow(() -> new IllegalStateException("Account not found")); %>

        <% System.out.println(logs); %>
        <label >Role ID:</label>
        <input type="text" name="log_id" value="<%= logs.getId() %>" readonly>
        <br>
        <label> Acoount Name:</label>
        <input type="text" name="account_id" value="<%= logs.getAccount_id() %>" readonly>
        <br>
        <label >Log In Time:</label>
        <input type="text" name="login_time" value="<%= logs.getLoginTime() %>" readonly>
        <br>
        <label >Log Out Time:</label>
        <input type="text" name="description" value="<%= logs.getLogoutTime() %>" readonly>
        <label >Note:</label>
        <br>
        <textarea name="note" cols="32" rows="10"><%= logs.getNote() %></textarea>
        <br>
        <br>
        <br>
        <input type="submit" value="Update">
    </form>
</div>
</body>
</html>
