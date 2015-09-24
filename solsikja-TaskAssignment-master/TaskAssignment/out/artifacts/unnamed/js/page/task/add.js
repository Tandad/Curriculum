/**
 * Created by liymm on 2015-03-30.
 */
var members = null;

$(document).ready(function(){
    $("#add-task-user").click(function(){
        $("#follower-list-dialog").modal("show");
        loadFollower();
    });

    $(document).on("click", ".select-mem", function(){
        members[$(this).data("idx")].selected = true;
        $("#follower-list-dialog").modal("hide");

        var render = _.template($("#task-user-item").html());
        $("#task-user-list").append(render(members[$(this).data("idx")]));
    });

    $(document).on("click", ".unselect-mem", function(){
        $("tr[data-idx='"+ $(this).data("idx") +"']").remove();
        members[$(this).data("idx")].selected = false;
    });

    $('.datetime-picker').datetimepicker({
        language:  'zh-CN',
        autoclose: true,
        todayHighlight: true,
        format: 'yyyy-mm-dd',
        minView: 2
    });

    $("#pub-task").click(function(){

        var idx = 0;
        for (var i in members) {
            if (!members[i].selected)
                continue;

            $("#uid-" + i).attr("name", "taskusers["+ idx +"].user.id");
            $("#detail-" + i).attr("name", "taskusers["+ idx +"].detail");

            idx ++;
        }

        if (idx == 0) {
            Util.errorTip("请选择执行人");
            return;
        }

        if ($("#sd").val() == null || $("#sd").val() == "") {
            Util.errorTip("请选择任务开始时间！");
            return;
        }

        if ($("#ed").val() == null || $("#ed").val() == "") {
            Util.errorTip("请选择任务结束时间！");
            return;
        }

        var sd = getDate($("#sd").val());
        var ed = getDate($("#ed").val());

        if (ed.getTime() < sd.getTime()) {
            Util.errorTip("结束时间不能早于开始时间！");
            return;
        }

        if ($("#title").val() == null || $("#title").val() == "") {
            Util.errorTip("请输入标题！");
            return;
        }

        Util.ajax({
            type: "POST",
            data: $("#task-form").serialize(),
            url: "/rest/task/add",
            success : function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                $("#task-form")[0].reset();
                $("#task-user-list").html("");
                members = null;
                return "任务发布成功";
            }
        });
    });
});

function loadFollower() {

    if (members != null) {
        renderMember();
        return;
    }

    Util.ajax({
        type: "GET",
        url: "/rest/user/" + currUser + "/follower",
        success : function(res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            members = res.data;

            for (var i in members) {
                members[i].idx = i;
                members[i].selected = false;
            }

            renderMember();
        }
    });
}

function renderMember() {
    var render = _.template($("#follower-item-tmpl").html());

    $("#follower-list").html("");
    for (var i in members) {
        if (members[i].selected)
            continue;

        $("#follower-list").append(render(members[i]));
    }
}

function getDate(str) {
    var dt = str.split("-");
    var ret = new Date();

    ret.setFullYear(parseInt(dt[0]));
    ret.setMonth(parseInt(dt[1]) - 1);
    ret.setDate(parseInt(dt[2]));

    return ret;
}