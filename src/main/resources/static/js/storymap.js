function over(ele){
    if(role!=1){
        var id = $(ele).children("div").attr("id")
        // console.log(id)
        $("#"+id).css("display","")
    }
}

function leave(ele){
    if(role!=1){
        var id = $(ele).children("div").attr("id")
        $("#"+id).css("display","none")
    }
}

function createActivity(element,a_posId,t_posId){
    var id;
    if(a_posId!=0) {
        id = $(element).parent().parent().parent().parent().parent().attr("id");
    }
    var a_num;
    var t_num;

    var a_name = "未命名activity";
    var t_name = "未命名task";
    var activityId;
    $.ajax({
        type: "POST",
        url: "/activity/create",
        data: {"proId":proId, "name":a_name, "posId":a_posId},
        async: false,
        success: function (data){
            activityId = data.data.id;
            a_num = activityId
            $.ajax({
                type: "POST",
                url: "/task/create",
                data: {"activityId": activityId, "name": t_name, "posId": t_posId},
                async: false,
                success: function (data) {
                    t_num = data.data.id
                }
            })
        }
    });
    var column_id = "column-"+a_num
    var acard_id = "acard-"+a_num
    var amenu_id = "amenu-"+a_num
    var aarea_id = "aarea-"+a_num
    var tcard_id = "tcard-"+t_num
    var tmenu_id = "tmenu-"+t_num
    var tarea_id = "tarea-"+t_num
    var tmp='<div class="column" id="'+column_id+'">\n' +
        '                    <div class="column-container"><div class="column-border-line" style="min-height: 600px;"></div>\n' +
        '                        <div class="activity-container">\n' +
        '                            <div class="activity-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+acard_id+'">\n' +
        '                                <textarea class="a-name-editor" maxlength="50" readonly="readonly" id="'+aarea_id+'">未命名activity</textarea>\n' +
        '                                <div class="a-operation-menu" id="'+amenu_id+'" style="display: none">\n' +
        '                                    <a onclick="deleteActivity(this)"><img class="delete" title="删除" src="img/delete.png"></a>' +
        '                                    <a onclick="editActivity(this)"><img class="edit" title="编辑" src="img/edit.png"></a>\n' +
        '                                    <a onclick="createActivity(this,'+a_posId+1+',0)"><img class="create" title="在右侧新建" src="img/create.png"></a>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                        <ul class="task-container">\n' +
        '                            <li class="task-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+tcard_id+'">\n' +
        '                                <textarea class="t-name-editor" maxlength="50" readonly="readonly" id="'+tarea_id+'">未命名task</textarea>\n' +
        '                                <div class="t-operation-menu" id="'+tmenu_id+'" style="display: none">\n' +
        '                                    <a onclick="deleteTask(this)"><img class="delete" title="删除" src="img/delete.png"></a>' +
        '                                    <a onclick="editTask(this)"><img class="edit" title="编辑" src="img/edit.png"></a>\n' +
        '                                    <a onclick="createTask(this,'+activityId+',1)"><img class="create" title="在右侧新建" src="img/create.png"></a>\n' +
        '                                </div>\n' +
        '                            </li>\n' +
        '                        </ul>\n' +
        '                    </div>\n' +
        '\n' +
        '                </div>'

    if(a_posId==0){
        $("#init-column").remove()
        $("#head-container").append(tmp)
        $(".feature-block-list").each(function () {
            var acolumn_id= "acolumn-"+activityId+$(this).attr("id")
            var feature_block = '<li class="feature-block">' +
                '<div class="feature-block-expanded">' +
                '<ul class="feature-column-list non-list flat-list" id="'+acolumn_id+'">' +
                '</ul>' +
                '</div>'
            '</li>'
            $(this).append(feature_block)
            var tcolumn_id = "tcolumn-"+t_num+$(this).attr("id")
            var feature_column = '<li class="feature-column vertical-list ">' +
                    '<ul class="card-list non-list ui-sortable" id="'+tcolumn_id+'">' +
                    '<div class="init-card" data-toggle="modal" data-target="#createStoryModal" onclick="labelCreateStory(this,'+t_num+')">' +
                    '<p class="title-placeholder">新建标签</p></div>' +
                    '</ul>' +
                    '</li>'
            $("#"+acolumn_id).append(feature_column)
        })
    }else {
        $("#"+id).after(tmp)
        var aindex = id.lastIndexOf("-")
        var preActivityId = id.substr(aindex+1)

        $(".feature-block-list").each(function () {
            var acolumn_id= "acolumn-"+activityId+$(this).attr("id")
            var feature_block = '<li class="feature-block">' +
                '<div class="feature-block-expanded">' +
                '<ul class="feature-column-list non-list flat-list" id="'+acolumn_id+'">' +
                '</ul>' +
                '</div>'
            '</li>'
            var preacolumn = "acolumn-"+preActivityId+$(this).attr("id")
            $("#"+preacolumn).parent().parent().after(feature_block)

            var tcolumn_id = "tcolumn-"+t_num+$(this).attr("id")
            var feature_column= '<li class="feature-column vertical-list ">' +
                    '<ul class="card-list non-list ui-sortable" id="'+tcolumn_id+'">' +
                    '<div class="init-card" data-toggle="modal" data-target="#createStoryModal" onclick="labelCreateStory(this,'+t_num+')">' +
                    '<p class="title-placeholder">新建标签</p></div>' +
                    '</ul>' +
                    '</li>'
            $("#"+acolumn_id).append(feature_column)
        })
    }
    changeColumnWidth()
}

