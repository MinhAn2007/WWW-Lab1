<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Hello World!</h1>
<br/>

<!-- Nút Hiển thị danh sách -->
<form action="ControlServlet" method="get">
    <input type="hidden" name="action" value="displayList">
    <button type="submit">Hiển thị Danh sách</button>
</form>

<!-- Nút Thêm -->
<form action="ControlServlet" method="get" >
    <label for="account_id">Account ID:</label>
    <input type="text" id="account_id" name="account_id" required><br>

    <label for="full_name">Họ và Tên:</label>
    <input type="text" id="full_name" name="full_name" required><br>

    <label for="password">Mật Khẩu:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="phone">Số Điện Thoại:</label>
    <input type="tel" id="phone" name="phone" required><br>

    <label for="status">Trạng Thái:</label>
    <select id="status" name="status">
        <option value="ACTIVE">ACTIVE</option>
        <option value="DEACTIVE">DEACTIVE</option>
        <option value="DELETE">DELETE</option>
    </select><br>
    <input type="hidden" name="action" value="add">
    <button type="submit">Thêm</button>
</form>

<!-- Nút Xóa -->
<form action="ControlServlet" method="get">
    <label for="account_id">Account ID:</label>
    <input type="text" id="account_id3" name="account_id" required><br>
    <input type="hidden" name="action" value="delete">
    <button type="submit">Xóa</button>
</form>

<!-- Nút Cập nhật -->
<form action="ControlServlet" method="get" >
    <label for="account_id">Account ID:</label>
    <input type="text" id="account_id2" name="account_id" required><br>

    <label for="full_name">Họ và Tên:</label>
    <input type="text" id="full_name2" name="full_name" required><br>

    <label for="password">Mật Khẩu:</label>
    <input type="password" id="password2" name="password" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email2" name="email" required><br>

    <label for="phone">Số Điện Thoại:</label>
    <input type="tel" id="phone2" name="phone" required><br>

    <label for="status">Trạng Thái:</label>
    <select id="status2" name="status">
        <option value="ACTIVE">ACTIVE</option>
        <option value="DEACTIVE">DEACTIVE</option>
        <option value="DELETE">DELETE</option>
    </select><br>
    <input type="hidden" name="action" value="update">
    <button type="submit">Update</button>
</form>

</body>
</html>
