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

	<input type="button" value="提价" id="testajax">

</body>
<script type="text/javascript">
	$(function() {
		$("#testajax").bind("click", function(ev) {

			$.get("${pageContext.request.contextPath}/AjaxForward", {
				"testData" : "datastr"
			}, function(data, status) {
				console.log(data + status);

			})

		})

	})
</script>
</html>