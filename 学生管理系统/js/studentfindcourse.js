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

                    $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" cid="${res.data[i].cid}" xgcname="${res.data[i].cname}">选 课</button></td></tr>`)

                }

                for(var i=1; i<=res.totalPage;i++){
                    $("#nbt").append(`<input type="button" class="pn" value="${i}"></input>`);
                }
            }
            $(".pn[value='1']").attr("id","hdpn");
            localStorage.setItem("pageNo",res.currentPage);

        },
        error: function () {

        }

    })
    $(".updatacbtn").click(function(){

        $.ajax({
            xhrFields: {withCredentials: true},
            url:"http://www.zcg.com/StudentManagerSystem/choice.do?operation=16",
            type:"get",
            dataType:"json",
            async:false,
            data:{
                "cid":$(this).attr("cid"),
                "sid":localStorage.getItem("studentid"),
            },
            success:function (res) {
                if(res.status == 0){
                    alert(res.msg);
                    window.location.href="http://localhost:63342/学生管理系统/studentcourse.html";
                }else if(res.status == 16){
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

                        $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" cid="${res.data[i].cid}" xgcname="${res.data[i].cname}">选 课</button></td></tr>`)

                    }
                }



            },
            error: function () {

            }

        })
        $(".updatacbtn").click(function(){
            console.log("527974107")
            $.ajax({
                xhrFields: {withCredentials: true},
                url:"http://www.zcg.com/StudentManagerSystem/choice.do?operation=16",
                type:"get",
                dataType:"json",
                async:false,
                data:{
                    "cid":$(this).attr("cid"),
                    "sid":localStorage.getItem("studentid"),
                },
                success:function (res) {
                    if(res.status == 0){
                        alert(res.msg);
                        window.location.href="http://localhost:63342/学生管理系统/studentcourse.html";
                    }else if(res.status == 16){
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

                        $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" cid="${res.data[i].cid}" xgcname="${res.data[i].cname}">选 课</button></td></tr>`)

                    }
                }
                localStorage.setItem("pageNo",res.currentPage);

            },
            error: function () {

            }

        })
        $(".updatacbtn").click(function(){
            console.log("527974107")
            $.ajax({
                xhrFields: {withCredentials: true},
                url:"http://www.zcg.com/StudentManagerSystem/choice.do?operation=16",
                type:"get",
                dataType:"json",
                async:false,
                data:{
                    "cid":$(this).attr("cid"),
                    "sid":localStorage.getItem("studentid"),
                },
                success:function (res) {
                    if(res.status == 0){
                        alert(res.msg);
                        window.location.href="http://localhost:63342/学生管理系统/studentcourse.html";
                    }else if(res.status == 16){
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

                        $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" cid="${res.data[i].cid}" xgcname="${res.data[i].cname}">选 课</button></td></tr>`)

                    }
                }
                localStorage.setItem("pageNo",res.currentPage);

            },
            error: function () {

            }

        })
        $(".updatacbtn").click(function(){
            console.log("527974107")
            $.ajax({
                xhrFields: {withCredentials: true},
                url:"http://www.zcg.com/StudentManagerSystem/choice.do?operation=16",
                type:"get",
                dataType:"json",
                async:false,
                data:{
                    "cid":$(this).attr("cid"),
                    "sid":localStorage.getItem("studentid"),
                },
                success:function (res) {
                    if(res.status == 0){
                        alert(res.msg);
                        window.location.href="http://localhost:63342/学生管理系统/studentcourse.html";
                    }else if(res.status == 16){
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


})
$(function(){

    ;
    //发送请求

    $.ajax({
        xhrFields: {withCredentials: true},
        url:"http://www.zcg.com/StudentManagerSystem/admin.do?operation=9",
        type:"get",
        dataType:"json",

        success:function (res) {
            var name = localStorage.getItem("studentname");
            if(res.status == 0){
                $(".top").append(`<div class="thisUser">当前用户：${name}</div>`);
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

//学生选课
// $(function(){
//
//     //获取btn按钮
//     $(".updatacbtn").click(function(){
//         console.log("527974107")
//         $.ajax({
//             xhrFields: {withCredentials: true},
//             url:"http://www.zcg.com/StudentManagerSystem/choice.do?operation=16",
//             type:"get",
//             dataType:"json",
//             async:false,
//             data:{
//                 "cid":$(this).attr("cid"),
//                 "sid":localStorage.getItem("studentid"),
//             },
//             success:function (res) {
//                 if(res.status == 0){
//                     alert(res.msg);
//                     window.location.href="http://localhost:63342/学生管理系统/studentcourse.html";
//                 }else if(res.status == 16){
//                     alert(res.msg);
//                 }
//                 console.log(res);
//             },
//             error:function (res) {
//                 console.log(res);
//             }
//
//         })
//     });
//
//  })



































































// $(function() {
//
//         $.ajax({
//             xhrFields: {withCredentials: true},
//             async: false,
//             type: "GET",
//             dataType: "json",
//             url: "http://www.zcg.com/StudentManagerSystem/course.do?operation=11",
//             success: function (res) {
//                 if (res.status == 0) {
//                     // $(".adminfindstablec").empty();
//                     for (var i = 0; i < res.data.length; i++) {
//                         $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" cid="${res.data[i].cid}" xgcname="${res.data[i].cname}">选 课</button></td></tr>`)
//                     }
//                 }
//             },
//                 error: function () {
//
//                 }
//
//         })
//
//
//     })
//
//
//
//
//     $(function() {
//         $(".thisUser").click(function () {
//             $.ajax({
//                 xhrFields: {withCredentials: true},
//                 async:false,
//                 url: "http://www.zcg.com/StudentManagerSystem/admin.do?operation=3",
//                 type: "get",
//                 dataType: "json",
//                 success: function (res) {
//
//                     window.location.href = "http://localhost:63342/学生管理系统/index.html";
//                     console.log(res);
//                 },
//                 error: function (res) {
//                     console.log(res);
//                 }
//
//             })
//         })
//
//     });
//








