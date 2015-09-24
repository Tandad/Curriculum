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
            url: "/rest/task/list/"+type+"/count",
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
            url: "/rest/task/list/"+type,
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
});