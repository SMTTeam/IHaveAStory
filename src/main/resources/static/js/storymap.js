function over(ele){
    var id = $(ele).children("div").attr("id")
    $("#"+id).css("display","")
}

function leave(ele){
    var id = $(ele).children("div").attr("id")
    $("#"+id).css("display","none")
}

function createActivity(element,a_posId,t_posId){
    var id = $(element).parent().parent().parent().parent().parent().attr("id");
    var a_num = 1;
    var t_num = 1;
    $.ajax({
        type: "GET",
        url: "http://localhost:8888/activity/maxId",
        data: {},
        async: false,
        success: function (data){
            a_num += data.data
        }
    });
    $.ajax({
        type: "GET",
        url: "http://localhost:8888/task/maxId",
        data: {},
        async: false,
        success: function (data){
            t_num += data.data
        }
    });

    var a_name = "未命名activity"
    var t_name = "未命名task"
    var activityId
    $.ajax({
        type: "POST",
        url: "http://localhost:8888/activity/create",
        data: {"proId":proId, "name":a_name, "posId":a_posId},
        async: false,
        success: function (data){
            activityId = data.data.id;
            $.ajax({
                type: "POST",
                url: "http://localhost:8888/task/create",
                data: {"activityId": activityId, "name": t_name, "posId": t_posId},
                async: false,
                success: function (data) {

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
        '                                    <a th:title="编辑" onclick="editActivity(this)"><img class="edit" src="img/edit.png"></a>\n' +
        '                                    <a th:title="在右侧新建" onclick="createActivity(this,'+a_posId+1+','+t_posId+1+')"><img class="create" src="img/create.png"></a>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                        <div class="task-container">\n' +
        '                            <div class="task-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+tcard_id+'">\n' +
        '                                <textarea class="t-name-editor" maxlength="50" readonly="readonly" id="'+tarea_id+'">未命名task</textarea>\n' +
        '                                <div class="t-operation-menu" id="'+tmenu_id+'" style="display: none">\n' +
        '                                    <a th:title="编辑" onclick="editTask(this)"><img class="edit" src="img/edit.png"></a>\n' +
        '                                    <a th:title="在右侧新建" onclick="createTask(this,'+activityId+','+t_posId+1+')"><img class="create" src="img/create.png"></a>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '\n' +
        '                </div>'

    $("#"+id).after(tmp)


}

function createTask(element,activityId,t_posId) {
    var t_num = 1;
    $.ajax({
        type: "GET",
        url: "http://localhost:8888/task/maxId",
        data: {},
        async: false,
        success: function (data){
            t_num += data.data
        }
    });

    var t_name = "未命名task"
    $.ajax({
        type: "POST",
        url: "http://localhost:8888/task/create",
        data: {"activityId": activityId, "name": t_name, "posId": t_posId},
        async: false,
        success: function (data) {

        }
    })
    var tcard_id = "tcard-"+t_num
    var tmenu_id = "tmenu-"+t_num
    var tarea_id = "tarea-"+t_num
    var id = $(element).parent().parent().attr("id");
    var tmp = '<div class="task-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+tcard_id+'">\n' +
        '                                <textarea class="t-name-editor" maxlength="50" readonly="readonly" id="'+tarea_id+'">未命名task</textarea>\n' +
        '                                <div class="t-operation-menu" id="'+tmenu_id+'" style="display: none">\n' +
        '                                    <a th:title="编辑" onclick="editTask(this)"><img class="edit" src="img/edit.png"></a>\n' +
        '                                    <a th:title="在右侧新建" onclick="createTask(this,'+activityId+','+t_posId+1+')"><img class="create" src="img/create.png"></a>\n' +
        '                                </div>\n' +
        '                            </div>'
    $("#"+id).after(tmp)

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


$(document).ready(function () {
    getActivity(proId)
})

function getActivity(proId) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8888/activity/list/"+proId,
        data: {},
        async: false,
        success: function (data) {
            var alist=data.data;
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
                                '                                    <a th:title="编辑" onclick="editTask(this)"><img class="edit" src="img/edit.png"></a>\n' +
                                '                                    <a th:title="在右侧新建" onclick="createTask(this,'+activityId+','+t_posId+')"><img class="create" src="img/create.png"></a>\n' +
                                '                                </div>\n' +
                                '                            </div>\n' 
                        }
                        task+="</div>"
                        tmp = '<div class="column" id="'+column_id+'"><div class="column-container"><div class="activity-container">\n' +
                            '                            <div class="activity-card" onmousemove="over(this)" onmouseleave="leave(this)" id="'+acard_id+'">\n' +
                            '                                <textarea class="a-name-editor" maxlength="50" readonly="readonly" id="'+aarea_id+'">'+a_name+'</textarea>\n' +
                            '                                <div class="a-operation-menu" id="'+amenu_id+'" style="display: none">\n' +
                            '                                    <a th:title="编辑" onclick="editActivity(this)"><img class="edit" src="img/edit.png"></a>\n' +
                            '                                    <a th:title="在右侧新建" onclick="createActivity(this,'+a_posId+','+t_posId+')"><img class="create" src="img/create.png"></a>\n' +
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                        </div>'+ task
                        console.log(task)
                    }
                })

                tmp+='</div></div>'
                $("#head-container").append(tmp)
            }

        }
    })
}