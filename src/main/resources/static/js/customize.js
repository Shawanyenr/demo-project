/*const toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');
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

*/

/*过渡动画*/
$(function () {
    function hidePreloader() {
        $('.spinner-wrapper').fadeOut(500);
    }
    hidePreloader();
});


/*collapse*/
$(document).on('click', ':not(#collapseExample)', function () {
    $("#collapseExample").collapse('hide');

});

/*图片modal*/
$('#showImgModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var recipient = button.data('whatever') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this)
    $('#modal-img').attr("src", recipient);
})

/*返回顶部*/
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

/*like&fav*/
$(function () {
    $("input[class=like]").change(function () {
        /*if (this.checked){
            alert($(this).prop('id'));
        }else alert("123")*/
        var likeId = $(this).prop('id').substring($(this).prop('id').split("_", 2)[0].length + 1);
        console.log(likeId);
        $.ajax({
            url: "/toggleLike?id=" + likeId,
            success: function (data) {
                if (data === "FAIL") {
                    window.location.href = "/login";
                }
            }
        })
    });
    $("input[class=fav]").change(function () {
        var favId = $(this).prop('id').substring($(this).prop('id').split("_", 2)[0].length + 1);
        console.log(favId);
        $.ajax({
            url: "/toggleFav?id=" + favId,
            success: function (data) {
                if (data === "FAIL") {
                    window.location.href = "/login";
                }
            }
        })
    })
});

function sendReport(pid) {
    if (confirm("确认举报pid: "+pid+"?")){
        $.post({
            url: "/report",
            data: {pid: pid},
            success: function (data) {
                if (data==="ok"){
                    alert("举报成功");
                }else{
                    alert(data);
                }
            },
            error: function () {
                alert("举报失败");
            }
        })
    }
}

/*
document.addEventListener('DOMContentLoaded', evt => {
    $('#notificationSection').load(/!*[[@{/user/reloadUnchecked}]]*!/"");
})
*/
