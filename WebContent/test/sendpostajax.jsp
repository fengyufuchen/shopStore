<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/jscss.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="button" value="点击" id="sendjax">

</body>
<script>
	$(function() {
		$("#sendjax").bind(
				"click",
				function() {

					data = {
						"data1" : "value1",
						"data2" : "value2"
					}
					/* 	$.ajax({
							url:"${pageContext.request.contextPath}/sendajax",
							type:"post",
							data:data,
							dataType:"json",
							contentType:"application/json",
							
							success:function(data){
								console.log(data);
								console.log(data.name);
						
							}
						}) */

					function getXMLHttpRequest() {

						var xhr;
						if (window.ActiveObject) {
							xhr = new ActiveObject("Microsoft.XMLHTTP");
						} else if (window.XMLHttpRequest) {
							xhr = new XMLHttpRequest();
						} else {
							xhr = null;
						}
						return xhr;
					}

					function send() {
						var xhr = getXMLHttpRequest();
						xhr.open("post",
								"${pageContext.request.contextPath}/sendajax");
						xhr.send(data);
						xhr.onreadystatechange = function() {
							if (xhr.readyState == 4 && xhr.status == 200) {
								alert("returned:" + xhr.responseText);
							}
						};

					}
					send();

				})

	})
</script>
</html>