function createTask(element,activityId,t_posId) {
    var t_num ;

    var t_name = "未命名task";
    $.ajax({
        type: "POST",
        url: "/task/create",
        data: {"activityId": activityId, "name": t_name, "posId": t_posId},
        async: false,
        success: function (data) {
            t_num = data.data.id
        }
    })
    var tcard_id = "tcard-"+t_num
    var tmenu_id = "tmenu-"+t_num
    var tarea_id = "tarea-"+t_num
    var id;
    if(t_posId!=0){
        id = $(element).parent().parent().attr("id");
    }

    var new_id = t_posId+1
    var tmp = '<li class="task-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+tcard_id+'">\n' +
        '                                <textarea class="t-name-editor" maxlength="50" readonly="readonly" id="'+tarea_id+'">未命名task</textarea>\n' +
        '                                <div class="t-operation-menu" id="'+tmenu_id+'" style="display: none">\n' +
        '                                    <a onclick="deleteTask(this)"><img class="delete" title="删除" src="img/delete.png"></a>' +
        '                                    <a onclick="editTask(this)"><img class="edit" title="编辑" src="img/edit.png"></a>\n' +
        '                                    <a onclick="createTask(this,'+activityId+','+new_id+')"><img class="create" title="在右侧新建" src="img/create.png"></a>\n' +
        '                                </div>\n' +
        '                            </li>'

    if(t_posId!=0){
        $("#"+id).after(tmp)
        var tindex = id.lastIndexOf("-")
        var preTaskId = id.substr(tindex+1)
        $(".feature-block-list").each(function () {
            var tcolumn_id = "tcolumn-"+t_num+$(this).attr("id")
            var feature_column = '<li class="feature-column vertical-list ">' +
                    '<ul class="card-list non-list ui-sortable" id="'+tcolumn_id+'">' +
                    '<div class="init-card" data-toggle="modal" data-target="#createStoryModal" onclick="labelCreateStory(this,'+t_num+')">' +
                    '<p class="title-placeholder">新建标签</p></div>' +
                    '</ul>' +
                    '</li>'
            var pretcolumn = "tcolumn-"+preTaskId+$(this).attr("id")
            $("#"+pretcolumn).parent().after(feature_column)
        })
    }else {
        $(element).parent().append(tmp)
        $(element).remove()
        $(".feature-block-list").each(function () {
            var acolumn_id = "acolumn-"+activityId+$(this).attr("id")
            var tcolumn_id = "tcolumn-"+t_num+$(this).attr("id")
            var feature_column= '<li class="feature-column vertical-list ">' +
                    '<ul class="card-list non-list ui-sortable" id="'+tcolumn_id+'">' +
                    '<div class="init-card" data-toggle="modal" data-target="#createStoryModal" onclick="labelCreateStory(this,'+t_num+')">' +
                    '<p class="title-placeholder">新建标签</p></div>' +
                    '</ul>' +
                    '</li>'
            $("#"+acolumn_id).append(feature_column)
        })
    }
    changeColumnWidth()
}

