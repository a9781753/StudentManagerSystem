function pageClick(k) {
	$(k).parent().find("div").removeClass("active");
	$(k).addClass("active");
	$("#flTitle").text($(k).text());
    $("#content").text($(k).text());
}

//加载初始页面
$(function(){


    $("#up").removeAttr("disabled")
    $("#down").removeAttr("disabled")
            var pageSize = 7;
            var pageNo = 1;
            $.ajax({
            xhrFields: {withCredentials: true},
            url:"http://www.zcg.com/StudentManagerSystem/course.do?operation=21",
            type:"get",
            dataType:"json",
            async:false,
            data:{
                "pageNo":pageNo,
                "pageSize":pageSize,
            },

            success:function (res) {

                if(res.hasbefore==false){
                    $("#up").attr('disabled',true);
                }
                if(res.hasnext==false){
                    $("#down").attr('disabled',true);
                }

                if (res.totalPage >0) {
                        $(".adminfindstablec").empty();

                    for (var i = 0; i < res.data.length; i++) {

                        $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" value="${res.data[i].cid}" xgcname="${res.data[i].cname}">修改</button>&nbsp;<button class="alldelc" delidc="${res.data[i].cid}">删除</button></td></tr>`);

                    }

                    for(var i=1; i<=res.totalPage;i++){
                        $("#nbt").append(`<input type="button" class="pn" value="${i}"></input>`);
                    }
                }
                $(".pn[value='1']").attr("id","hdpn");
                localStorage.setItem("pageNo",res.currentPage);
                $(function(){


                    //获取btn按钮
                    $(".updatacbtn").click(function(){
                        var value=$(this).attr("value");
                        var cname=$(this).attr("xgcname");

                        localStorage.setItem('value', value);
                        localStorage.setItem('cname', cname);


                        window.location.href = "http://localhost:63342/学生管理系统/updatacourse.html";

                    })


                });
                $(function(){

                    //获取btn按钮
                    $(".alldelc").click(function(){
                        var cid=$(this).attr("delidc");
                        //发送请求
                        $.ajax({
                            xhrFields: {withCredentials: true},
                            type: "GET",
                            dataType:"json",
                            async:false,
                            url: "http://www.zcg.com/StudentManagerSystem/course.do?operation=13",
                            data:{
                                "cid":cid
                            },
                            success: function (res) {

                                if (res.status == 0) {
                                    alert(res.msg);
                                    window.location.href = "http://localhost:63342/学生管理系统/findallcourse.html";
                                }else if (res.status == 8) {
                                    alert(res.msg);
                                }
                            },
                            error: function () {

                            },

                        })
                    });
                })
            },
            error: function () {

            }

        })


})


