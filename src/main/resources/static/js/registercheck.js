$(function(){
        window.Parsley.addAsyncValidator("checkEmail" , function (xhr) {
            var jsonData = JSON.parse(xhr.responseText);
            var code = jsonData.code;
            // alert(responsedata.get("code"));
            if (code === 200){
                return false;
            }else {
                return true;
            }
        },'/register/checkemail',{"dataType": "json","data":{"emailname":function(){return $('#signupInputEmail').val();}}});
    }
)

$('#registerButton').click(function () {
    alert('已向您的邮箱发送验证邮件！请登录邮箱，验证之后才能登录哦！');
    var email = $.trim($("#signupInputEmail").val());
    var username = $.trim($("#signupInputName").val());
    var password = $.trim($("#signupInputPassword").val());
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
        'foxmail.com': 'http://www.foxmail.com',
        'outlook.com': 'http://www.outlook.com'
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
        }

    });
    window.location.href = temp_result_url;
    // if( temp_result_url.length > 0 ){
    //     window.open(temp_result_url, "_blank");
    // }
})