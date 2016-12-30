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
				data-backdrop="true">点击添加分类</button>
		</div>

		<div class="container">
			<table class="table" id="table">
				<tr>
					<th style="text-align: center">序号</th>
					<th style="text-align: center">分类名称</th>
					<th style="text-align: center">编辑</th>
					<th style="text-align: center">删除</th>

				</tr>
				<c:forEach items="${list }" var="item" varStatus="status">
					<tr class="categoryRow">
						<td>${status.index }</td>
						<td>${item.cname }</td>
						<td><a class="edit" data-cid="${item.cid }"
							data-cname="${item.cname }" href="javascript:testvoid(0)"
							data-toggle="modal" data-target=".popupcss" data-backdrop="true">
								<img src="/store/images/i_edit.gif" border="0"
								style="CURSOR: hand">
						</a></td>
						<td><a class="del" data-cid="${item.cid }"
							data-cname="${item.cname }" data-toggle="modal"
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
					<h4 class="modal-title">modal 标题</h4>
				</div>
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath}/adminCategory?method=addCategory"
						method="post" class="form-horizontal" id="basic_validate">
						<div class="  form-group">
							<label class="control-label col-md-2" name="categoryname">分类名称</label>
							<div class="col-md-8">
								<input type="text" name="categoryname" class="form-control"
									id="categorynameInput">
							</div>
							<div class="col-md-1">
								<span id="tipmsg" class="col-md-2 pull-right"> </span>
							</div>
							<input type="hidden" id=cid_edit_hidden_input> <input
								type="hidden" id=cid_del_hidden_input>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button id="savebtn" type="button" class="btn btn-primary"
						data-dismiss="modal">确定</button>

					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
			<!--modal-content-->
		</div>
		<!--modal modal-dialog-->
	</div>
	<!--modal show-->
</body>
<script type="text/javascript">
	function testvoid(param) {
		console.log(param);
	}

	$(function() {
		/*  $("#savebtn").bind("click",function(ev){
			$("#basic_validate").submit();
			
		}) */

		$("#savebtn")
				.bind(
						"click",
						function(ev) {
							console.log($("#basic_validate").serialize())

							var edit_datacid = $("#cid_edit_hidden_input")
									.val();
							$("#cid_edit_hidden_input").val("");//清空

							var del_datacid = $("#cid_del_hidden_input").val();
							$("#cid_del_hidden_nput").val("");

							var url = "";
							var data = null;
							var categoryName = $("#categorynameInput").val();
							$("#categorynameInput").val("");
							if (!isNull(edit_datacid)
									&& !isUndefined(edit_datacid)&&!isEmptyString(edit_datacid)) {
								//如果有datacid 更新操作
								var datacname = $("#" + edit_datacid).attr(
										"data-cname");

								$("#categorynameInput").val(datacname);
								url = "${pageContext.request.contextPath}/adminCategory?method=updateCategory&cid="
										+ edit_datacid;
								data = {
									"categoryname" : categoryName,
									"type" : "update"
								};

							} else {
								if (!isNull(del_datacid)
										&& !isUndefined(del_datacid)&&!isEmptyString(del_datacid)) {
									//如果是删除操作

									url = "${pageContext.request.contextPath}/adminCategory?method=deleteCategory&cid="
											+ del_datacid;
									data = {
										"type" : "del"

									};

								} else {
									//如果为空
									url = "${pageContext.request.contextPath}/adminCategory?method=addCategory"
									data = {
										"categoryname" : categoryName,
										"type" : "add"
									};
								}
							}
							console.log("url:" + url)

							if (!isNull(categoryName)
									&& !isUndefined(categoryName)) {
								$
										.ajax({
											url : url,
											data : data,
											contentType : "application/x-www-form-urlencoded; charset=utf-8",
											type : "post",
											dataType : "json",
											success : function(data, statusText) {
												console.log("data:" + data
														+ " StatusText:"
														+ statusText);
												if (!isNull(data)) {
													$(".categoryRow").remove();

													$
															.each(
																	data,
																	function(
																			index,
																			value) {
																		var view = "<tr class='categoryRow'>";
																		view += "<td>";
																		view += index;
																		view += "</td>"

																		view += "<td>";
																		view += value.cname;
																		view += "</td>"

																		view += "<td>";
																		view += " <a href='javascript:void(0)' class='edit' data-toggle='modal' data-target='.popupcss' data-backdrop='true' data-cid='"
																				+ value.cid
																				+ "' data-cname='"
																				+ value.cname
																				+ "'>";
																		view += "<img src='"+"/store/images/i_edit.gif"+"' border='0' style='CURSOR: hand'>"
																		view += "</a></td>";

																		view += "<td>";
																		view += " <a href='javascript:void(0)' class='del' data-toggle='modal' data-target='.popupcss' data-backdrop='true' data-cid='"
																				+ value.cid
																				+ "' data-cname='"
																				+ value.cname
																				+ "'>";

																		view += "<img src='"+"/store/images/i_del.gif"+"' border='0' style='CURSOR: hand'>"
																		view += "</a></td>";

																		view += "</tr>";
																		$(
																				".table")
																				.append(
																						$(view));

																	});
													bindEvent();//重新绑定事件

												}

											},
											error : function(xhr, statusText,
													errorThrown) {

											},
											complete : function(xhr, statusText) {

											}
										});
							} else {
								alert("输入名称不合法")
							}

						});

		function isNull(str) {

			if (!str && typeof (str) != "undefined" && str != 0) {
				return true
			} else {
				return false;
			}

		}
		function isUndefined(param) {
			return typeof (param) === "undefined";
		}

		function isEmptyString(data) {
			var dataTrim = data.replace(/(^\s*)|(\s*$)/g, "");
			if (dataTrim == 0)
				return true;
			return false;
		}

		function bindEvent() {

			$(".edit").bind("click", function(ev) {
				console.log("edit click");
				var datacid = $(this).attr("data-cid");
				$("#cid_edit_hidden_input").val(datacid)
				var datacname = $(this).attr("data-cname");
				$("#categorynameInput").val(datacname);

			});
			$(".del").bind("click", function(ev) {
				if(confirm()){
					window.href=""
				}
				var datacid = $(this).attr("data-cid");
				$("#cid_del_hidden_input").val(datacid)
				$(".modal-body").css("visibility", "hidden");
			})

		}
		bindEvent();

	})
</script>
</html>