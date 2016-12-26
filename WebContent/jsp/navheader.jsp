
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x"%>
<!-- 导航部分 -->
<div id="cotainerBody" class="container-fluid">
	<div class="col-md-4">
		<img src="/Store18s/img/logo2.png">
	</div>
	<div class="col-md-5">
		<img src="/Store18s/img/header.png">
	</div>
	<div id="nav-guide" class="col-md-3">
		<ol>
			<c:if test="${user==null }">
				<li><a
					href="${pageContext.request.contextPath }/user?method=loginUI">登陆</a></li>
				<li><a
					href="${pageContext.request.contextPath }/user?method=registUI">注册</a></li>
				<li><a
					href="${pageContext.request.contextPath }/Cart?method=goToCart">购物车</a></li>
			</c:if>

			<c:if test="${user!=null }">
				<li>欢迎${user.username}</li>
				<li><a
					href="${pageContext.request.contextPath }/user?method=logOut">注销</a></li>
				<li><a href="${pageContext.request.contextPath }/Order?method=findAllByPage&currPage=1">我的订单</a></li>
				<li><a
					href="${pageContext.request.contextPath }/Cart?method=goToCart">购物车</a></li>
			</c:if>


		</ol>
	</div>
</div>
<!-- 导航条部分 -->
<div class="container-fluid">
	<div id="navbar" class="navbar navbar-inverse " role="navigation">
		<div class="navbar-header"></div>
		<ul class="nav navbar-nav" id="categoryTitle">
			<li class="active"><a href="${pageContext.request.contextPath }">首页</a></li>


		</ul>
		<form action="##" class="searchform navbar-form navbar-right"
			role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="请输入商品名称">
			</div>
			<button type="submit" class="btn btn-default" value="submit">搜索</button>
		</form>

	</div>
</div>


