/**
 * 彩票ajax
 */

function lotterySubmit(href) {
	$.ajax({
		url:href,
		type:"get",
		data:$('#lotteryForm').serialize(),
		dataType:"json",
		success:function(info){
			var status = eval(info).status;
			var msg = eval(info).msg;
			if (status == 0) {
				var endWith = msg.substr(msg.length-3);
				if ('.do' == endWith) {
					lotterySubmit(msg);
				} else {
					location.href=msg;
				}
			} else {
				alert(msg);
			}
		}
	});
}

