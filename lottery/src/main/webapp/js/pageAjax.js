/**
 * 分页ajax
 */

function pageSubmit(href) {
	$.ajax({
		url:href,
		type:"get",
		data:$('#pageForm').serialize(),
		dataType:"json",
		success:function(info){
			var status = eval(info).status;
			var msg = eval(info).msg;
			if (status == 0) {
				var endWith = msg.substr(msg.length-3);
				if ('.do' == endWith) {
					buySubmit(msg);
				} else {
					location.href=msg;
				}
			} else {
				alert(msg);
			}
		}
	});
}