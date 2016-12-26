/**
 * 
 */
;(function($){
	
	 var code;//在全局定义验证码
	 //生成验证码
	 function createCode(){
		 code="";
		 var codeLength=4;//验证码的长度
		 var $checkCode=$("code");
		 var random=new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
			     'S','T','U','V','W','X','Y','Z');
		 for(var i=0;i<codeLength;i++){
			 var index=Math.floor(Math.random()*36);//取得随机数的索引（0-35）
			 code+=random[index];
			 
		 }
		 $checkCode.attr("value",code);
		 
		 
	 }
	 function validate(){
		  var $inputCodeView=$("input");
		  var inputValue=$inputCodeView.attr("value");
		  
		  if(inputValue.length<0){
			  alert("请输入验证码");
		  }else if(inputCode!=code){
			  alert("验证码输入有误");
			  createCode();
			  $inputCodeView.attr("value","");
			  
		  }else{
			 console.log("验证成功")
		  }
		 
		 
	 }
	
})(jQuery)