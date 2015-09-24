/**
 * Created by liymm on 2015-03-24.
 */
$(document).ready(function(){
    $("#submitbtn").click(submitForm);
});

function submitForm() {
    Util.ajax({
        type: "POST",
        url: "/rest/user/add",
        data: $("form").serialize(),
        success: function(res) {
            if (res.status != 0) {
                return Util.errorData(res.info);
            }

            resetForm();

            return "添加成功!";
        }
    });
}

function resetForm() {
    $("#name").val("");
    $("#realname").val("");
    $("#admin").val(0);
}