<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理系统 - 借阅记录</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
    .container {
        max-width: 800px;
        margin: 50px auto;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        text-align: center;
    }
    h1 {
        color: #333;
        margin-bottom: 20px;
    }
    p {
        font-size: 18px;
        color: #666;
    }
    button, input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 10px 2px;
        cursor: pointer;
        border-radius: 4px;
        transition: background-color 0.3s ease;
    }
    button:hover, input[type="submit"]:hover {
        background-color: #45a049;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    th, td {
        padding: 12px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    tr:hover {
        background-color: #f5f5f5;
    }
    a {
        color: #4CAF50;
        text-decoration: none;
    }
    a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>入库记录</h1>
        <c:if test="${empty recordsList}">
            <p>没有入库记录！</p>
        </c:if>
        <button type="button" onclick="window.history.back();">返回上一界面</button>
        <c:if test="${!empty recordsList}">
            <table>
                <tr>
                    <th>入库记录ID</th>
                    <th>订单ID</th>
                    <th>书籍ID</th>
                    <th>书籍名称</th>
                    <th>日期</th>
                    <th>状态</th>
                </tr>
                <c:forEach var="r" items="${recordsList}">
                    <tr>
                        <td>${r.recordId}</td>
                        <td>${r.orderId}</td>
                        <td>${r.bookId}</td>
                        <td>${r.title }</td>
                        <td>${r.outDate}</td>
                        <td>${r.status}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>