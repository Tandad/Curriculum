/**
 * Created by LiYm on 2015/7/15.
 */
$(function(){
    $("input[name$=complete]").click(function(){

        var parent = $(this).parents("tr");
        var score = parent.find("[name$=score]:checked");

        if ($(this).val() == 0) {
            parent.find(".complete").hide();
            parent.find(".incomplete").show();

            if (score.length > 0) {
                if (score.val() > 70) {
                    score.removeAttr("checked");
                    parent.find(".score").html("");
                }
            }

        } else {
            parent.find(".complete").show();
            parent.find(".incomplete").hide();

            if (score.length > 0) {
                if (score.val() < 80) {
                    score.removeAttr("checked");
                    parent.find(".score").html("");
                }
            }
        }
    });

    $("input[name$=score]").click(function(){
        $(this).parents("tr").find(".score").html($(this).val());
    });

    $(".complete").hide();
    $(".incomplete").hide();

    $("#btn-submit").click(function(){

        if (!validate())
            return;

        Util.ajax({
            type: "POST",
            data: $("#main-form").serialize(),
            url: "/rest/evaluation/mark",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                location.reload();

                return "评分成功";
            }
        });
    });

    function validate() {
        var max = $("#max-user").val();

        for (var i=0; i<max; i++) {
            var checkItem = $("input[name=\"data[" + i +"].score\"]:checked");
            var item = $("input[name=\"data[" + i +"].score\"]");

            if (checkItem.length == 0) {
                item.parents("tr").addClass("danger");
                Util.errorTip("请为员工打分");
                return false;
            }

            item.parents("tr").removeClass("danger");
        }

        return true;
    }
});
