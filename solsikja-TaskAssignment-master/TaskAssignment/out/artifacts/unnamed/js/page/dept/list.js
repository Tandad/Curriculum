/**
 * Created by liymm on 2015-03-24.
 */
$(document).ready(function () {

    loadDeptlist();

    function loadDeptlist() {
        Util.ajax({
            type: "GET",
            url: "/rest/dept/list",
            success: function (res) {
                if (res.status!=0) {
                    return Util.errorData(res.info);
                }

                var render =  _.template($("#item-tmpl").html());
                var str = "";
                _.each(res.data, function (d) {

                    if (d.evausername == null) {
                        d.evausername = "无";
                    }

                    if (d.evaluate == null) {
                        d.evaluate = 0;
                        d.eva = "否";
                    } else if (d.evaluate == 0) {
                        d.eva = "否";
                    } else {
                        d.eva = "是";
                    }
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
            url: "/rest/dept/remove",
            success: function (res) {
                if (res.status!=0) {
                    return Util.errorData(res.info);
                }

                location.reload();
            }
        })
    });

    $("#list").on("click", ".add-eva", function(){
        Util.ajax({
            type: "GET",
            data: {id: $(this).data("id")},
            url: "/rest/dept/evaluate/add",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                loadDeptlist();

                return "操作成功";
            }
        });
    });

    $("#list").on("click", ".del-eva", function(){
        Util.ajax({
            type: "GET",
            data: {id: $(this).data("id")},
            url: "/rest/dept/evaluate/del",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                loadDeptlist();

                return "操作成功";
            }
        });
    });

    var eva_did = null;

    $("#list").on("click", ".eva-user", function(){

        eva_did = $(this).data("id");

        Util.ajax({
            type: "GET",
            url: "/rest/dept/" + eva_did + "/members",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                var render = _.template($("#eva-item").html());
                var str = "";
                _.each(res.data, function(d){
                    str += render(d);
                });
                $("#eva-list").html(str);
                $("#eva-dialog").modal("show");

                return "加载成功";
            }
        });
    });

    $("#eva-list").on("click", ".select-eva-user", function(){
        Util.ajax({
            type: "GET",
            data: {id: eva_did, uid: $(this).data("id")},
            url: "/rest/dept/evaluate/user",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                $("#eva-dialog").modal("hide");
                loadDeptlist();
                eva_did = null;

                return "操作成功";
            }
        });
    });

    $("#clear-eva-user").click(function(){
        Util.ajax({
            type: "GET",
            data: {id: eva_did, uid: -1},
            url: "/rest/dept/evaluate/user",
            success: function(res) {
                if (res.status != 0) {
                    return Util.errorData(res.info);
                }

                $("#eva-dialog").modal("hide");
                loadDeptlist();
                eva_did = null;

                return "操作成功";
            }
        });
    });

});