<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
</style>
<body>
	<%@include file="/jsp/navheader.jsp"%>

	<div class="container">
		<c:if test="${pageBean.totalCount>0 }">

			<div class="panel panel-default">
				<div class="panel-heading">
					<div>订单详情</div>
				</div>
				<table class="table table-bordered">
					<c:forEach items="${pageBean.list }" var="order">
						<tbody>

							<tr class="success">
								<th colspan="1">订单编号:${order.oid }</th>
								
								<th colspan="1">订单时间：${order.orderTime }</th>
							
								<c:if test="${order.state==0}">
									<th><a href="${pageContext.request.contextPath }/Order?method=findOrderById&oid=${order.oid}">付款</a></th>
								</c:if>
								<c:if test="${order.state==1}">
									<th><a href="javascript:void(0)">已付款</a></th>
								</c:if>
								<c:if test="${order.state==2 }">
									<a href="">确认收货</a>
								</c:if>
								<c:if test="${order.state==3 }">
									<a hrf="">已完成</a>
								</c:if>
								<th>总金额：￥ ${order.total }</th>
								<th></th>
							</tr>

							<tr class="warning">

								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>

							</tr>




							<c:forEach items="${order.listOrderItem}" var="orderItem">


								<tr id="${orderItem.itemid }">

									<td><img
										src="${ pageContext.request.contextPath}/${orderItem.product.pimage }"></td>
									<td>${orderItem.product.pname}</td>
									<td>${orderItem.product.shop_price }</td>
									<td><input type="text" size="8"
										value="${orderItem.count }" readonly="readonly"></td>
									<td>${orderItem.subTotal }</td>

								</tr>
								<div style="height: 5px" class="success"></div>

							</c:forEach>




						</tbody>

					</c:forEach>
				</table>
			</div>
			<!-- end panel -->
			<div class="row ">
				<div class="pull-right">
					<ul class="pagination">

						<c:if test="${pageBean.currPage==1 }">
							<li class="disabled"><a
								href="${pageContext.request.contextPath}/Order?method=findAllByPage&currPage=${pageBean.currPage-1}">&laquo;</a></li>

						</c:if>
						<c:if test="${pageBean.currPage>1 }">
							<li><a
								href="${pageContext.request.contextPath }/Order?method=findAllByPage&currPage=${pageBean.currPage-1}">&laquo;</a></li>
						</c:if>
						<c:forEach begin="${pageBean.currPage>5?pageBean.currPage-5:1 }"
							end="${pageBean.currPage+4>pageBean.totalPage?pageBean.totalPage:pageBean.currPage+4 }"
							var="pageNum">
							<c:if test="${pageBean.currPage==pageNum }">

								<li class="active"><a
									href="${pageContext.request.contextPath }/Order?method=findAllByPage&currPage=${pageNum}">${pageNum }</a></li>
							</c:if>
							<c:if test="${pageBean.currPage!=pageNum }">

								<li><a
									href="${pageContext.request.contextPath }/Order?method=findAllByPage&currPage=${pageNum}">${pageNum }</a></li>
							</c:if>

						</c:forEach>
						<c:if test="${pageBean.totalPage==pageBean.currPage }">
							<li class="disabled"><a
								href="${pageContext.request.contextPath }/Order?method=findAllByPage&currPage=${pageBean.totalPage}">&raquo;</a></li>
						</c:if>

						<c:if test="${pageBean.totalPage!=pageBean.currPage }">

							<li><a
								href="${pageContext.request.contextPath }/Order?method=findAllByPage&currPage=${pageBean.currPage+1}">&raquo;</a></li>
						</c:if>

					</ul>
				</div>
			</div>




		</c:if>
		<!-- 以上为如果有订单 -->
		<!-- 如果没有订单 -->
		<c:if test="${pageBean.totalCount<1 }">
			<div class="row cart-empty">

				<div class="message col-md-4 col-md-push-4 ">
					<ul>
						<li class="txt">没有订单的哦~，去看看心仪的商品吧~</li>
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
		//请求分类信息
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