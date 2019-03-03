$(function () {
    // $(window).resize(function(){
    //     location.reload()
    // });


    checkLoginState();  //检查用户登录状态
    // var username= sessionStorage.getItem("user").uername;
    // var showUser = [[${session.user}]];
    var username="";

    $('#user-name').val(username);
});

//点击个人中心下拉框
$('#personalSetting').on('click', function() {
    // popMsg(username);
    if( showUser!=null){
        username = showUser.username;

    }


    $('#user-name').val(username);
    $('#my-prompt').modal("hide");
    $('#my-prompt').modal({
        backdrop: "static",//点击空白处不关闭对话框
        keyboard: false,//键盘关闭对话框
        show:true//弹出对话框
    });

    //初始化模态框状态，将状态恢复至基本信息状态 相当于点击了"基本信息"
    if( $('#smt-li-baseinfo').hasClass("am-active")){
        return;
    }else {
        $('#smt-li-baseinfo').addClass("am-active");
        document.getElementById("changePsw").style.display="none";
        document.getElementById("baseInfo").style.display="";  //显示基本信息
        $('#smt-li-changepsw').removeClass("am-active");
    }


    //将"修改密码"页面的输入框清零
    $("#user-oldpsw").val('');
    $("#user-newpsw").val('');
    $("#user-confirm-newpsw").val('');
    // $("#my-prompt").modal({
    //     // relatedTarget: this,
    //     backdrop: "static",//点击空白处不关闭对话框
    //     // show:true,
    //     keyboard: false//触发键盘esc事件时不关闭。
    //
    //     // onConfirm: function(e) {
    //     //     alert('你输入的是：' + e.data || '')
    //     // },
    //     // onCancel: function(e) {
    //     //     alert('不想说!');
    //     // }
    // });
    // var currentPassword = $sessionScope.user.password;
});

