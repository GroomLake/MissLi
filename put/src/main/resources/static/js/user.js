//普通用户
window.onload=function(){
    var websocket=null;
    var host=window.location.host;
    var flag=true;
    var url="ws://"+host+"/webSocket";
    if ('WebSocket' in window) {
        websocket = new WebSocket(url);
    }else {
        alert('当前浏览器 Not support websocket')
    }
    //收到信息
    websocket.onmessage=function(event){
        var obj=eval("("+event.data+")");
        if(obj.finish==='true'){
            $('#img').css('background-image','url("/images/user/rong.jpg")');
            flag=true;
        }
    };
    //连接错误时提示的消息
    websocket.onerror=function () {
        $('.info').text('主机连接发生错误');
    };
    //连接成功
    websocket.onopen=function () {
        $('.info').text('主机连接成功');
    };
    websocket.onclose = function () {
        $('.info').text('主机断开连接');
    };
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload=function () {
        websocket.close();
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