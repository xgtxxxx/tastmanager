<%@ page contentType="text/html;charset=utf-8" isELIgnored="false"%>   
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Manage</title>
	<script type="text/javascript">
		var contextPath = '${pageContext.request.contextPath}';
	</script>
    <!-- <x-bootstrap> -->
    <script type="text/javascript" src="include-ext.js?theme=classic"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/logout.js"></script>
	<!--  
	<script type="text/javascript" src="${pageContext.request.contextPath}/admin/newapp/scripts/Adminpanel.js"></script>
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/admin/newapp/pages/scripts/Menutree.js"></script>
</head>
<body>
	<script type="text/javascript">
		Ext.onReady(function(){
			Ext.create('Ext.container.Viewport', {
				layout : 'border',
				items : [{
							xtype : 'menutree'
						}, {
							xtype : 'tabpanel',
							region: 'center',
							items : [{
								xtype : 'panel',
								title : 'home',
								html  : 'hello'
							}]
						}]
			});
	    });
	
	</script>
</body>
</html>