function createStory(element,taskId,s_posId,iteration) {
    $("#taskId").val(taskId)
    $("#posId").val(s_posId)
    // alert(groupName)
    var groupName = $("#group"+iteration).text()
    $("#groupName").val(groupName)
    $("#iteration").val(iteration)
    var id = $(element).parent().parent().attr("id");
    $("#preId").val(id)
}

function labelCreateStory(element,taskId) {
    $("#taskId").val(taskId)
    var prev_obj = $(element).prev()
    var s_posId = -1
    var preId = -1
    if (prev_obj.length > 0) {
        var s_posStr = prev_obj.children("div").children().eq(2).attr("id")
        var index = s_posStr.lastIndexOf("-")
        s_posId = s_posStr.substr(index+1)
        preId = prev_obj.attr("id")
    }
    $("#posId").val(s_posId)
    var iterationStr = $(element).parent().attr("id")
    var iter_index = iterationStr.lastIndexOf("-")
    var iteration = iterationStr.substr(iter_index+1)
    var groupName = $("#group"+iteration).text()
    $("#groupName").val(groupName)
    $("#iteration").val(iteration)
    $("#preId").val(preId)
}

$("#createStorySubmit").click(function () {
    var taskId = $("#taskId").val()
    var name = $("#name").val()
    if (name == "") {
        alert("story名称不能为空")
        return
    }
    var storyPoint = $("#storyPoint").val()
    var priority = $("#priority").val()
    var description = $("#description").val()
    var posId = $("#posId").val()
    var acceptance = $("#acceptance").val()
    var groupName = $("#groupName").val()
    var iteration = $("#iteration").val()
    var preId = $("#preId").val()
    var data = {
        "taskId": taskId,
        "name": name,
        "storyPoint": storyPoint,
        "priority": priority,
        "description": description,
        "posId": posId,
        "acceptance": acceptance,
        "groupName": groupName,
        "iteration": iteration,
    }
    var s_num ;
    $.ajax({
        type: "POST",
        url: "/story/create",
        dataType:"json",
        contentType : 'application/json',
        data:JSON.stringify(data),
        async: false,
        success: function (data) {
            s_num = data.data.id
            var scard_id = "scard-"+s_num
            var smenu_id = "smenu-"+s_num
            var sarea_id = "sarea-"+s_num
            var new_posId = posId+1
            var story = '<li class="issue-card board-card doing" onmousemove="over(this)" onmouseleave="leave(this)" id="' + scard_id + '">' +
                '<p class="card-title" title="'+name+'" id="' + sarea_id + '">' + name + '</p>' +
                '<div class="operation-menu" id="' + smenu_id + '" style="display: none">' +
                '   <a onclick="deleteStory(this)"><img class="delete" title="删除" src="img/delete.png"></a>\' +' +
                '   <a data-toggle="modal" data-target="#modifyStoryModal" onclick="editStory(this,'+s_num+')"><img class="edit" title="编辑" src="img/edit.png"></a>' +
                '   <a data-toggle="modal" data-target="#createStoryModal" onclick="createStory(this,' + taskId + ',' + new_posId + ','+iteration+')" id="'+new_posId+'">' +
                '       <img class="create" title="在下方新建" src="img/createBelow.png">' +
                '   </a>' +
                '</div>' +
                '</li>'
            if (preId == -1) {
                var tcolumn_id = "tcolumn-"+taskId+"release-"+iteration
                $("#"+tcolumn_id).prepend(story)
            }
            else {
                $("#"+preId).after(story)
            }
            changeLineHeight()
        }
    })
    $("#name").val("")
    $("#storyPoint").val("")
    $("#priority").val("")
    $("#description").val("")
    $("#acceptance").val("")
})

function editActivity(ele) {
    var id = $(ele).parent().prev().attr("id");
    $("#"+id).removeAttr("readonly")
    $("#"+id).css("cursor","auto")
    $("#"+id).select()
    $("#"+id).blur(function () {
        $("#"+id).attr("readonly","readonly")
        $("#"+id).css("cursor","default")
        var index = id.lastIndexOf("-")
        var activityId=id.substr(index+1)
        var name = $("#"+id).val()
        $.ajax({
            type: "POST",
            url: "/activity/modify",
            data: {"id": activityId, "name": name},
            async: false,
            success: function (data) {

            }
        })
    })

}

