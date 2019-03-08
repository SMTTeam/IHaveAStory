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

    if(proId == null){
        window.location.href = '/';
    }
    var canEdit = false;
    $.when(
        sendGet('/api/invite/role?proId=' + proId + '&userId=' + userId, function (data) {
            //可编辑
            if(data != null && data.role > 3){
                canEdit = true;
                $('.edit-role').show();
                $('.smt-input-for-row').attr("disabled",false);
                if(data.role == 15){
                    $('.owner-role').show();
                }
            }

        })
    ).done(function () {
        sendGet('/api/project/detail?proId=' + proId, function (data) {
            if(data == null){
                $('.project-container').empty();
                $('.project-container').append('<div class="am-g">' +
                    '<p style="text-align: center;margin-top: 200px">该项目不存在!</p>' +
                    '</div>');
                $('.project-container').show();
                return;
            }
            $('.project-container').show();
            $.each(data, function (name, value) {
                var $input = $('[name=' + name + ']');
                if ($input.length)
                    $input.val(value);
            });
            $('[name=proId]').val(data.id);
        });

        sendGet('/api/invite/list?proId=' + proId, function (data) {
            $.each(data, function (index, user) {
                var img = '<span class="smt-line-img attend-img"></span>';
                if(user.role === 15){
                    img = '<span class="smt-line-img owner-img"></span>';
                }else if(canEdit){
                    img = '<span class="smt-line-img edit-img" onclick="showModInvite(' + user.userId +',\'' + user.username + '\',' + user.role  + ')"></span>' +
                          '<span class="smt-line-img delete-img" onclick="deleteInvite(' + user.userId + ')"></span>'
                }
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
                    '                           <div class="am-g">' +
                    '                                <div class="am-u-sm-12 am-u-md-10 am-u-lg-8 am-u-sm-centered">' + img +
                    '                                </div>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </div>' +
                    '                </li>')
            })
        });

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

function showDel() {
    popConfirm('Notice: 删除项目会清除项目所有相关信息且不可恢复，是否删除?', function () {
        sendPost('/api/project/delete', {"proId": proId}, function () {
            popMsg("删除项目成功！", 1000, function () {
                window.location.href = '/project';
            });
        })
    });
}

function showModInvite(id, name, role) {
    $('#modify-userId').val(id);
    $('#modify-name').val(name);
    $('#modify-role').val(role);

    $('#invite-modal').modal({
        relatedTarget: this,
        closeViaDimmer: false,
        onConfirm: function(e) {
            var modifyId = $('#modify-userId').val();
            var modifyRole = $('#modify-role').val();
            sendPost('/api/invite/modify', {"proId": proId, "userId": modifyId, "role": modifyRole}, function () {
                popMsg("修改成员权限成功！", 1000, function () {
                    window.location.reload();
                });
            })
        }
    });
}

function deleteInvite(inviteId) {
    sendPost('/api/invite/delete', {"proId": proId, "userId": inviteId}, function () {
        popMsg("删除成员成功！", 1000, function () {
            window.location.reload();
        });
    })
}