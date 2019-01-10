function over(ele){
    var id = $(ele).children("div").attr("id")
    $("#"+id).css("display","")
}

function leave(ele){
    var id = $(ele).children("div").attr("id")
    $("#"+id).css("display","none")
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
        url: "http://localhost:8888/activity/create",
        data: {"proId":proId, "name":a_name, "posId":a_posId},
        async: false,
        success: function (data){
            activityId = data.data.id;
            a_num = activityId
            $.ajax({
                type: "POST",
                url: "http://localhost:8888/task/create",
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
        '                    <div class="column-container">\n' +
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
        '                        <div class="task-container">\n' +
        '                            <div class="task-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+tcard_id+'">\n' +
        '                                <textarea class="t-name-editor" maxlength="50" readonly="readonly" id="'+tarea_id+'">未命名task</textarea>\n' +
        '                                <div class="t-operation-menu" id="'+tmenu_id+'" style="display: none">\n' +
        '                                    <a onclick="deleteTask(this)"><img class="delete" title="删除" src="img/delete.png"></a>' +
        '                                    <a onclick="editTask(this)"><img class="edit" title="编辑" src="img/edit.png"></a>\n' +
        '                                    <a onclick="createTask(this,'+activityId+',1)"><img class="create" title="在右侧新建" src="img/create.png"></a>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '\n' +
        '                </div>'

    if(a_posId==0){
        $("#init-column").remove()
        $("#head-container").append(tmp)
    }else {
        $("#"+id).after(tmp)
    }

}

function createTask(element,activityId,t_posId) {
    var t_num ;

    var t_name = "未命名task";
    $.ajax({
        type: "POST",
        url: "http://localhost:8888/task/create",
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
    var tmp = '<div class="task-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+tcard_id+'">\n' +
        '                                <textarea class="t-name-editor" maxlength="50" readonly="readonly" id="'+tarea_id+'">未命名task</textarea>\n' +
        '                                <div class="t-operation-menu" id="'+tmenu_id+'" style="display: none">\n' +
        '                                    <a onclick="deleteTask(this)"><img class="delete" title="删除" src="img/delete.png"></a>' +
        '                                    <a onclick="editTask(this)"><img class="edit" title="编辑" src="img/edit.png"></a>\n' +
        '                                    <a onclick="createTask(this,'+activityId+','+new_id+')"><img class="create" title="在右侧新建" src="img/create.png"></a>\n' +
        '                                </div>\n' +
        '                            </div>'

    if(t_posId!=0){
        $("#"+id).after(tmp)
    }else {
        $(element).parent().append(tmp)
        $(element).remove()
    }

}

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
            url: "http://localhost:8888/activity/modify",
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
            url: "http://localhost:8888/task/modify",
            data: {"id": taskId, "name": name},
            async: false,
            success: function (data) {

            }
        })
    })

}

function deleteActivity(ele) {
    var conf = confirm("此操作会删除该activity以及对应的所有task和story，确定要删除吗？");
    if(conf===true) {
        var id = $(ele).parent().parent().parent().parent().parent().attr("id");
        var activity_id = id.substr(id.lastIndexOf("-")+1);
        $("#"+id).remove();
        //TODO delete story
        $.ajax({
            type: "POST",
            url: "http://localhost:8888/activity/delete",
            data: {"id": activity_id},
            async: false,
            success: function (data) {

            }
        })
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
        $.ajax({
            type: "POST",
            url: "http://localhost:8888/task/delete",
            data: {"id": task_id},
            async: false,
            success: function (data) {

            }
        })
        //TODO delete story
    }



}
$(document).ready(function () {
    getActivity(proId)
})

function init () {
    var init_card = '<div class="column" id="init-column"><div class="column-container"><div class="init-card" id="init-card" onclick="createActivity(this,0,0)">\n' +
        '            <p class="title-placeholder">新建标签</p></div></div></div>'
    $("#head-container").append(init_card)
}

function getActivity(proId) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8888/activity/list/"+proId,
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

                    $.ajax({
                        type: "GET",
                        url: "http://localhost:8888/task/list/"+a_id,
                        data: {},
                        async: false,
                        success: function (data) {
                            var tlist=data.data;
                            var task='<div class="task-container">'
                            var t_posId
                            for(var j in tlist) {
                                var t_name = tlist[j].name
                                var t_id = tlist[j].id
                                var activityId = tlist[j].activityId
                                t_posId=tlist[j].posId
                                var tcard_id = "tcard-"+t_id
                                var tmenu_id = "tmenu-"+t_id
                                var tarea_id = "tarea-"+t_id
                                task+='<div class="task-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+tcard_id+'">\n' +
                                    '                                <textarea class="t-name-editor" maxlength="50" readonly="readonly" id="'+tarea_id+'">'+t_name+'</textarea>\n' +
                                    '                                <div class="t-operation-menu" id="'+tmenu_id+'" style="display: none">\n' +
                                    '                                    <a onclick="deleteTask(this)"><img class="delete" title="删除" src="img/delete.png"></a>' +
                                    '                                    <a onclick="editTask(this)"><img class="edit" title="编辑" src="img/edit.png"></a>\n' +
                                    '                                    <a onclick="createTask(this,'+activityId+','+t_posId+')"><img class="create" title="在右侧新建" src="img/create.png"></a>\n' +
                                    '                                </div>\n' +
                                    '                            </div>\n'
                            }
                            task+="</div>"
                            tmp = '<div class="column" id="'+column_id+'"><div class="column-container"><div class="activity-container">\n' +
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
}