function editTask(ele) {
    var id = $(ele).parent().prev().attr("id");
    $("#"+id).removeAttr("readonly")
    $("#"+id).css("cursor","auto")
    $("#"+id).select()
    $("#"+id).blur(function () {
        $("#"+id).attr("readonly","readonly")
        $("#"+id).css("cursor","default")
        var index = id.lastIndexOf("-")
        var taskId=id.substr(index+1)
        var name = $("#"+id).val()
        $.ajax({
            type: "POST",
            url: "/task/modify",
            data: {"id": taskId, "name": name},
            async: false,
            success: function (data) {

            }
        })
    })

}

function editStory(ele,s_id) {
    $.ajax({
        type: "GET",
        url: "/story/"+s_id,
        data: {},
        async: false,
        success: function (data) {
            var content = data.data
            $("#m_taskId").val(content.taskId)
            $("#m_id").val(content.id)
            $("#m_name").val(content.name)
            $("#m_storyPoint").val(content.storyPoint)
            if (content.priority == 2) {
                $("#m_priority").val('高')
            }
            else if (content.priority == 1) {
                $("#m_priority").val('中')
            }
            else {
                $("#m_priority").val('低')
            }
            // $("#m_priority").val(content.priority)
            $("#m_posId").val(content.posId)
            $("#m_groupName").val(content.groupName)
            $("#m_iteration").val(content.iteration)
            $("#m_description").val(content.description)
            $("#m_acceptance").val(content.acceptance)
        }
    })
}

$("#modifyStorySubmit").click(function () {
    var taskId = $("#m_taskId").val()
    var name = $("#m_name").val()
    if (name == "") {
        alert("story名称不能为空")
        return
    }
    var storyPoint = $("#m_storyPoint").val()
    var priority = $("#m_priority").val()
    var description = $("#m_description").val()
    var posId = $("#m_posId").val()
    var acceptance = $("#m_acceptance").val()
    var groupName = $("#m_groupName").val()
    var iteration = $("#m_iteration").val()
    var id = $("#m_id").val()
    var data = {
        "taskId": taskId,
        "name": name,
        "storyPoint": storyPoint,
        "priority": priority,
        "description": description,
        "posId": posId,
        "acceptance": acceptance,
        "groupName": groupName,
        "iteration": iteration,
    }
    var s_num ;
    $.ajax({
        type: "POST",
        url: "/story/modify?id="+id,
        dataType:"json",
        contentType : 'application/json',
        data:JSON.stringify(data),
        async: false,
        success: function (data) {
            var sarea_id = "sarea-" + id
            var nameobj = $("#"+sarea_id)
            nameobj.text(name)
            nameobj.attr("title",name)
        }
    })
})

function deleteActivity(ele) {
    var conf = confirm("此操作会删除该activity以及对应的所有task和story，确定要删除吗？");
    if(conf===true) {
        var id = $(ele).parent().parent().parent().parent().parent().attr("id");
        var activity_id = id.substr(id.lastIndexOf("-")+1);
        $("#"+id).remove();
        $(".feature-block-list").each(function () {
            var acolumn_id = "acolumn-"+activity_id+$(this).attr("id")
            $("#"+acolumn_id).parent().parent().remove();
        })
        $.ajax({
            type: "POST",
            url: "/activity/delete",
            data: {"id": activity_id},
            async: false,
            success: function (data) {

            }
        })

        if($("#head-container").children().length==0){
            init()
        }
    }

}

function deleteTask(ele) {
    var conf = confirm("此操作会删除该task以及对应的所有story，确定要删除吗？");
    if(conf===true) {
        var tcard_id = $(ele).parent().parent().attr("id");
        var task_id = tcard_id.substr(tcard_id.lastIndexOf("-")+1);
        var column_id = $(ele).parent().parent().parent().parent().parent().attr("id");
        var container = $(ele).parent().parent().parent();
        var new_card;
        if($("#"+tcard_id).parent().children().length==1) {
            var activity_id = column_id.substr(column_id.lastIndexOf("-")+1);
            var ncard_id = "n-card-"+task_id;
            new_card = '<div class="init-card" onclick="createTask(this,'+activity_id+',0)" id='+ncard_id+'><div class="title-placeholder">新建标签</div></div>';
        }
        container.append(new_card);
        $("#"+tcard_id).remove()
        $(".feature-block-list").each(function () {
            var tcolumn_id = "tcolumn-"+task_id+$(this).attr("id")
            $("#"+tcolumn_id).parent().remove();
        })
        $.ajax({
            type: "POST",
            url: "/task/delete",
            data: {"id": task_id},
            async: false,
            success: function (data) {

            }
        })
    }

}

