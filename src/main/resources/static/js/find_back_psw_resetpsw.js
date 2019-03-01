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


$("#resetPswButton").click(function () {
    var useremail = $.trim($("#findback_PswEmail").val());
    var newPsw = $.trim($("#findbackPswInputNewPassword").val());
    var confirm_newPsw = $.trim($("#confirmInputNewPassword").val());
    var password_pattern = /^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$)\S{6,20}$/;

    if(!password_pattern.test(newPsw) || !password_pattern.test(confirm_newPsw) || newPsw !== confirm_newPsw){
        return;//啥都不做
    }

    $.ajax({
        type:'POST',
        url:'/userinfo/resetpassword',
        data:{"useremail":useremail,"newpsw":newPsw},
        dataType:'json',
        timeout:1000,
        error:function () {
            popMsg("请求更改密码信息失败，请重试",3000);
            $("#findbackPswInputNewPassword").val('');
            $("#confirmInputNewPassword").val('');
        },
        success:function (result) {
            if( result.code === 200){

                $("#findbackPswInputNewPassword").val('');
                $("#confirmInputNewPassword").val('');
                popMsg('保存新密码成功',1500,function () {
                    window.location.href = "/login";
                });
            }else {
                popMsg('密码重置失败！');
            }
        }
    });
});