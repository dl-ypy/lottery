$(document).ready(function(){
	/*表单里的错误提示*/
	function show(errMsg){
		$('.error-item').show().find('.err-msg').text(errMsg);
	}
	function hide(){
	    $('.error-item').hide().find('.err-msg').text('');
	}
	$("#submit-phone").click(function(){
		var phone		= $.trim($('#phone').val()),
			phonecode	= $.trim($('#phone-code').val());
		if(phone === ""){
			show("请输入手机号码！");
		}else if(phonecode === ""){
			show("请输入验证码！");
		}
		$('.step-phone').hide()
		.siblings('.step-password').show();
	});
	
	//输入新密码后的按钮点击
	$('#submit-password').click(function(){
		var password = $.trim($('#password').val());
		/*密码是否存在判断*/
		if(password && password.length >= 6){
			alert("密码重置成功！");
		}else{
			formError.show('请输入不少于6位的新密码！');
		}
	});
});