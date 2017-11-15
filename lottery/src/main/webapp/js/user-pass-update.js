/**
 * 修改密码
 */

/*原密码*/
function ruleOldPwdKeyUp(val) {
	var old = $('#oldPassword').val();
	if (old == val) {
		$('#old_span').html("<font color='green' size='2'>密码正确</font>");
		return true;
	} else {
		$('#old_span').html("<font color='red' size='2'>密码不正确</font>");
		return false;
	}
}

/*确认密码填写时的规则*/
function ruleAffirmPWDKeyUp() {
	var password = $('#password-new').val();
	var apassword = $('#password-confirm').val();
	if (password === apassword) {
		$('#apwd_span').html("<font color='green' size='2'>密码正确</font>");
		return true;
	} else {
		$('#apwd_span').html("<font color='red' size='2'>密码不正确</font>");
		return false;
	}
}

/*提交按钮*/
function ruleSubmit(val) {
	return ruleOldPwdKeyUp(val)&&ruleAffirmPWDKeyUp();
}