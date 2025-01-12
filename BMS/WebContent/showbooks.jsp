<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理系统</title>
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
    form {
        margin: 20px 0;
    }
    input[type="text"] {
        padding: 10px;
        width: 300px;
        border: 1px solid #ccc;
        border-radius: 4px;
        margin-right: 10px;
    }
    input[type="submit"], button {
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
    input[type="submit"]:hover, button:hover {
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
        <h1>图书管理系统</h1>
        <!-- 图书查询表单 -->
        <form action="/BMS/Book/selectAllBooks" method="post" id="searchForm" accept-charset="UTF-8">
            	记录查询（模糊查询）：<input type="text" placeholder="输入书名" name="title">
            <input type="submit" value="查询">
        </form>

        <!-- 返回上一界面按钮 -->
        <button type="button" onclick="window.history.back();">返回上一界面</button>

        <!-- 如果找不到图书，显示提示信息 -->
        <c:if test="${empty books}">
            <p>找不到图书！</p>
        </c:if>

        <!-- 如果找到图书，显示图书列表 -->
        <c:if test="${!empty books}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>书名</th>
                    <th>作者</th>
                    <th>出版社</th>
                    <th>ISBN</th>
                    <th>类别</th>
                    <th>库存数量</th>
                    <th>功能</th>
                </tr>
                <!-- 遍历图书列表 -->
                <c:forEach var="b" items="${books}">
                    <tr>
                        <td>${b.bookId}</td>
                        <td>${b.title}</td>
                        <td>${b.author}</td>
                        <td>${b.publisher}</td>
                        <td>${b.ISBN}</td>
                        <td>${b.category}</td>
                        <td>${b.stock}</td>
                        <td><a href="/BMS/Book/borrowBook?bookId=${b.bookId}&title=${b.title}">借阅</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>