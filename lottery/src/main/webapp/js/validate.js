/**
 * 验证信息，并弹出提示框
 */

function jump(value, href) {
	if (1 == value) {
		alert('请先登录！');
	} else {
		location.href=href;
	}
}

