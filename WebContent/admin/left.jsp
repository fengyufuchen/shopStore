<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="StyleSheet"
	href="${pageContext.request.contextPath }/css/left.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/dtree.css"
	type="text/css">

<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/dtree.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="100" border="0" cellspcing="0" cellpadding="0">

		<tr>

			<td height="12"></td>
		</tr>
	</table>
	<table width="100%" border="0">
		<tr>
			<td>

				<div class="dtree">
					<a href="javascript:d.openAll()">展开所有</a>|<a
						href="javascript:d.closeAll()">关闭所有</a>

					<script type="text/javascript">
						/*
								dTree.protype.add=function(id,pid,name ,url, title, icon,iconOpen ,open)
										id当前结点的标示
										pid父节点的表示
										name当前结点的名字，
										url当前菜单被点击时响应的超链接
										title，鼠标放置到该标签的时候提示信息
										target：在目标窗口中打开连接
										icon关闭时的显示图标
										iconOpen打开时显示的图标
										open默认的打开状态，false关闭，
						 */

						/* d=new dTree();
						d.add("01","-1","系统菜单树")
						
						d.add("0102","01","分类管理","","","mainFrame");
						
						d.add("010201","0102","展示所有","${pageConatext.request.contextPath}/adminCategory?method=findAll","","mainFrame");//空位置为title
						d.add("010202","0102","添加分类","${pageConatext.request.contextPath}/adminCategory?method=addUI","","mainFrame");//空位置为title
						
						
						d.add("0104","01","商品管理");
						
						d.add("010401","0104","商品管理","${pageConatext.request.contextPath}/adminProduct?method=findAll","","mainFrame");//空位置为title

						
						d.add("0105","01","订单管理");
						d.add("010501","0105","所有订单","${pageConatext.request.contextPath}/adminOrder?method=findAllByState","","mainFrame");//空位置为title

						d.add("010502","0105","未支付订单","${pageConatext.request.contextPath}/adminOrder?method=findAllByState&state=0","","mainFrame");//空位置为title

						d.add("010503","0105","已支付订单","${pageConatext.request.contextPath}/adminOrder?method=findAllByState&state=1","","mainFrame");//空位置为title
						d.add("010504","0105","已发送订单","${pageConatext.request.contextPath}/adminOrder?method=findAllByState&state=2","","mainFrame");//空位置为title
						d.add("010505","0105","已完成订单","${pageConatext.request.contextPath}/adminOrder?method=findAllByState&state=3","","mainFrame");//空位置为title
						document.write(d);
						 */
						d = new dTree('d');
						d.add('01', -1, '系统菜单树');
						d.add('0102', '01', '分类管理', '', '', 'mainFrame');
						d
								.add(
										'010201',
										'0102',
										'展示所有',
										'${pageContext.request.contextPath}/adminCategory?method=findAll',
										'', 'mainFrame');
						d
								.add(
										'010202',
										'0102',
										'添加分类',
										'#',
										'', 'mainFrame');
						d.add('0104', '01', '商品管理');
						d
								.add(
										'010401',
										'0104',
										'商品管理',
										'${pageContext.request.contextPath}/adminProduct?method=findAllProduct',
										'', 'mainFrame');
						d.add('0105', '01', '订单管理');
						d
								.add(
										'010501',
										'0105',
										'所有订单',
										'${pageContext.request.contextPath}/adminOrder?method=findAllByState',
										'', 'mainFrame');
						d
								.add(
										'010502',
										'0105',
										'未支付订单',
										'${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=0',
										'', 'mainFrame');
						d
								.add(
										'010503',
										'0105',
										'已支付订单',
										'${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=1',
										'', 'mainFrame');
						d
								.add(
										'010504',
										'0105',
										'已发货订单',
										'${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=2',
										'', 'mainFrame');
						d
								.add(
										'010505',
										'0105',
										'已完成订单',
										'${pageContext.request.contextPath}/adminOrder?method=findAllByState&state=3',
										'', 'mainFrame');
						document.write(d);
					</script>
				</div>

			</td>

		</tr>

	</table>

	<!-- end  table -->

	

</body>
</html>