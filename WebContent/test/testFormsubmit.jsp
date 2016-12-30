<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/jsp/jscss.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form
		action="${pageContext.request.contextPath }/FormTestServlet?action=post"
		method="get">
		<div class="form-group">
			<lable for="username" class="col-md-2 control-label"></lable>
			<div class="col-md-4">
				<input type="text" class="form-control" name="username">
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="control-label col-md-2"></label>
			<div class="col-md-4">
				<input type="text" class="form-control" name="email" disabled
					value="这个是disabled">
			</div>

		</div>

		<div clas="form-group">
			<label class="col-md-2" for="password"> </label>
			<div class="col-md-4">
				<input type="text" class="form-control" name="password" readonly
					value="这是密码">
			</div>
		</div>

		<input type="submit" value="提价">
	</form>

</body>
</html>