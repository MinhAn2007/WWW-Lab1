<%@ page import="java.util.List"%>
<%@ page import="vn.edu.fit.iuh.lab1.models.Account" %>


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
    <!-- Nút thêm tài khoản -->
    <button type="button" onclick="location.href='ControlServlet?action=listRole'">Role</button>
    <!-- Nút sửa tài khoản -->
    <button type="button" onclick="location.href='ControlServlet?action=getGrant'">Permission</button>
    <!-- Nút xóa tài khoản -->
    <button type="button" onclick="deleteAccount()">Delete Account</button>

    <!-- Nút cấp quyền -->
    <button type="button" onclick="grantPermission()">Grant Permission</button>

    <!-- Account List (You can loop through a list of accounts) -->
    <h3>Account List</h3>
    <table>
        <tr>
            <th>Username</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Status</th>
            <th>Role</th>

        </tr>
        <% List<Account> listAcc = (List) request.getAttribute("listAcc"); %>
        <% for(int i=0;i<listAcc.size();i++){%>
        <tr>
            <td> <%= listAcc.get(i).getAccount_id()%></td>
            <td><%= listAcc.get(i).getFullName()%></td>
            <td><%= listAcc.get(i).getEmail()%></td>
            <td><%= listAcc.get(i).getEmail()%></td>
            <td><%= listAcc.get(i).getStatus()%></td>
            <td><a href="ControlServlet?action=listRoleOfAcc&account_id=<%= listAcc.get(i).getAccount_id()%>">Show Roles</a></td>
            </tr>
        <%}%>
    </table>
</div>

<script>


    function editAccount() {
        // Redirect or show a modal for editing an account
        // Example: window.location.href = 'edit_account.jsp';
    }

    function deleteAccount() {
        // Redirect or show a modal for deleting an account
        // Example: window.location.href = 'delete_account.jsp';
    }

    function grantPermission() {
        // Redirect or show a modal for granting permission to an account
        // Example: window.location.href = 'grant_permission.jsp';
    }

</script>
</body>
</html>