;(function(window){
    function Dotline(option){
        this.opt = this.extend({
            dom:'J_dotLine',//画布id
            cw:1000,//画布宽
            ch:500,//画布高
            ds:150,//点的个数
            r:0.5,//圆点半径
            cl:'#000',//颜色
            dis:100//触发连线的距离
        },option);
        //限制
        if(this.opt.cw < 648){
            this.opt.ds = 100;
        }else if(this.opt.cw<1024){
            this.opt.ds = 200;
        }
        this.c = document.getElementById(this.opt.dom);//canvas元素id
        this.ctx = this.c.getContext('2d');
        this.c.width = this.opt.cw;//canvas宽
        this.c.height = this.opt.ch;//canvas高
        this.dotSum = this.opt.ds;//点的数量
        this.radius = this.opt.r;//圆点的半径
        this.disMax = this.opt.dis*this.opt.dis;//点与点触发连线的间距
        this.color = this.color2rgb(this.opt.cl);//设置粒子线颜色
        this.dots = [];
        //requestAnimationFrame控制canvas动画
        var RAF = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function(callback) {
            window.setTimeout(callback, 1000 / 60);
        };
        var _self = this;
        //增加鼠标效果
        var mousedot = {x:null,y:null,label:'mouse'};
        this.c.onmousemove = function(e){
            var e = e || window.event;
            mousedot.x = e.clientX - _self.c.offsetLeft;
            mousedot.y = e.clientY - _self.c.offsetTop;
        };
        this.c.onmouseout = function(e){
            mousedot.x = null;
            mousedot.y = null;
        }
        //控制动画
        this.animate = function(){
            _self.ctx.clearRect(0, 0, _self.c.width, _self.c.height);
            _self.drawLine([mousedot].concat(_self.dots));
            RAF(_self.animate);
        };
    }
    //合并配置项，es6直接使用obj.assign();
    Dotline.prototype.extend = function(o,e){
        for(var key in e){
            if(e[key]){
                o[key]=e[key]
            }
        }
        return o;
    };
    //设置线条颜色(参考{抄袭}张鑫旭大大，http://www.zhangxinxu.com/wordpress/2010/03/javascript-hex-rgb-hsl-color-convert/)
    Dotline.prototype.color2rgb = function(colorStr){
        var red = null,
            green = null,
            blue = null;
        var cstr = colorStr.toLowerCase();//变小写
        var cReg = /^#[0-9a-fA-F]{3,6}$/;//确定是16进制颜色码
        if(cstr&&cReg.test(cstr)){
            if(cstr.length==4){
                var cstrnew = '#';
                for(var i=1;i<4;i++){
                    cstrnew += cstr.slice(i,i+1).concat(cstr.slice(i,i+1));
                }
                cstr = cstrnew;
            }
            red = parseInt('0x'+cstr.slice(1,3));
            green = parseInt('0x'+cstr.slice(3,5));
            blue = parseInt('0x'+cstr.slice(5,7));
        }
        return red+','+green+','+blue;
    }
    //画点
    Dotline.prototype.addDots = function(){
        var dot;
        for(var i=0; i<this.dotSum; i++){//参数
            dot = {
                x : Math.floor(Math.random()*this.c.width)-this.radius,
                y : Math.floor(Math.random()*this.c.height)-this.radius,
                ax : (Math.random() * 2 - 1) / 1.5,
                ay : (Math.random() * 2 - 1) / 1.5
            }
            this.dots.push(dot);
        }
    };
    //点运动
    Dotline.prototype.move = function(dot){
        dot.x += dot.ax;
        dot.y += dot.ay;
        //点碰到边缘返回
        dot.ax *= (dot.x>(this.c.width-this.radius)||dot.x<this.radius)?-1:1;
        dot.ay *= (dot.y>(this.c.height-this.radius)||dot.y<this.radius)?-1:1;
        //绘制点
        this.ctx.beginPath();
        this.ctx.arc(dot.x, dot.y, this.radius, 0, Math.PI*2, true);
        this.ctx.stroke();
    };
    //点之间画线
    Dotline.prototype.drawLine = function(dots){
        var nowDot;
        var _that = this;
        //自己的思路：遍历两次所有的点，比较点之间的距离，函数的触发放在animate里
        this.dots.forEach(function(dot){

            _that.move(dot);
            for(var j=0; j<dots.length; j++){
                nowDot = dots[j];
                if(nowDot===dot||nowDot.x===null||nowDot.y===null) continue;//continue跳出当前循环开始新的循环
                var dx = dot.x - nowDot.x,//别的点坐标减当前点坐标
                    dy = dot.y - nowDot.y;
                var dc = dx*dx + dy*dy;
                if(Math.sqrt(dc)>Math.sqrt(_that.disMax)) continue;
                // 如果是鼠标，则让粒子向鼠标的位置移动
                if (nowDot.label && Math.sqrt(dc) >Math.sqrt(_that.disMax)/2) {
                    dot.x -= dx * 0.02;
                    dot.y -= dy * 0.02;
                }
                var ratio;
                ratio = (_that.disMax - dc) / _that.disMax;
                _that.ctx.beginPath();
                _that.ctx.lineWidth = ratio / 2;
                _that.ctx.strokeStyle = 'rgba('+_that.color+',' + parseFloat(ratio + 0.2).toFixed(1) + ')';
                _that.ctx.moveTo(dot.x, dot.y);
                _that.ctx.lineTo(nowDot.x, nowDot.y);
                _that.ctx.stroke();//不描边看不出效果

                //dots.splice(dots.indexOf(dot), 1);
            }
        });
    };
    //开始动画
    Dotline.prototype.start = function(){
        var _that = this;
        this.addDots();
        setTimeout(function() {
            _that.animate();
        }, 100);
    };
    window.Dotline = Dotline;



}(window));

//初始化将登录和注册按钮隐藏，显示个人中心图标 ，表示已经登录
function changeLoginStatusToLogin() {
    document.getElementById("topbar_login").style.display="none";
    document.getElementById("topbar_register").style.display="none";
    document.getElementById("topbar_user").style.display="";//显示个人中心图标
};

