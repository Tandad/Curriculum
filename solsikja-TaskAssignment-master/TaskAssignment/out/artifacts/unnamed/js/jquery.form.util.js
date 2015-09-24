/**
 * Created by LiYm on 2015/7/2.
 */
(function ($) {
    /**
     * 表单 绑定数据/获取数据
     * @param object 绑定数据
     * @returns {Object} 获取数据
     */
    $.fn.formValue = function (object) {
        var that = this;
        var value = new Object();

        function getValue(t, obj) {
            var _this = $(t);
            var name = _this.attr("name");
            if (name == null)
                return;

            obj[name] = _this.val();
        };

        function setValue(t, obj) {
            var _this = $(t);
            var o = obj;
            var n = _this.attr("name").split(".");
            for (var i in n) {
                if (o == null) {
                    o = "";
                    break;
                }
                o = o[n[i]];
            }

            _this.val(o);
        }

        if (object != null) {
            that.formReset();

            that.find("input[name],textarea[name],select[name]").each(function () {
                setValue(this, object);
            });
        }

        that.find("input[name],textarea[name],select[name]").each(function() {
            getValue(this, value);
        });

        return value;
    };

    /**
     * 表单预校验
     */
    $.fn.formPreValidate = function() {
        var that = this;

        $(that).find("[data-validate]").blur(function(){
            v_validate(this);
        });

        $(that).find("[data-validate]").each(function(){
            var _this = $(this);
            var listener = _this.data("listener") == null ? _this : $(_this.data("listener"));
            var target = _this.data("target") == null ? _this : $(_this.data("target"));

            listener.on("focus change", null, function() {
                target.popover("destroy");
            });
        });
    };

    function v_regValidate(exp, val){
        if (exp == null) {
            return val != "";
        } else if (val == "") {
            return true;
        } else {
            return exp.test(val);
        }
    }

    var v_tipFunc = function(_this, txt) {
        var target = _this.data("target") == null ? _this : $(_this.data("target"));
        target.popover({placement: "top", content: txt, trigger: "manual"});
        target.popover('show');
    }

    var v_data = {
        required: {
            warning: "不能为空",
            regExp: null
        },

        number: {
            warning: "必须为正整数",
            regExp: /^\d+$/
        },

        preal: {
            warning: "必须为正整数或小数",
            regExp: /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/
        }
    }

    function v_validate(_t) {
        var _this = $(_t);

        if (_this.data("validate") == null || _this.data("validate") == "")
            return true;

        var flags = _this.data("validate").split("|");

        for (var i in flags) {
            var v = v_data[flags[i]];

            if (v == null)
                continue;
            //
            //console.log(flags[i]);

            if (!v_regValidate(v.regExp, _this.val())) {
                v_tipFunc(_this, v.warning);
                return false;
            }
        }

        return true;
    }

    /**
     * 表单校验
     * @returns {boolean}   true 验证通过；false 验证失败
     */
    $.fn.formValidate = function() {
        var that = this;
        var res = true;

        var arr = that.find("[data-validate]");

        for (var i=0; i<arr.length; i++) {
            if (!v_validate(arr[i])) {
                res = false;
            }
        }

        return res;
    };

    $.fn.formReset = function() {

        var that = this;
        that[0].reset();
        that.find("[data-validate]").each(function(){
            var _this = $(this);
            var target = _this.data("target") == null ? _this : $(_this.data("target"));
            target.popover("destroy");
        });
    };

})(jQuery)
