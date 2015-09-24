/**
 * Created by LiYm on 2015/7/27.
 */
$(function(){

    $("#publish").click(function(){
        Util.ajax({
            type: "POST",
            data: {date: $(this).data("date")},
            url: "/rest/evaluation/publish/month",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                location.reload();

                return "发布成功";
            }
        });
    });

});