<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
header['Content-type']=${header['Content-type'] }
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="post" enctype="multipart/form-data">

		<input type="text" name="text1"><%=request.getParameter("text1")%>

		<br> <input type="text" name="text2"><%=request.getParameter("text2")%>

		<br> <input type="file" name="file1">
		<%
			File file1 = (File) request.getAttribute("file");
			if (file1 != null) {
				out.print("<br/>文件：" + file1 + ",<br/>大小：" + file1.length());

			}
		%><br /> <input type="file" name="file2">
		<%
			File file2 = (File) request.getAttribute("file2");
			if (file1 != null) {
				out.print("<br/>文件：" + file2 + ",<br/>大小：" + file2.length());

			}
		%><br />
		
		
		<input type="submit" value="上传文件">
	</form>

</body>
</html>