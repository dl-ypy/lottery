//红球数量
var rball_nums = 0;
//蓝球数量
var bball_nums = 0;
//所选号码
rnumber = '';     //全局变量
bnumber = '';
/*
 *点击红球，变红，再点击变回原色
 */
$("#redBall dd p").click(function () {
	//    点击红球动画效果
	$(this).toggleClass("active1");    //切换类名
	var num = $(this).text();   //得到此值
	if ($(this).prop("className") == "active1") {   //判断其类名
		if (rball_nums < 6) {
			rnumber+=(num+',');   //加‘,’确保替换的时候是正确的
		} else {
			alert('红球个数已够!');
			$(this).toggleClass("active1");
		}
	} else {
		rnumber = rnumber.replace((num+','),'');
	}
	ballNums();
	
});

/*
 *点击蓝球，变蓝，再点击变回原色
 */
$("#blueBall li p").click(function () {
//    点击篮球动画效果
    $(this).toggleClass("active2");
    var num = $(this).text();   //得到此值
    if ($(this).prop("className") == "active2") {   //判断其类名
    	if (bball_nums < 1) {
    		bnumber=num;
		} else {
			alert('蓝球个数已够!');
			$(this).toggleClass("active2");
		}
    } else {
    	bnumber = '';
    }
    ballNums();
});
/*
 *点击蓝球的全选，选中所有蓝球
 */
var select_all = true;
$("#selectallBox").click(function () {
    if (select_all) {
        //点击全选，全选蓝球
        $("#blueBall li p").addClass('active2');
        $(this).text('全清');
        select_all = false;
    } else {
        //点击全清，零选蓝球
        $(".active2").removeClass('active2');
        $(this).text('全选');
        select_all = true;
    }
    ballNums();
});
/*
 //输出选择的红球和蓝球数量
 */
function ballNums() {
    //    得出选出的红球数量
    rball_nums = $('.active1').length;
//    console.log(rball_nums);
    //    得出选出的蓝球数量
    bball_nums = $('.active2').length;
    var ball_text = "您选了" + rball_nums + "个红球，" + bball_nums + "个蓝球。";
    $("#confirmBallnums").text(ball_text);
}

/*
 //点击机选红球
 */
$("#machineSelectedred").click(function () {
	rnumber = '';
    arr_red = new Array();
//    选择随机红球个数
    maxrnums = parseInt(6);
    redRand();
    ballNums();
    rnumber = arr_red.toString()+',';  //确保删除的时候正确
});
//取机选红球数量函数
function redRand() {
    $('#redBall dd p').removeClass('active1');
    //输出0～33之间的随机整数
    var rrandom_integer = parseInt(Math.random() * 33 + 1);
    if (rrandom_integer < 10) {
        rrandom_integer = '0' + rrandom_integer;
    }
    arr_red.push(rrandom_integer);
    $.unique(arr_red);
//      console.log(rrandom_integer);
    if (arr_red.length < maxrnums) {
        redRand();
    } else {
        $.each(arr_red, function (i, n) {
            $("#redBall p").each(function (k, v) {
                if ($(v).text() == n) {
                    $(this).addClass('active1');
                }
            });
        });
    }
}
//    清空红球
function emptyRedballs() {
    $('#redBall dd p').removeClass('active1');
    rnumber = '';
    ballNums();
}
/*
 //点击机选蓝球
 */
$("#machineSelectedblue").click(function () {
	bnumber = ''; 
    arr_blue = new Array();
//    取机选篮球个数
    maxbnums = parseInt($("#machineBBnums dt em").text());
    blueRan();
    ballNums();
    bnumber = arr_blue.toString();
});
function blueRan() {
    $('#blueBall p').removeClass('active2');
    obj = Math.floor(Math.random() * 16 + 1);
    if (obj < 10) {
        obj = '0' + obj;
    }
    arr_blue.push(obj);
    $.unique(arr_blue);
    if (arr_blue.length < maxbnums) {
        blueRan();
    } else {
        $(arr_blue).each(function (k, v) {
            $('#blueBall p').each(function () {
                if ($(this).text() == v) {
                    $(this).addClass('active2');
                }
            });
        });
    }
}
//    清空蓝球
function emptyBlueballs() {
    $('#blueBall p').removeClass('active2');
    bnumber = '';
    ballNums();
}
/*
 //确定选号
 */

$("#confirmBox").click(function () {
    if (rball_nums == 6 && bball_nums == 1) {
        red_text = $(".active1").text();
        redBallnums();
//        console.log(arr_r);
        blue_text = $(".active2").text();
        blueBallnums();
//        console.log(arr_b);
        $(".revise_area").remove();
        var str = "<li><span><b>单式 &nbsp;</b><b class=\"red_balls\">" + arr_r.sort().join(' ') + "</b><b>+</b> <b class=\"blue_balls\">" + arr_b.sort().join(' ') + "</b> [1注,2元]</span> <strong>修改</strong> <em>删除</em></li>";
        $("#endBallbox").prepend(str);
        /*$("#redBall dd p").removeClass('active1');
        $('#blueBall p').removeClass('active2');*/
        ballNums();
        var reg = new RegExp(",","g");//用正则表达式，g表示全部
        $('#buyno').val(rnumber.replace(reg, '')+bnumber);
        $('#jump').submit();
    } else {
        if (rball_nums != 6 && bball_nums != 1) {
            var error = "您选了（" + rball_nums + "红 +" + bball_nums + "蓝）,个数错误,请选6个红球,1个蓝球";
            alert(error);
        }
        if (rball_nums != 6 && bball_nums == 1) {
            var error = "您选了（" + rball_nums + "红 +" + bball_nums + "蓝）,红球个数错误，请选6个红球";
            alert(error);
        }
        if (rball_nums == 6 && bball_nums != 1) {
            var error = "您选了（" + rball_nums + "红 +" + bball_nums + "蓝）,蓝球个数错误,请选1个蓝球";
            alert(error);
        }
    }
});
function redBallnums() {
    arr_r = new Array();
    var i = 0;
    while (i <= red_text.length - 1) {
        var r = red_text.substr(i, 2);
        arr_r.push(r);
        i += 2;
    }
}
function blueBallnums() {
    arr_b = new Array();
    var n = 0;
    while (n <= blue_text.length - 1) {
        var b = blue_text.substr(n, 2);
        arr_b.push(b);
        n += 2;
    }
}
//清空选号
function emptyBox() {
    $("#redBall dd p").removeClass('active1');
    $('#blueBall p').removeClass('active2');
    ballNums();
}