$(function () {

    var pageSize = 7;
    $(".pn").click(function () {
        $("#down").removeAttr("disabled")
        $("#up").removeAttr("disabled")
        var pageNo = $(this).attr("value");
        $(".pn").removeAttr("id");

        $(this).attr("id","hdpn");

        $.ajax({
            xhrFields: {withCredentials: true},
            url: "http://www.zcg.com/StudentManagerSystem/course.do?operation=21",
            type: "get",
            dataType: "json",
            async:false,
            data: {
                "pageNo": pageNo,
                "pageSize": pageSize,
            },

            success: function (res) {
                if(res.hasnext==false){
                    $("#down").attr('disabled',true);
                }
                if(res.hasbefore==false){
                    $("#up").attr('disabled',true);
                }
                localStorage.setItem("pageNo",pageNo);
                console.log(res.currentPage)
                if (res.totalPage > 0) {
                    $(".adminfindstablec").empty();
                    for (var i = 0; i < res.data.length; i++) {

                        $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" value="${res.data[i].cid}" xgcname="${res.data[i].cname}">修改</button>&nbsp;<button class="alldelc" delidc="${res.data[i].cid}">删除</button></td></tr>`);

                    }
                    }
                $(function(){


                    //获取btn按钮
                    $(".updatacbtn").click(function(){
                        var value=$(this).attr("value");
                        var cname=$(this).attr("xgcname");

                        localStorage.setItem('value', value);
                        localStorage.setItem('cname', cname);


                        window.location.href = "http://localhost:63342/学生管理系统/updatacourse.html";

                    })


                });
                $(function(){

                    //获取btn按钮
                    $(".alldelc").click(function(){
                        var cid=$(this).attr("delidc");
                        //发送请求
                        $.ajax({
                            xhrFields: {withCredentials: true},
                            type: "GET",
                            dataType:"json",
                            async:false,
                            url: "http://www.zcg.com/StudentManagerSystem/course.do?operation=13",
                            data:{
                                "cid":cid
                            },
                            success: function (res) {

                                if (res.status == 0) {
                                    alert(res.msg);
                                    window.location.href = "http://localhost:63342/学生管理系统/findallcourse.html";
                                }else if (res.status == 8) {
                                    alert(res.msg);
                                }
                            },
                            error: function () {

                            },

                        })
                    });
                })

            },
            error: function () {

            }

        })
    })


})
$(function () {

    var pageSize = 7;

    $("#up").click(function () {
        $("#down").removeAttr("disabled")
        $("#up").removeAttr("disabled")
        var pageNo = parseInt(localStorage.getItem("pageNo"))-1;
        $(".pn").removeAttr("id");


        $.ajax({
            xhrFields: {withCredentials: true},
            url: "http://www.zcg.com/StudentManagerSystem/course.do?operation=21",
            type: "get",
            dataType: "json",
            async:false,
            data: {
                "pageNo": pageNo,
                "pageSize": pageSize,
            },

            success: function (res) {
                $(".pn[value='"+pageNo+"']").attr("id","hdpn");
                if(res.hasnext==false){
                    $("#down").attr('disabled',true);
                }
                if(res.hasbefore==false){
                    $("#up").attr('disabled',true);
                }
                if (res.totalPage > 0) {
                    $(".adminfindstablec").empty();
                    for (var i = 0; i < res.data.length; i++) {

                        $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" value="${res.data[i].cid}" xgcname="${res.data[i].cname}">修改</button>&nbsp;<button class="alldelc" delidc="${res.data[i].cid}">删除</button></td></tr>`);

                    }
                }
                localStorage.setItem("pageNo",res.currentPage);
                $(function(){


                    //获取btn按钮
                    $(".updatacbtn").click(function(){
                        var value=$(this).attr("value");
                        var cname=$(this).attr("xgcname");

                        localStorage.setItem('value', value);
                        localStorage.setItem('cname', cname);


                        window.location.href = "http://localhost:63342/学生管理系统/updatacourse.html";

                    })


                });
                $(function(){

                    //获取btn按钮
                    $(".alldelc").click(function(){
                        var cid=$(this).attr("delidc");
                        //发送请求
                        $.ajax({
                            xhrFields: {withCredentials: true},
                            type: "GET",
                            dataType:"json",
                            async:false,
                            url: "http://www.zcg.com/StudentManagerSystem/course.do?operation=13",
                            data:{
                                "cid":cid
                            },
                            success: function (res) {

                                if (res.status == 0) {
                                    alert(res.msg);
                                    window.location.href = "http://localhost:63342/学生管理系统/findallcourse.html";
                                }else if (res.status == 8) {
                                    alert(res.msg);
                                }
                            },
                            error: function () {

                            },

                        })
                    });
                })
            },
            error: function () {

            }

        })
    })


})




$(function () {

    var pageSize = 7;

    $("#down").click(function () {
        $(".pn").removeAttr("id");
        $("#down").removeAttr("disabled")
        $("#up").removeAttr("disabled")
        var pageNo = parseInt(localStorage.getItem("pageNo"))+1;
        $.ajax({
            xhrFields: {withCredentials: true},
            url: "http://www.zcg.com/StudentManagerSystem/course.do?operation=21",
            type: "get",
            dataType: "json",
            async:false,
            data: {
                "pageNo": pageNo,
                "pageSize": pageSize,
            },

            success: function (res) {
                $(".pn[value='"+pageNo+"']").attr("id","hdpn");
                if(res.hasnext==false){
                    $("#down").attr('disabled',true);
                }
                if(res.hasbefore==false){
                    $("#up").attr('disabled',true);
                }
                if (res.totalPage > 0) {
                    $(".adminfindstablec").empty();
                    for (var i = 0; i < res.data.length; i++) {

                        $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" value="${res.data[i].cid}" xgcname="${res.data[i].cname}">修改</button>&nbsp;<button class="alldelc" delidc="${res.data[i].cid}">删除</button></td></tr>`);

                    }
                }
                localStorage.setItem("pageNo",res.currentPage);
                $(function(){


                    //获取btn按钮
                    $(".updatacbtn").click(function(){
                        var value=$(this).attr("value");
                        var cname=$(this).attr("xgcname");

                        localStorage.setItem('value', value);
                        localStorage.setItem('cname', cname);


                        window.location.href = "http://localhost:63342/学生管理系统/updatacourse.html";

                    })


                });
                $(function(){

                    //获取btn按钮
                    $(".alldelc").click(function(){
                        var cid=$(this).attr("delidc");
                        //发送请求
                        $.ajax({
                            xhrFields: {withCredentials: true},
                            type: "GET",
                            dataType:"json",
                            async:false,
                            url: "http://www.zcg.com/StudentManagerSystem/course.do?operation=13",
                            data:{
                                "cid":cid
                            },
                            success: function (res) {

                                if (res.status == 0) {
                                    alert(res.msg);
                                    window.location.href = "http://localhost:63342/学生管理系统/findallcourse.html";
                                }else if (res.status == 8) {
                                    alert(res.msg);
                                }
                            },
                            error: function () {

                            },

                        })
                    });
                })
            },
            error: function () {

            }

        })
    })


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
                    window.location.href="http://localhost:63342/学生管理系统/findallcourse.html";
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



