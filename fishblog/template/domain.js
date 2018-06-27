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
         var numberId = [];
         var groupCheckbox=$("input[name='checkbox']");
             for(i=0;i < groupCheckbox.length;i++) {
                 if(groupCheckbox[i].checked) {
                     var val =groupCheckbox[i].value;
                     numberId.push(val);
                 }
             }
         if(numberId.length == 1) {
             $('#Modal').modal('show');//显示模态框
             $('#myModalLabel').empty().append('修改标签<small id="error"></small>');//赋值
             $.get("/${domain}/selectOnebyId.do",{"id":numberId[0]},function(data) {
                 var dom = [];
                 dom.push('<label for="name"> 标 签 : </label>');
                 dom.push('<input name="domain.id" value="'+data.domain.id+'" hidden/>')
                 dom.push('<input id="name" name="domain.name" value="'+data.domain.name+'" class="re form-control" placeholder="请输入新标签" minlength="2" maxlength="20" required/>');
                 $('#modalForm').empty().append(dom.join(''));//赋值
             });
             $("#modalForm").on("submit", function(ev) {
                 var name = $("#name").val();
                 $.get("/${domain}/selectRe.do",{"domain.name":name},function(data) {
                     if(data.length == "") {
                         $.post("/${domain}/updateOne.do",$('#modalForm').serialize(),function(data){
                             $('#Modal').modal('hide');search();
                         });
                     }else{
                         $("#error").empty().text('标签已存在').css('color','#d01a1a');
                     }
                 });
                 ev.preventDefault();
             });
         }else{alert('请选择一条数据修改...');}
     });
     //添加按钮
     $("#addModal").on('click',function() {
         $('#Modal').modal('show');//显示模态框
         $('#myModalLabel').empty().append('添加标签<small id="error"></small>');//赋值
         var dom = [];
         dom.push('<label for="name"> 标 签 : </label>');
         dom.push('<input id="name" name="domain.name" class="re form-control" placeholder="请输入新标签" minlength="2" maxlength="20" required/>');
         $('#modalForm').empty().append(dom.join(''));//赋值
         $("#modalForm").on("submit", function(ev) {
             var name = $("#name").val();
             $.get("/${domain}/selectRe.do",{"domain.name":name},function(data) {
                 if(data.length == "") {
                     $.post("/${domain}/insertOne.do",$('#modalForm').serialize(),function(data){
                         $('#Modal').modal('hide');search();
                     });
                 }else{
                     $("#error").empty().text('标签已存在').css('color','#d01a1a');
                 }
             });
             ev.preventDefault();
         });
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
                  $.post("/${domain}/updateStateManyOrOne.do",{"ids":JSON.stringify(ids)},function(data) {
                      search();
                  });
              }
         }else{alert('请选择一条数据删除...');}
     });

 });

 //异步分页查询
 function search() {
        var tagName = $("#tag").val();
        var indexPage = $("#indexPage").val();
        var pageSize = $("#pageSize option:selected").val();
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "/${domain}/pageList.do",
            data: {"indexPage" : indexPage,
                   "pageSize" : pageSize,
                   "domain.name" : ${domain}Name},
            success: function (data) {
                if (data.rows != "") {
                    var dom = [];
                    for(var i = 0 ; i < data.rows.length; i++) {
                        dom.push("<tr>");
                        dom.push("<td>");
                        dom.push("<input name='checkbox' type='checkbox' value='"+data.rows[i].domain.id+"'>");
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push("0"+data.rows[i].domain.id);
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push("<b>"+data.rows[i].domain.name+"</b>");
                        dom.push("</td>");
                        dom.push("<td>");
                        dom.push(data.rows[i].domain.tagtype_id);
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
                                url: "/${domain}/pageList.do",
                                type: "GET",
                                data: {"indexPage" : page,
                                       "domain.name" : ${domain}Name,
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
                                            dom.push("0"+data.rows[i].domain.id);
                                            dom.push("</td>");
                                            dom.push("<td>");
                                            dom.push("<b>"+data.rows[i].domain.name+"</b>");
                                            dom.push("</td>");
                                            dom.push("<td>");
                                            dom.push(data.rows[i].domain.tagtype_id);
                                            dom.push("</td>");
                                            dom.push("</tr>");
                                        }
                                        $("#list").empty().append(dom.join(''));
                                    }else{alert('未搜索到查询条件');}
                                    $("#indexPage").val(page);
                                    $("#total").text(data.pageInfo.total);
                                }
                            });
                        }
                    };
                    $('#example').bootstrapPaginator(options);
                }else{alert('未搜索到查询条件');}
            }
        });
    }