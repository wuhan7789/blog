$(function () {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/article/pageList",
        data: {"indexPage" : 1},
        success: function (data) {
            if (data.rows != "") {
                var dom = [];
                for(var i = 0 ; i < data.rows.length; i++) {
                    dom.push('<div>');
                    dom.push('<div class="text-center">');
                    dom.push('<p class="tit"><a href="/articles/article0'+data.rows[i].domain.id+'.html?ran='+Math.random()*10+'">'+ data.rows[i].domain.title +'</a></p>');
                    dom.push('<h5>'+ data.rows[i].domain.inputtime +' | In Java | by wuhan</h5>');
                    dom.push('</div>');
                    dom.push('<div class="content">');
                    dom.push('<p>&ensp;&ensp;'+ data.rows[i].domain.introduction +'</p>');
                    dom.push('</div>');
                    dom.push('<div class="text-center">');
                    dom.push('<a href="/articles/article0'+data.rows[i].domain.id+'.html?ran='+Math.random()*10+'" class="abtn">');
                    dom.push('Read more &rsaquo;&rsaquo;');
                    dom.push('</a>');
                    dom.push('</div>');
                    dom.push('<div>');
                    dom.push('<span class="line"></span>');
                    dom.push('</div>');
                    dom.push('</div>');
                }
                $(".title-content").empty().append(dom.join(''));
                var options = {
                    bootstrapMajorVersion: 3, //版本!!!(!(bootstrap版本2用div,3版本用ul!)!)
                    currentPage: data.pageInfo.pageNum, //当前页数
                    totalPages: data.pageInfo.pages, //总页数
                    numberOfPages: 6,//显示页数上限
                    //控制每个操作按钮的显示文字。是个函数，有3个参数: type, page, current。通过这个参数我们就可以将操作按钮上的英文改为中文，如first-->首页，last-->尾页。
                    itemTexts: function (type, page, current) {
                        switch (type) {
                            case "first":
                                return "first";
                            case "prev":
                                return "&laquo;";
                            case "next":
                                return "&raquo;";
                            case "last":
                                return "last";
                            case "page":
                                return page;
                        }
                    },//点击事件，用于通过Ajax来刷新整个list列表
                    onPageClicked: function (event, originalEvent, type, page) {
                        $.ajax({
                            url: "/article/pageList",
                            type: "GET",
                            data: {"indexPage" : page},
                            success: function (data) {
                                if (data.rows != "") {
                                    var dom = [];
                                    for(var i = 0 ; i < data.rows.length; i++) {
                                        dom.push('<div>');
                                        dom.push('<div class="text-center">');
                                        dom.push('<p class="tit"><a href="/articles/article0'+data.rows[i].domain.id+'.html?ran='+Math.random()*10+'">'+ data.rows[i].domain.title +'</a></p>');
                                        dom.push('<h5>'+ data.rows[i].domain.inputtime +' | In Java | by wuhan</h5>');
                                        dom.push('</div>');
                                        dom.push('<div class="content">');
                                        dom.push('<p>&ensp;&ensp;'+ data.rows[i].domain.introduction +'</p>');
                                        dom.push('</div>');
                                        dom.push('<div class="text-center">');
                                        dom.push('<a href="/articles/article0'+data.rows[i].domain.id+'.html?ran='+Math.random()*10+'" class="abtn">');
                                        dom.push('Read more &rsaquo;&rsaquo;');
                                        dom.push('</a>');
                                        dom.push('</div>');
                                        dom.push('<div>');
                                        dom.push('<span class="line"></span>');
                                        dom.push('</div>');
                                        dom.push('</div>');
                                    }
                                    $(".title-content").empty().append(dom.join(''));
                                    $('#example a').attr('href','javascript:void(0);');
                                }
                            }
                        });
                    }
                };
                $('#example').bootstrapPaginator(options);
                $('#example a').attr('href','javascript:void(0);');
                $('.bottom').show();
            }
        }
    });
    $("#fixed").smartFloat();
    txtBtnFadeIn();
    $('#ESSearch').on('click',function() {
        $('#modal-container-457419').modal('show');
    });
});

var txtBtnFadeIn = function () {
    var $txt = $('.opreation .text-wrap');
    var $ali = $('.opreation .munes');
    var $tc = $('.title-content');
    setTimeout(function () {
        var animate_para = { 'margin-top': 10, 'opacity': 1 };
        $txt.animate(animate_para, 300 ,function () {
            $ali.animate(animate_para, 300,function(){
                $tc.animate(animate_para, 300);
            });
        });
    }, 200);
}
$.fn.smartFloat = function() {
    var position = function(element) {
        var top = element.position().top, pos = element.css("position");
        $(window).scroll(function() {
            var scrolls = $(this).scrollTop();
            if (scrolls > top) {
                if (window.XMLHttpRequest) {
                    element.css({
                        position: "fixed",
                        width:"100%",
                        background: "#fff",
                        margin:"0px 0px 0px 0px",
                        display: "inline-block",
                        top: 0
                    });
                } else {
                    element.css({
                        top: scrolls
                    });
                }
            }else {
                element.css({
                    background: " ",
                    position: pos,
                    top: top
                });
            }
        });
    };
    return $(this).each(function() {
        position($(this));
    });
};