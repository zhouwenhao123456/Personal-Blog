$(function () {

    $(".firstMenu").click(function () {

        var sign = $(this).children(".sign");
        var secondMenu = $(this).children("ul");
        if ("+" == sign.html()) {

            /*打开一个新的菜单之前，讲之前所有的菜单全部关闭*/
          /*  var allSecondMenu = $(".secondMenu");
            allSecondMenu.css("display", "none");
            var allSign = $(".sign");
             allSign.html("+");*/

            secondMenu.css("display", "block");
            sign.html("-");
        }
        else if ("-" == sign.html()) {
            secondMenu.css("display", "none");
            sign.html("+");
        }
    });
    $(".secondMenu").click(function (event) {
         event.stopPropagation();  /* 阻止 click 事件冒泡到父元素：触发子元素事件的同时，不触发父元素事件*/
    });
});