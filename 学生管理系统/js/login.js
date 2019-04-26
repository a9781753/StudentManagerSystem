/**
 * Created by Administrator on 2019\4\15 0015.
 */
$(function(){

    //获取btn按钮
    $("#loginBtn").click(function(){

       //获取username
         var username=$("#username").val();
         //获取password
         var password=$("#password").val();
        //获取role
        var role=$("#role").val();
        //发送请求
        var url = null;
        var href = null;
        var operation = null;

        if(role == 1){
            url = "http://www.zcg.com/StudentManagerSystem/admin.do";
        }else if(role == 2){
            url = "http://www.zcg.com/StudentManagerSystem/studentlogin.do";
        }
        if(role == 1){
            href = "admin.html";
        }else if(role == 2){
            href = "student.html";
        }
        if(role == 1){
            operation = "1";
        }else if(role == 2){
            operation = "15";
        }
        $.ajax({
            xhrFields: {withCredentials: true},
            url:url,
            type:"get",
            dataType:"json",
            data:{
                "operation":operation,
                "username":$("#username").val(),
                "id":$("#username").val(),
                "password":$("#password").val()
            },
            success:function (res) {

                if(res.status == 0) {
                    localStorage.setItem("name",res.data.username);
                    localStorage.setItem("studentname",res.data.name);
                    localStorage.setItem("studentid",res.data.id);

                    window.location.href = href;
                }else if(res.status == 12){
                    alert(res.msg);
                }else if(res.status == 11){
                    alert("非法输入，学号为数字格式！")
                }

            },
            error:function (res) {

            }

        })



    });


})



//学生注册
$(function(){

    //获取btn按钮
    $("#regBtn").click(function(){


        //获取password
        var password=$("#password").val();
        //获取role
        var sex=$("#sex").val();
        var age=$("#age").val();
        var _class=$("#_class").val();
        var name=$("#name").val();
        var score=$("#score").val();

        $.ajax({
            xhrFields: {withCredentials: true},
            url:"http://www.zcg.com/StudentManagerSystem/studentlogin.do?operation=14",
            type:"get",
            dataType:"json",
            data:{
                "password":password,
                "age":age,
                "sex":sex,
                "_class":_class,
                "score":score,
                "name":name,
            },
            success:function (res) {
                console.log(res.status);
                if(res.status == 0) {
                    alert(res.msg);
                    window.location.href = "http://localhost:63342/学生管理系统/index.html";
                }else if(res.status == 250){
                    alert(res.msg);
                }
            },
            error:function (res) {
                alert(res.msg);
            }

        })



    });


})

