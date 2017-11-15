/*滚动条*/
$(document).ready(function(e) {
    var unslider04 = $('#b04').unslider({
		dots: true
	}),
	data04 = unslider04.data('unslider');	
	$('.unslider-arrow04').click(function() {
        var fn = this.className.split(' ')[1];
        data04[fn]();
   });
});

/*字体上滚*/
 function AutoScroll(obj) {
    $(obj).find("ul:first").animate({
        marginTop: "-25px"
    }, 500, function () {
        $(this).css({ marginTop: "0px" }).find("li:first").appendTo(this);
    });
}
$(document).ready(function () {
    var myar = setInterval('AutoScroll("#scrollDiv")', 1000);
    //当鼠标放上去的时候，滚动停止，鼠标离开的时候滚动开始
    $("#scrollDiv").hover(
    	function () { 
    		clearInterval(myar); 
    	}, function () { 
    		myar = setInterval('AutoScroll("#scrollDiv")', 1000) 
    });
});
