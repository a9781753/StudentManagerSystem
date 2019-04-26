$(function(){
    var value = localStorage.getItem("value");
    var name = localStorage.getItem('name');
    var sex = localStorage.getItem('sex');
    var age = localStorage.getItem('age');
    var _class = localStorage.getItem('_class');
    var score = localStorage.getItem('score');
    var password = localStorage.getItem('password');

    $("#id").attr("value",value);
    $("#name").attr("placeholder",name);
    $("#sex").attr("placeholder",sex);
    $("#age").attr("placeholder",age);
    $("#_class").attr("placeholder",_class);
    $("#score").attr("placeholder",score);
    $("#password").attr("placeholder",password);

})







//管理员退出
$(function() {
    $(".thisUser").click(function () {
        $.ajax({
            xhrFields: {withCredentials: true},
            url: "http://www.zcg.com/StudentManagerSystem/admin.do?operation=3",
            type: "get",
            dataType: "json",
            success: function (res) {

                window.location.href = "http://localhost:63342/学生管理系统/index.html";
                console.log(res);
            },
            error: function (res) {
                console.log(res);
            }

        })
    });

})


//获取在线人数
$(function(){


    //发送请求

    $.ajax({
        xhrFields: {withCredentials: true},
        url:"http://www.zcg.com/StudentManagerSystem/admin.do?operation=9",
        type:"get",
        dataType:"json",

        success:function (res) {
            if(res.status == 0){
                $(".top").append(`<div class="thisUser">当前用户在线人数：${res.data}</div>`);
            }else if(res.status == 9){
                alert(res.msg);
            }
            console.log(res);
        },
        error:function (res) {
            console.log(res);
        }

    })
})


//提交修改请求
$(function(){

    //获取btn按钮
    $("#updataBtn").click(function(){

        var id=$("#id").val();
        var name=$("#name").val();
        var sex=$("#sex").val();
        var age=$("#age").val();
        var _class=$("#_class").val();
        var score=$("#score").val();
        var password=$("#password").val();
        //发送请求

        $.ajax({
            xhrFields: {withCredentials: true},
            url:"http://www.zcg.com/StudentManagerSystem/student.do?operation=7",
            type:"get",
            dataType:"json",
            data:{
                "id":$("#id").val(),
                "name":$("#name").val(),
                "sex":$("#sex").val(),
                "age":$("#age").val(),
                "_class":$("#_class").val(),
                "score":$("#score").val(),
                "password":$("#password").val(),

            },

            success:function (res) {
                if(res.status == 0){
                    alert(res.msg);
                    window.location.href="http://localhost:63342/学生管理系统/findall.html";
                }else if(res.status == 9){
                    alert(res.msg    );
                }
                console.log(res);
            },
            error:function (res) {
                console.log(res);
            }

        })
    });



})
