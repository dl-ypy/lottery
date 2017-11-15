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
			password 			= $.trim($('#password').val());
		if(username === ""){
			show("用户名不能为空！");
		}else if(password === ""){
			show("密码不能为空！");
		}
	});
	
	if($('#isPhone').prop('checked')) {
		$("#user").attr("name","phone");
	}
});

function isPhone( ) {
	if($('#isPhone').prop('checked')) {
		$("#user").attr("name","phone");
	} else {
		$("#user").attr("name","username");
	}
}



