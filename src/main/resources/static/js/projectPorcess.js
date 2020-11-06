$(function () {
    var testProId = 1
    $.ajax({
        url: '/projectProcess/getProcessInfo?proId=' + testProId,
        type: 'GET',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                //渲染对应的进度
                if (i != data.length -1) {
                    // console.log(data[i][item])
                    $("#lybContent").append(
                        "<div class='item'>" +
                        "<div class='top'>" +
                        "<div class='yuan'></div>" +
                        "<div class='top'>" + data[i]["eventTime"] + "</div>" +
                        "</div>" +
                        "<div class='bottom'>" +
                        "<div class='xian'></div>" +
                        "<div class='content'>" + data[i]["eventContent"] + "</div>" +
                        "</div>" +
                        "</div>"
                    )
                } else {
                    $("#lybContent").append(
                        "<div class='item'>" +
                        "<div class='top'>" +
                        "<div class='yuan'></div>" +
                        "<div class='top'>" + data[i]["eventTime"] + "</div>" +
                        "</div>" +
                        "<div class='bottom'>" +
                        "<div class='content'>" + data[i]["eventContent"] + "</div>" +
                        "</div>" +
                        "</div>"
                    )
                }

            }
        }
    })
});