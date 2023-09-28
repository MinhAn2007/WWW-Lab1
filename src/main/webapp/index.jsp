<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="login.css">

</head>
<body>
<script>
    // Lấy thông báo lỗi từ JSON
    var errorMessage = '<%=(String) request.getAttribute("errorMessage")%>';

    // Kiểm tra xem có thông báo lỗi hay không

    // Hiển thị thông báo lỗi
    var errorMessageElement = document.getElementById("error-message");
    errorMessageElement.innerText = errorMessage;
    errorMessageElement.style.display = "block";

</script>
<div class="container">
    <h2>Login</h2>
    <form action="ControlServlet" method="POST">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>

        <button type="submit" name="action" value="login">Login</button>
    </form>
    <div id="error-message" class="error-message"></div>
</div>

</body>
</html>
