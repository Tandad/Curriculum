/**
 * Created by liymm on 2015-03-24.
 */
$(document).ready(function () {
    var p = new Pagination();

    var type = "pub";

    genPage();

    $(".nav-tabs a").click(function () {
        $(".nav-tabs li").removeClass("active");
        $(this).parent().addClass("active");
        type = $(this).attr("id");
        genPage();
    });

    function genPage() {

        $("#head").html($("#" + type + "-head").html());

        Util.ajax({
            type: "GET",
            url: "/rest/task/list/member/"+type+"/count",
            success: function (res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                p.init(0, res.data, loadTasklist);
            }
        })
    };

    function loadTasklist() {
        Util.ajax({
            type: "GET",
            data: {offset: p.getOffset(), length: p.getLen()},
            url: "/rest/task/list/member/"+type,
            success: function (res) {
                if (res.status!=0) {
                    return Util.errorData(res.info);
                }

                var render =  _.template($("#"+type+"-item-tmpl").html());
                var str = "";
                _.each(res.data, function (d) {
                    str += render(d);
                });

                $("#list").html(str);
            }
        });
    };

    $("#list").on("click", ".task-commit", function() {

        $("#tuid").val($(this).data("id"));
        $("#report").val("");

        $("#commit-dialog").modal("show");
    });

    $("#commit-btn").click(function(){
        Util.ajax({
            type: "POST",
            data: $("#commit-form").serialize(),
            url: "/rest/task/commit",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                genPage();

                $("#commit-dialog").modal("hide");

                return "操作成功";
            }
        })
    });

    $("#list").on("click", ".tu-info", function() {
        Util.ajax({
            type: "GET",
            url: "/rest/task/taskuser/" + $(this).data("id"),
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                $("#info-form").formValue(res.data);
                $("#info-dialog").modal("show");

                return "加载成功!";
            }
        });
    });
});