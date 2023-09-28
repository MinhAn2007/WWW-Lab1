<%@ page import="vn.edu.fit.iuh.lab1.models.Account" %><%--
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
    </style>
</head>
<body>
<div class="container">
    <h2>Insert Account</h2>
    <form action="ControlServlet" method="POST">
        <p>Name: <%= ((Account) request.getAttribute("acc")).getFullName()%></p>
        <p>Id: <%= ((Account) request.getAttribute("acc")).getAccount_id()%></p>
        <p>Email: <%= ((Account) request.getAttribute("acc")).getEmail()%></p>
        <p>Phone: <%= ((Account) request.getAttribute("acc")).getPhone()%></p>
        <p>Status: <%= ((Account) request.getAttribute("acc")).getStatus()%></p>

    </form>

</div>


</body>
</html>
