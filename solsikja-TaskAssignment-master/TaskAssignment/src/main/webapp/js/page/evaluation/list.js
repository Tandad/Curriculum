/**
 * Created by liymm on 2015-03-24.
 */
$(document).ready(function () {
    var p = new Pagination();

    genPage();

    function genPage() {
        Util.ajax({
            type: "GET",
            url: "/rest/evaluation/list/count",
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
            url: "/rest/evaluation/list",
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