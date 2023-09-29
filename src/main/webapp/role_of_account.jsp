<%@ page import="vn.edu.fit.iuh.lab1.models.Role" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 9/29/2023
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        /* Add this CSS to your existing CSS file or create a new one */

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            background-color: #fff;
            margin: 20px auto;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e0e0e0;
        }

        button {
            background-color: #007BFF;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            font-weight: bold;
            border-radius: 5px;
            margin-top: 20px;
        }

        button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
<div class="container">
    <h2>Account Roles</h2>

    <% List<Role> listRole = (List) request.getAttribute("listRoleOfAcc"); %>
    <h2>List Role of Account: <%= request.getParameter("account_id") %></h2>
    <% if(listRole.size()>0) {%>

        <table>
            <tr>
                <th>Role ID</th>
                <th>Role Name</th>
                <th>Description</th>
                <th>Status</th>
            </tr>
            <% for (int i = 0; i < listRole.size(); i++) { %>
            <tr>
                <td><%=listRole.get(i).getRole_id()%></td>
                <td><%=listRole.get(i).getRoleName()%></td>
                <td><%=listRole.get(i).getDescription()%></td>
                <td><%=listRole.get(i).getStatus()%></td>

            </tr>
            <% } %>
            <% }else {%>
            <p>Roles Not Assigned </p>
            <% } %>
        </table>

</div>
</body>
</html>
