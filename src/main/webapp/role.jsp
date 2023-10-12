<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Role Selection</title>
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
<div class="container">
    <h2>Select a Role</h2>
    <form action="ControlServlet?action=listRole" method="GET" style="text-align: center">
        <label for="selectedRole"></label>
        <select id="selectedRole">
            <% List<String> listRole = (List) request.getAttribute("listRole"); %>
            <% for (int i = 0; i < listRole.size(); i++) { %>
            <option value="<%= listRole.get(i) %>"><%= listRole.get(i) %></option>
            <% } %>
        </select>
        <br><br>
        <button type="button" onclick="showAccounts()">Show Accounts</button>
    </form>
    <br>
    <button type="button" onclick="window.history.back()">Back</button>

</div>
<script>
    function showAccounts() {
        var selectedRole = document.getElementById("selectedRole").value;
        if (selectedRole) {
            var url = 'ControlServlet?action=listAccByRole&role_id=' + selectedRole;
            window.location.href = url;
        }
    }
</script>
</body>
</html>
