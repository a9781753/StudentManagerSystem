

    $(function() {

        $.ajax({
            xhrFields: {withCredentials: true},
            async: false,
            type: "GET",
            dataType: "json",
            url: "http://www.zcg.com/StudentManagerSystem/course.do?operation=11",
            success: function (res) {
                if (res.status == 0) {
                    // $(".adminfindstablec").empty();
                    for (var i = 0; i < res.data.length; i++) {
                        $(".adminfindstablec").append(`<tr class="adds_tr"><td>${res.data[i].cid}</td><td>${res.data[i].cname}</td><td><button class="updatacbtn" value="${res.data[i].cid}" xgcname="${res.data[i].cname}">修改</button>&nbsp;<button class="alldelc" delidc="${res.data[i].cid}">删除</button></td></tr>`)
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
                    console.log(res);
                },
                error: function (res) {
                    console.log(res);
                }

            })
        })

    });



    //跳转修改页面
    $(function(){


        //获取btn按钮
        $(".updatacbtn").click(function(){
            var value=$(this).attr("value");
            var cname=$(this).attr("xgcname");

            localStorage.setItem('value', value);
            localStorage.setItem('cname', cname);


            window.location.href = "http://localhost:63342/学生管理系统/updatacourse.html";
 ;
            })


        });




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






    //删除学生信息
    $(function(){

        //获取btn按钮
        $(".alldelc").click(function(){
            var cid=$(this).attr("delidc");
            //发送请求

            $.ajax({
                xhrFields: {withCredentials: true},
                type: "GET",
                dataType:"json",
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