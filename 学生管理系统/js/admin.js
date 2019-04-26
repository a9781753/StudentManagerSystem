function pageClick(k) {
	$(k).parent().find("div").removeClass("active");
	$(k).addClass("active");
	$("#flTitle").text($(k).text());
    $("#content").text($(k).text());
}

//添加学生
$(function(){

    //获取btn按钮
    $("#addBtn").click(function(){


        var name=$("#name").val();
        var sex=$("#sex").val();
        var age=$("#age").val();
        var _class=$("#_class").val();
        var score=$("#score").val();
        var password=$("#password").val();
        //发送请求

        $.ajax({
            xhrFields: {withCredentials: true},
            url:"http://www.zcg.com/StudentManagerSystem/student.do?operation=4",
            type:"get",
            dataType:"json",
            data:{

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


//根据ID找学生
$(function(){

    //获取btn按钮
    $("#findsByidBtn").click(function(){
        var id=$("#findid").val();
        //发送请求

        $.ajax({
            xhrFields: {withCredentials: true},
            type: "GET",
            dataType:"json",
            url: "http://www.zcg.com/StudentManagerSystem/student.do?operation=6",
            data:{
                "id":$("#findid").val()
            },
            success: function (res) {
                $("#byids .adminfindstable1").empty();
                if (res.status == 0) {
                        $("#byids .adminfindstable1").append(`<tr class="adds_tr"><td>${res.data.id}</td><td>${res.data.name}</td><td>${res.data.sex}</td><td>${res.data.age}</td><td>${res.data._class}</td><td>${res.data.score}</td><td>${res.data.password}</td><td>${res.data.rgetime}</td><td>${res.data.modtime}</td></tr>`);
                }else if (res.status == 8) {
                    alert("未找到该ID");
                }
            },
            error: function () {

            },

        })
    });
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





//查看全部学生信息
var finds = $(function() {
    $("#findsBtn").click(function () {
        $.ajax({
            xhrFields: {withCredentials: true},
            type: "GET",
            dataType:"json",
            url: "http://www.zcg.com/StudentManagerSystem/student.do?operation=5",
            success: function (res) {

                if (res.status == 0) {
                    $(".adminfindstable").empty();
                    for (var i = 0; i < res.data.length; i++) {
                        console.log(res.data[i]);

                        $(".adminfindstable").append(`<tr class="adds_tr"><td>${res.data[i].id}</td><td>${res.data[i].name}</td><td>${res.data[i].sex}</td><td>${res.data[i].age}</td><td>${res.data[i]._class}</td><td>${res.data[i].score}</td><td>${res.data[i].password}</td><td>${res.data[i].rgetime}</td><td>${res.data[i].modtime}</td></tr>`);
                    }
                }
            },
            error: function () {

            },

        })

    })
})










//获取在线人数
$(function(){

    ;
        //发送请求

        $.ajax({
            xhrFields: {withCredentials: true},
            url:"http://www.zcg.com/StudentManagerSystem/admin.do?operation=9",
            type:"get",
            dataType:"json",

            success:function (res) {
                var name = localStorage.getItem("name");
                if(res.status == 0){
                    $(".top").append(`<div class="thisUser">当前用户在线人数：${res.data}</div><div class="thisUser">当前用户：${name}</div>`);

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




//添加课程
$(function(){

    //获取btn按钮
    $("#addcBtn").click(function(){

        var cname=$("#cname").val();
        //发送请求

        $.ajax({
            xhrFields: {withCredentials: true},
            url:"http://www.zcg.com/StudentManagerSystem/course.do?operation=10",
            type:"get",
            dataType:"json",
            data:{

                "cname":$("#cname").val()
            },
            success:function (res) {
                if(res.status == 0){
                    alert(res.msg);
                    window.location.href="http://localhost:63342/学生管理系统/findallcourseByPage.html";
                }else if(res.status == 15){
                    alert(res.msg);
                }
                console.log(res);
            },
            error:function (res) {
                console.log(res);
            }

        })
    });



})

