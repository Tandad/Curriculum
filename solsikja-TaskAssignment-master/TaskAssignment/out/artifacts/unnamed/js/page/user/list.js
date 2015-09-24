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
            url: "/rest/user/listcount",
            success: function (res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                p.init(0, res.data, loadUserlist);
            }
        })
    };

    function loadUserlist() {
        Util.ajax({
            type: "GET",
            data: {offset: p.getOffset(), length: p.getLen()},
            url: "/rest/user/list",
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

    $("#list").on("click", ".delete", function(){
        Util.ajax({
            type: "GET",
            data: { id: $(this).data("id") },
            url: "/rest/user/remove",
            success: function (res) {
                if (res.status!=0) {
                    return Util.errorData(res.info);
                }

                location.reload();
            }
        })
    });

    $(document).on("click", ".leader", function(){
        selectedUser = $(this).data("id");
        $("#leader-add-dialog").modal("show");
        loadLeadDepts();
    });

    $(document).on("click", ".del-leader", function(){
        Util.ajax({
            type: "GET",
            data: {uid : selectedUser},
            url: "/rest/dept/"+ $(this).data("id") +"/leader/remove",
            success: function(res) {
                if (res.status) {
                    return Util.errorData(res.info);
                }

                loadLeadDepts();

                return "删除成功";
            }
        });
    });

    $("#leader-add").click(function() {

        Util.ajax({
            type: "POST",
            data: {uid : selectedUser, title:$("#title").val()},
            url: "/rest/dept/"+ $("#did").val() +"/leader/add",
            success: function(res) {
                if (res.status) {
                    return Util.errorData(res.info);
                }

                var render = _.template($("#lead-item-tmpl").html());
                $("#lead-dept-list").append(render(res.data));

                return "设置成功";
            }
        });

    });
});

function loadLeadDepts() {
    Util.ajax({
        type: "GET",
        url: "/rest/user/" + selectedUser + "/dept/lead",
        success: function(res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            var render = _.template($("#lead-item-tmpl").html());

            $("#lead-dept-list").html("");

            _.each(res.data, function (d) {
                if (d.title == null) {
                    d.title = "";
                }

                $("#lead-dept-list").append(render(d));
            });

            return "加载成功";
        }
    });
}