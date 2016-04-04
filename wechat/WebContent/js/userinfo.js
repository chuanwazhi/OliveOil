var insureBuySex = "";
var insurantBuySex = "";

function goclick(index1,index2){
    if(index1===0){
        if (index2 == 0) {
			$("#img00").attr('src', 'images/no.png');
			$("#img01").attr('src', 'images/no.png');
			$("#img00").addClass('on');
			$("#img01").removeClass('on');
			$("#insureBuySex").val("男");
		} else if (index2 == 1) {
			$("#img01").attr('src', 'images/no.png');
			$("#img00").attr('src', 'images/no.png');
			$("#img01").addClass('on');
			$("#img00").removeClass('on');
			$("#insureBuySex").val("女");
		}
		insureBuySex=$("#insureBuySex").val();
    }else if(index1===1){
        if (index2 == 0) {
			$("#img10").attr('src', 'images/no.png');
			$("#img11").attr('src', 'images/no.png');
			$("#img10").addClass('on');
			$("#img11").removeClass('on');
			$("#insurantBuySex").val("男");
		} else if (index2 == 1) {
			$("#img11").attr('src', 'images/no.png');
			$("#img10").attr('src', 'images/no.png');
			$("#img11").addClass('on');
			$("#img10").removeClass('on');
			$("#insurantBuySex").val("女");
		}
		insurantBuySex=$("#insurantBuySex").val();
    }
}

// 护照验证
function isHid(obj) {
  var that = {};
  that.result = "n";

  var controlObj = $.trim(obj);
  if (controlObj.length == 0 || controlObj == null || controlObj == undefined) {
    that.result = "n";
  }else{
    var reg = /^(G|E)\d{8}$/;
    if (!reg.test(controlObj)) {
      that.result = "x";
    } else {
      that.result = "t";
    }

  }
  return that;
}


// 军官证士兵证验证
function isJid(obj) {
  var that = {};
  that.result = "n";

  var controlObj = $.trim(obj);
  if (controlObj.length == 0 || controlObj == null || controlObj == undefined) {
    that.result = "n";
  }else{
    var reg = /(^([\u4E00-\u9FA5\uF900-\uFA2D]{2,5})?\d{6,10}$)|(^\d{6,12}$)/;
    if (!reg.test(controlObj)) {
      that.result = "x";
    } else {
      that.result = "t";
    }

  }
  return that;
}

