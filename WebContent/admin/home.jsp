<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>home</title>
</head>


<frameset rows="103,*,43" frameborder="0" border="0" framespacing="0">

	<frame src="${pageContext.request.contextPath }/admin/top.jsp"
		name="topFrame" scrolling="NO" noresize>
	<frameset cols="159,*" frameborder="0" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath }/admin/left.jsp"
			name="leftFrame" noresize scrolling="yes">
		<frame src="${pageContext.request.contextPath }/admin/welcome.jsp"
			name="mainFrame">
	</frameset>
	<frame src="${pageContext.request.contextPath }/admin/bottom.jsp"
		name="bottomFrame" scrolling="NO" noresize>

</frameset>


</html>