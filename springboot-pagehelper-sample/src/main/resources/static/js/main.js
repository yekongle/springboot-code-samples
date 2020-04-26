"use strict";

// DOM 加载完再执行
$(function () {
    // 根据用户名、页面索引、页面大小获取用户列表
    function getProductList(pageNum, pageSize) {
        $.ajax({
            url: "/",
            contentType: 'application/json',
            data: {
            	"async": true,
                "pageNum": pageNum,
                "pageSize": pageSize
            },
            success: function (data) {
                $("#mainContainer").html(data);
            },
            error: function () {
                toastr.error("error!");
            }
        });
    }

    // 分页
    $.tbpage("#mainContainer", function (pageNum, pageSize) {
    	getProductList(pageNum, pageSize);
    });

});