$(document).ready(function () {
    $("#submitbtn").click(function () {

        if ($("#oldpw").val()=="" || $("#newpw").val()=="" || $("#reppw").val()==""){
            Util.errorTip("请输入密码");
            return;
        }

        if ($("#newpw").val()!=$("#reppw").val()) {
            Util.errorTip("两次密码输入不同");
            return;
        }

        Util.ajax({
            type: "POST",
            data: $("form").serialize(),
            url: "/rest/user/password/modify",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                return "密码修改成功!";
            }
        });
    });
});
