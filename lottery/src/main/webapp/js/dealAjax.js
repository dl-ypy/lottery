/**
 * 交易信息ajax
 */

function dealSubmit(href) {
	$.ajax({
		url:href,
		type:"get",
		data:$('#dealForm').serialize(),
		dataType:"json",
		success:function(info){
			var status = eval(info).status;
			var msg = eval(info).msg;
			if (status == 0) {
				var endWith = msg.substr(msg.length-3);
				if ('.do' == endWith) {
					dealSubmit(msg);
				} else {
					location.href=msg;
				}
			} else {
				alert(msg);
				if (msg == '交易成功!') {
					location.href='type/index';
				}
				if (msg == '余额不足，请充值！') {
					location.href='front/deal/recharge.jsp';
				}
			}
		}
	});
}

