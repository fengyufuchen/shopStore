<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.itheima.web.utils.*,com.itheima.web.servlet.* ,com.itheima.web.domain.*"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@ include file="/jsp/jscss.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style: none;
	text-decoration: none;
}

.scanproduct {
	padding: 10px 20px;
	border: 2px solid #999999;
}
</style>
<body>
	<%@ include file="/jsp/navheader.jsp"%>

	<div class="container">
		<c:forEach items="${pageBean.list }" var="productItem">

			<div class="col-md-2" style="outline: 1px solid red; height: 240px;">
				<a
					href="${pageContext.request.contextPath }/product?method=getById&pid=${productItem.pid }"><img
					src="${pageContext.request.contextPath }/${productItem.pimage}"></a>

				<p>
					<a
						href="${pageContext.request.contextPath }/product?method=getById&pid=${productItem.pid}">${fn:substring(productItem.pname,0,10)}</a>
				</p>
				<p>
					<a
						href="${pageContext.request.contextPath }/product?method=getById&pid=${productItem.pid}">${productItem.shop_price }</a>
				</p>
			</div>
		</c:forEach>

	</div>

	<div class="container">

		<div style="width: 380px; margin: 0px atuo; margin-top: 50px"
			class="pull-right">
			<ul class="pagination" style="text-align: center; margin-top: 10px;">


				<c:if test="${pageBean.currPage==1 }">
					<li class="disabled"><a href="javascript:void(0)"
						aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
				</c:if>
				<c:if test="${pageBean.currPage!=1 }">
					<li><a
						href="${pageContext.request.contextPath }/product?method=findByPage&currPage=${pageBean.currPage-1}&cid=${param.cid}"
						aria-label="Previous"><span aria-hidden="true"></span></a></li>
				</c:if>
				<!-- 设置显示当前页前面5页，以及当前页，以及当前页后面4页 -->

				<c:forEach begin="${ pageBean.currPage-5>0?pageBean.currPage-5:1}"
					end="${pageBean.currPage+4>pageBean.totalPage?pageBean.totalPage: pageBean.currPage+4}"
					var="pageN">
					<!-- 判断是否是当前页 -->
					<c:if test="${pageBean.currPage ==pageN}">

						<li class="active"><a href="javascript:void(0)">${pageN }</a></li>
					</c:if>
					<c:if test="${pageBean.currPage!=pageN }">
						<li><a
							href="${pageContext.request.contextPath }/product?method=findByPage&currPage=${pageN}&cid=${param.cid}">${pageN }</a>
					</c:if>

				</c:forEach>
				<c:if test="${pageBean.currPage==pageBean.totalPage }">
					<li class="disabled"><a href="javascript:void(0)"><span>&raquo;</span></a></li>

				</c:if>
				<c:if test="${pageBean.currPage!=pageBean.totalPage }">
					<li><a
						href="${pageContext.request.contextPath }/product?method=findByPage&currPage=${pageBean.currPage+1}&cid=${param.cid}">&raquo;</a>
				</c:if>


			</ul>


		</div>

	</div>

	<div class="container scanproduct">
		<h4 style="text-align: left">浏览记录</h4>
		<%
			Cookie cookiePids = (Cookie) CookUtils.getCookieByName("scanPids", request.getCookies());
			if (cookiePids != null) {
				String pids = cookiePids.getValue();
				if (pids != null) {
					String[] pidsArray = pids.split("-");

					for (int i = 0; i < pidsArray.length; i++) {
						String pid = pidsArray[i];
						ProductServlet productServlet = (ProductServlet) request.getAttribute("productServlet");
						Product product = productServlet.findScanProductByProductId(pidsArray[i]);
						if (product != null) {
		%>


		<div class="col-md-2" style="outline: 1px solid red; height: 240px;">
			<a
				href="${pageContext.request.contextPath }/product?method=getById&pid=<%=product.getPid() %>>"><img
				src="${pageContext.request.contextPath }/<%=product.getPimage() %>"></a>

			<p>
				<a
					href="${pageContext.request.contextPath }/product?method=getById&pid=<%=product.getPid() %>"><%=product.getPname()%>
				</a>
			</p>
			<p>
				<a
					href="${pageContext.request.contextPath }/product?method=getById&pid=<%=product.getPid() %>"><%=product.getShop_price()%>
				</a>
			</p>
		</div>


		<%
			}

					}
				}
			}
		%>



	</div>

	<!-- end 浏览记录 -->

	<div style="margin-top: 50px;">
		<img src="${pageContext.request.contextPath}/image/footer.jpg"
			width="100%" height="78" alt="我们的优势" title="我们的优势" />
	</div>

	<div style="text-align: center; margin-top: 5px;">
		<ul class="list-inline">
			<li><a>关于我们</a></li>
			<li><a>联系我们</a></li>
			<li><a>招贤纳士</a></li>
			<li><a>法律声明</a></li>
			<li><a>友情链接</a></li>
			<li><a target="_blank">支付方式</a></li>
			<li><a target="_blank">配送方式</a></li>
			<li><a>服务声明</a></li>
			<li><a>广告声明</a></li>
		</ul>
	</div>
	<div style="text-align: center; margin-top: 5px; margin-bottom: 20px;">
		Copyright &copy; 2005-2016 传智商城 版权所有</div>




</body>
<script type="text/javascript">
	$(function() {

		//获取分类
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

		//获取cookie
		function getCookie(name) {
			var arr, reg = new RegExp("(|)" + name + "=([^:]*)(:|$)");
			if (arr = document.cookie.match(reg)) {
				return unescape(arr[2]);

			} else {
				return null;
			}
		}//scanPids
		var cookieStr = getCookie("scanPids");

		/* dataType指定服务器的响应类型
		 */

		console.log("cookie:" + cookieStr)
		if (false) {

			$.ajax({

				url : "",
				type : "post",
				dataType : "json",
				success : function(data) {
					console.log(data);
				},
				error : function(data) {
					//200的响应也有可能被认定为error，responseText中没有message部分
					console.log("error:"
							+ $.parseJSON(data.responseText).Message)
				},
				complete : function(data) {
					//after success or error
				}

			});

		}

	})
</script>
</html>