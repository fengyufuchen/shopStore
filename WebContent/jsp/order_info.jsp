<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/jsp/jscss.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	list-style: none;
	text-align: left;
	vertical-align: center;
}

tbody>tr>td>img {
	display: inline-block;
	height: 70px;
}

.bankerdiv {
	margin-top: 15px;
}
</style>
<body>
	<%@include file="/jsp/navheader.jsp"%>


	<div class="container" id="cartContainer">
		<c:if test="${order.count>0 }">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div>订单详情</div>
				</div>
				<table class="table table-bordered">

					<tbody>
						<tr class="warning">

							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>

						</tr>

						<tr class="warning">
							<th colspan="2">订单编号：${order.oid }</th>
							<th colspan="2">订单时间：${order.orderTime }</th>

						</tr>

						<c:forEach items="${order.listOrderItem }" var="itemEntry">

							<tr id="${itemEntry.product.pid }">

								<td><img
									src="${ pageContext.request.contextPath}/${itemEntry.product.pimage }"></td>
								<td>${itemEntry.product.pname}</td>
								<td>${itemEntry.product.shop_price }</td>
								<td><input type="text" size="8" value="${itemEntry.count }"
									readonly="readonly"></td>
								<td>${itemEntry.subTotal }</td>

							</tr>


						</c:forEach>


					</tbody>


				</table>
			</div>
			<!-- end panel -->
			<div class="row ">
				<div class="pull-right">
					<span>商品金额：</span>￥${order.total }
				</div>
			</div>
			<br>
			<!-- start -->
			<div class="row ">


				<div class="row ">
					<label for="address" class="col-md-1 col-md-push-2">地址</label> <input
						type="text" size="120" name="address" id="address"
						class="col-md-4 col-md-push-2">

				</div>
				<div class="row">
					<label for="receiver" class="col-md-1 col-md-push-2">收货人</label> <input
						type="text" size="120" name="receiver" id="receiver"
						class="col-md-4 col-md-push-2">

				</div>
				<div class="row ">
					<label for="telephone" class="col-md-1 col-md-push-2">电话</label> <input
						type="text" size="120" name="telephone" id="telephone"
						class="col-md-4 col-md-push-2">

				</div>


			</div>

			<!-- end  -->

			<hr />
			<!-- banker -->

			<div style="margin-top: 5px; margin-left: 120px;" class="row">
				<strong>选择银行：</strong> <br />

				<div class="col-md-4 bankerdiv">
					<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"
						checked="checked" />工商银行 <img
						src="${pageContext.request.contextPath}/bank_img/icbc.bmp"
						align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
				</div>


				<div class="col-md-4 bankerdiv">
					<input type="radio" name="pd_FrpId" value="BOC-NET-B2C" />中国银行 <img
						src="${pageContext.request.contextPath}/bank_img/bc.bmp"
						align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
				</div>

				<div class="col-md-4 bankerdiv">

					<input type="radio" name="pd_FrpId" value="ABC-NET-B2C" />农业银行 <img
						src="${pageContext.request.contextPath}/bank_img/abc.bmp"
						align="middle" />
				</div>


				<div class="col-md-4 bankerdiv">

					<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C" />交通银行 <img
						src="${pageContext.request.contextPath}/bank_img/bcc.bmp"
						align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
				</div>


				<div class="col-md-4 bankerdiv">

					<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" />平安银行
					<img src="${pageContext.request.contextPath}/bank_img/pingan.bmp"
						align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
				</div>

				<div class="col-md-4 bankerdiv">
					<input type="radio" name="pd_FrpId" value="CEB-NET-B2C" />光大银行 <img
						src="${pageContext.request.contextPath}/bank_img/guangda.bmp"
						align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
				</div>

				<div class="col-md-4 bankerdiv">

					<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" />招商银行
					<img src="${pageContext.request.contextPath}/bank_img/cmb.bmp"
						align="middle" />
				</div>

				<hr />
				<br>
			</div>

			<!-- banker -->
			<!-- 提交订单按钮 -->
			<div class="row">
				<div class="row">

					<p style="text-align: right; margin-right: 100px;">
						<a
							href="javascript:document.getElementById('orderForm').submit();">
							<img
							src="${pageContext.request.contextPath}/images/finalbutton.gif"
							width="204" height="51" border="0" />
						</a>
					</p>
				</div>
				<hr />
			</div>

		</c:if>
	</div>
	<!-- div container -->

		<div class="row cart-empty">
	<c:if test="${order.count<1 }">

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
	})
</script>
</html>