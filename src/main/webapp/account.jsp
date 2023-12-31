<%@ page import="vn.edu.fit.iuh.lab1.models.Account" %>
<%@ page import="vn.edu.fit.iuh.lab1.repositories.RoleRepository" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 9/28/2023
  Time: 3:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            background: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        p {
            margin: 5px 0;
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

    </style>
</head>
<body>
<div class="container">
    <h2>InFor Account</h2>
    <form action="ControlServlet" method="get">
        <p>Name: <%= ((Account) request.getAttribute("acc")).getFullName()%></p>
        <p>Id: <%= ((Account) request.getAttribute("acc")).getAccount_id()%></p>
        <p>Email: <%= ((Account) request.getAttribute("acc")).getEmail()%></p>
        <p>Phone: <%= ((Account) request.getAttribute("acc")).getPhone()%></p>
        <% int status = ((Account) request.getAttribute("acc")).getStatus(); %>
       <p> <% if (status == 1) { %>
        Status: Active
        <% } else if (status == -1) { %>
        Status: Delete
        <% }%>
        </p>
        <% RoleRepository roleRepository = new RoleRepository();%>
        <p>Role : <%= roleRepository.getName(((Account) request.getAttribute("acc")).getAccount_id()) %></p>
    </form>
    <button type="button" onclick="location.href='ControlServlet?action=logout'">Logout</button>

</div>


</body>
</html>
