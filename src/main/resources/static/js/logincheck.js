// 初始化
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

// $(function(){
//         window.Parsley.addAsyncValidator("checkExisting" , function (xhr) {
//             // var responsedata = $.parseJSON(xhr.responseText);
//             var jsonData = JSON.parse(xhr.responseText);
//             // alert(xhr.responseText);
//             // if(d.code == 200){
//             //     return !d.data.object;
//             // }
//             // console.log(xhr.responseText);
//             // var code = responsedata.get("code");
//             // console.log(xhr.toString());
//             // alert(jsonData.code);
//             var code = jsonData.code;
//             // alert(responsedata.get("code"));
//             if (code === 200){
//                 return true;
//             }else {
//                 return false;
//             }
//         },'/checkEmail',{"dataType": "json","data":{"emailname":function(){return $('#loginMailOrName').val();}}});
//     }
// )

// window.onload = function () {
//     alert('aaaa');
// }


function focusNextInput(thisInput){
    var inputs = document.getElementsByTagName("input");

    for( var i = 0 ; i < inputs.length ; i ++ ){
        if( i === inputs.length-1 ){
            $('form').submit();
            break;
        }else if(thisInput == inputs[i]){
            inputs[i+1].focus();
            break;
        }
    }
    return false;
}

function submitForm() {
    var email = $.trim($("#loginEmail").val());
    var password = $.trim($("#loginPsd").val());
    if (email=='' || password=='' ){
        return ;
    }
    $.ajax({
        type:'POST',
        url:'/checklogin',
        data:{"email":email,"pwd":password},
        dataType:'json',
        timeout:1000,
        error:function () {
            var tip = $("#formtip");
            tip.css("color","red");
            $("#loginEmail").val('');
            $("#loginPsd").val('');
            tip.html("登录失败！请重试。");
        },
        success:function(result){
            var tip = $("#formtip");
            //result为json数据  类似{"code":200,"message":"用户名或密码错误","data":null}
            if(result.code === 200){
                //若登录成功，跳转到"/main.html"
                // alert('成功');
                tip.html('');//清空提示文字
                window.location.href='/project';
            }else {
                // alert('失败');
                tip.css("color","red");
                tip.html(result.message);//设置提示文字
                $("#loginPsd").val('');
            }
        }
    });
};


$("#loginButton").click(function () {

    var email = $.trim($("#loginEmail").val());
    var password = $.trim($("#loginPsd").val());
    if (email=='' || password=='' ){
        return ;
    }
    $.ajax({
        type:'POST',
        url:'/checklogin',
        data:{"email":email,"pwd":password},
        dataType:'json',
        timeout:1000,
        error:function () {
            var tip = $("#formtip");
            tip.css("color","red");
            $("#loginEmail").val('');
            $("#loginPsd").val('');
            tip.html("登录失败！请重试。");
        },
        success:function(result){
            // alert('dddd');
            var tip = $("#formtip");
            //result为json数据  类似{"code":200,"message":"用户名或密码错误","data":null}
            if(result.code === 200){
                //若登录成功，跳转到"/main.html"
                tip.html('');//清空提示文字
                popMsg('登录成功!');
                window.location.href='/project';
            }else {
                popMsg('登录失败!');
                tip.css("color","red");
                tip.html(result.message);//设置提示文字
                $("#loginPsd").val('');
            }
        }
    });
});

$("#registerButton").click(function () {
   window.location.href='/register';
});