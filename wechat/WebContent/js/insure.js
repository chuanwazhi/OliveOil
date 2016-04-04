$(document).ready(function(){
	var je_val = $('.je span').text();
	check_add_jian('figure','add','del');
	$("#add").click(function(){ add('figure','add','del'); });
	$("#del").click(function(){ jian('figure','add','del'); });
	
	/*Input值在改变时  金额同时改变*/
	$('#figure').bind('input',function(){
		var val = $(this).val();
		if(parseInt(val)!= val){
		    val = parseInt(val);
		}
		if(val>40){
			alert('最多40份');
			val =40;
		};
		if(val<=1){
		    val =1;
		}
		if(val=='-'){
			val =1;
		}
	    $(this).val(val);
		window.sessionStorage.setItem("insureNum", ''+val);
		$('.je span').text(Math.round(val*9.99*100)/100);
		$("#loveCardLi1 #span1").html(val*5000);
		$("#loveCardLi2 #span2").html(val*1000)
	});
	
	$("#figure").blur(function(){
	    var val1 = $("#figure").val();
	    if(!val1){  
           val1 = 1;
        }
        $("#figure").val(val1);
		window.sessionStorage.setItem("insureNum", ''+val1);
		$('.je span').text(Math.round(val1*9.99*100)/100);
		$("#loveCardLi1 #span1").html(val1*5000);
		$("#loveCardLi2 #span2").html(val1*1000);
	});
	$("#loveCardLi1").click(function(){
		window.location='prd_children.html';
	});
	$("#loveCardLi2").click(function(){
	    window.location='prd_medical.html';
	});
	$("#loveCardRule").click(function(){
		var $lCRuleChange = $("#loveCardRule .loveCtitle");
		var $lCRuleChangeKai = $("#loveCardRule .loveCconWrap");
		if($lCRuleChange.is(':hidden')){
			$lCRuleChange.css('display','block');
			$('.copys').css('display','block');
			$lCRuleChangeKai.css('display','none');
		}else{
			$lCRuleChangeKai.css('display','block');
			$('.copys').css('display','none');
			$lCRuleChange.css('display','none');
		}
	});
});




/*初始化判断*/
function check_add_jian(val_id,jia_id,jian_id){
	var m=$("#"+val_id).val();
	var nums=parseInt(m);
	window.sessionStorage.setItem("insureNum", ''+nums);
	$('.je span').text(Math.round(nums*9.99*100)/100);
	if(m == 10){
		$('.je span').text(99.9);
	}
	$("#loveCardLi1 #span1").html(nums*5000);
	$("#loveCardLi2 #span2").html(nums*1000);
}
/*加函数*/
function add(val_id,jia_id,jian_id){
	var n=$("#"+val_id).val();
	var num=parseInt(n)+1;
	$('.je span').text(Math.round(num*9.99*100)/100);
	if(num==40){
		$('.je span').text(Math.round(40*9.99*100)/100);
	}
	if(num>=41){
		$("#"+val_id).val(40);
		num = 40;
		$('.je span').text(Math.round(40*9.99*100)/100);
		alert('最多可以购买40份');
	}
	 $("#"+val_id).val(num);
	 $("#loveCardLi1 #span1").html(num*5000);
	 $("#loveCardLi2 #span2").html(num*1000);
	 window.sessionStorage.setItem("insureNum", ''+num);
}
/*减函数*/
function jian(val_id,jia_id,jian_id){
	var n=$("#"+val_id).val();
	var num=parseInt(n)-1;
	$('.je span').text(Math.round(num*9.99*100)/100);
	$("#"+val_id).val(num);
	if(num<=1){
	    num = 1;
		$("#"+val_id).val(1);
		$('.je span').text(9.99);
	}
	window.sessionStorage.setItem("insureNum", ''+num);
	$("#loveCardLi1 #span1").html(num*5000);
	$("#loveCardLi2 #span2").html(num*1000);
}

