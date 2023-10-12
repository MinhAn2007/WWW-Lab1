<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/13/2023
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Account</title>
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
        }

        h1 {
            color: #333;
        }

        form {
            width: 300px;
            margin: 0 auto;
            text-align: left;
            background-color: #f5f5f5;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        input, select {
            width: 90%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
        }

    </style>
</head>
<body>
<h1>Add Account</h1>
<form method="post" action="ControlServlet?action=addAcc">
    <label>Accout ID:</label>
    <input type="text" name="account_id" required>
    <label>Full Name:</label>
    <input type="text" name="full_name" required>
    <br>
    <label>Password:</label>
    <input type="password" name="password" required>
    <br>
    <label>Email:</label>
    <input type="text" name="email" required>
    <br>
    <label>Phone:</label>
    <input type="text" name="phone">
    <br>
    <label>Status:</label>
    <select id="status" name="status">
        <option value="1">Active</option>
        <option value="0">Delete</option>
    </select>
    <br>
    <input type="submit" value="Add Account">
</form>
</body>
</html>
