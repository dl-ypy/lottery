/**
 * 银行信息ajax
 */
function bankSubmit(href) {
	$.ajax({
		url:href,
		type:"get",
		data:$('#bankForm').serialize(),
		dataType:"json",
		success:function(info){
			var status = eval(info).status;
			var msg = eval(info).msg;
			if (status == 0) {
				var endWith = msg.substr(msg.length-3);
				if ('.do' == endWith) {
					bankSubmit(msg);
				} else {
					location.href=msg;
				}
			} else {
				alert(msg);
			}
		}
	});
}

