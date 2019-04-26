function pageClick(k) {
    $(k).parent().find("div").removeClass("active");
    $(k).addClass("active");
    $("#flTitle").text($(k).text());
    $("#content").text($(k).text());
}

$(function() {
    console.log("123")
    $.ajax({
        xhrFields: {withCredentials: true},
        async: false,
        type: "GET",
        dataType: "json",
        url: "http://www.zcg.com/StudentManagerSystem/choice.do?operation=18&cid=55",
        data:{
            "sid":localStorage.getItem("studentid"),
        },
        success: function (res) {
            console.log(res.status)
            if (res.status == 0) {
                for (var i = 0; i < res.data.length; i++) {
                    $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="alldelc" delidc="${res.data[i].cid}">删除</button></td></tr>`)
                }
            }
        },
        error: function () {

        }

    })


})




$(function() {
    $(".thisUser").click(function () {
        $.ajax({
            xhrFields: {withCredentials: true},
            async:true,
            url: "http://www.zcg.com/StudentManagerSystem/admin.do?operation=3",
            type: "get",
            dataType: "json",
            success: function (res) {

                window.location.href = "http://localhost:63342/学生管理系统/index.html";

            },
            error: function (res) {
               alert(res.msg);
            }

        })
    })

});







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






//取消选课
$(function(){

    //获取btn按钮
    $(".alldelc").click(function(){
        var cid=$(this).attr("delidc");
        //发送请求

        $.ajax({
            xhrFields: {withCredentials: true},
            type: "GET",
            dataType:"json",
            url: "http://www.zcg.com/StudentManagerSystem/choice.do?operation=19",
            data:{
                "cid":cid,
                "sid":localStorage.getItem("studentid"),
            },
            success: function (res) {

                if (res.status == 0) {
                    alert(res.msg);
                    window.location.href = "http://localhost:63342/学生管理系统/studentcourse.html";
                }else if (res.status == 18) {
                    alert(res.msg);
                }
            },
            error: function () {

            },

        })
    });
})