var userId = /*[[${session.user.getUsername()}]]*/"";
if ('WebSocket' in window) {
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
    var sender = JSON.parse(sendMessage).userId;
    var content = JSON.parse(sendMessage).message;
    var senderAvatar = JSON.parse(sendMessage).userAvatar;
    if (window.Notification) {
        console.log('正在推送通知');
        Notification.requestPermission().then(function (permission) {
            if (permission === 'granted') {
                console.log('用户允许通知');
                var n = new Notification('@'+sender, {
                    body: content,
                    tag: sender,
                    icon: senderAvatar
                })

            } else if (permission === 'denied') {
                console.log('用户拒绝通知');
            }
        });
    }else {
        console.log('浏览器不支持Notification');
    }

}

//关闭WebSocket连接
function closeWebSocket() {
    websocket.close();
}

