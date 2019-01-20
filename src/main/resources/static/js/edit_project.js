$(function () {
    $('.am-nav li').removeClass('am-active');
    $('.invite-area').hide();

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

    $('#invite-form').validate({
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
    });

    sendGet('/api/invite/list?proId=' + proId, function (data) {
        $.each(data, function (index, user) {
            $('#project-users').append('<li class="am-u-sm-12 am-u-md-11 am-u-sm-centered" >' +
                '                    <div class="am-g smt-line">' +
                '                        <div class="am-u-sm-2">' +
                '                            <img class="smt-line-icon" src="/img/boy.png">' +
                '                        </div>' +
                '                        <div class="am-u-sm-2">' +
                '                            <p>'+ user.username +'</p>' +
                '                        </div>' +
                '                        <div class="am-u-sm-3">' +
                '                            <p class="smt-line-role" style="width: auto">'+ parseRole(user.role) +'</p>' +
                '                            <!--<img class="am-fl smt-role-select" src="/img/common/select.png" alt="">-->' +
                '                        </div>' +
                '                        <div class="am-u-sm-3">' +
                '                            <p>'+ parseInviteStatus(user.status) +'</p>' +
                '                        </div>' +
                '                        <div class="am-u-sm-2">' +
                '                            <img class="smt-line-operator" src="/img/common/delete.png">' +
                '                        </div>' +
                '                    </div>' +
                '                </li>')
        })
    });
});


function modifyProject() {
    if($('#project-form').valid()){
        var data = $('#project-form').serializeObject();
        popLoading();
        sendPost('/api/project/modify', data, function (data) {
            closeLoading();
            popMsg("修改项目成功！", 1500, function () {
                window.location.reload();
            });
        });
    }
}

function showInvite() {
    $('.invite-area').show();
    var $w = $(window);
    $w.smoothScroll({
        position: $(document).height() - $w.height(),
        speed: 1500
    });
}

function inviteUser() {
    var proId = getUrlParam('proId');
    if($('#invite-form').valid()) {
        var data = $('#invite-form').serializeObject();
        data.proId = proId;
        popLoading();
        sendPost('/api/invite/create', data, function (data) {
            closeLoading();
            popMsg("邀请发送成功！", 1500, function () {
                window.location.reload();
            });
        });
    }

}
