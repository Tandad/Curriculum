/**
 * Created by liymm on 2015-04-17.
 */

var ue;

$(document).ready(function(){

    ue = UE.getEditor('container');

    $("#submitbtn").click(function(){
        if (emptyString($("#title").val())) {
            Util.errorTip("标题不能为空");
            return;
        }

        Util.ajax({
            type: "POST",
            data: $("#form").serialize(),
            url: "/rest/news/add",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                $("#form")[0].reset();
                ue.execCommand('cleardoc');

                return "公告发布成功！";
            }
        });
    });

});