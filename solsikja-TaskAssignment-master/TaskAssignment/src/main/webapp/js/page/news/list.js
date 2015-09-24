/**
 * Created by liymm on 2015-03-24.
 */
var selectedUser = null;

$(document).ready(function () {
    var p = new Pagination();

    genPage();

    function genPage() {
        Util.ajax({
            type: "GET",
            url: "/rest/news/list/count",
            success: function (res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                p.init(0, res.data, loadNewslist);
            }
        })
    };

    function loadNewslist() {
        Util.ajax({
            type: "GET",
            data: {offset: p.getOffset(), length: p.getLen()},
            url: "/rest/news/list",
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

    $("#list").on("click", ".edit", function(){
        location.href = "/news/" + $(this).data("id") + "/edit";
    });

    $("#list").on("click", ".delete", function(){

        if (!confirm("是否要删除此公告？")) {
            return;
        }

        Util.ajax({
            type: "GET",
            data: { id: $(this).data("id") },
            url: "/rest/news/remove",
            success: function (res) {
                if (res.status!=0) {
                    return Util.errorData(res.info);
                }

                location.reload();
            }
        })
    });
    //
    //$(document).on("click", ".leader", function(){
    //    selectedUser = $(this).data("id");
    //    $("#leader-add-dialog").modal("show");
    //    loadLeadDepts();
    //});
    //
    //$(document).on("click", ".del-leader", function(){
    //    Util.ajax({
    //        type: "GET",
    //        data: {uid : selectedUser},
    //        url: "/rest/dept/"+ $(this).data("id") +"/leader/remove",
    //        success: function(res) {
    //            if (res.status) {
    //                return Util.errorData(res.info);
    //            }
    //
    //            loadLeadDepts();
    //
    //            return "删除成功";
    //        }
    //    });
    //});
});