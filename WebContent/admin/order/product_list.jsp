<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/jscss.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h3 style="text-align: center;">分类列表</h3>
		<div class="row">
			<button class=" btn btn-primary pull-right" data-toggle="modal"
				data-target=".popupcss" style="margin-top: 5px;"
				data-backdrop="true" id="addproductbtn">点击添加商品</button>
		</div>

		<div class="container">
			<table class="table" id="table">
				<tr>
					<th style="text-align: center">序号</th>
					<th style="text-align: center">商品图片</th>
					<th style="text-align: center">商品名称</th>
					<th style="text-align: center">商品价格</th>
					<th style="text-align: center">是否热门</th>
					<th style="text-align: center">编辑</th>
					<th style="text-align: center">删除</th>

				</tr>
				<c:forEach items="${list }" var="item" varStatus="status">
					<tr class="productRow">
						<td>${status.index }</td>
						<td><img src="${item.pimage }" style="height: 50px;"></td>
						<td>${item.pname }</td>
						<td>${item.shop_price }</td>
						<c:if test="${item.is_hot==0 }">
							<td>否</td>
						</c:if>
						<c:if test="${item.is_hot!=0 }">
							<td>是</td>
						</c:if>

						<td><a class="edit" data-cid="${item.pid }"
							data-cname="${item.pname }" href="javascript:testvoid(0)"
							data-toggle="modal" data-target=".popupcss" data-backdrop="true">
								<img src="/store/images/i_edit.gif" border="0"
								style="CURSOR: hand">
						</a></td>
						<td><a class="del" data-cid="${item.pid }"
							data-cname="${item.pname }" data-toggle="modal"
							data-target=".popupcss" data-backdrop="true"
							href="javascript:void(0)"> <img src="/store/images/i_del.gif"
								border="0" style="CURSOR: hand">
						</a></td>

					</tr>

				</c:forEach>


			</table>


		</div>
	</div>

	<div class="modal fade popupcss">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">x</button>
					<h4 class="modal-title">添加商品信息</h4>
				</div>
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath}/adminProduct?method=addProduct"
						method="post" class="form-horizontal" id="basic_validate"
						enctype="multipart/form-data">
						<div class="  form-group">
							<label class="control-label col-md-2" name="productname">商品名称</label>
							<div class="col-md-8">
								<input type="text" name="productname" class="form-control"
									id="product_name_input">
							</div>
							<div class="col-md-1">
								<span id="productname_tipmsg" class="col-md-2 pull-right">
								</span>
							</div>
							<input type="hidden" id=cid_edit_hidden_input> <input
								type="hidden" id=cid_del_hidden_input>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2" name="productmarketprice">市场价格</label>
							<div class="col-md-8">
								<input type="text" name="productmarketprice"
									class="form-control" id="product_marketprice_input">
							</div>
							<div class="col-md-1">
								<span id="product_marketprice_tipmsg" class="pull-right"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-2" name="productimage">商品图片</label>
							<div class="col-md-8">
								<input type="file" name="productimage" class="form-control"
									id="product_image_input">
							</div>
							<div class="col-md-1">
								<span id="product_image_tipmsg" class="pull-right"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2" name="productcategory">商品分类</label>
							<div class="col-md-2">
								<select id="selectprocutcategory" name="productcategory"
									class="selectpick show-tick" id="productCategorySelect">

									<option value="cid">数码产品</option>
									<option value="cname">服装</option>
								</select>


							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-2" name="productdescription">商品描述</label>
							<div class="col-md-8">
								<input type="text" name="productdescription"
									class="form-control" id="product_des_input">
							</div>
							<div class="col-md-1">
								<span id="product_description_tipmsg" class="pull-right"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2" name="productishot">是否热门</label>

							<div class="col-md-8">
								<label>是<input type="radio" name="productishot"
									value="1">
								</label> <label>否<input type="radio" name="productishot"
									value="0">
								</label>
							</div>

						</div>

						<div class="form-group">
							<label class="control-label col-md-2" name="productshopprice">商城价格</label>
							<div class="col-md-8">
								<input type="text" name="productshopprice" class="form-control"
									id="product_shopprice_input">
							</div>
							<div class="col-md-1">
								<span id="product_shopprice_tipmsg" class="pull-right"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-md-push-8">
								<input id="savebtn" type="submit"
									class="btn btn-primary pull-right" >
							</div>


							<div class="col-md-2 col-md-push-8">
								<button type="button" class="btn btn-default pull-right"
									data-dismiss="modal">取消</button>
							</div>
						</div>

					</form>


				</div>
				<!-- div modal body end -->

			</div>
			<!--modal-content-->
		</div>
		<!--modal modal-dialog-->
	</div>
	<!--modal show-->
</body>
<script type="text/javascript">
	$(function() {
		/*  $("#savebtn").bind("click",function(ev){
			$("#basic_validate").submit();
			
		}) */
		$("#addproductbtn")
				.bind(
						"click",
						function(ev) {

							$("select>option").remove();

							$
									.ajax({
										url : "${pageContext.request.contextPath}/adminCategory?method=getAllCategory",
										dataType : "json",
										data : {},
										type : "post",
										contentType : "application/x-www-form-urlencoded",
										success : function(data, statusText) {
											console.log(data);
											$
													.each(
															data,
															function(index,
																	value) {
																var option = "<option value='"+value.cid+"'>"
																		+ value.cname
																		+ "</option>";
																$(
																		"#selectprocutcategory")
																		.append(
																				$(option));

															})

										}
									});

						});

	})
</script>
</html>