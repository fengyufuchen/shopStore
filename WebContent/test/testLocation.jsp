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
	

		<input type="submit" value="提价" id="testlocation">
	

</body>
<script type="text/javascript">

 $(function(){
	
	 $("#testlocation").bind("click",function(ev){
		 console.log("跳转");
		 window.location="${pageContext.request.contextPath}/index";
	 })
 })

</script>
</html>