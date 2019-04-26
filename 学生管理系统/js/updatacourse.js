$(function(){
    var value = localStorage.getItem("value");
    var cname = localStorage.getItem('cname');
    console.log(value)
    $("#cid").attr("value",value);
    $("#cname").attr("placeholder",cname);

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
    $("#updatacBtn").click(function(){

        var cid=$("#cid").val();
        var cname=$("#cname").val();
        //发送请求

        $.ajax({
            xhrFields: {withCredentials: true},
            url:"http://www.zcg.com/StudentManagerSystem/course.do?operation=12",
            type:"get",
            dataType:"json",
            data:{
                "cid":$("#cid").val(),
                "cname":$("#cname").val(),


            },

            success:function (res) {
                if(res.status == 0){
                    alert(res.msg);
                    window.location.href="http://localhost:63342/学生管理系统/findallcourse.html";
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
