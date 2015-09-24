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
        Util.ajax({
            type: "GET",
            url: "/rest/task/list/my/"+type+"/count",
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
            url: "/rest/task/list/my/"+type,
            success: function (res) {
                if (res.status!=0) {
                    return Util.errorData(res.info);
                }

                var render =  _.template($("#item-tmpl").html());
                var str = "";
                _.each(res.data, function (d) {
                    str += render(d);
                });

                $("#list").html(str);
            }
        });
    };

    $("#list").on("click", ".task-evaluate", function() {
        Util.ajax({
            type: "GET",
            url: "/rest/task/" + $(this).data("id") + "/taskuser/list",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                var render = _.template($("#tu-item").html());
                var render_eva = _.template($("#tu-eva-item").html());
                var str = "";
                _.each(res.data, function (d) {
                    console.log(d.status);
                    if (d.status == 2) {
                        str += render_eva(d);
                    } else {
                        str += render(d);
                    }
                });
                $("#tu-list").html(str);

                $("#evaluate-dialog").modal("show");

                return "列表获取成功";
            }
        });
    });

    $("#evaluate-dialog").on("click", ".evaluate-btn", function() {
        var parent = $(this).parent();
        var target = $("#tu-form-" + $(this).data("id"));

        Util.ajax({
            type: "POST",
            data: target.serialize(),
            url: "/rest/task/evaluate",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                target.find("input").attr("disable", true);
                target.find("textarea").attr("disable", true);
                parent.remove();

                return "评分成功!";
            }
        });
    });

    $("#evaluate-dialog").on("hidden.bs.modal", function () {
        genPage();
    });

    $("#tu-list").on("click", "input[name=complete]", function(){
        var id = $(this).data("id");
    });
});