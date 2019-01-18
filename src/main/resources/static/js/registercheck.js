var isemailExist ;
var commitStatus = false;

//初始化
$(function () {
    $('.am-nav li').removeClass('am-active');

    var dotline_register = new Dotline({
        dom:'J_dotLine',//画布id
        cw: $(window).width(),//画布宽
        ch: $(window).height() - 40,//画布高
        ds: 300,//点的个数
        r: 0.5,//圆点半径
        // cl:'#12FC41',//粒子线颜色
        cl: '#DCDFE6',
        dis:100//触发连线的距离
    }).start();
});

$("#signupInputEmail").parsley().addAsyncValidator("checkEmail" , function (xhr) {
            var jsonData = JSON.parse(xhr.responseText);
            // alert('emmmmm1'+jsonData.code.toString());
            var code = jsonData.code;
            // alert(responsedata.get("code"));
            if (code === 200){

                isemailExist = true;
                // alert("isemailExist="+isemailExist);
                return false;
            }else {

                isemailExist = false;
                // alert("isemailExist="+isemailExist);
                return true;
            }
        },'/register/checkemail',{"dataType": "json","data":{"emailname":function(){return $('#signupInputEmail').val();}}
});



//聚焦到下一个输入框
function focusNextInput(thisInput){
    var inputs = document.getElementsByTagName("input");
    // alert(inputs.length);
    // alert('dafafafaf');
    // console.log(thisInput);
    for(var i = 0;i<inputs.length;i++){
        // alert('i='+i.toString()+',inputs.length-1='+(inputs.length-1).toString());
        if(i===(inputs.length-1)){// 如果是最后一个，则焦点回到第一个
            // inputs[0].focus();
            // alert('2346575687568');
            // var submit = document.getElementsByTagName("registerButton");
            // submit.click();
            $('#form').submit(); //最后到了确认密码输入框，按下Enter键 提交表单
            break;
        }else if(thisInput == inputs[i]){
            inputs[i+1].focus();
            break;
        }
    }
    return false;
}
function submitForm() {
    if(commitStatus==false)
        //提交表单后，讲提交状态改为true
        commitStatus = true;
    else
        return false;
    // alert('isemailExist='+isemailExist);
    var email = $.trim($("#signupInputEmail").val());
    var username = $.trim($("#signupInputName").val());
    var password = $.trim($("#signupInputPassword").val());
    var verify_password = $.trim($("#confirmSignupInputPassword").val());
    var email_pattern= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    var password_pattern = /^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$)\S{6,20}$/;
    if ( username == "" || ! email_pattern.test(email) || ! password_pattern.test(password) ||  password !== verify_password || isemailExist){
        return;//啥都不做
    }
    alert('已向您的邮箱发送验证邮件！请登录邮箱，验证之后才能登录哦！');
    var temp_result_url = "";//用来存放需要进入的邮箱界面
    var hash = {    //域名解析数组
        'qq.com': 'https://mail.qq.com',
        'gmail.com': 'https://mail.google.com',
        'sina.com': 'https://mail.sina.com.cn',
        '163.com': 'https://mail.163.com',
        '126.com': 'https://mail.126.com',
        'yeah.net': 'https://www.yeah.net/',
        'sohu.com': 'https://mail.sohu.com/',
        'tom.com': 'https://mail.tom.com/',
        'sogou.com': 'https://mail.sogou.com/',
        '139.com': 'https://mail.10086.cn/',
        'hotmail.com': 'https://www.hotmail.com',
        'live.com': 'https://login.live.com/',
        'live.cn': 'https://login.live.cn/',
        'live.com.cn': 'https://login.live.com.cn',
        '189.com': 'http://webmail16.189.cn/webmail/',
        'yahoo.com.cn': 'https://mail.cn.yahoo.com/',
        'yahoo.cn': 'https://mail.cn.yahoo.com/',
        'eyou.com': 'http://www.eyou.com/',
        '21cn.com': 'http://mail.21cn.com/',
        '188.com': 'http://www.188.com/',
        'foxmail.com': 'https://www.foxmail.com',
        'outlook.com': 'https://www.outlook.com'
    }
    var _mail = $.trim($("#signupInputEmail").val()).split('@')[1];
    for (var j in hash){
        if(j == _mail){
            temp_result_url = hash[_mail];
        }
    }
    $.ajax({
        type:'POST',
        url:'/register/verifyemail',
        data:{"email":email,"username":username,"psw":password},
        dataType:'json',
        timeout:10000,   //设置为1s可能有时候网速比较慢了，容易引发超时错误
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            // alert(XMLHttpRequest.status);//状态码
            // alert(XMLHttpRequest.readyState);//状态
            // alert(textStatus);//错误信息
            alert("可能网络有点延时，可以进邮箱耐心等待哦！");
        },
        success:function(result){
            // alert(result.data.toString());
            // window.location.href='/loginsmt';
            alert("邮件已发送到您的邮箱！请查收！");
            // location.reload();
        }

    });
    // alert(temp_result_url);
    window.location.href = "/project";
};

