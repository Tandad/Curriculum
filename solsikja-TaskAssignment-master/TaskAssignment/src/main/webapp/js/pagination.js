/** 分页相关 **/
function Pagination() {

    /* 私有成员 */
    var page = 0;
    var length = 10;
    var tpage = 0;

    /* 公有函数 */
    this.init = function(p, count, func) {
        page = p;
        func();
        generate(count);
        addListener(func);
    };

    this.getPage = function() {
        return page;
    }

    this.getLen = function() {
        return length;
    }

    this.getOffset = function() {
        return page * length;
    }

    /* 私有函数 */
    function addListener(func) {
        if ($(".page-a") != null) {
            $(".page-a").unbind();
            $(".page-a").click(function () {
                var li = $(this).parent();
                if (li.hasClass("active"))
                    return;

                page = parseInt($(this).data("page"));
                onClick(func);
            });
        }

        if ($("#pre-a") != null) {
            $("#pre-a").unbind();
            $("#pre-a").click(function () {
                if ($(this).parent().hasClass("disabled"))
                    return;
                page = 0;
                onClick(func);
            });
        }

        if ($("#next-a") != null) {
            $("#next-a").unbind();
            $("#next-a").click(function () {
                console.log("next-a");
                if ($(this).parent().hasClass("disabled"))
                    return;
                page = tpage-1;
                onClick(func);
            });
        }
    };

    function onClick(func) {
        func();

        $(".pagination li").removeClass("active").removeClass("disabled");
        $("a[data-page='"+page+"']").parent().addClass("active");

        if (page == 0)
            $("#pre-a").parent().addClass("disabled");

        if (page == tpage - 1)
            $("#next-a").parent().addClass("disabled");

        showPageNum();
    };

    function generate(cnt) {

        /* 总数为0 不计算分页 */
        if (cnt == 0)
            return;

        //计算总页码
        tpage = parseInt(cnt/length);
        if (cnt%length != 0)
            tpage ++;

        var str = "";
        str += "<p>找到" + cnt + "条记录，共" + tpage + "页</p>";
        str += "<nav><ul class='pagination'>";
        if (page == 0)
            str += "<li class='disabled'><a href='javascript:void(0);' id='pre-a'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>上一页</span></a></li>";
        else
            str += "<li><a href='javascript:void(0);' id='pre-a'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>上一页</span></a></li>";

        for (var i=0; i<tpage; i++) {
            if (i==page)
                str += "<li class='active'><a href='javascript:void(0);' class='page-a' data-page='"+i+"'>"+(i+1)+"</a></li>"
            else
                str += "<li><a href='javascript:void(0);' class='page-a' data-page='"+i+"'>"+(i+1)+"</a></li>";
        }

        if (tpage <= page+1)
            str += "<li class='disabled'><a href='javascript:void(0);'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>下一页</span></a></li>";
        else
            str += "<li><a href='javascript:void(0);' id='next-a'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>下一页</span></a></li>";

        str += "</ul></nav>";

        $("#page").html(str);

        showPageNum();
    };

    function showPageNum() {
        if (tpage <= 10)
            return;

        $(".pagination li").addClass("hidden");
        $("#pre-a").parent().removeClass("hidden");
        $("#next-a").parent().removeClass("hidden");

        for (var p=page; p>=Math.max(page-5, 0); p--) {
            $("a[data-page='"+p+"']").parent().removeClass("hidden");
        }

        for (var p=page; p<=Math.min(page+5, tpage-1); p++) {
            $("a[data-page='"+p+"']").parent().removeClass("hidden");
        }
    }
}

