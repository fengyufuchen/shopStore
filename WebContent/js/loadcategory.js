;(function($) {

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
})(jQuery)
