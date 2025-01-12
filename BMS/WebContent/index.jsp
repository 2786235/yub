<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书馆里系统</title>
<style>
.title {
	text-align: center;
}

.error {
	text-align: center;
	color: red;
	font-weight: bold
}

#loginFrom {
	width: 300px;
	border: 2px solid;
	margin: 60px auto;
	padding: 50px;
}

.button {
	text-align: center;
	margin-top: 10px;
}

.input {
	text-align: center;
	padding: 10px;
}
</style>
</head>
<body>
	<h2 class="title">图书管理系统</h2>
	<!--  EL表达式可以从request，session，application对象中自动寻找对应的属性值-->
	<div class="error">${msg}</div>
	<form action="/BMS/User/userLoginServlet" method="post" id="loginFrom">
		<div class="input">
			<span>学号：</span><input name="userId" type="text" placeholder="请输入学号">
		</div>
		<div class="input">
			<span>密码：</span><input name="password" type="password"
				placeholder="请输入密码">
		</div>
		<div class="button">
			<input type="submit" value="登录">
		</div>
</body>
</html>