function deleteStory(ele) {
    var id = $(ele).parent().parent().attr("id");
    var story_id = id.substr(id.lastIndexOf("-")+1);
    $("#"+id).remove();
    $.ajax({
        type: "POST",
        url: "/story/delete",
        data: {"id": story_id},
        async: false,
        success: function (data) {

        }
    })
}

$(document).ready(function () {
    $('.am-nav li').removeClass('am-active');
    $('.am-nav li:eq(1)').addClass('am-active');
    getRole()
    getIteration(proId)
    getActivity(proId)

    changeLineHeight()
    changeColumnWidth()
})

function changeLineHeight(){
    var winHeight = $(document).height();
    $(".column-border-line").css("height",winHeight);
}

function changeColumnWidth(){
    var winWidth = $(document).width();
    $(".release-border-line").css("width",winWidth);
}

function init () {
    var init_card = '<div class="column" id="init-column"><div class="column-container"><div class="init-card" id="init-card" onclick="createActivity(this,0,0)">\n' +
        '            <p class="title-placeholder">新建标签</p></div></div></div>'
    $("#head-container").append(init_card)
}

function getRole() {
    $.ajax({
        type: "GET",
        url: '/api/invite/role?proId='+proId+'&userId='+userId,
        async: false,
        success: function (data) {
            role = data.data.role
        }
    })

}
function getActivity(proId) {
    $.ajax({
        type: "GET",
        url: "/activity/list/"+proId,
        data: {},
        async: false,
        success: function (data) {
            var alist=data.data;
            if(alist.length!=0){
                for(var i in alist){
                    var a_name = alist[i].name
                    var a_id = alist[i].id
                    var a_posId = alist[i].posId
                    var column_id="column-"+a_id
                    var acard_id="acard-"+a_id
                    var amenu_id="amenu-"+a_id
                    var aarea_id="aarea-"+a_id
                    var tmp=""

                    $(".feature-block-list").each(function () {
                        var acolumn_id= "acolumn-"+a_id+$(this).attr("id")
                        var feature_block = '<li class="feature-block">' +
                            '<div class="feature-block-expanded">' +
                            '<ul class="feature-column-list non-list flat-list" id="'+acolumn_id+'">' +
                            '</ul>' +
                            '</div>'
                            '</li>'
                        $(this).append(feature_block)
                    })

                    $.ajax({
                        type: "GET",
                        url: "/task/list/"+a_id,
                        data: {},
                        async: false,
                        success: function (data) {
                            var tlist=data.data;
                            var task='<ul class="task-container">'
                            if(tlist.length==0){
                                task+='<div class="init-card" onclick="createTask(this,'+a_id+',0)"><div class="title-placeholder">新建标签</div></div>';
                            }
                            var t_posId
                            for(var j in tlist) {
                                var t_name = tlist[j].name
                                var t_id = tlist[j].id
                                var activityId = tlist[j].activityId
                                t_posId=tlist[j].posId
                                var tcard_id = "tcard-"+t_id
                                var tmenu_id = "tmenu-"+t_id
                                var tarea_id = "tarea-"+t_id
                                task+='<li class="task-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+tcard_id+'">\n' +
                                    '                                <textarea class="t-name-editor" maxlength="50" readonly="readonly" id="'+tarea_id+'">'+t_name+'</textarea>\n' +
                                    '                                <div class="t-operation-menu" id="'+tmenu_id+'" style="display: none">\n' +
                                    '                                    <a onclick="deleteTask(this)"><img class="delete" title="删除" src="img/delete.png"></a>' +
                                    '                                    <a onclick="editTask(this)"><img class="edit" title="编辑" src="img/edit.png"></a>\n' +
                                    '                                    <a onclick="createTask(this,'+activityId+','+t_posId+')"><img class="create" title="在右侧新建" src="img/create.png"></a>\n' +
                                    '                                </div>\n' +
                                    '                            </li>\n'

                                $(".feature-block-list").each(function () {
                                    var acolumn_id = "acolumn-"+a_id+$(this).attr("id")
                                    var tcolumn_id = "tcolumn-"+t_id+$(this).attr("id")
                                    var feature_column = '<li class="feature-column vertical-list ">' +
                                            '<ul class="card-list non-list ui-sortable" id="'+tcolumn_id+'">' +
                                            '<div class="init-card" data-toggle="modal" data-target="#createStoryModal" onclick="labelCreateStory(this,'+t_id+')">' +
                                            '<p class="title-placeholder">新建标签</p></div>' +
                                            '</ul>' +
                                            '</li>'
                                    $("#"+acolumn_id).append(feature_column)
                                })

                                $.ajax({
                                    type: "GET",
                                    url: "/story/list/" + t_id,
                                    data: {},
                                    async: false,
                                    success: function (data) {
                                        var slist = data.data;
                                        var s_posId
                                        for (var k in slist) {
                                            var s_name = slist[k].name
                                            var s_id = slist[k].id
                                            s_posId = slist[k].posId
                                            var groupname = String(slist[k].groupName)
                                            var iteration = slist[k].iteration
                                            var iterid = "release-"+iteration
                                            var acolumn_id = "tcolumn-"+t_id+iterid
                                            var scard_id = "scard-" + s_id
                                            var smenu_id = "smenu-" + s_id
                                            // console.log(smenu_id)
                                            var sarea_id = "sarea-" + s_id
                                            var spos_id = "spos-" + s_posId
                                            // var story = '<div class="story-card" onmousemove="over(this)" onmouseleave="leave(this)" id="' + scard_id + '">\n' +
                                            //     '                                <textarea class="s-name-editor" maxlength="50" readonly="readonly" id="' + sarea_id + '">' + s_name + '</textarea>\n' +
                                            //     '                                <div class="s-operation-menu" id="' + smenu_id + '" style="display: none">\n' +
                                            //     '                                    <a onclick="deleteStory(this)"><img class="delete" title="删除" src="img/delete.png"></a>' +
                                            //     '                                    <a onclick="editStory(this)"><img class="edit" title="编辑" src="img/edit.png"></a>\n' +
                                            //     '                                    <a onclick="createStory(this,' + t_id + ',' + s_posId + ')"><img class="create" title="在下方新建" src="img/create.png"></a>\n' +
                                            //     '                                </div>\n' +
                                            //     '                            </div>\n'
                                            var story = '<li class="issue-card board-card doing" onmousemove="over(this)" onmouseleave="leave(this)" id="' + scard_id + '">' +
                                                '<p class="card-title" title="'+s_name+'" id="' + sarea_id + '">' + s_name + '</p>' +
                                                '<div class="operation-menu" id="' + smenu_id + '" style="display: none">' +
                                                '   <a onclick="deleteStory(this)"><img class="delete" title="删除" src="img/delete.png"></a>\' +' +
                                                '   <a data-toggle="modal" data-target="#modifyStoryModal" onclick="editStory(this,'+s_id+')"><img class="edit" title="编辑" src="img/edit.png"></a>' +
                                                '   <a data-toggle="modal" data-target="#createStoryModal" onclick="createStory(this,' + t_id + ',' + s_posId + ','+iteration+')" id="'+spos_id+'">' +
                                                '       <img class="create" title="在下方新建" src="img/createBelow.png">' +
                                                '   </a>' +
                                                '</div>' +
                                                '</li>'
                                            $("#"+acolumn_id).prepend(story)
                                        }
                                    }
                                })
                            }
                            task+="</ul>"
                            tmp = '<div class="column" id="'+column_id+'"><div class="column-container"><div class="column-border-line" style="min-height: 600px;"></div><div class="activity-container">\n' +
                                '                            <div class="activity-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+acard_id+'">\n' +
                                '                                <textarea class="a-name-editor" maxlength="50" readonly="readonly" id="'+aarea_id+'">'+a_name+'</textarea>\n' +
                                '                                <div class="a-operation-menu" id="'+amenu_id+'" style="display: none">\n' +
                                '                                    <a onclick="deleteActivity(this)"><img class="delete" title="删除" src="img/delete.png"></a>' +
                                '                                    <a onclick="editActivity(this)"><img class="edit" title="编辑" src="img/edit.png"></a>\n' +
                                '                                    <a onclick="createActivity(this,'+a_posId+',0)"><img class="create" title="在右侧新建" src="img/create.png"></a>\n' +
                                '                                </div>\n' +
                                '                            </div>\n' +
                                '                        </div>'+ task
                        }
                    })

                    tmp+='</div></div>'
                    $("#head-container").append(tmp)
                }
            }
            else {
                init()
            }


        }
    })
    if (role == 1) {
        $(".init-card").attr("onclick","")
        $(".init-card").attr("data-target","")
    }
}

