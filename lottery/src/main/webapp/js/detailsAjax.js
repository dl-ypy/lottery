/**
 * 彩种信息ajax
 */
function detailsSubmit(href) {
	$.ajax({
		url:href,
		type:"get",
		data:$('#detailsForm').serialize(),
		dataType:"json",
		success:function(info){
			var status = eval(info).status;
			var msg = eval(info).msg;
			if (status == 0) {
				var endWith = msg.substr(msg.length-3);
				if ('.do' == endWith) {
					detailsSubmit(msg);
				} else {
					location.href=msg;
				}
			} else {
				alert(msg);
			}
		}
	});
}

