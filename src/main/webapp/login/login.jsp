<%@page import="org.springframework.security.authentication.encoding.Md5PasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- General meta information -->
<title>Login</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="robots" content="index, follow" />
<meta charset="utf-8" />
<!-- // General meta information -->


<!-- Load Javascript -->

<!-- Load stylesheets -->
<link type="text/css" rel="stylesheet" href="css/style.css"
	media="screen" />
<!-- // Load stylesheets -->

</head>
<body onload="_load()">

	<div id="wrapper">
		<div id="wrappertop"></div>

		<div id="wrappermiddle">

			<h2>Login</h2>
			<form action="${pageContext.request.contextPath}/handleLogin"
				method="post" id="loginForm">
				<div id="username_input">

					<div id="username_inputleft"></div>

					<div id="username_inputmiddle">
						<input type="text" class="url" id="username" placeholder="E-mail Address" name="up">
						<img id="url_user" src="./images/mailicon.png" alt="">
					</div>

					<div id="username_inputright"></div>

				</div>

				<div id="password_input">

					<div id="password_inputleft"></div>

					<div id="password_inputmiddle">
						<input type="password" class="url" id="password" placeholder="Password" name="pp">
						<img id="url_password" src="./images/passicon.png" alt="">
					</div>

					<div id="password_inputright"></div>

				</div>

				<input type="submit" id="submit" value="">
			</form>

			<div id="links_left">

				<div id="login-error">${errors}</div>

			</div>

		</div>

		<div id="wrapperbottom"></div>

		<div id="powered">
			<p></p>
		</div>
	</div>

</body>
</html>