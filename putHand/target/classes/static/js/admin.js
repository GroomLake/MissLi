//管理员
//普通用户
window.onload=function(){
    var lineCount=0;
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
        //false 上线或下线通知
        if(obj.hand==='false'){
            onlineAdvice(obj);
        }
        if(obj.hand==='true'){
            infoAdvice(obj);
        }
    };
    //连接错误时提示的消息
    websocket.onerror=function () {

    };
    //连接成功,管理员页面要刷新
    websocket.onopen=function () {
       $.ajax({
           url:'/online/people',
           type:'post',
           dataType:'json',
           success:function (event) {
               if(!$.isEmptyObject(event)){
                   var obj=eval(event);
                   for (var item in obj){
                       onlineAdvice(obj[item]);
                   }
               }
           }
       });
    };
    websocket.onclose = function () {

    };
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload=function () {
        websocket.close();
    };
    //上线或线下通知
    function onlineAdvice(obj) {
        $('#tbody tr').each(function () {
            if($(this).attr('id')===obj.user.userName){
                $(this).children('td').each(function (i) {
                    if(i===2){
                        $(this).text(obj.status);
                    }
                })
            }
        });
    }
    //通知设置
    function infoAdvice(obj) {
        $('#tbody tr').each(function () {
            if($(this).attr('id')===obj.user.userName){
                $(this).children('td').each(function (i) {
                    if(i===3){
                        if(obj.user.i===1){
                            lineCount++;
                            $(this).text('已完成');
                        }
                    }
                });
                $(this).addClass('table-active');
            }
        });
        $('#online').text('完成人数 '+lineCount);
        $('#unline').text('未完成人数 '+(31-lineCount));
    }
    $('#clear').click(function () {
        $('#tbody tr').each(function () {
            $(this).children('td').each(function (i) {
                if(i===3){
                    $(this).text('未完成');
                }
            })
            $(this).removeClass('table-active');
        });
        lineCount=0;
        $('#online').text('完成人数 '+lineCount);
        $('#unline').text('未完成人数 '+(31-lineCount));
        var data='{"userName":'+'"'+$('#name').text()+'"'+',"putHand":'+'"false"}';
        websocket.send(data);
    });
};