function getIteration(proId) {
    $.ajax({
        type: "GET",
        url: "/story/iterList/" + proId,
        data: {},
        async: false,
        success: function (data) {
            var ilist = data.data;
            if(ilist.length!=0) {
                for (var k in ilist) {
                    var iteration = ilist[k].iteration
                    var groupName = ilist[k].groupName
                    // alert(iteration)
                    // alert(groupName)
                    var release = '<div class="release-group">\n' +
                        '                <div class="release-header">\n' +
                        '                    <div class="release-border-line" style="min-width: 1000px;"></div>\n' +
                        '                    <div class="expand-switcher">\n' +
                        '                        <i class="fa fa-minus"></i>\n' +
                        '                    </div>\n' +
                        '                    <div class="release-name">\n' +
                        '                        <span id="group' + iteration + '">' + groupName + '</span>\n' +
                        '                        <input type="text" class="form-control name-editor" placeholder="请输入release名称" maxlength="50">\n' +
                        '                    </div>\n' +
                        '                    <div class="dropdown">\n' +
                        '                        <button class="transparent-btn dropdown-toggle" type="button" data-toggle="dropdown">\n' +
                        '                            <i class="fa fa-caret-down fa-fw"></i>\n' +
                        '                        </button>\n' +
                        '                        <ul class="dropdown-menu">\n' +
                        '                            <li><a href="javascript: void(0)" class="edit-name">编辑名称</a></li>\n' +
                        '                            <li class="disabled" title="有从属卡片不能被删除"><a href="javascript: void(0)" class="delete-release">删除分组</a></li>\n' +
                        '                            <li><a href="javascript: void(0)" class="create-release below">在下方新建分组</a></li>\n' +
                        '                        </ul>\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </div>'
                    var block_container = '<div class="feature-block-container expanded">' +
                        '<ul class="feature-block-list non-list flat-list" id="release-' + iteration + '">' +
                        '</ul>' +
                        '</div>'
                    release += block_container
                    $(".board-body").append(release)
                }
            }
            else {
                var release = '<div class="release-group">\n' +
                    '                <div class="release-header">\n' +
                    '                    <div class="release-border-line" style="min-width: 1000px;"></div>\n' +
                    '                    <div class="expand-switcher">\n' +
                    '                        <i class="fa fa-minus"></i>\n' +
                    '                    </div>\n' +
                    '                    <div class="release-name">\n' +
                    '                        <span id="group0">默认分组</span>\n' +
                    '                        <input type="text" class="form-control name-editor" placeholder="请输入release名称" maxlength="50">\n' +
                    '                    </div>\n' +
                    '                    <div class="dropdown">\n' +
                    '                        <button class="transparent-btn dropdown-toggle" type="button" data-toggle="dropdown">\n' +
                    '                            <i class="fa fa-caret-down fa-fw"></i>\n' +
                    '                        </button>\n' +
                    '                        <ul class="dropdown-menu">\n' +
                    '                            <li><a href="javascript: void(0)" class="create-release below">在上方新建分组</a></li>\n' +
                    '                        </ul>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </div>'
                var block_container = '<div class="feature-block-container expanded">' +
                    '<ul class="feature-block-list non-list flat-list" id="release-0">' +
                    '</ul>' +
                    '</div>'
                release += block_container
                $(".board-body").append(release)
            }
        }
    })
}

// function releaseExist(groupname) {
//     $("div.release-name").each(function(){
//         var group=$(this).children().first()
//         if (typeof groupname != typeof undefined && group==groupname){
//             return true
//         }
//     })
//     return false
// }