//初始化将个人中心图标隐藏，显示登录和注册按钮，表示用户未登录
function changeLoginStatusToUnLogin() {
    document.getElementById("topbar_login").style.display="";//显示登录按钮
    document.getElementById("topbar_register").style.display="";//显示注册按钮
    document.getElementById("topbar_user").style.display="none";
}

//检查用户的登录状态
function checkLoginState() {

    $.ajax({
        type:'GET',
        url:'/getuserloginstate',
        dataType:'json',
        timeout:10000,
        async:false,
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            // alert(XMLHttpRequest.status); 不知道为啥这里 始终error 失败
            // alert(XMLHttpRequest.readyState);
            // alert(textStatus.toString());
            // console.log(errorThrown.toString());
        },
        success:function (result) {
            if( result.code===200){
                console.log('这里已经登录过了！');
                changeLoginStatusToLogin();
            }else {
                console.log('尚未登录！');
                changeLoginStatusToUnLogin();
                popMsg('尚未登录');
            }
        }
    })

};



function sendGet(url, success) {
    $.get(url, function (res) {
        var obj = objectRes(res);
        if (obj.code === 200) {
            if (success) {
                success(obj.data);
            }
        } else{
            closeLoading();
            popMsg(obj.message);
        }
    }).fail(function (obj) {
        popMsg("请求数据失败，请刷新或重试！")
        closeLoading();
    });
}


function sendPost(url, data, success) {
    $.post(url, data, function (response) {
        if (response.code === 200) {
            if (success) {
                success(response.data);
            }
        } else {
            closeLoading();
            popMsg(response.message);
        }
    }).fail(function (obj) {
        closeLoading();
        popMsg("操作失败，请刷新或重试!")
    });
}

//判断字符串是否为空 , $blank 为是否可以含空格
function isEmptyStr($str, $blank) {
    if ($str == null || $str === "") {
        return true;
    }
    if (!$blank) {
        if ($str.trim() === "") {
            return true;
        }
    }
    return false;
}

function objectRes(res) {
    return typeof res === 'object' ? res : JSON.parse(res);
}

/**
 * 自动将form表单封装成json对象
 */
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function smt_alert(title, message) {
    $('#smt-alert-title').text(title);
    $('#smt-alert-content').text(message);
    $('#smt-alert').modal();
}

/**
 * 覆盖加载遮罩
 */
function popLoading() {
    $('body').addClass('smt-loading');
}

/**
 * 关闭加载遮罩
 */
function closeLoading() {
    $('body').removeClass('smt-loading');
}

// 弹出消息
function popMsg(msg, mills, c) {
    var time = 1500, callback = $.noop, $msg;
    if (mills) {
        if ($.isFunction(mills))
            callback = mills;
        else {
            time = parseInt(mills);
            if (c)
                callback = c;
        }
    }
    $('body').append($msg = $('<div class="smt-pop smt-msg"><span>' + msg + '</span></div>'));
    setTimeout(function () {
        $msg.remove();
        callback();
    }, time);
}


//确认对话框
function popConfirm(msg, callback) {
    var $a, $b;
    $('body').append(
        $a = $('<div class="smt-pop-shade"></div>'),
        $b = $('<div class="smt-pop smt-confirm">' + msg + '</div>').append($('<div class="smt-btn-wp"></div>').append(
            '<input type="button" value="确定/OK" class="btn btn-primary radius" data-confirm>',
            '<input type="button" value="取消/Cancel" class="btn btn-default radius" data-cancel>'
        ))
    );
    $b.find(':button').click(function () {
        var confirm = 0;
        if ($(this).is('[data-confirm]'))
            confirm = 1;
        $a.remove();
        $b.remove();
        if ((callback || 0) && confirm)
            callback.apply($b[0]);
    });
    return $b;
}

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

