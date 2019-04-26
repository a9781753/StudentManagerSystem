

    $(function() {
        $("#up").removeAttr("disabled")
        $("#down").removeAttr("disabled")
        var pageSize = 7;
        var pageNo = 1;
        $.ajax({
                xhrFields: {withCredentials: true},
                async:false,
                type: "GET",
                dataType:"json",
                url: "http://www.zcg.com/StudentManagerSystem/student.do?operation=5",
                data:{
                    pageSize:pageSize,
                    pageNo:pageNo,
                },
                success: function (res) {

                    if(res.hasbefore==false){
                        $("#up").attr('disabled',true);
                    }
                    if(res.hasnext==false){
                        $("#down").attr('disabled',true);
                    }

                        $(".adminfindstable").empty();
                        for (var i = 0; i < res.data.length; i++) {

                            $(".adminfindstable").append(`<tr class="adds_tr"><td idcx="i">${res.data[i].id}</td><td namecx="i">${res.data[i].name}</td><td sexcx="i">${res.data[i].sex}</td><td agecx="i">${res.data[i].age}</td><td _classcx="i">${res.data[i]._class}</td><td scorecx="i">${res.data[i].score}</td><td passwordcx="i">${res.data[i].password}</td><td>${res.data[i].regtime}</td><td>${res.data[i].modtime}</td><td><button class="updatabtn" value="${res.data[i].id}" xgname="${res.data[i].name}" xgsex="${res.data[i].sex}" xgage="${res.data[i].age}" xg_class="${res.data[i]._class}" xgscore="${res.data[i].score}" xgname="${res.data[i].name}" xgpassword="${res.data[i].password}">修改</button>&nbsp;<button class="alldel" delid="${res.data[i].id}">删除</button></td></tr>`);
                        }
                    for(var i=1; i<=res.totalPage;i++){
                        $("#nbt").append(`<input type="button" class="pn" value="${i}"></input>`);
                    }
                    $(".pn[value='1']").attr("id","hdpn");
                    localStorage.setItem("pageNo",res.currentPage);
                    $(".updatabtn").click(function(){
                        var value=$(this).attr("value");
                        var name=$(this).attr("xgname");
                        var sex=$(this).attr("xgsex");
                        var age=$(this).attr("xgage");
                        var _class=$(this).attr("xg_class");
                        var score=$(this).attr("xgscore");
                        var password=$(this).attr("xgpassword");
                        localStorage.setItem('value', value);
                        localStorage.setItem('name', name);
                        localStorage.setItem('sex', sex);
                        localStorage.setItem('age', age);
                        localStorage.setItem('_class', _class);
                        localStorage.setItem('score', score);
                        localStorage.setItem('password', password);
                        window.location.href = "http://localhost:63342/学生管理系统/updata.html";
                        console.log(localStorage.getItem('id'));
                    })
                },
                error: function () {

                }

            })


    });




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
                url: "http://www.zcg.com/StudentManagerSystem/student.do?operation=5",
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

                    if (res.totalPage > 0) {
                        $(".adminfindstable").empty();
                        for (var i = 0; i < res.data.length; i++) {

                            $(".adminfindstable").append(`<tr class="adds_tr"><td idcx="i">${res.data[i].id}</td><td namecx="i">${res.data[i].name}</td><td sexcx="i">${res.data[i].sex}</td><td agecx="i">${res.data[i].age}</td><td _classcx="i">${res.data[i]._class}</td><td scorecx="i">${res.data[i].score}</td><td passwordcx="i">${res.data[i].password}</td><td>${res.data[i].rgetime}</td><td>${res.data[i].modtime}</td><td><button class="updatabtn" value="${res.data[i].id}" xgname="${res.data[i].name}" xgsex="${res.data[i].sex}" xgage="${res.data[i].age}" xg_class="${res.data[i]._class}" xgscore="${res.data[i].score}" xgname="${res.data[i].name}" xgpassword="${res.data[i].password}">修改</button>&nbsp;<button class="alldel" delid="${res.data[i].id}">删除</button></td></tr>`);

                        }
                    }
                    $(".updatabtn").click(function(){
                        var value=$(this).attr("value");
                        var name=$(this).attr("xgname");
                        var sex=$(this).attr("xgsex");
                        var age=$(this).attr("xgage");
                        var _class=$(this).attr("xg_class");
                        var score=$(this).attr("xgscore");
                        var password=$(this).attr("xgpassword");
                        localStorage.setItem('value', value);
                        localStorage.setItem('name', name);
                        localStorage.setItem('sex', sex);
                        localStorage.setItem('age', age);
                        localStorage.setItem('_class', _class);
                        localStorage.setItem('score', score);
                        localStorage.setItem('password', password);
                        window.location.href = "http://localhost:63342/学生管理系统/updata.html";
                        console.log(localStorage.getItem('id'));
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
                url: "http://www.zcg.com/StudentManagerSystem/student.do?operation=5",
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
                        $(".adminfindstable").empty();
                        for (var i = 0; i < res.data.length; i++) {

                            $(".adminfindstable").append(`<tr class="adds_tr"><td idcx="i">${res.data[i].id}</td><td namecx="i">${res.data[i].name}</td><td sexcx="i">${res.data[i].sex}</td><td agecx="i">${res.data[i].age}</td><td _classcx="i">${res.data[i]._class}</td><td scorecx="i">${res.data[i].score}</td><td passwordcx="i">${res.data[i].password}</td><td>${res.data[i].rgetime}</td><td>${res.data[i].modtime}</td><td><button class="updatabtn" value="${res.data[i].id}" xgname="${res.data[i].name}" xgsex="${res.data[i].sex}" xgage="${res.data[i].age}" xg_class="${res.data[i]._class}" xgscore="${res.data[i].score}" xgname="${res.data[i].name}" xgpassword="${res.data[i].password}">修改</button>&nbsp;<button class="alldel" delid="${res.data[i].id}">删除</button></td></tr>`);

                        }
                    }
                    localStorage.setItem("pageNo",res.currentPage);
                    $(".updatabtn").click(function(){
                        var value=$(this).attr("value");
                        var name=$(this).attr("xgname");
                        var sex=$(this).attr("xgsex");
                        var age=$(this).attr("xgage");
                        var _class=$(this).attr("xg_class");
                        var score=$(this).attr("xgscore");
                        var password=$(this).attr("xgpassword");
                        localStorage.setItem('value', value);
                        localStorage.setItem('name', name);
                        localStorage.setItem('sex', sex);
                        localStorage.setItem('age', age);
                        localStorage.setItem('_class', _class);
                        localStorage.setItem('score', score);
                        localStorage.setItem('password', password);
                        window.location.href = "http://localhost:63342/学生管理系统/updata.html";
                        console.log(localStorage.getItem('id'));
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
                url: "http://www.zcg.com/StudentManagerSystem/student.do?operation=5",
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
                        $(".adminfindstable").empty();
                        for (var i = 0; i < res.data.length; i++) {

                            $(".adminfindstable").append(`<tr class="adds_tr"><td idcx="i">${res.data[i].id}</td><td namecx="i">${res.data[i].name}</td><td sexcx="i">${res.data[i].sex}</td><td agecx="i">${res.data[i].age}</td><td _classcx="i">${res.data[i]._class}</td><td scorecx="i">${res.data[i].score}</td><td passwordcx="i">${res.data[i].password}</td><td>${res.data[i].rgetime}</td><td>${res.data[i].modtime}</td><td><button class="updatabtn" value="${res.data[i].id}" xgname="${res.data[i].name}" xgsex="${res.data[i].sex}" xgage="${res.data[i].age}" xg_class="${res.data[i]._class}" xgscore="${res.data[i].score}" xgname="${res.data[i].name}" xgpassword="${res.data[i].password}">修改</button>&nbsp;<button class="alldel" delid="${res.data[i].id}">删除</button></td></tr>`);

                        }
                    }
                    localStorage.setItem("pageNo",res.currentPage);
                    $(".updatabtn").click(function(){
                        var value=$(this).attr("value");
                        var name=$(this).attr("xgname");
                        var sex=$(this).attr("xgsex");
                        var age=$(this).attr("xgage");
                        var _class=$(this).attr("xg_class");
                        var score=$(this).attr("xgscore");
                        var password=$(this).attr("xgpassword");
                        localStorage.setItem('value', value);
                        localStorage.setItem('name', name);
                        localStorage.setItem('sex', sex);
                        localStorage.setItem('age', age);
                        localStorage.setItem('_class', _class);
                        localStorage.setItem('score', score);
                        localStorage.setItem('password', password);
                        window.location.href = "http://localhost:63342/学生管理系统/updata.html";
                        console.log(localStorage.getItem('id'));
                    })
                },
                error: function () {

                }

            })
        })


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



    // //跳转修改页面
    // $(function(){
    //
    //
    //     //获取btn按钮
    //     $(".updatabtn").click(function(){
    //         var value=$(this).attr("value");
    //         var name=$(this).attr("xgname");
    //         var sex=$(this).attr("xgsex");
    //         var age=$(this).attr("xgage");
    //         var _class=$(this).attr("xg_class");
    //         var score=$(this).attr("xgscore");
    //         var password=$(this).attr("xgpassword");
    //         localStorage.setItem('value', value);
    //         localStorage.setItem('name', name);
    //         localStorage.setItem('sex', sex);
    //         localStorage.setItem('age', age);
    //         localStorage.setItem('_class', _class);
    //         localStorage.setItem('score', score);
    //         localStorage.setItem('password', password);
    //         window.location.href = "http://localhost:63342/学生管理系统/updata.html";
    //         console.log(localStorage.getItem('id'));
    //         })
    //
    //
    //     });


    //删除学生信息
    $(function(){

        //获取btn按钮
        $(".alldel").click(function(){
            var id=$(this).attr("delid");
            //发送请求

            $.ajax({
                xhrFields: {withCredentials: true},
                type: "GET",
                dataType:"json",
                url: "http://www.zcg.com/StudentManagerSystem/student.do?operation=8",
                data:{
                    "id":id
                },
                success: function (res) {

                    if (res.status == 0) {
                        alert(res.msg);
                        window.location.href = "http://localhost:63342/学生管理系统/findall.html";
                    }else if (res.status == 8) {
                        alert(res.msg);
                    }
                },
                error: function () {

                },

            })
        });
    })