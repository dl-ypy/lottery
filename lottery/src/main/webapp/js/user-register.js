$(document).ready(function(){
	/*表单里的错误提示*/
	function show(errMsg){
		$('.error-item').show().find('.err-msg').text(errMsg);
	}
	function hide(){
	    $('.error-item').hide().find('.err-msg').text('');
	}
	$("#submit").click(function(){
		var username 			= $.trim($('#username').val()),
			truename 			= $.trim($('#truename').val()),
			password 			= $.trim($('#password').val()),
			passwordConfirm 	= $.trim($('#password-confirm').val()),
			phone  				= $.trim($('#phone').val()),
			phone2 				= /^1\d{10}$/;
		if(username === ""){
			show("用户名不能为空！");
		}else if(truename === ""){
			show("真实姓名不能为空！");
		}else if(password === ""){
			show("密码不能为空！");
		}else if(password.length < 6){
			show("密码长度不能少于6位");
		}else if(password !== passwordConfirm){
			show("两次输入的密码不一致");
		}else if(phone !== phone2){
			show("手机号格式不正确");
		}
		if(phone2.test(phone)){
			hide();
		}
	});
});



