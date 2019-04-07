package com.hand.demo.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.demo.pojo.Advice;
import com.hand.demo.pojo.FinalStatic;
import com.hand.demo.pojo.TransferData;
import com.hand.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

/**
 * @author MissLi
 * @create 2019/4/6 13:38
 */
@ServerEndpoint(value = "/webSocket")
@Component
public class MyWebSocket {
    private static UserModel userModel;
    private static int onlineClient=0;
    public static Map<String,MyWebSocket> sockets=new HashMap<>();
    public static Set<Advice> hashSet=new HashSet<>();
    private Advice advice=new Advice();
    private Session session;
    private User user;
    private static HttpSession httpSession;
    @Autowired
    public void beanUserModel(UserModel userModel){
        MyWebSocket.userModel=userModel;
    }
    public static void setHttpSession(HttpSession httpSession){
        MyWebSocket.httpSession=httpSession;
    }

    /**
     * 连接成功,时向管理员提示
     */
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        User user= (User) httpSession.getAttribute(FinalStatic.USER.toString());
        sockets.put(user.getUserName(),this);
        this.user=user;
        System.out.println("advice:"+user);
        if("user".equals(user.getRole().toLowerCase())){
            addOnlineClient();
            try {
                advice.setUser(user);
                advice.setStatus("online");
                sendInfoToAdmin(advice);
                hashSet.add(advice);
            } catch (Exception e) {
                System.out.println("通知失败");
            }
        }
        System.out.println("online :"+getOnlineClient());
    }
    /**
     * 连接关闭,通知管理员
     */
    @OnClose
    public void onClose() throws Exception{
        if("user".equals(user.getRole().toLowerCase())){
            subOnlineClient();
            advice.setStatus("offline");
            sendInfoToAdmin(advice);
            sockets.remove(user.getUserName());
            hashSet.remove(advice);
        }
    }

    /**
     * 接收消息,信息处理站
     */
    @OnMessage
    public void onMessage(String msg) throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        TransferData transferData=mapper.readValue(msg,TransferData.class);
        //判断信息的来源，做不同的处理
        Advice dataAdvice=new Advice();
        if(!jumpUserIfAdmin(transferData.getUserName())){
            //用户完成信息
            User dataUser=new User(transferData.getUserName(),1);
            dataAdvice.setUser(dataUser);
            dataAdvice.setHand(transferData.getPutHand());
            dataAdvice.setStatus("online");
            System.out.println("receiver "+dataAdvice);
            sendInfoToAdmin(dataAdvice);
        }else{
            //管理员信息
            Map<String,String> map=new HashMap<>();
            map.put("finish","true");
            sendInfoToUser(map);
            System.out.println(map.entrySet().toString());
        }

    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println();
        error.printStackTrace();
    }
    //自己发送信息
    private void sendMessage(String message) throws Exception{
        this.session.getBasicRemote().sendText(message);
    }
    private static synchronized int getOnlineClient(){
        return MyWebSocket.onlineClient;
    }
    private static synchronized void addOnlineClient(){
        MyWebSocket.onlineClient++;
    }
    private static synchronized void subOnlineClient(){
        MyWebSocket.onlineClient--;
    }
    /**
     * 多个管理员,发送上线消息通知
     */
    public synchronized void sendInfoToUser(Map<String,String> stringMap) throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        for (Map.Entry<String,MyWebSocket> map:sockets.entrySet()){
            if("user".equals(map.getValue().user.getRole().toLowerCase())){
                map.getValue().sendMessage(mapper.writeValueAsString(stringMap));
            }
        }
    }
    public  synchronized void sendInfoToAdmin(Advice advice) throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        for (Map.Entry<String,MyWebSocket> map:sockets.entrySet()){
            if("admin".equals(map.getValue().user.getRole().toLowerCase())){
                map.getValue().sendMessage(mapper.writeValueAsString(advice));
            }
        }
    }

    public boolean jumpUserIfAdmin(String name){
        for (User item:userModel.getListUser()){
            if(item.getUserName().equals(name)){
                return false;
            }
        }
        return true;
    }
}
