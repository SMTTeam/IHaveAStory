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

$("#backToSendResetPswEmailButton").click(function () {
    window.location.href="/findbackpsw";//返回发送重置密码邮件 界面
});