$('#registerButton').click(function () {
    // location.reload();
    // alert('isemailExist='+isemailExist);
    var email = $.trim($("#signupInputEmail").val());
    var username = $.trim($("#signupInputName").val());
    var password = $.trim($("#signupInputPassword").val());
    var verify_password = $.trim($("#confirmSignupInputPassword").val());
    var email_pattern= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    var password_pattern = /^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$)\S{6,20}$/;

    // $.ajax({
    //     type:'GET',
    //     url:'/register/checkemail',
    //     data:{"emailname":email},
    //     dataType:'json',
    //     error:function () {
    //
    //     },
    //     success:function(result) {
    //         if(result.code === 200){
    //             isemailExist = false;
    //         }else {
    //             isemailExist = true;
    //         }
    //     }
    //
    // });
    if ( username == "" || ! email_pattern.test(email) || ! password_pattern.test(password) ||  password !== verify_password || isemailExist){
        return;//啥都不做
    }
    alert('已向您的邮箱发送验证邮件！请登录邮箱，验证之后才能登录哦！');
    var temp_result_url = "";//用来存放需要进入的邮箱界面
    var hash = {    //域名解析数组
        'qq.com': 'https://mail.qq.com',
        'gmail.com': 'https://mail.google.com',
        'sina.com': 'https://mail.sina.com.cn',
        '163.com': 'https://mail.163.com',
        '126.com': 'https://mail.126.com',
        'yeah.net': 'https://www.yeah.net/',
        'sohu.com': 'https://mail.sohu.com/',
        'tom.com': 'https://mail.tom.com/',
        'sogou.com': 'https://mail.sogou.com/',
        '139.com': 'https://mail.10086.cn/',
        'hotmail.com': 'https://www.hotmail.com',
        'live.com': 'https://login.live.com/',
        'live.cn': 'https://login.live.cn/',
        'live.com.cn': 'https://login.live.com.cn',
        '189.com': 'http://webmail16.189.cn/webmail/',
        'yahoo.com.cn': 'https://mail.cn.yahoo.com/',
        'yahoo.cn': 'https://mail.cn.yahoo.com/',
        'eyou.com': 'http://www.eyou.com/',
        '21cn.com': 'http://mail.21cn.com/',
        '188.com': 'http://www.188.com/',
        'foxmail.com': 'https://www.foxmail.com',
        'outlook.com': 'https://www.outlook.com'
    }
    var _mail = $.trim($("#signupInputEmail").val()).split('@')[1];
    for (var j in hash){
        if(j == _mail){
            temp_result_url = hash[_mail];
        }
    }
    $.ajax({
        type:'POST',
        url:'/register/verifyemail',
        data:{"email":email,"username":username,"psw":password},
        dataType:'json',
        timeout:10000,   //设置为1s可能有时候网速比较慢了，容易引发超时错误
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            // alert(XMLHttpRequest.status);//状态码
            // alert(XMLHttpRequest.readyState);//状态
            // alert(textStatus);//错误信息
            alert("可能网络有点延时，可以进邮箱耐心等待哦！");
        },
        success:function(result){
            // alert(result.data.toString());
            // window.location.href='/loginsmt';
            alert("邮件已发送到您的邮箱！请查收！");
            // location.reload();
        }

    });
    alert(temp_result_url);
    window.location.href = "/project";
    // if( temp_result_url.length > 0 ){
    //     window.open(temp_result_url, "_blank");
    // }
});

$('#loginButton1').click(function () {
   window.location.href='/login';
});