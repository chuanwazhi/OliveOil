/*Input交换 start*/
/*用法：*/
//-----------------------------------
// HMLT code
//-----------------------------------
// <input class="name_input" value="Your name" />
//-----------------------------------
// JS code
//-----------------------------------
// if ($('.name_input').length > 0) {
//     $('.name_input').inputSwapFn({
//         obj: $('.name_input'),     //input项，触发方法的class, id, name
//         color_light: '#999',       //失去焦点文字颜色
//         color_dark: '#666'         //获得焦点文字颜色
//     });
// }
;
(function($) {
    $.fn.inputSwapFn = function(options) {
        var ip = $.extend({}, $.fn.inputSwapFn.defaults, options);
        swapValues = [];
        this.each(function(i) {
            ip.obj.css('color', ip.color_light);
            swapValues[i] = $(this).val();
            $(this).focus(function() {
                if ($(this).val() == swapValues[i]) {
                    //$(this).val("");
                    $(this).css('color', '#666');
                }
            }).blur(function() {
                if ($.trim($(this).val()) == "") {
                    //$(this).val(swapValues[i]);
                    $(this).css('color', '#999');
                }
            });
        });
    }
    $.fn.inputSwapFn.defaults = {
        obj: null, //input项，触发方法的class, id, name
        color_light: null, //失去焦点文字颜色
        color_dark: null //获得焦点文字颜色
    }
})(jQuery);
/*Input交换 end*/


/*模拟数字键盘 start*/
/*用法：*/
//-----------------------------------
// JS code
//-----------------------------------
// 触发方法
// if ($('.kb').length > 0) {
//     $('.kb').keyboardFn({
//         pare_obj: $('.kb'),                      //选择器的父级，可以和本身相同，触发方法的class, id, name
//         obj: $('.kb'),                           //标签项，触发方法的class, id, name
//         input: false,                            //选择器类别是否是input
//         callback: comfirm,                       //回调方法
//         remove_origin: false,                    //删除原有数值
//         addon: ""                                //附加字符
//         placeholder: '',                         //默认字符
//         scrollUp: false,                         //向上滑动
//         count: false,                            //字符数上限
//     });
// }
// 回调方法
// function comfirm(n) {
//  alert("value/text：" + n);
// }
;
(function($) {
    $.fn.keyboardFn = function(options) {
        var kb = $.extend({}, $.fn.keyboardFn.defaults, options);
        var idx_key;
        var str_txt;
        var delFlag=true;
        return this.each(function() {
            kb.obj.text(kb.addon + '0');
            // 显示键盘
            kb.pare_obj.tap(function() {
                if ($('.keyboard').hasClass('hide')) {
                    $('.keyboard').addClass('show').removeClass('hide');
                    if (kb.scrollUp) {
                        if ($('.space').length > 0) $('.space').height(197);
                        $("html, body").animate({
                            scrollTop: $("html, body").position().top + 297
                        }, 400);
                    }
                };
                delFlag=true;
            })
            // 数字键
            $('.keyboard .keypadNum').tap(function() {
                idx_key = $('.keyboard .keypadNum').index(this);
                idx_key++;
                if (idx_key == 10) idx_key = 0;
                if ((kb.obj.text().length - kb.addon.length) < kb.count) {
                    str_txt = kb.obj.text() + idx_key;
                    if (str_txt.substr(2, 1) == '0') {
                        str_txt = str_txt.replace(str_txt.substr(2, 1), '');
                    };
                    kb.obj.text(str_txt);
                }
                callback();
            })
            // 删除键
            $('.keyboard .del').tap(function(event) {
            	if(!delFlag){
            		return false;
            	}
            	event.stopPropagation();             	
                if (kb.obj.text().substr(2, 1) != 0) {
                    str_txt = kb.obj.text().slice(0, -1);
                    if (str_txt.length < 3) str_txt = kb.addon + "0";
                    kb.obj.text(str_txt);
                    callback();
                }
            })
            // 隐藏键盘
            $('.keyboard .ok, .keyboard .hideKb').tap(function(event) {
            	 delFlag=false;
            	event.stopPropagation(); 
                $('.keyboard').addClass('hide').removeClass('show');
                if (kb.scrollUp) {
                    if ($('.space').length > 0) $('.space').height(0);
                }
                callback();
            })

            function callback() {
                kb.callback(kb.obj.text().replace(kb.addon, ''));
            }
        });
    }
    $.fn.keyboardFn.defaults = {
        pare_obj: null, //选择器的父级，可以和本身相同，触发方法的class, id, name
        obj: null, //选择器，触发方法的class, id, name
        callback: null, //回调方法
        remove_origin: false, //删除原有数值
        addon: '￥ ', //附加字符
        scrollUp: false, //向上滑动
        count: false, //字符数上限
    }
})(jQuery);
/*模拟数字键盘 end*/

