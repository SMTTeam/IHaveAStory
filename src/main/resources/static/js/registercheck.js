$(function(){
        window.Parsley.addAsyncValidator("checkEmail" , function (xhr) {
            var jsonData = JSON.parse(xhr.responseText);
            var code = jsonData.code;
            // alert(responsedata.get("code"));
            if (code === 200){
                return false;
            }else {
                return true;
            }
        },'/checkemail',{"dataType": "json","data":{"emailname":function(){return $('#signupInputEmail').val();}}});
    }
)