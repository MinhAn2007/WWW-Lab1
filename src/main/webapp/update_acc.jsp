<%@ page import="vn.edu.fit.iuh.lab1.models.Account" %>
<%@ page import="vn.edu.fit.iuh.lab1.repositories.AccountRepository" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/12/2023
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Account</title>
    <link rel="stylesheet" type="text/css" href="login.css">

    <STYLE>

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

    </STYLE>
</head>
<body>
<h1>Update Account</h1>
<form method="post" action="ControlServlet?action=updateAcc">
    <%  AccountRepository accountRepository = new AccountRepository();%>
    <%   Optional<Account> optionalAccount = accountRepository.findbyId(request.getParameter("account_id")); %>
    <%   Account acc = optionalAccount.orElseThrow(() -> new IllegalStateException("Account not found")); %>

    <% System.out.println(acc); %>
    <label>ID:</label>
    <br>
    <input type="text" name="account_id" value="<%= acc.getAccount_id() %>" readonly >
    <br>
    <label>Full Name:</label>
    <br>
    <input type="text" name="full_name" value="<%= acc.getFullName()  %>">
    <br>
    <label>Password:</label>
    <input type="password" name="password" value="<%= acc.getPassword() %>">
    <br>
    <label>Email:</label>
    <input type="text" name="email" value="<%= acc.getEmail()%>">
    <br>
    <label>Phone:</label>
    <input type="number" name="phone" value="<%= acc.getPhone() %>">
    <br>
    <label>Status:</label>
    <select id="status" name="status">
        <option value="1">Active</option>
        <option value="0">Delete</option>
    </select>
    <br>
    <input type="submit" value="Update">
</form>
</body>
</html>

