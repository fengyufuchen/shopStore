<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, com.itheima.web.domain.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/jsp/jscss.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
	text-decoration: none;
	list-style: none;
}

#cotainerBody {
	outline: 2px solid green;
}

#nav-guide {
	height: 100%;
}

#nav-guide ol {
	margin: 20px 0px;
}

#nav-guide ol li {
	display: inline-block;
	margin-left: 7px;
	cursor: pointer;
}

.searchform {
	margin-left: -20px;
}

/*
分类标题设置
*/
.catalogheader {
	outline: 2px solid green;
}

.catalogtitle {
	display: inline-block;
	font-size: 32px;
	font-weight: blod;
	margin-right: 3px;
}
/*清除由于 col-md-2 中的padding为15px带来的影响*/
.firstrecommendproduct {
	padding: 0px;
	margin-right: 10px
}

.productItemsArea>div {
	height: 200px;
}

.productitemtype3 {
	text-align: center;
}
</style>
<body>



	<%@include file="/jsp/navheader.jsp"%>
	<!-- 大屏幕展播 -->
	<div class="container-fluid">
		<div class="carousel slide" id="carousel-container">

			<!-- 图片容器 -->
			<div class="carousel-inner">
				<div class="item">
					<img src="/Store18s/img/1.jpg">
				</div>
				<div class="item active">
					<img src="/Store18s/img/2.jpg">
				</div>
				<div class="item">
					<img src="/Store18s/img/3.jpg">
				</div>

			</div>
			<!-- 指示器 -->
			<ol class="carousel-indicators">
				<li data-slide-to="0" data-target="#carousel-container"></li>
				<li data-slide-to="1" data-target="#carousel-container"></li>
				<li data-slide-to="2" data-target="#carousel-container"
					class="active"></li>

			</ol>
			<a data-slide="pre" href="#carousel-container"
				class="left carousel-control"> <span
				class="glyphicon glyphicon-chevron-left"></span></a> <a
				data-slide="next" href="#carousel-container"
				class="right carousel-control"> <span
				class="glyphicon glyphicon-chevron-right"></span>
			</a>

		</div>
	</div>


	<div class="container-fluid">
		<div class="catalogheader">
			<h3 class="catalogtitle">热门商品</h3>
			<img src="/Store18s/img/title2.jpg">
		</div>
		<div class="productArea row">
			<div class="col-md-2 left firstrecommendproduct">
				<a href="product_info.html"> <img
					src="/Store18s/products/hao/big01.jpg" style="height: 400px;">
				</a>
			</div>
			<div class="col-md-9 left productItemsArea">
				<div class="col-md-5" style="outline: 2px solid red;">
					<a><img src="/Store18s/products/hao/middle01.jpg"
						style="height: 200px;"></a>
				</div>


				<c:forEach items="${hotList }" var="hotProductItem">
					<div class="col-md-2 productitemtype3"
						style="outline: 1px solid red;">
						<a
							href="${pageContext.request.contextPath }/product?method=getById&pid=${hotProductItem.pid }"><img
							src="${pageContext.request.contextPath }/${hotProductItem.pimage}"></a>

						<p>
							<a
								href="${pageContext.request.contextPath }/product?method=getById&pid=${hotProductItem.pid}">${fn:substring(hotProductItem.pname,0,10)}</a>
						</p>
						<p>
							<a
								href="${pageContext.request.contextPath }/product?method=getById&pid=${hotProductItem.pid}">${hotProductItem.shop_price }</a>
						</p>
					</div>
				</c:forEach>


			</div>
			<!-- productItemsArea end -->
		</div>
		<!--  productArea end-->

	</div>
	<!-- end -->
	<!-- 广告 -->
	<div class="container-fluid">
		<img src="/Store18s/products/hao/ad.jpg" style="width: 100%">
	</div>

	<!-- 热门商品 -->
	<div class="container-fluid">
		<div class="">
			<h3 class="catalogtitle">热门商品</h3>
			<a><img src="/Store18s/img/title2.jpg"></a>
		</div>
		<div class="productArea row">

			<div class="col-md-2">
				<a href="product_info.html"> <img
					src="/Store18s/products/hao/big01.jpg" style="height: 400px;">
				</a>
			</div>
			<div class="col-md-9 left productItemsArea">
				<div class="col-md-5" style="outline: 2px solid red;">
					<a><img src="/Store18s/products/hao/middle01.jpg"
						style="height: 200px;"></a>
				</div>



				<c:forEach items="${newList }" var="newProductItem">

					<div class="col-md-2 productitemtype3"
						style="outline: 1px solid red;">
						<a
							href="${pageContext.request.contextPath }/product?method=getById&pid=${newProductItem.pid }"><img
							src="${pageContext.request.contextPath }/${newProductItem.pimage}"></a>

						<p>
							<a
								href="${pageContext.request.contextPath }/product?method=getById&pid=${newProductItem.pid}">${fn:substring(newProductItem.pname,0,10)}</a>
						</p>

						<p>
							<a
								href="${pageContext.request.contextPath }/product?method=getById&pid=${newProductItem.pid}">${newProductItem.shop_price }</a>
						</p>
					</div>

				</c:forEach>
			</div>
			<!-- end productItemsArea -->


		</div>
		<!-- end productArea -->
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
	$(function() {

		function getCategory() {

			$
					.get(
							"${pageContext.request.contextPath}/index?method=getCategory",
							{},
							function(data, status) {

								var array = eval("[" + data + "]");
								for (var i = 0; i < array.length; i++) {

									$("#categoryTitle")
											.append(
													"<li><a href=\" ${pageContext.request.contextPath}/product?method=findByPage&cid="
															+ array[i].cid
															+ "&currPage=1\">"
															+ array[i].cname
															+ " </a></li>")

								}

							}

					)

		}

		getCategory();
	})
</script>
</html>