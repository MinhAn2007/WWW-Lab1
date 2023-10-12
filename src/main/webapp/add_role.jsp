<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/13/2023
  Time: 12:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Role</title>
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
<h1>Add Role</h1>
<form method="post" action="ControlServlet?action=addRole">
    <label>Role Id:</label>
    <input type="text" name="role_id" required>
    <label>Role Name:</label>
    <input type="text" name="role_name" required>
    <br>
    <label>Description:</label>
    <input type="text" name="description" required>
    <br>
    <label>Status:</label>
    <select name="status" required>
        <option value="1">Active</option>
        <option value="0">Delete</option>
    </select>
    <br>
    <input type="submit" value="Add Role">
</form>
</body>
</html>
