/*!
 * 分页处理.
 * 
 * @since: 1.0.0
 */
(function ($) {

    "use strict";

    /**
     * handler:pageIndex 所选页面的索引，从0开始；pageSize 页面的大小
     */
    $.tbpage = function (selector, handler) {

        $(selector).off("click", ".tbpage-item").on("click", ".tbpage-item", function () {

            var pageNum= $(this).attr("pageNum");

            var pageSize = $('.tbpage-size option:selected').val();
            // 判断所选元素是否为当前页面
            // 若不是当前页面才需要处理
            if ($(this).parent().attr("class").indexOf("active") > 0) {
                //console.log("为当前页面");
            } else {
                handler(pageNum, pageSize);
            }

        });


        $(selector).off("change", ".tbpage-size").on("change", ".tbpage-size", function () {

            var pageNum = $(this).attr("pageNum");

            var pageSize = $('.tbpage-size option:selected').val();

            handler(pageNum, pageSize);
        });
    };

})(jQuery);