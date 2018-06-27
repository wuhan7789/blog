 $(function(){
     search();
     $("#search,#pageSize").on('click',function(){
         search();
     });
     //全选
     var flag = true;
     $("#fuxuan").on('click',function(){
         if(flag){
             $("input[name='checkbox']").prop("checked",true);
             flag = false;
         }else{
             $("input[name='checkbox']").prop("checked",false);
             flag = true;
         }
     });
     //刷新按钮
     $("#refrash").on('click',function(){
         $("#tag").val("");
         $("#indexPage").val("1");
         $("#pageSize").val("10");
         $("#fuxuan").prop("checked",false);
         search();
     });
     //修改按钮
     $("#update").on('click',function() {
         var ids = [];
         var groupCheckbox=$("input[name='checkbox']");
             for(i=0;i < groupCheckbox.length;i++) {
                 if(groupCheckbox[i].checked) {
                     var val =groupCheckbox[i].value;
                     ids.push(val);
                 }
             }
         if(ids.length == 1) {
             window.location.href = '/articleUpdate?articleId=' + ids[0];
         }else{alert('请选择一条数据修改...');}
     });
     //删除按钮
     $("#delect").on('click',function() {
         var ids = [];
         var groupCheckbox=$("input[name='checkbox']");
         for(i=0;i<groupCheckbox.length;i++) {
             if(groupCheckbox[i].checked) {
                 var val =groupCheckbox[i].value;
                 ids.push(val);
             }
         }
         if(ids.length != 0) {
              if(confirm("确认删除？")) {
                  $.post("/article/updateStateManyOrOne",{"ids":JSON.stringify(ids)},function(data) {
                      search();
                  });
              }
         }else{alert('请选择一条数据删除...');}
     });

 });
 //异步分页查询
 function search() {
        var articleName = $("#article").val();
        var indexPage = $("#indexPage").val();
        var pageSize = $("#pageSize option:selected").val();
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "/article/pageList",
            data: {"indexPage" : indexPage,
                   "pageSize" : pageSize,
                   "domain.title" : articleName},
            success: function (data) {
                console.info(data);
                if (data.rows != "") {
                    var dom = [];
                    for(var i = 0 ; i < data.rows.length; i++) {
                        dom.push("<tr>");
                        dom.push("<td>");
                        dom.push("<input name='checkbox' type='checkbox' value='"+data.rows[i].domain.id+"'>");
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push(data.rows[i].domain.id);
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push("<b>"+data.rows[i].domain.title+"</b>");
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push(data.rows[i].tag);
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push(data.rows[i].authorName);
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push(data.rows[i].domain.inputtime);
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push(data.rows[i].domain.updatetime);
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push(data.rows[i].domain.clicknum);
                        dom.push("</td>");
                        dom.push("<td class='text-center'>");
                        dom.push("<a target='_Blank' href='/articles/article0"+data.rows[i].domain.id+".html?ran="+Math.random()*10+"'>");
                        dom.push("<i class='fa fa-search fa-lg'></i>");
                        dom.push("</a>");
                        dom.push("</td>");
                        dom.push("</tr>");
                    }
                    $("#list").empty().append(dom.join(''));
                    $("#indexPage").val(data.pageInfo.pageNum);//当前页
                    $("#total").text(data.pageInfo.total);//总条数
                    var options = {
                        bootstrapMajorVersion: 3, //版本!!!(!(bootstrap版本2用div,3版本用ul!)!)
                        currentPage: data.pageInfo.pageNum, //当前页数
                        totalPages: data.pageInfo.pages, //总页数
                        numberOfPages: 6,//显示页数上限
                        //控制每个操作按钮的显示文字。是个函数，有3个参数: type, page, current。通过这个参数我们就可以将操作按钮上的英文改为中文，如first-->首页，last-->尾页。
                        itemTexts: function (type, page, current) {
                            switch (type) {
                                case "first":
                                    return "首页";
                                case "prev":
                                    return "上一页";
                                case "next":
                                    return "下一页";
                                case "last":
                                    return "末页";
                                case "page":
                                    return page;
                            }
                        },//点击事件，用于通过Ajax来刷新整个list列表
                        onPageClicked: function (event, originalEvent, type, page) {
                            $.ajax({
                                url: "/article/pageList",
                                type: "GET",
                                data: {"indexPage" : page,
                                       "domain.title" : articleName,
                                       "pageSize" : pageSize},
                                success: function (data) {
                                    if (data.rows != "") {
                                        var dom = [];
                                        for(var i = 0 ; i < data.rows.length; i++) {
                                            dom.push("<tr>");
                                            dom.push("<td>");
                                            dom.push("<input name='checkbox' type='checkbox' value='"+data.rows[i].domain.id+"'>");
                                            dom.push("</td>");
                                            dom.push("<td>");
                                            dom.push(data.rows[i].domain.id);
                                            dom.push("</td>");
                                            dom.push("<td>");
                                            dom.push("<b>"+data.rows[i].domain.title+"</b>");
                                            dom.push("</td>");
                                            dom.push("<td>");
                                            dom.push(data.rows[i].tag);
                                            dom.push("</td>");
                                            dom.push("<td>");
                                            dom.push(data.rows[i].authorName);
                                            dom.push("</td>");
                                            dom.push("<td>");
                                            dom.push(data.rows[i].domain.inputtime);
                                            dom.push("</td>");
                                            dom.push("<td>");
                                            dom.push(data.rows[i].domain.updatetime);
                                            dom.push("</td>");
                                            dom.push("<td>");
                                            dom.push(data.rows[i].domain.clicknum);
                                            dom.push("</td>");
                                            dom.push("<td class='text-center'>");
                                            dom.push("<a target='_Blank' href='/article_html/article0"+data.rows[i].domain.id+".html?ran="+Math.random()*10+"'>");
                                            dom.push("<i class='fa fa-search fa-lg'></i>");
                                            dom.push("</a>");
                                            dom.push("</td>");
                                            dom.push("</tr>");
                                        }
                                        $("#list").empty().append(dom.join(''));
                                        $('#example a').attr('href','javascript:void(0);');
                                    }else{alert('未搜索到查询条件');}
                                    $("#indexPage").val(page);
                                    $("#total").text(data.pageInfo.total);
                                }
                            });
                        }
                    };
                    $('#example').bootstrapPaginator(options);
                    $('#example a').attr('href','javascript:void(0);');
                    $("#list").on('click','.open',function() {
                        console.info($(this).data().id);
                    });
                }else{alert('未搜索到查询条件');}
            }
        });
    }