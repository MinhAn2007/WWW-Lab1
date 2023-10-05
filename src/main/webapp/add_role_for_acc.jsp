<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 9/29/2023
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Grant Permissions</title>
    <style>
        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        select, input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        button[type="submit"]:focus {
            outline: none;
        }

        p {
            color: #900;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Grant Permissions</h2>
    <form action="ControlServlet?action=addGrant" method="POST">
        <label>Select an Account:</label>
        <select id="selectedAcc" name="selectedAcc">
            <% List<String> listAcc = (List) request.getAttribute("listAccForGrant"); %>
            <% for (int i = 0; i < listAcc.size(); i++) { %>
            <option value="<%= listAcc.get(i) %>"><%= listAcc.get(i) %></option>
            <% } %>
        </select>

        <label>Select a Permission:</label>
        <select id="selectedRole" name="selectedRole">
            <% List<String> listRole = (List) request.getAttribute("listRoleForGrant"); %>
            <% for (int i = 0; i < listRole.size(); i++) { %>
            <option value="<%= listRole.get(i) %>"><%= listRole.get(i) %></option>
            <% } %>
        </select>
        <p>Description</p>
        <label for="description"></label><input type="text" name="description" id="description">
        <br><br>
        <button type="submit">Grant Permission</button>
    </form>
</div>
</body>
</html>
