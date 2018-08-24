var myModal_div = "#myModal-div";
var my_alertBox = "#my_alertBox";

$(function () {
    //存在模态框div,则加载模态框
    if ($(myModal_div).length > 0) {
        //模态框html代码被编译成Unicode编码，保存在js代码中
        $(myModal_div).html(myModalUnicode);
    }
});


/* 显示提示框 */
function alertBox(tip, color) {
    $("#warningTip").remove();
    var box = $("<div></div>");
    box.attr("id", "warningTip");
    box.attr("class", "alert alert-" + color + " alert-dismissible");
    box.attr("role", "alert");
    box.html("<button type='button' class='close' data-dismiss='alert' aria-label='Close'><spanaria-hidden='true'>&times;</span></button>" + tip);
    $(my_alertBox).html(box);
}

//显示模态框
function showMyModal(title, content, fun) {
    var myModal = $("#myModal");
    myModal.find("#title").text(title);
    myModal.find("#content").text(content);
    myModal.on('hide.bs.modal', fun);
    myModal.modal('show');
}
