//普通用户
window.onload=function(){
    var lockRececonnect=false;//重连机制
    var tt;
    var websocket=null;
    var host=window.location.host;
    var flag=true;
    var url="ws://"+host+"/webSocket";
    if ('WebSocket' in window) {
        websocket = new WebSocket(url);
        init();
    }else {
        alert('当前浏览器 Not support websocket')
    }
    function init(){
        //收到信息
        websocket.onmessage=function(event){
            var obj=eval("("+event.data+")");
            if(obj.finish==='true'){
                $('#img').css('background-image','url("/images/user/rong.jpg")');
                flag=true;
            }
            if(obj.heart==='true'){
                heartCheck.start();
            }
        };
        //连接错误时提示的消息
        websocket.onerror=function () {
            $('.info').text('发生错误,尝试重连');
            reconner();
        };
        //连接成功
        websocket.onopen=function () {
            $('.info').text('主机连接成功');
            //连接成功，心跳重置
            heartCheck.start();
        };
        websocket.onclose = function () {
            // $('.info').text('主机断开连接');
            reconner();
        };
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload=function () {
            websocket.close();
        };
    }
    function reconner(){
        if(lockRececonnect){
            return;
        }
        lockRececonnect=true;
        //没连接上会一直重连,3秒请求一次
        tt&&clearTimeout(tt);
        tt=setTimeout(function () {
            createWebSocket();
            lockRececonnect=false;
        },5000);
    }
    //心跳检测
    var heartCheck={
        timeout: 3000,
        timeoutObj: null,
        serverTimeoutObj: null,
        start: function(){
            var self = this;
            this.timeoutObj && clearTimeout(this.timeoutObj);
            this.serverTimeoutObj && clearTimeout(this.serverTimeoutObj);
            this.timeoutObj = setTimeout(function(){
                //这里发送一个心跳，后端收到后，返回一个心跳消息，
                var data='{"userName":'+'"'+$('#name').text()+'"'+',"putHand":'+'"4826866"}';
                websocket.send(data);
                self.serverTimeoutObj = setTimeout(function() {
                    //websocket.close();
                    // createWebSocket();
                }, self.timeout);

            }, this.timeout)
        }
    };
    //点击事件
    $('#img').click(function () {
        if(flag){
            var data='{"userName":'+'"'+$('#name').text()+'"'+',"putHand":'+'"true"}';
            websocket.send(data);
            $('#img').css('background-image','url("/images/user/time.jpg")');
            flag=false;
        }
    });

};