<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@include file="/jsp/jscss.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.tipMsg{
	
    display:inline-block;
    text-align:center;
	margin-top: 10px;
    font-size: 38px;
    font-weight: 100;
    color: #555;
    line-height: 50px;
    
}
</style>

</head>
<body>
	<div class="container-fluid">

		<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
		<div class="container-fluid">
			<div class="col-md-4">
				<img src="${pageContext.request.contextPath}/img/logo2.png" />
			</div>
			<div class="col-md-5">
				<img src="${pageContext.request.contextPath}/img/header.png" />
			</div>
			<div class="col-md-3" style="padding-top: 20px">
				<ol class="list-inline">
					<li><a href="">登录</a></li>
					<li><a href="">注册</a></li>
					<li><a href="">购物车</a></li>
				</ol>
			</div>
		</div>
		<!--
            	时间：2015-12-30
            	描述：导航条
            -->
		<div class="container-fluid">
			<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">首页</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">手机数码<span class="sr-only">(current)</span></a></li>
						<li><a href="#">电脑办公</a></li>
						<li><a href="#">电脑办公</a></li>
						<li><a href="#">电脑办公</a></li>
					</ul>
					<form class="navbar-form navbar-right" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>

				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid --> </nav>
		</div>

		<div class="container-fluid">
			<span class="tipMsg">${msg }<span class="tipMsg" id="remaindSecond"></span>
			</span>
		</div>

	</div>
	<div class="container-fluid">
		<div style="margin-top: 50px;">
			<img src="${pageContext.request.contextPath}/img/footer.jpg"
				width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center; margin-top: 5px;">
			<ul class="list-inline">
				<li><a href="info.html">关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a>支付方式</a></li>
				<li><a>配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center; margin-top: 5px; margin-bottom: 20px;">
			Copyright &copy; 2005-2016 传智商城 版权所有</div>
	</div>
	
</body>
<script type="text/javascript">

$(function(){

	 function activeInfo(){
		 
		var time=3;
		function junmpToIndex(){
			
				 $("#remaindSecond").text(time);
				 if(time==0){
                    clearInterval(timer);
					if(${activeResult}==="true"){//激活成功
						  window.location="${pageContext.request.contextPath}/index"; 
					}else{
						 window.location="${pageContext.request.contextPath}/jsp/register.jsp" 
					}
					 
				 }
				 time--;
		}	
	var timer=setInterval(junmpToIndex,1000);
		 
	 }
	 if(${activeResult}){
		 console.log("需要显示激活信息");
		activeInfo();
	 }
	
})


</script>

</html>