/**
 * 
 */
;
(function($) {
	/*
	 * todayHighlight:默认值为false，如果为true，那么就高亮当前日期
	 * 
	 * 
	 * keyboardNavigation: 默认值为true 是否允许通过方向键更改日期
	 * 
	 * forceParse 默认是为true，
	 * 当选择器关闭的时候，是否强制解析输入框中的值，也就说当用户在输入框中输入了不正确的日期，选择器会尽量解析输入的数值，并将解析后的正确值按照给定的格式format设置到输入框中
	 * 
	 * weekstart 默认值是0 一周从哪一天开始，星期天（使用0表示）到星期六（使用6表示）
	 * 
	 * startDate 默认值，开始时间，The earlies date that may be selected all earlier
	 * dates will be disabled
	 * 
	 * 
	 * atuoClose :选择一个日期之后是否立即关闭这个日期选择器 minview: Number,String 默认值是：4,"decade"
	 * 日期事件选择器最高能展示的选择范围视图
	 * 
	 * 如何设置弹出窗口的位置？？
	 */

	$(".form-date").datetimepicker({
		language : "fr",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0,
		format:"mm/dd/yyyy"

	});
	getAuthCode();
	// 设置更新验证码refresh-authcode
	$("#authcodeimg").bind("click", function(ev) {
		getAuthCode();
	})
	$("#refresh-authcode").bind("click", function(ev) {
		getAuthCode();
	})

	// 获取验证码
	function getAuthCode() {
		var url = $("#authcodeimg").attr("data-authpath");
		console.log(url)
		var xhr = new XMLHttpRequest();
		xhr.responseType = "blob";
		xhr.open('GET', url, true);
		xhr.responseType = "blob";
		xhr.setRequestHeader("client_type", "DESKTOP_WEB");

		xhr.onload = function() {
			if (this.status == 200) {
				var blob = this.response;
				var img = document.getElementById("authcodeimg");
				img.onload = function(e) {
					window.URL.revokeObjectURL(img.src);
				};
				img.src = window.URL.createObjectURL(blob);

			}
		}
		xhr.send();
	}
	;

})(jQuery)