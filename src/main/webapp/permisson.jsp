        <%@ page import="vn.edu.fit.iuh.lab1.models.Logs" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.fit.iuh.lab1.models.GrantAccess" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/5/2023
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
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

<div class="container">


    <div id="successMessage" style="display:none; color: red; font-size: 20px; font-weight: bold;">
        <% String successMessage = (String) request.getAttribute("successMessage"); %>
        <%= successMessage %>
    </div>

    <% if (successMessage != null) { %>
    <script>
        document.getElementById("successMessage").style.display = "block";
    </script>
    <% } %>

    <% List<GrantAccess> listGrants = (List) request.getAttribute("listGrants"); %>
    <button type="button" onclick="location.href='ControlServlet?action=getGrant'">Permission</button>
    <table>
        <tr>
            <th>Role ID</th>
            <th>Account ID</th>
            <th>Note</th>
            <th>Status</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <% for (int i = 0; i < listGrants.size(); i++) { %>
        <tr>
            <td><%=listGrants.get(i).getRole_id()%>
            </td>
            <td><%=listGrants.get(i).getAccount_id()%>
            </td>
            <td><%=listGrants.get(i).getNote()%>
            </td>
            <td><%=listGrants.get(i).isGrant()%>
            </td>
            <td>
                <a href="update_grant.jsp?role_id=<%=listGrants.get(i).getRole_id()%>&account_id=<%=listGrants.get(i).getAccount_id()%>">Update</a>
            </td>
            <td>
                <form method="post"
                      action="ControlServlet?action=deleteGrant&role_id=<%=listGrants.get(i).getRole_id()%>&account_id=<%=listGrants.get(i).getAccount_id()%>">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>

        <% } %>
    </table>
    <button type="button" onclick="window.history.back()">Back</button>
</div>

</body>
</html>