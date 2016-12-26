
	<%@page import="com.github.cage.examples.cage_e02_servlet.CaptchaServlet"%>
	<%@page contentType="text/html" pageEncoding="UTF-8"%><%
	boolean showGoodResult;
	boolean showBadResult;
	if ("POST".equals(request.getMethod())) {
		String sessionToken = CaptchaServlet.getToken(session);
		String requestToken = request.getParameter("captcha");
		showGoodResult = sessionToken != null && sessionToken.equals(requestToken);
		showBadResult = !showGoodResult;
	} else {
		showGoodResult = showBadResult = false;
	 System.out.println("	showGoodResult = showBadResult = false;");
	}
	System.out.println(showGoodResult);

	CaptchaServlet.generateToken(session);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="get">
		<lable for="testreadonly">readonly</lable>
		<input name="testreadonly" type="text" value="readonlytxt" readonly="readonly">
		
		<input type="submit" value="submit">
		
	</form>
		<form action="" method="post">
			<input name="captcha" type="text" autocomplete="off" />
			<input type="submit" />
		</form>
		<img alt="captcha image" src="captcha" />
</body>
</html>