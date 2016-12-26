<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.itheima.web.domain.* ,java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/jscss.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Insert title here</title>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	list-style: none;
	text-align: left;
}


.searchform {
	margin-left: -20px;
}



tbody>tr>td>img {
	display: inline-block;
	height: 70px;
}

.table>tbody>tr>td {
	vertical-align: baseline;
}

.deleteCartItemTag {
	
}

.cart-empty {
	background: url("${pageContext.request.contextPath}/img/cart.png") 250px
		22px no-repeat;
}
/*空购物车*/
.txt {
	font-size: 14px;
}

.cart-empty .message li {
	line-height: 26px;
}

.ftx-05, .ftx05 {
	color: #005ea7;
}
</style>
</head>
<body>
	<%@include file="/jsp/navheader.jsp"%>



	<div class="container" id="cartContainer">
		<c:if test="${cart.count>0 }">
			<div class="panel panel-default">
				<div class="panel-heading">订单详情</div>
				<table class="table">
					<thead>
						<tr>

							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${cart.items }" var="itemEntry">

							<tr id="${itemEntry.product.pid }">

								<td><img
									src="${ pageContext.request.contextPath}/${itemEntry.product.pimage }"></td>
								<td>${itemEntry.product.pname}</td>
								<td>${itemEntry.product.shop_price }</td>
								<td><input type="text" size="8" value="${itemEntry.count }"
									readyonly></td>
								<td>${itemEntry.subtotal }</td>
								<td><a href="javascript:void(0)" class="deleteCartItemTag"
									data-pid="${itemEntry.product.pid }">删除</a></td>

							</tr>


						</c:forEach>


					</tbody>

				</table>
			</div>
			<!-- end panel -->
			<div class="row " >
				<div class="pull-right">
					<span> 登陆后去人是否享有优惠</span> 赠送积分：<span>596</span><span>商品金额：</span>￥${cart.sumTotal }
				</div>
			</div>
			<br>

			<div class="row" >
				<div class="pull-right">

					<a href="javascript:void(0)" id="clearCart">清空购物车</a
					> <a
						href="${pageContext.request.contextPath}/Order?method=add"> <input type="button"
						class="btn btn-default" value="提交订单">
					</a>

				</div>
			</div>

		</c:if>
		<c:if test="${cart.count<1 }">
			<div class="row cart-empty">

				<div class="message col-md-4 col-md-push-4 ">
					<ul>
						<li class="txt">购物车空空的哦~，去看看心仪的商品吧~</li>
						<li><a href="${pageContext.request.contextPath }"
							class="ftx-05"> 去购物&gt; </a></li>
						<li><a
							href="${pageContext.request.contextPath }/Cart?method=goToCart"
							class="ftx-05"> 回普通商品购物车&gt; </a></li>
					</ul>
				</div>
			</div>

		</c:if>



	</div>
	<!-- end container -->
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
		/*
		删除商品，发送ajax请求
		 */
		$(".deleteCartItemTag")
				.bind(
						"click",
						function(ev) {
							var datapid = $(this).attr("data-pid");

							location.href = "${pageContext.request.contextPath}/Cart?method=remove&pid="
									+ datapid;

						});

		//清空购物车
		$("#clearCart")
				.bind(
						"click",
						function(ev) {
							/* $
									.ajax({
										url : "${pageContext.request.contextPath}/Cart?method=clearCart",
										data : {},
										type : "post",
										dataType : "json",
										success : function(data, statusText) {
											console.log("clearCart")
											console.log(data);
											if (data.clearResult == "OK") {
												$(".panel").remove();//清空购物车中的商品，
												$("#totalMoney").remove();
												$("#submitOrder").remove();
												$("#becameEmptyCart").css(
														"display", "block");

											}

										},
										error : function(xhr, testStatus,
												errorThrown) {

										},
										complete : function(xhr, textStatus) {

										}
									})
									
							 */

							location.href = "${pageContext.request.contextPath}/Cart?method=clearCart";

						});

	})
</script>
</html>