//姓名验证
function isNotChineseOrNull(obj) {
  var that = {};
  that.result = "n";

  var controlObj = $.trim(obj);
  if (controlObj.length == 0 || controlObj == null || controlObj == undefined) {
    that.result = "n";
  }else if (controlObj.length <2 || controlObj.length > 50 ) {
    that.result = "l";
  }else{
    var reg = /^([A-z\u4E00-\u9FA5\uF900-\uFA2D]+[.|·]?)+([A-z\u4E00-\u9FA5\uF900-\uFA2D]+)$/;///^[A-z]+$|^[\u4E00-\u9FA5\uF900-\uFA2D]+$/;//^[\u4E00-\u9FA5\uF900-\uFA2D]*$  \u0391-\uFFE5
    var reg2 = /^[A-z]+$/;
    if (!reg.test(controlObj)) {
      that.result = "x";
    } else {
		if(reg2.test(controlObj)){
			if(controlObj.length <3){
				that.result = "l";
			}else{
				that.result = "t";
			}
		}else{
			that.result = "t";
		}
    }

  }
  return that;
}
//非空验证
function checkNull(){
	$("#m").html("");
	//投保人姓名
	var insureInfo = new Object;
	insureInfo.insureBuyName = $("#insureBuyName").val();						//投保人姓名
	insureInfo.insureBuyIdType = $("#insureBuyIdType").val();	//投保人证件类型
	insureInfo.insureBuyIdNo = $("#insureBuyIdNo").val();						//投保人证件编号
	insureInfo.insureBuySex = $("#insureBuySex").val();		                    //投保人性别
	insureInfo.insureBuyBirth = $("#appDate").val();							//投保人生日
	insureInfo.insureBuyPhone = $("#insureBuyPhone").val();						//投保人手机(允许为空)
	insureInfo.insureBuyEmail = $("#insureBuyEmail").val();						//投保人email(允许为空)
	insureInfo.insurantBuyName= $("#insurantBuyName").val();	                    //被保人姓名
	insureInfo.insurantBuyIdType = $("#insurantBuyIdType").val();	                        //被保人证件类型
	insureInfo.insurantBuyIdNo = $("#insurantBuyIdNo").val();                      //被保人证件编号
	insureInfo.insurantBuyBirth = $("#rappDate").val();                    //被保人生日
	insureInfo.insurantBuySex = $("#insurantBuySex").val();                        //被保人性别
	if(insureInfo.insureBuyName==null||insureInfo.insureBuyName==''){
		//alert("投保人姓名不允许为空!");
		$('#m').html("<font>投保人姓名不允许为空</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
		return;
	}else{
		if(isNotChineseOrNull(insureInfo.insureBuyName).result!="t"){
		$('#m').html("<font>正确填写投保人姓名</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
		return;
		}
	}
	if(insureInfo.insurantBuyName==null||insureInfo.insurantBuyName==''){
		//alert("被保人姓名不允许为空!");
		$('#m').html("<font>被保人姓名不允许为空</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
		return;
	}else{
		if(isNotChineseOrNull(insureInfo.insurantBuyName).result!="t"){
		$('#m').html("<font>正确填写被保人姓名</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
		return;
		}
	}
	if(insureInfo.insureBuyIdType==null||insureInfo.insureBuyIdType==''){
		//alert("投保人证件类型不允许为空!");
		$('#m').html("<font>投保人证件类型不允许为空</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
		return;
	}
	if(insureInfo.insurantBuyIdType==null||insureInfo.insurantBuyIdType==''){
		//alert("被保人证件类型不允许为空!");
		$('#m').html("<font>被保人证件类型不允许为空</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
		return;
	}
	if(insureInfo.insureBuyIdNo==null||insureInfo.insureBuyIdNo==''){
		//alert("投保人证件号码不允许为空!");
		$('#m').html("<font >投保人证件号码不允许为空</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
		return;
	}
	/*if(insureInfo.insureBuyIdType=='0'){
		//投保人证件类型为‘身份证’
		var card = new clsIDCard(insureInfo.insureBuyIdNo);
		if(card.IsValid()){
			insureInfo.insureBuyBirth = card.GetBirthDate();				//根据身份证号码获取生日
			insureInfo.insureBuySex = card.GetSex();						//根据身份证号码获取性别
			var age = jsGetAge(insureInfo.insureBuyBirth);
			age = parseInt(age);
			if(age <18){
				//alert("投保人年龄不得小于18周岁!");
				$('#m').html("<font>投保人年龄不得小于18周岁</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
				return;
			}
			if(insureInfo.insureBuySex=='1'){
				insureInfo.insureBuySex = 'M';
			}else{
				insureInfo.insureBuySex = 'F';
			}
	 	}else{
	 		//alert("请输入有效的身份证号码!");
	 		$('#m').html("<font>请输入有效的投保人身份证号码</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
	 		return;
	 	}
	}else if(insureInfo.insureBuyIdType!='0'){
		if(insureInfo.insureBuyIdType=='13'){
			if(isHid(insureInfo.insureBuyIdNo).result!='t'){
				$('#m').html("<font>请输入有效的投保人护照</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
				return false;
			}
		}else if(insureInfo.insureBuyIdType=='65'){
			if(isJid(insureInfo.insureBuyIdNo).result!='t'){
				$('#m').html("<font>请输入有效的投保人军官证</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
				return false;
			}
		}
		
		
		//证件类型不为‘身份证’的投保人
		if("男"==insureInfo.insureBuySex){
			insureInfo.insureBuySex ="M";
		}
		if("女"==insureInfo.insureBuySex){
			insureInfo.insureBuySex ="F";
		}
		if(insureInfo.insureBuySex==null||insureInfo.insureBuySex==''){
			//alert("投保人性别不允许为空!");
			$('#m').html("<font>投保人性别不允许为空</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
			return;
		}else if(insureInfo.insureBuyBirth==null||insureInfo.insureBuyBirth==''){
			//alert("投保人生日不允许为空!");
			$('#m').html("<font>投保人生日不允许为空</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
			return;
		}else{
			//根据birthday 去判断年龄是否超过17周岁
			var age = jsGetAge(insureInfo.insureBuyBirth);
			age = parseInt(age);
			if(age <18){
				//alert("投保人年龄必须大于18周岁!");
				$('#m').html("<font>投保人年龄必须大于18周岁</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
				return;
			}
		}
	}*/

	/*if(insureInfo.insurantBuyIdType=='0'){
		//被保人证件类型为‘身份证’
		var card = new clsIDCard(insureInfo.insurantBuyIdNo);
		if(card.IsValid()){

			insureInfo.insurantBuyBirth = card.GetBirthDate();				//根据身份证号码获取生日
			insureInfo.insurantBuySex = card.GetSex();						//根据身份证号码获取性别

			var age = jsGetAge(insureInfo.insurantBuyBirth);
			age = parseInt(age);
			if(age > 18 || age == 18){
				$('#m').html("<font>被保人年龄必须小于18周岁</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
				return;
			}
			if(insureInfo.insurantBuySex=='1'){
				insureInfo.insurantBuySex = 'M';
			}else{
				insureInfo.insurantBuySex = 'F';
			}
	 	}else{
	 		//alert("请输入有效的被保人身份证号码!");
	 		$('#m').html("<font>请输入有效的身份证号码</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
	 		return;
	 	}
	}else if(insureInfo.insurantBuyIdType!='0'){
		if(insureInfo.insurantBuyIdType=='13'){
			if(isHid(insureInfo.insureBuyIdNo).result!='t'){
				$('#m').html("<font>请输入有效的投保人护照</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
				return false;
			}
		}
		//证件类型不为‘身份证’的被保人s
		if("男"==insureInfo.insurantBuySex){
			insureInfo.insurantBuySex ="M";
		}
		if("女"==insureInfo.insurantBuySex){
			insureInfo.insurantBuySex ="F";
		}
		if(insureInfo.insurantBuySex==null||insureInfo.insurantBuySex==''){
			//alert("被保人性别不允许为空!");
			$('#m').html("<font>被保人性别不允许为空</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
			return;
		}else if(insureInfo.insurantBuyBirth==null||insureInfo.insurantBuyBirth==''){
			//alert("被保人生日不允许为空!");
			$('#m').html("<font>被保人生日不允许为空</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
			return;
		}else{
			//根据birthday 去判断年龄是否超过17周岁
			var age1 = jsGetAge(insureInfo.insurantBuyBirth);
			age1= parseInt(age1);
			if(age1 > 17){
				//alert("被保人年龄不得大于17周岁!");
				$('#m').html("<font>被保人年龄不得大于17周岁</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
				return;
			}
		}
	}*/
	/*if(null!=insureInfo.insurantBuyBirth || ""!=insureInfo.insurantBuyBirth){
	     if(parseInt(getBirthDayTime(insureInfo.insurantBuyBirth))<30){
	           $('#m').html("<font>被保人年龄不能小于30天</font>");
			   $('#m').fadeIn().delay(3000).fadeOut();
	 	       return;
	     }
			var age1 = jsGetAge(insureInfo.insureBuyBirth);
			age1 = parseInt(age1);
			var age2 = jsGetAge(insureInfo.insurantBuyBirth);
			age2 = parseInt(age2);
			if(age1-age2<18){
				$('#m').html("<font>投保人必须大于被保人18岁以上</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
				return;
			}
	}
	if(parseInt(jsGetAge(insureInfo.insurantBuyBirth))-parseInt(jsGetAge(insureInfo.insurantBuyBirth))){
				$('#m').html("<font>投被保人年龄差应大于18岁</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
				return;	}
	if(null==insureInfo.insureBuyPhone || ""==insureInfo.insureBuyPhone){
	 	//alert("手机号码不允许为空!");
	 	$('#m').html("<font>投保人手机号码不允许为空</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
	 	return;
 	}else{
    		var isp =new RegExp(/^0*(86)*(13|15|14|17|18)\d{9}$/).test(insureInfo.insureBuyPhone);
	 	if(!isp){
	 		//alert("请输入有效的手机号码!");
	 		$('#m').html("<font>请输入有效的手机号码</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
	 		return;
	 	}
 	}
 	var checkNum=$("#checkNum").val();
 	if(null==checkNum || ""==checkNum){
 		$('#m').html("<font>验证码不允许为空</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
	 	return;
 	}

	if(null!=insureInfo.insureBuyEmail && ""!=insureInfo.insureBuyEmail){
    	var isp =new RegExp(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/).test(insureInfo.insureBuyEmail);
	 	if(!isp){
	 		$('#m').html("<font>请输入有效的电子邮箱</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
	 		return;
	 	}
 	}*/
 	$("#nextStepGray").show();
 	$("#nextStep").hide();
	
	$("#nextStepGray").hide();
	$("#nextStep").show();
		     var WeChat = 1;
             var sign = 1;
             var insureBeginDate = "2015-10-12";
             var insureEndDate = "2016-10-12";
             var insureBuyName = insureInfo.insureBuyName;
             var insurantBuyName = insureInfo.insurantBuyName;
             var totallFee = "100.00";
             var wspTradeNo = "1234567890";

             window.sessionStorage.setItem("WeChat", ''+WeChat);
			 window.sessionStorage.setItem("sign", ''+JSON.stringify(sign));
		     window.sessionStorage.setItem("insureBeginDate", ''+insureBeginDate);
			 window.sessionStorage.setItem("insureEndDate", ''+insureEndDate);
			 window.sessionStorage.setItem("insureBuyName", ''+insureBuyName);
			 window.sessionStorage.setItem("insurantBuyName", ''+insurantBuyName);
			 window.sessionStorage.setItem("totallFee", ''+totallFee);
			 window.sessionStorage.setItem("wspTradeNo", ''+wspTradeNo);

			 window.location.href='affirm.html';
 	//发送ajax请求到/tkwechatinsure/CheckInsureServlet
	/*$.post("/tkwechatinsure/CheckInsureServlet", {
		"prodId" : "2-N09",
		"checkNum" : checkNum,
		"insureNum" : insureNum,
		"insureBuyName" : insureInfo.insureBuyName,
		"insureBuyIdType" : insureInfo.insureBuyIdType,
		"insureBuyIdNo" : insureInfo.insureBuyIdNo,
		"insureBuyPhone" : insureInfo.insureBuyPhone,
		"insureBuyBirth" : insureInfo.insureBuyBirth,
		"insureBuySex" : insureInfo.insureBuySex,
		"insureBuyEmail" : insureInfo.insureBuyEmail,
		"insurantBuyName" : insureInfo.insurantBuyName,
		"insurantBuyIdType" : insureInfo.insurantBuyIdType,
		"insurantBuyIdNo" : insureInfo.insurantBuyIdNo,
		"insurantBuyBirth" : insureInfo.insurantBuyBirth,
		"insurantBuySex" : insureInfo.insurantBuySex,
		"openId" : openId,
		"ssid" : ssid,
		"fromId" : fromId
		},function(data){
		$("#nextStepGray").hide();
		$("#nextStep").show();
			var status = data.status;
			var info = data.info;
			if(status == "true"){
			     var WeChat = data.WeChat;
                 var sign = data.sign;
                 var insureBeginDate = data.insureBeginDate;
                 var insureEndDate = data.insureEndDate;
                 var insureBuyName = data.insureBuyName;
                 var insurantBuyName = data.insurantBuyName;
                 var totallFee = data.totallFee;
                 var wspTradeNo = data.wspTradeNo;

                 window.sessionStorage.setItem("WeChat", ''+WeChat);
				 window.sessionStorage.setItem("sign", ''+JSON.stringify(sign));
			     window.sessionStorage.setItem("insureBeginDate", ''+insureBeginDate);
				 window.sessionStorage.setItem("insureEndDate", ''+insureEndDate);
				 window.sessionStorage.setItem("insureBuyName", ''+insureBuyName);
				 window.sessionStorage.setItem("insurantBuyName", ''+insurantBuyName);
				 window.sessionStorage.setItem("totallFee", ''+totallFee);
				 window.sessionStorage.setItem("wspTradeNo", ''+wspTradeNo);

				 window.location.href='/tkwechatinsure/jsp/affirm.html';
			}else if(!status || status =="false"){
				$('#m').html("<font>"+info+"</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
			}else{
				$('#m').html("<font>连接服务器失败</font>");
				$('#m').fadeIn().delay(3000).fadeOut();
			}

	}, "json");*/
}

//构造函数，变量为15位或者18位的身份证号码
function clsIDCard(CardNo) {
  this.Valid = false;
  this.ID15 = '';
  this.ID18 = '';
  this.Local = '';
  if (CardNo != null)
  this.SetCardNo(CardNo);
}

// 设置身份证号码，15位或者18位
clsIDCard.prototype.SetCardNo = function(CardNo) {
  this.ID15 = '';
  this.ID18 = '';
  this.Local = '';
  CardNo = CardNo.replace(" ", "");
  var strCardNo;
  if (CardNo.length == 18) {
    pattern = /^\d{17}(\d|x|X)$/;
    if (pattern.exec(CardNo) == null)
      return;
    strCardNo = CardNo.toUpperCase();
  } else {
    pattern = /^\d{15}$/;
    if (pattern.exec(CardNo) == null)
      return;
    strCardNo = CardNo.substr(0, 6) + '19' + CardNo.substr(6, 9)
    strCardNo += this.GetVCode(strCardNo);
  }
  this.Valid = this.CheckValid(strCardNo);
}
// 校验身份证有效性
clsIDCard.prototype.IsValid = function() {
  return this.Valid;
}
// 返回生日字符串，格式如下，1981-10-10
clsIDCard.prototype.GetBirthDate = function() {
  var BirthDate = '';
  if (this.Valid)
    BirthDate = this.GetBirthYear() + '-' + this.GetBirthMonth() + '-'
        + this.GetBirthDay();
  return BirthDate;
}
// 返回生日中的年，格式如下，1981
clsIDCard.prototype.GetBirthYear = function() {
  var BirthYear = '';
  if (this.Valid)
    BirthYear = this.ID18.substr(6, 4);
  return BirthYear;
}
// 返回生日中的月，格式如下，10
clsIDCard.prototype.GetBirthMonth = function() {
  var BirthMonth = '';
  if (this.Valid)
    BirthMonth = this.ID18.substr(10, 2);
  //if (BirthMonth.charAt(0) == '0')
   // BirthMonth = BirthMonth.charAt(1);
  return BirthMonth;
}
// 返回生日中的日，格式如下，10
clsIDCard.prototype.GetBirthDay = function() {
  var BirthDay = '';
  if (this.Valid)
    BirthDay = this.ID18.substr(12, 2);
  return BirthDay;
}

// 返回性别，1：男，0：女
clsIDCard.prototype.GetSex = function() {
  var Sex = '';
  if (this.Valid)
    Sex = this.ID18.charAt(16) % 2;
  return Sex;
}

// 返回15位身份证号码
clsIDCard.prototype.Get15 = function() {
  var ID15 = '';
  if (this.Valid)
    ID15 = this.ID15;
  return ID15;
}

// 返回18位身份证号码
clsIDCard.prototype.Get18 = function() {
  var ID18 = '';
  if (this.Valid)
    ID18 = this.ID18;
  return ID18;
}

// 返回所在省，例如：上海市、浙江省
clsIDCard.prototype.GetLocal = function() {
  var Local = '';
  if (this.Valid)
    Local = this.Local;
  return Local;
}

clsIDCard.prototype.GetVCode = function(CardNo17) {
  var Wi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
  var Ai = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
  var cardNoSum = 0;
  for (var i = 0; i < CardNo17.length; i++)
    cardNoSum += CardNo17.charAt(i) * Wi[i];
  var seq = cardNoSum % 11;
  return Ai[seq];
}

clsIDCard.prototype.CheckValid = function(CardNo18) {
  if (this.GetVCode(CardNo18.substr(0, 17)) != CardNo18.charAt(17))
    return false;
  if (!this.IsDate(CardNo18.substr(6, 8)))
    return false;
  var aCity = {
    11 : "北京",
    12 : "天津",
    13 : "河北",
    14 : "山西",
    15 : "内蒙古",
    21 : "辽宁",
    22 : "吉林",
    23 : "黑龙江 ",
    31 : "上海",
    32 : "江苏",
    33 : "浙江",
    34 : "安徽",
    35 : "福建",
    36 : "江西",
    37 : "山东",
    41 : "河南",
    42 : "湖北 ",
    43 : "湖南",
    44 : "广东",
    45 : "广西",
    46 : "海南",
    50 : "重庆",
    51 : "四川",
    52 : "贵州",
    53 : "云南",
    54 : "西藏 ",
    61 : "陕西",
    62 : "甘肃",
    63 : "青海",
    64 : "宁夏",
    65 : "新疆",
    71 : "台湾",
    81 : "香港",
    82 : "澳门",
    91 : "国外"
  };
  if (aCity[parseInt(CardNo18.substr(0, 2))] == null)
    return false;
  this.ID18 = CardNo18;
  this.ID15 = CardNo18.substr(0, 6) + CardNo18.substr(8, 9);
  this.Local = aCity[parseInt(CardNo18.substr(0, 2))];
  return true;
}

clsIDCard.prototype.IsDate = function(strDate) {
  var r = strDate.match(/^(\d{1,4})(\d{1,2})(\d{1,2})$/);
  if (r == null)
    return false;
  var d = new Date(r[1], r[2] - 1, r[3]);
  return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[2] && d
      .getDate() == r[3]);
}

/*
* 根据出生日期计算年龄
* @param strBirthday
* @returns
* */
function jsGetAge(strBirthday){
   var returnAge;
   var strBirthdayArr=strBirthday.split("-");
   var birthYear = strBirthdayArr[0];
   var birthMonth = strBirthdayArr[1];
   var birthDay = strBirthdayArr[2];

   d = new Date();
   d = d.valueOf();
   d = d + 24*60*60*1000;//保单第二天生效
   d = new Date(d);

   var nowYear = d.getFullYear();
   var nowMonth = d.getMonth() + 1;
   var nowDay = d.getDate();
   if(nowYear == birthYear)
   {
       returnAge = 0;//同年 则为0岁
   }
   else
   {
       var ageDiff = nowYear - birthYear ; //年之差
       if(ageDiff > 0)
       {
           if(nowMonth == birthMonth)
           {
               var dayDiff = nowDay - birthDay;//日之差
               if(dayDiff < 0)
               {
                   returnAge = ageDiff - 1;
               }
               else
               {
                   returnAge = ageDiff ;
               }
           }
           else
           {
               var monthDiff = nowMonth - birthMonth;//月之差
               if(monthDiff < 0)
               {
                   returnAge = ageDiff - 1;
               }
               else
               {
                   returnAge = ageDiff ;
               }
           }
       }
       else
       {
           returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
       }
   }
  return returnAge;
}

/**
 * 根据出生日期计算年龄(距今天数)
 * @param birthday
 * @returns
 */
function getBirthDayTime(birthday)
{
	var s1 = birthday;
	s1 = new Date(s1.replace(/-/g, "/"));
	s2 = new Date();
	var days = s2.getTime() - s1.getTime();
	var time = parseInt(days / (1000 * 60 * 60 * 24));
	return time;
}

//获取手机验证码
var InterValObj;
var count = 60;
var curCount;
function checkCheckNum(){
	$("#m").html("");
	//投保人手机号码
	var insureBuyPhone = $("#insureBuyPhone").val();
	if(null == insureBuyPhone || "" == insureBuyPhone){
	 	$("#m").html("<font  style=''>请输入有效的手机号码!</font>");
		$('#m').fadeIn().delay(3000).fadeOut();
	 	return false;
	}else{
	 	var isp = new RegExp(/^0*(86)*(13|15|14|17|18)\d{9}$/).test(insureBuyPhone);
	 	if(!isp){
	 		$("#m").html("<font>请输入有效的手机号码!</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
	 		return false;
	 	}
	}
	curCount = count;
	$("#getCheckNum").hide();
	$("#m").hide();
	$("#countTime").show();
	$("#countTime").html("正在发送(<font>"+curCount+"</font> 秒)");
	InterValObj=window.setInterval(SetRemainTime,1000);
	//发送ajax请求到/weishop/Message
	$.post("/tkwechatinsure/MessageServlet", {"insureBuyPhone" : insureBuyPhone}, function(data){
		if(data == "success"){
			$('#m').html("<font>手机验证码发送成功！</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
		} else if(data == "error"){
			$('#m').html("<font>手机验证码发送失败！</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
		}else if(data == "fail"){
			$('#m').html("<font>用户您好,请勿多次请求手机短信验证码,请稍后再试！</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
		}else{
			$('#m').html("<font>对不起，系统校验您的电话号码所属地为西藏，暂不能承保本产品，敬请谅解！</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
		}
	}, "text");

}

function SetRemainTime(){
	if(curCount==0){
		window.clearInterval(InterValObj);
		$("#getCheckNum").show();
		$("#countTime").hide();
	}else{
		curCount--;
		$("#getCheckNum").hide();
		$("#countTime").html("正在发送(<font>"+curCount+"</font> 秒)");
	}
}

function checkfirst() {
    var openId = window.sessionStorage.getItem("openId");
	$.post("/tkwechatinsure/QueryInsurePersonInfoServlet", {"openId":openId}, function (data) {
		var Code = data.Code;
		var Message = data.Message;
		if (Code == "0") {
			var insureBuyName = data.InsureName;
			$("#insureBuyName").val(insureBuyName);
			var insurantBuyName = data.InsurantName;
			$("#insurantBuyName").val(insurantBuyName);
			var insureBuyIdType = data.InsureIdType;
			var insureantIdType = data.InsureantIdType;
			var insureIdNo = data.InsureIdNo;
			var insureantIdNo = data.InsureantIdNo;
			var birthday = data.InsureBirthday;
			var insureBuyPhone = data.InsurePhoneYM;
			var insureBuyEmail = data.InsureEmail;
			var insurantName = data.InsurantName;
			var insureantIdNo = data.InsureantIdNo;

			var appDate = "";
			var sex = "";
			var age = "";
			if (insureBuyIdType == "0") {
		//投保人证件类型为‘身份证’
				var card = new clsIDCard(insureIdNo);
				if (card.IsValid()) {
					appDate = card.GetBirthDate();				//根据身份证号码获取生日
					sex = card.GetSex();						//根据身份证号码获取性别
					age = jsGetAge(appDate);
					age = parseInt(age);
					if (age < 18) {
				//alert("投保人年龄不得小于18周岁!");
						$("#m").html("<font>\u6295\u4fdd\u4eba\u5e74\u9f84\u4e0d\u5f97\u5c0f\u4e8e18\u5468\u5c81</font>");
						$('#m').fadeIn().delay(3000).fadeOut();
						return;
					}
					if (sex == "1") {
						sex = "M";
					} else {
						sex = "F";
					}
				} else {
	 		//alert("请输入有效的身份证号码!");
					$("#m").html("<font>\u8bf7\u8f93\u5165\u6709\u6548\u7684\u6295\u4fdd\u4eba\u8eab\u4efd\u8bc1\u53f7\u7801</font>");
					$('#m').fadeIn().delay(3000).fadeOut();
					return;
				}
			} else {
				if (insureBuyIdType != "0") {
					$("#hiddenField").show();
					if (insureBuyIdType == "00") {
						$("#insureBuyIdType").val("00");
						var val = $("#insureBuyIdType option[value='00']").html();
					    $(".choose ").eq(0).find(".ui-btn-text span").html(val);
//					    $(".choose ").eq(1).find(".ui-btn-text span").html(val);
					} else {
						if (insureBuyIdType == "65") {
							$("#insureBuyIdType").val("65");
							var val = $("#insureBuyIdType option[value='65']").html();
							$(".choose ").eq(0).find(".ui-btn-text span").html(val);
//						    $(".choose ").eq(1).find(".ui-btn-text span").html(val);
						} else {
							if (insureBuyIdType == "13") {
								$("#insureBuyIdType").val("13");
								var val = $("#insureBuyIdType option[value='13']").html();
								//$(".choose .ui-btn-text span").html(val);
								$(".choose ").eq(0).find(".ui-btn-text span").html(val);
							    //$(".choose ").eq(1).find(".ui-btn-text span").html(val);
							} else {
								$("#insureBuyIdType").val("0");
								var val = $("#insureBuyIdType option[value='0']").html();
								$(".choose ").eq(0).find(".ui-btn-text span").html(val);
							   //$(".choose ").eq(1).find(".ui-btn-text span").html(val);
							}
						}
					}
					sex = data.InsureSex;
					if (sex == null || sex == "") {
						$("#m").html("<font>\u6295\u4fdd\u4eba\u6027\u522b\u4e0d\u5141\u8bb8\u4e3a\u7a7a</font>");
						$('#m').fadeIn().delay(3000).fadeOut();
						return;
					} else {
						if (sex == "M") {
							$("#img00").attr('src', 'images/yes.png');
							$("#img00").addClass('on');
							$("#img01").attr('src', 'images/no.png');
							$("#img01").removeClass('on');
							$("#insureBuySex").val("男");
						} else {
							if (sex == "F") {
								$("#img01").attr('src', 'images/yes.png');
								$("#img01").addClass('on');
								$("#img00").attr('src', 'images/no.png');
								$("#img00").removeClass('on');
								$("#insureBuySex").val("女");
							} else {
								if (appDate == null || appDate == "") {
									$("#m").html("<font>\u6295\u4fdd\u4eba\u751f\u65e5\u4e0d\u5141\u8bb8\u4e3a\u7a7a</font>");
									$('#m').fadeIn().delay(3000).fadeOut();
									return;
								} else {
									age = jsGetAge(appDate);
									age = parseInt(age);
									if (age < 18) {
										$("#m").html("<font>\u88ab\u4fdd\u4eba\u5e74\u9f84\u4e0d\u5f97\u5927\u4e8e17\u5468\u5c81</font>");
										$('#m').fadeIn().delay(3000).fadeOut();
										return;
									}
								}
							}
						}
					}
				}
			}
			$("#insureBuyIdNo").val(insureIdNo);
			$("#appDate").val(birthday);
			//$("#insureBuyPhone").val(insureBuyPhone);
			$("#insureBuyEmail").val(insureBuyEmail);
			$("#insurantBuyName").val(insurantName);
			$("#insurantBuyIdNo").val(insureantIdNo);
			var rappDate = data.InsureantBirthday;
			var insurantBuyIdType = data.InsureantIdType;
			$("#rappDate").val(rappDate);
			var appDate1 = "";
			var sex1 = "";
			var age1 = "";
			if (insurantBuyIdType == "0") {
				var card = new clsIDCard(insureantIdNo);
				if (card.IsValid()) {
					appDate1 = card.GetBirthDate();				//根据身份证号码获取生日
					sex1 = card.GetSex();						//根据身份证号码获取性别
					age1 = jsGetAge(appDate);
					age1 = parseInt(age);
					if (age1 < 18) {
						$("#m").html("<font>\u88ab\u4fdd\u4eba\u5e74\u9f84\u4e0d\u5f97\u5c0f\u4e8e18\u5468\u5c81</font>");
						$('#m').fadeIn().delay(3000).fadeOut();
						return;
					}
					if (sex1 == "1") {
						sex1 = "M";
					} else {
						sex1 = "F";
					}
					$("#insurantBuyIdType").val("0");
					var val_bbr = $("#insurantBuyIdType option[value='0']").html();
				    $(".choose ").eq(1).find(".ui-btn-text span").html(val_bbr);

				} else {
					$("#m").html("<font>\u8bf7\u8f93\u5165\u6709\u6548\u7684\u88ab\u4fdd\u4eba\u8eab\u4efd\u8bc1\u53f7\u7801</font>");
					$('#m').fadeIn().delay(3000).fadeOut();
					return;
				}
			} else if (insurantBuyIdType != "0") {

					$("#insurantBuyIdType").val("00");
					var val_bbr = $("#insurantBuyIdType option[value='00']").html();
				    $(".choose ").eq(1).find(".ui-btn-text span").html(val_bbr);
				    $("#insurantBuyIdNo").attr('disabled',true);
				    $("#insurantBuyIdNo").attr('placeholder','').val('');

					$("#rhiddenField").show();

					sex1 = data.InsureantSex;
					if (sex1 == null || sex1 == "") {
						$("#m").html("<font>\u88ab\u4fdd\u4eba\u6027\u522b\u4e0d\u5141\u8bb8\u4e3a\u7a7a</font>");
						$('#m').fadeIn().delay(3000).fadeOut();
						return;
					} else {
						if (sex1 == "M") {
							$("#img10").attr('src', 'images/yes.png');
							$("#img10").addClass('on');
							$("#img11").attr('src', 'images/no.png');
							$("#img11").removeClass('on');
							$("#insurantBuySex").val("男");
						} else {
							if (sex1 == "F") {
								$("#img11").attr('src', 'images/yes.png');
								$("#img11").addClass('on');
								$("#img10").attr('src', 'images/no.png');
								$("#img10").removeClass('on');
								$("#insurantBuySex").val("女");
							} else {
								if (appDate1 == null || appDate1 == "") {
									$("#m").html("<font>\u88ab\u4fdd\u4eba\u751f\u65e5\u4e0d\u5141\u8bb8\u4e3a\u7a7a</font>");
									$('#m').fadeIn().delay(3000).fadeOut();
									return;
								} else {
									age1 = jsGetAge(appDate1);
									age1 = parseInt(age1);
									if (age1 < 18) {
										$("#m").html("<font>\u88ab\u4fdd\u4eba\u5e74\u9f84\u4e0d\u5f97\u5927\u4e8e17\u5468\u5c81</font>");
										$('#m').fadeIn().delay(3000).fadeOut();
										return;
									}
								}
							}
						}
					}
				}
		} else if(Code == "1"){

		}else if (Code == "2") {
			$("#m").html("<font>" + Message + "</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
		} else {
			$("#m").html("<font>\u8fde\u63a5\u670d\u52a1\u5668\u5931\u8d25</font>");
			$('#m').fadeIn().delay(3000).fadeOut();
		}
	}, "json");
}