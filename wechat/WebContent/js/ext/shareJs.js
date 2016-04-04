var prePath = getRootPath();
var imgUrl = 'http://mapps.taikanglife.com/panorama/zh_CN/tkwechatinsure/shareLogoNew.png';
var descContent_friend = '18种少儿高发重大疾病，外加各种磕磕碰碰、招猫逗狗引致的意外伤害医疗，全都保！不到10元，给孩子十分的关爱！';
var shareTitle_friend = '世界那么大，你还那么小。宝贝，我想看你健康成长';

var shareTitle_timeLine = '世界那么大，你还那么小。宝贝，我想看你健康成长！';

var appid = 'wx2f763d09aa9ca523';//生产：wx2f763d09aa9ca523  测试：wxdbbe2c84a6e68304

var urlinfo = window.location.href;
var params = getParam(urlinfo);
var ssid = params['ssid'];
var fromId = params['fromId'];
var redirectUrl = "http://mapps.taikanglife.com/tkwechatinsure/jsp/loveCard.html";
var param = "ssid="+ssid+"&fromId="+fromId+"&redirectUrl="+redirectUrl;
//var oauth = "http://mapps.taikanglife.com/PubOauth/OauthCodeServlet?redirectUrl=";
var oauth = prePath+"/tkwechatinsure/QueryUserOpenIdServlet?"+param;
//var shareUrl = oauth+urls;
shareUrl = oauth;

/**
 * 获取页面url参数
 * @param url
 * @return
 */
function getParam(url){  
    var myArr = new Array();  
    var params = {};  
    myArr = url.split("?");  
    if(myArr.length<=1) 
    	return params;  
    var myP = myArr[1].split("&")  
    for(i=0;i<myP.length;i++){  
        var myO = myP[i].split("=")  
        params[myO[0]] = myO[1];  
    }  
    return params;  
}

/*$(function(){
	try{
		$.post("http://mapps.taikanglife.com/weishop/simpleTransControl", {
				"stcParam" : "jsApi",
				"signUrl" : urlinfo
				}, function(data){
					var retCode = data.RetCode;
					if("SUCCESS"==retCode){
						var signObj=data.RetData;
						
						wx.config({
						    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						    appId: 'wx2f763d09aa9ca523', // 必填，公众号的唯一标识
						    timestamp: signObj.timestamp, // 必填，生成签名的时间戳
						    nonceStr: ''+(signObj.nonceStr)+'', // 必填，生成签名的随机串
						    signature: ''+(signObj.signature)+'',// 必填，签名，见附录1
						    jsApiList: [
								'checkJsApi',
						        'onMenuShareTimeline',
						        'onMenuShareAppMessage',
						        'hideMenuItems',
						        'hideAllNonBaseMenuItem',
						        'getNetworkType',
						        'getLocation',
						        'hideOptionMenu',
						        'showOptionMenu',
						        'closeWindow'
						        ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
						});
					}else{
						//获取签名失败
						alert('获取签名失败');
					}
				}, "json");
	}catch (e) {
			//alert('touchSatrtFunc：' + e.message);
	}
});*/

//获取地址
function getRootPath(){
	var strFullPath = window.document.location.href;						//当前地址
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0,pos);								//上下文根之前
	var postPath = strPath.substring(0,strPath.substr(1).indexOf('/')+1);	//上下文根
	
	return prePath;
}

wx.ready(function () {
	wx.showOptionMenu();
  
  // 2. 分享接口
    wx.onMenuShareAppMessage({
      title: shareTitle_friend,
      desc: descContent_friend,
      link: shareUrl,
      imgUrl: imgUrl,
      trigger: function (res) {
        //alert('用户点击发送给朋友');
      },
      success: function (res) {
        //alert('已分享');
      },
      cancel: function (res) {
        //alert('已取消');
      },
      fail: function (res) {
        //alert(JSON.stringify(res));
      }
    });

    wx.onMenuShareTimeline({
      title: shareTitle_timeLine,
      link: shareUrl,
      imgUrl: imgUrl,
      trigger: function (res) {
        //alert('用户点击分享到朋友圈');
      },
      success: function (res) {
        //alert('已分享');
      },
      cancel: function (res) {
        //alert('已取消');
      },
      fail: function (res) {
        //alert(JSON.stringify(res));
      }
    });
});

wx.error(function (res) {
  //alert(res.errMsg);
});