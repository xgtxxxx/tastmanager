<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Task Manage</title>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var user = '<sec:authentication property="name"/>';
	var isDev = false;
	var isAdmin = false;
	<sec:authorize ifAllGranted = "ROLE_DEVELOPER" >
	isDev = true;
	</sec:authorize >
	<sec:authorize ifAllGranted = "ROLE_ADMIN" >
	isAdmin = true;
	</sec:authorize >
	var team = '${teams}';
</script>
<c:forEach items="${headers.js}" var="js">
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/${js.script}.js"></script>
</c:forEach>
<c:forEach items="${headers.css}" var="css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/${css.link}.css" >
</link>
</c:forEach>