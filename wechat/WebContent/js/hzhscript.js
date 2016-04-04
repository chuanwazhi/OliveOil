$(document).ready(function(){
	var div = $('.chooses');
	div.bind('click',function(){
		var text = $(this).find('span').text();
		if(	$(this).find('img').attr('src') == '/images/no.png'){
			$(this).find('img').attr('src','/images/yes.png');
			$(this).find('img').addClass('on');
			$(this).siblings().find('img').attr('src','/images/no.png');
			$(this).siblings().find('img').removeClass('on');
		};
	});
	/*时间日期控件*/
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	//opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.default = {
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		lang:'zh',
        startYear:currYear - 100, //开始年份
        endYear:currYear  //结束年份
	};

	$("#appDate").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));
	$("#rappDate").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));
  	var optDateTime = $.extend(opt['datetime'], opt['default']);
  	var optTime = $.extend(opt['time'], opt['default']);
    $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
    $("#appTime").mobiscroll(optTime).time(optTime);
});
//非身份证类型，显示”出生日期、性别“
function selectType(obj,optName){
	var object = obj.options[obj.selectedIndex].value;
	if(object != "0"){
		$("#"+optName).show();
	}else{
		$("#"+optName).hide();
	}
}function selectType2(obj,optName){
	var object = obj.options[obj.selectedIndex].value;
	if(object != "0"){
		$("#"+optName).show();
		$("#insurantBuyIdNo").attr('disabled',true);
		$("#insurantBuyIdNo").attr('placeholder','').val('');
		//alert($("#insurantBuyIdNo").prop('disabled'));
	}else{
		$("#"+optName).hide();
		$("#insurantBuyIdNo").attr('disabled',false);
		$("#insurantBuyIdNo").attr('placeholder','选择证件类型');
		//alert($("#insurantBuyIdNo").prop('disabled'));
	}
}

$(function(){
	
	/*
	$("#insureBuyPhone").bind('input propertychange',function(){
		var inputTextLen = $('#insureBuyPhone').val().length;
		
		if(inputTextLen > 11){
			$('#getCheckNum').show().css('background','#52c225');
			$('#getCheckNumNo').hide();
		}else{
			$('#getCheckNum').hide().css('background','#52c225');
			$('#getCheckNumNo').show();
		}
	});
	*/
})






