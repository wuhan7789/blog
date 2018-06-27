$(document).keydown(function(event){
    if(event.keyCode == 13){
        $("#LOGIN_A").click();
    }
});
if (top.location != self.location) {top.location=self.location;}
$(function(){
    $("#Form").on("submit", function(ev) {
        var account = $("#username").val();
        var password = $("#password").val();
        if(account != "" && password != "" && account != undefined && password != undefined) {
            $.ajax({
                type : "post",
                url : "/login",
                data : {"username":account,"password":password},
                error : function() {
                    console.debug("系统维护中。。。")
                },
                success : function(data) {
                    if(data == "success"){
                        window.location.href = "/system";
                    }else {
                        $("#error").text('  用户名或密码错误 !').css('color','#ff5f32')
                    }
                }
            });
        }
        ev.preventDefault();
    });
});