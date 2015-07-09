<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

	<h1>Login</h1>

	<div id="login-error">${errors}</div>

	<form action="${pageContext.request.contextPath}/handleLogin"
		method="post">
		<p>
			<label for="j_username">Username</label> <input id="j_username"
				name="up" type="text" />
		</p>

		<p>
			<label for="j_password">Password</label> <input id="j_password"
				name="pp" type="password" />
		</p>

		<input type="submit" value="Login" />

	</form>

</body>
</html>