/*Accordion menu（手风琴菜单） start*/
/*用法：*/
//-----------------------------------
// HMLT code
//-----------------------------------
// <div class="guide_nav">
//     <div class="guide_nav_head"><a href="javascript:;">H0</a></div>
//     <div class="guide_nav_box">11</div>
//     <div class="guide_nav_head"><a href="javascript:;">H1</a></div>
//     <div class="guide_nav_box">22</div>
//     <div class="guide_nav_head"><a href="javascript:;">H2</a></div>
//     <div class="guide_nav_box">33</div>
//     <div class="guide_nav_head"><a href="javascript:;">H3</a></div>
//     <div class="guide_nav_box">44</div>
// </div>
//-----------------------------------
// JS code
//-----------------------------------
// if ($('.guide_nav').length > 0) {
//     $('.guide_nav').accordionMenuFn({
//         pare: $('.guide_nav .guide_nav_head'),            //标签项，触发方法的class, id, name
//         pare_actClass: 'guide_nav_head_actived',          //tab激活状态的类名
//         child: $('.guide_nav .guide_nav_box'),            //容器项，容器元素的class, id, name  
//         idx_default: "0",                                 //默认激活的项
//         trgger: "click",                                  //触发事件 ：click, hover
//         slide: true                                       //slide动画
//     });
// };
//-----------------------------------
// CSS code
//-----------------------------------
// .guide_nav_head_actived {
//     color: #666;
// }

;
(function($) {
    $.fn.accordionMenuFn = function(options) {
        var amObj = $.extend({}, $.fn.accordionMenuFn.defaults, options);
        var i = 0;
        return this.each(function() {
            amFnGo(amObj.idx_default);

            /*鼠标移上触发*/
            if (amObj.trgger == "hover") {
                amObj.pare.hover(function() {
                    i = amObj.pare.index(this);
                    amFnGo(i);
                });
            }

            /*鼠标点击触发*/
            if (amObj.trgger == "click") {
                amObj.pare.click(function() {
                    i = amObj.pare.index(this);
                    amFnGo(i);
                });
            }

            /*处理事件*/

            function amFnGo(n) {
                if (amObj.child.eq(n).is(":hidden")) {
                    if (amObj.slide) amObj.child.eq(n).slideDown(300)
                    else amObj.child.eq(n).show();
                    amObj.pare.eq(n).addClass(amObj.pare_actClass).removeClass(amObj.pare_disactClass);
                } else {
                    if (amObj.slide) amObj.child.eq(n).slideUp(300)
                    else amObj.child.eq(n).hide();
                    amObj.pare.eq(n).addClass(amObj.pare_disactClass).removeClass(amObj.pare_actClass);
                }
            }
        });
    }
    $.fn.accordionMenuFn.defaults = {
        pare: null, //标签项，触发方法的class, id, name
        pare_actClass: null, //menu激活状态的类名
        pare_disactClass: null, //menu常规状态的类名
        child: null, //容器项，容器元素的class, id, name
        idx_default: "0", //默认激活的项
        trgger: "hover", //触发事件 ：click, hover
        slide: true //slide动画
    }
})(jQuery);
/*Accordion menu（手风琴菜单） end*/


/*对话框（替换alert） start*/
/*用法：*/
//-----------------------------------
// JS code
//-----------------------------------
// 触发方法
// $(this).alertFn({
//    title: 'xx',       //弹窗标题
//    content: 'xx',     // 弹窗内容
//    buttonStr: 'xx',     // 按钮字符串，默认为“确认”
//    callback: comfirm,       //回调方法（可以带参数）
//});
// 回调方法
// function comfirm(n) {
//  alert("value/text：" + n);
// }
;
(function($) {
    $.fn.alertFn = function(options) {
        var at = $.extend({}, $.fn.alertFn.defaults, options);
        return this.each(function() {
            // 构建弹窗
            if ($('.popbox').length == 0) {
                $('body').append('<div class="graybg none fx">&nbsp;</div><div class="popbox none fx"><div class="popbox-in"><div class="top-pop"><strong id="cur_msg_title">'+at.title+'</strong></div><div class="mid-pop"><div class="pop_success"><h4 class="success" id="cur_msg_content">'+at.content+'</h4></div><div class="okbtn"><a class="common_btn okbtn_refine" id="cur_msg_buttonStr">'+at.buttonStr+'</a></div></div></div></div>');
            }else{
            	$("#cur_msg_title").html(at.title);
            	$("#cur_msg_content").html(at.content);
            	$("#cur_msg_buttonStr").html(at.buttonStr);
            };
            $('.graybg, .popbox').fadeIn(300);
            // 重定位
            resizeEvents();
            $(window).resize(function() {
                resizeEvents();
            });
            function resizeEvents() {
                // 弹窗
                if ($('.popbox').length > 0) {
                    $('.popbox').css('marginLeft', ($(document).width() - $('.popbox').width()) * .5 + 'px');
                    $('.popbox').css('marginTop', -$('.popbox').height() * .5 + 'px');
                };
                if ($('.popbox .tips').length > 0) {
                    $('.popbox .tips').css('marginLeft', ($('.popbox').width() - $('.popbox').width() * .93) * .5 + 'px');
                };
            }
            // 按钮事件
            $('.okbtn').click(function(event) {
                event.stopImmediatePropagation();
                $('.graybg, .popbox').fadeOut(300);
                at.callback(''); // n为参数
            })
        });
    }
    $.fn.alertFn.defaults = {
        title: null,     // 弹窗标题
        content: null,     // 弹窗内容
        buttonStr: '确认',     // 按钮字符串，默认为“确认”
        callback: null, //回调方法（可以带参数）
    }
})(jQuery);
/*对话框（替换alert） end*/