$('#smt-close-modal').click(function () {
    // event.stopPropagation();
    // $('#my-prompt').on('hide.bs.modal', function () {
    //     console.log('关闭模态框！');
    // });
    // popMsg("点击了关闭按钮");
    $('#my-prompt').modal('close');

});
//点击"基本信息"
$('#smt-li-baseinfo').click(function () {
    // var tempClass = $('#smt-li-baseinfo').getAttributeNames("class");
    if( $('#smt-li-baseinfo').hasClass("am-active")){
        return;
    }else {
        $('#smt-li-baseinfo').addClass("am-active");
        document.getElementById("changePsw").style.display="none";
        document.getElementById("baseInfo").style.display="";  //显示基本信息
        $('#smt-li-changepsw').removeClass("am-active");
    }
});

//点击"修改密码"
$('#smt-li-changepsw').click(function () {
   if( $('#smt-li-changepsw').hasClass("am-active")){
       return;
   } else{
       $('#smt-li-changepsw').addClass("am-active");
       document.getElementById("baseInfo").style.display="none";
       document.getElementById("changePsw").style.display="";  //显示修改密码
       $('#smt-li-baseinfo').removeClass("am-active");
   }
});

function clickToCloseModal() {
    // var tempClass = $('#smt-li-baseinfo').getAttributeNames("class");
    // if( $('#smt-li-changepsw').hasClass("am-active")){
    //     return;
    // }else {
    //     $('#smt-li-changepsw').addClass("am-active");
    //     $('#smt-li-baseinfo').removeClass("am-active");
    // }
};

//点击“保存”按钮，提交修改信息
$('#btn-updatebaseinfo').click(function () {
    var email = $.trim($("#user-em").val());
    var nickname =  $.trim($("#user-name").val());

    if ( nickname =='' ){
        popMsg('昵称不能为空',2000);
        return ;
    }
    $.ajax({
        type:'POST',
        url:'/userinfo/update',
        data:{"useremail":email,"username":nickname},
        dataType:'json',
        timeout:1000,
        error:function () {
            popMsg("请求更改信息失败，请重试",3000);
        },
        success:function (result) {
            if( result.code === 200){
                popMsg('保存信息成功');
                showUser.username = $("#user-name").val();
                username = showUser.username;
                // popMsg(username);
            }else {
                popMsg('信息修改失败！');
            }
        }
    });
});

var isOldPswTrue;

$("#user-oldpsw").parsley().addAsyncValidator("checkOldPsw" , function (xhr) {
        var jsonData = JSON.parse(xhr.responseText);
        var code = jsonData.code;
        if (code === 200){

            isOldPswTrue = true;
            // alert("isemailExist="+isemailExist);
            return true;
        }else {
            isOldPswTrue = false;
            return false;
        }
    },'/userinfo/checkoldpsw',{"dataType": "json","data":{"oldpsw":function(){return $('#user-oldpsw').val();} , "useremail":function(){ if(showUser == null){return "";} else{ return  showUser.email;}}}
});

//点击"保存"按钮，修改密码
$("#btn-updatePsw").click(function (){
   var newpsw =  $.trim($("#user-newpsw").val());
   var confirm_newpsw =  $.trim($("#user-confirm-newpsw").val());
   var password_pattern = /^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$)\S{6,20}$/;

   if(!isOldPswTrue || ! password_pattern.test(newpsw) || !password_pattern.test(confirm_newpsw) || newpsw !== confirm_newpsw){
       return;//啥都不做
   }
   $.ajax({
       type:'POST',
       url:'/userinfo/changepsw',
       data:{"useremail":showUser.email ,"newpsw":newpsw},
       dataType:'json',
       timeout:1000,
       error:function () {
           popMsg("请求更改密码信息失败，请重试",3000);
           $("#user-oldpsw").val('');
           $("#user-newpsw").val('');
           $("#user-confirm-newpsw").val('');
       },
       success:function (result) {
           if( result.code === 200){
               popMsg('保存新密码成功');
               $("#user-oldpsw").val('');
               $("#user-newpsw").val('');
               $("#user-confirm-newpsw").val('');
               // showUser.username = $("#user-name").val();
               // username = showUser.username;
               // popMsg(username);
           }else {
               popMsg('密码修改失败！');
               // $("#user-oldpsw").val('');
               // $("#user-newpsw").val('');
               // $("#user-confirm-newpsw").val('');
           }
       }
   })
});