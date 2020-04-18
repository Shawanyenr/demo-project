if ('WebSocket' in window) {
    /**
     * 手机端访问
     * window.location.href  /192.168.31.78:12006/ws
     * window.location.host  192.168.31.78:12006
     * window.location.port  12006
     */

    var localPath = (window.location.host);
    console.log(localPath);
    websocket = new WebSocket("ws://" + localPath + "/websocket/" + userId);
} else {
    alert('当前浏览器不支持websocket哦！')
}

//连接发生错误的回调方法
websocket.onerror = function () {
    // setMessageInnerHTML("WebSocket连接发生错误");
    console.log("WebSocket连接发生错误");
};

//连接成功建立的回调方法
websocket.onopen = function () {
    // setMessageInnerHTML("WebSocket连接成功");
    console.log("WebSocket连接成功");
}

//接收到消息的回调方法
websocket.onmessage = function (event) {
    sendNotification(event.data);
}

//连接关闭的回调方法
websocket.onclose = function () {
    // setMessageInnerHTML("WebSocket连接关闭");
    console.log("WebSocket连接关闭");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeWebSocket();
}

//将消息显示在网页上
function sendNotification(sendMessage) {
    // document.getElementById('message').innerHTML += sendMessage + '<br/>';
    var sender = sendMessage.split(",", 2)[0];
    var content = sendMessage.substring(sender.length + 1);
    if (sender === sendUserId) {
        if (window.Notification) {
            console.log('正在推送通知');
            /*for (var i = 0; i < 10; i++) {
                // 感谢标记，我们应该只看到内容为 "Hi! 9" 的通知
                var n = new Notification("Hi! " + i, {tag: 'soManyNotification'});
            }*/
            Notification.requestPermission().then(function (permission) {
                if (permission === 'granted') {
                    console.log('用户允许通知');
                    var n = new Notification('@'+sender, {
                        body: content,
                        tag: sender,
                        icon: 'http://blog.gdfengshuo.com/images/avatar.jpg',
                        requireInteraction: true
                    })

                } else if (permission === 'denied') {
                    console.log('用户拒绝通知');
                }
            });
        }
    } else {
        console.log('浏览器不支持Notification');
    }
}

//关闭WebSocket连接
function closeWebSocket() {
    websocket.close();
}


if (window.Notification) {
    console.log('浏览器支持Notification');
    if (Notification.permission === 'granted') {
        console.log('用户允许通知');
    } else if (Notification.permission === 'denied') {
        console.log('用户拒绝通知');
    } else {
        console.log('用户还没选择，去向用户申请权限吧');
    }

    Notification.requestPermission().then(function (permission) {
        if (permission === 'granted') {
            console.log('用户允许通知');
            var n = new Notification('状态更新提醒', {
                body: '你的朋友圈有3条新状态，快去查看吧',
                /*tag: 'linxin',
                icon: 'http://blog.gdfengshuo.com/images/avatar.jpg',*/
                requireInteraction: true
            })

        } else if (permission === 'denied') {
            console.log('用户拒绝通知');
        }
    });
} else {
    console.log('浏览器不支持Notification');
}
