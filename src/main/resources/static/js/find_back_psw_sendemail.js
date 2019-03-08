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

var isFindBackemailExist;

$("#form_findbackPsw").parsley().addAsyncValidator("checkEmailForFindBackPsw" , function (xhr) {
        var jsonData = JSON.parse(xhr.responseText);
        var code = jsonData.code;

        if (code === 200) {
            isFindBackemailExist = true;
            return true;
        } else {
            isFindBackemailExist = false;
            return false;
        }
    },'/register/checkemail',{"dataType": "json","data":{"emailname":function(){ return $('#findback_PswInputEmail').val();}}
});

$("#findback_RegisterButton").click(function () {
    window.location.href='/register';
});

$("#backToLoginButton").click(function () {
    window.location.href='/login';
});

$("#findback_SendEmailButton").click(function () {
    var useremail_findback = $.trim($("#findback_PswInputEmail").val());
    if(useremail_findback === "" || useremail_findback ===undefined){
        popMsg("邮箱不能为空！",1500);
        return;
    }

    if( isFindBackemailExist === false){
        return; //啥都不做
    }

    $.ajax({
        type:'POST',
        url:'/userinfo/sendresetpswemail',
        data:{"useremail":useremail_findback},
        dataType:'json',
        timeout:10000,   //设置为1s可能有时候网速比较慢了，容易引发超时错误
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            popMsg("可能网络有点延时，可以进邮箱耐心等待哦！");
            alert(errorThrown.toString());
        },
        success:function(result){

            popMsg("重置密码邮件已发送到您的邮箱！请查收！", 1500, function () {
                window.location.href = "/login";
            });

        }
    });
});
