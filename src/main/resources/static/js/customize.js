const toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');
const currentTheme = localStorage.getItem('theme');

if (currentTheme) {
    document.documentElement.setAttribute('data-theme', currentTheme);

    if (currentTheme === 'dark') {
        toggleSwitch.checked = true;
    }
}

function switchTheme(e) {
    if (e.target.checked) {
        document.documentElement.setAttribute('data-theme', 'dark');
        localStorage.setItem('theme', 'dark');
    } else {
        document.documentElement.setAttribute('data-theme', 'light');
        localStorage.setItem('theme', 'light');
    }
}

toggleSwitch.addEventListener('change', switchTheme, false);


$(document).on('click', ':not(#collapseExample)', function () {
    $("#collapseExample").collapse('hide');

});


$(function () {
    //返回顶部===>出现与消失
    //当屏幕滚动，触生 scroll 事件
    $(window).scroll(function () {
        var top1 = $(this).scrollTop();     //获取相对滚动条顶部的偏移
        if (top1 > 200) {      //当偏移大于200px时让图标淡入（css设置为隐藏）
            $(".top").stop().fadeIn();
        } else {
            //当偏移小于200px时让图标淡出
            $(".top").stop().fadeOut();
        }
    });
    //去往顶部
    $(".top").click(function () {
        $("body , html").animate({scrollTop: 0}, 300);   //300是所用时间
    });
});
