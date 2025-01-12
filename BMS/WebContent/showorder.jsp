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
        <h1>订单记录</h1>
        <c:if test="${empty order}">
            <p>没有订单记录！</p>
        </c:if>
        <button type="button" onclick="window.location.href='../admin.jsp'">返回主界面</button>
        <button type="button" onclick="window.location.href='http://localhost:2233/BMS/insertorder.jsp';">生成订单</button>
        <form action="/BMS/Book/findAll" method="get">
            <input type="submit" value="入库记录">
        </form>
        <c:if test="${!empty order}">
            <table>
                <tr>
                    <th>订单ID</th>
                    <th>图书ID</th>
                    <th>书名</th>
                    <th>数量</th>
                    <th>日期</th>
                    <th>状态</th>
                    <th>功能</th>
                </tr>
                <c:forEach var="o" items="${order}">
                    <tr>
                        <td>${o.orderId}</td>
                        <td>${o.bookId}</td>
                        <td>${o.title}</td>
                        <td>${o.quantity}</td>
                        <td>${o.orderDate}</td>
                        <td>${o.status}</td>
                        <td>
                            <c:choose>
                                <c:when test="${o.status == 'pending'}">
                                    <a href="/BMS/Book/inRecords?orderId=${o.orderId}&bookId=${o.bookId}&quantity=${o.quantity}">入库</a>
                                </c:when>
                                <c:otherwise>
                                    已入库
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>