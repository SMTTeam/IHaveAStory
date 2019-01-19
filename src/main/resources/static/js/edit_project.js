$(function () {
    $('.am-nav li').removeClass('am-active');

    var dotline = new Dotline({
        dom:'J_dotLine',//画布id
        cw: $(window).width(),//画布宽
        ch: $(window).height() - 40,//画布高
        ds: 300,//点的个数
        r: 0.5,//圆点半径
        // cl:'#12FC41',//粒子线颜色
        cl: '#DCDFE6',
        dis:100//触发连线的距离
    }).start();

    $('#project-form').validate({
        errorPlacement: function(error, element) {}
    });

    var proId = getUrlParam('proId');
    if(proId == null){
        window.location.href = '/';
    }
    sendGet('/api/project/detail?proId=' + proId, function (data) {
        $.each(data, function (name, value) {
            var $input = $('[name=' + name + ']');
            if ($input.length)
                $input.val(value);
        });
        $('[name=proId]').val(data.id);
    })
});


function modifyProject() {
    if($('#project-form').valid()){
        var data = $('#project-form').serializeObject();
        console.log(data);
        popLoading();
        sendPost('/api/project/modify', data, function (data) {
            closeLoading();
            popMsg("修改项目成功！", 1500, function () {
                window.location.reload();
            });
        });
    }
}
