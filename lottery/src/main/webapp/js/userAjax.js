/**
 * 用户ajax
 */
function submit(href) {
	var username = $('#username').val();
	var password = $('#password').val();
	var userid = $('#userid').val();
	var truename = $('#truename').val();
	var phone = $('#phone').val();
	var anumber = $('#anumber').val();
	var apassword = $('#apassword').val();
	var oldPassword = $('#oldPassword').val();
	var newPassword = $('#newPassword').val();
	$.ajax({
		url:href,
		type:"get",
		data:{
			username:username,
			password:password,
			userid:userid,
			truename:truename,
			phone:phone,
			anumber:anumber,
			apassword:apassword,
			oldPassword:oldPassword,
			newPassword:newPassword
		},
		dataType:"json",
		success:function(info){
			var status = eval(info).status;
			var msg = eval(info).msg;
			if (status == 0) {
				var endWith = msg.substr(msg.length-3);
				if ('.do' == endWith) {
					submit(msg);
				} else {
					location.href=msg;
				}
			} else {
				alert(msg);
			}
		}
	});
}

