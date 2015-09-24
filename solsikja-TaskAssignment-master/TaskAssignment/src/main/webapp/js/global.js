$(document).ready(function () {

    /* init underscore*/
    _.templateSettings = {
        evaluate    : /{{([\s\S]+?)}}/g,
        interpolate : /{{=([\s\S]+?)}}/g,
        escape      : /{{-([\s\S]+?)}}/g
    };

    /* init Messenger */
    Messenger.options = {
        extraClasses: "messenger-fixed messenger-on-top messenger-on-right",
        theme: "future"
    }

});

/** 时间格式化 **/
Date.prototype.format =function(format)
{
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    };
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4- RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
                RegExp.$1.length==1? o[k] :
                ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
};

function emptyString(str) {
    return str == null || str == "";
}


var Util = {
    /* 异步数据加载 */
    ajax : function (param) {
        Messenger().run({
            errorMessage: "数据加载失败!",
            progressMessage: "数据加载中",
            hideAfter: 3
        }, param);
    },

    /* 错误提示 */
    errorTip : function (msg) {
        Messenger().post({
            type: "error",
            message: msg,
            hideAfter: 1
        });
    },

    /* 错误提示数据 */
    errorData : function(msg) {
        return {type: "error", message: msg};
    }
};