// 初始化
$(function () {
    $('.am-nav li').removeClass('am-active');
    $('.am-nav li:eq(1)').addClass('am-active');

    sendGet('/api/project/list', function (list) {
        if(list.length === 0){
            $('.smt-empty-msg').removeAttr("hidden");
        }else{
            $.each(list, function (index, project) {
                $('#project-list').append('<li class="am-u-sm-11 am-u-sm-centered">' +
                    '                <div class="am-g smt-card">' +
                    '                    <div class="am-u-sm-10">' +
                    '                        <h3>'+ project.proName +'</h3>' +
                    '                        <p class="line-clamp">'+ project.description +'</p>' +
                    '                    </div>' +
                    '                    <div class="am-u-sm-2 smt-card-btn-group">' +
                    '                        <a href="/project/edit?proId='+ project.id +'"><button class="am-btn smt-bnt am-vertical-align-middle">编辑项目</button></a>' +
                    '                        <a href="/storyMapping?proId='+ project.id +'"><button class="am-btn smt-bnt am-vertical-align-middle">进入地图</button></a>' +
                    '                    </div>' +
                    '                </div>' +
                    '            </li>')
            });
        }
    });
});