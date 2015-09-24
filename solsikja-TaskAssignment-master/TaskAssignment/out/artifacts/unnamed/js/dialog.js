var taskpage = new Pagination();
var tid = 0;

$(document).ready(function () {

    $(document).on("click", ".user-detail", function(){
        $("#user-detail-dialog").modal("show");
        loadUserDetail($(this).data("id"));
    });

    $("#reply-task").click(function() {
        Util.ajax({
            type: "POST",
            data: {
                "task.id" : tid,
                "content" : $("#reply-content").val()
            },
            url: "/rest/task/reply",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                return "评论成功!";
            }
        })

    });

    $(document).on("click", "#modify-user-detail", modifyUserDetail);

    $(document).on("click", ".dept-detail", function(){
        $("#dept-detail-dialog").modal("show");
        loadDeptDetail($(this).data("id"));
    });

    $(document).on("click", "#modify-dept-detail", modifyDeptDetail);

    $(document).on("click", ".task-detail", function(){
        $("#task-detail-dialog").modal("show");
        loadTaskDetail($(this).data("id"));
    });

    //$(document).on("click", ".service-stop", function () {
    //    $("#service-stop-dialog").modal("show");
    //    $("#service-stop-confirm-btn").attr("data-id", $(this).attr("data-id"));
    //});
    //
    //$(document).on("click", "#service-stop-confirm-btn", function () {
    //    stopService($(this).attr("data-id"));
    //});
    //
    //$(document).on("click", "#modify-shop-info-btn", modifiyShopDetail);
    //
    //$(document).on("click", "#add-service-btn", function() {
    //    $("#modify-service-info-btn").unbind();
    //    $("#modify-service-info-btn").click(addServiceDetail);
    //    $("#service-detail-dialog").modal("show");
    //    loadServiceAdd($(this).data("shop-id"));
    //});
    //
    //$(document).on("click", ".service-detail", function() {
    //    $("#modify-service-info-btn").unbind();
    //    $("#modify-service-info-btn").click(modifyServiceDetail);
    //    $("#service-detail-dialog").modal("show");
    //    loadServiceDetail($(this).data("id"));
    //});

});

function loadTaskDetail(id) {
    Util.ajax({
        type: "GET",
        url: "/rest/task/" + id,
        success: function (res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            var render = _.template($("#task-detail-templ").html());
            $("#task-detail").html(render(res.data));
        }
    });

    tid = id;

    Util.ajax({
        type: "GET",
        url: "/rest/task/" + tid + "/reply/list/count",
        success: function (res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }


            taskpage.setID("task-page");
            taskpage.init(0, res.data, loadTaskComments);
        }
    });
}

function loadTaskComments() {
    Util.ajax({
        type: "GET",
        data: {offset: taskpage.getOffset(), length: taskpage.getLen()},
        url: "/rest/task/" + tid + "/reply/list",
        success: function (res) {
            if (res.status!=0) {
                return Util.errorData(res.info);
            }

            var render =  _.template($("#task-comment-tmpl").html());
            var str = "";
            _.each(res.data, function (d) {
                str += render(d);
            });

            $("#task-comments").html(str);
        }
    });
}

function loadUserDetail(id) {
    Util.ajax({
        type: "GET",
        url: "/rest/user/" + id,
        success: function (res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            var render = _.template($("#user-detail-tmpl").html());
            $("#user-detail").html(render(res.data.user));

            render = _.template($("#dept-item-tmpl").html());
            _.each(res.data.depts, function (d) {
                $("#did").append(render(d));
            });

            if (res.data.user.department != null) {
                $("#did").val(res.data.user.department.id);
            } else {
                $("#did").val(-1);
            }

            if (res.data.user.admin != null) {
                $("#admin").val(res.data.user.admin);
            } else {
                $("#admin").val(0);
            }
        }
    });
}

function modifyUserDetail() {
    Util.ajax({
        type: "POST",
        url: "/rest/user/modify",
        data: $("#user-detail-form").serialize(),
        success: function (res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            location.reload();
        }
    });
}

function loadDeptDetail(id) {

    Util.ajax({
        type: "GET",
        url: "/rest/dept/" + id,
        success: function (res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            var render = _.template($("#dept-detail-tmpl").html());
            $("#dept-detail").html(render(res.data));
        }
    });

    Util.ajax({
        type: "GET",
        url: "/rest/dept/" + id + "/members",
        success: function (res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            var render = _.template($("#member-item-tmpl").html());
            $("#member-list").html("");
            _.each(res.data, function (d) {
                $("#member-list").append(render(d));
            });
        }
    });

    Util.ajax({
        type: "GET",
        url: "/rest/dept/" + id + "/leaders",
        success: function (res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            var render = _.template($("#leader-item-tmpl").html());
            $("#leader-list").html("");
            _.each(res.data, function (d) {
                $("#leader-list").append(render(d));
            });
        }
    });
}

function modifyDeptDetail() {

    Util.ajax({
        type: "POST",
        url: "/rest/dept/modify",
        data: $("#dept-detail-form").serialize(),
        success: function (res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            location.reload();
        }
    });
}
