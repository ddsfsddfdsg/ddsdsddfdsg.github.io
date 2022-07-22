package com.clost.Service;

import com.clost.QQ_Common.Message;
import com.clost.QQ_Common.MessageType;
import com.clost.QQ_Common.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author clost
 * @date 2022/7/17 23:38
 */
public class UserClientService {

    private User u = new User();
    private Socket socket;


    /**
     *
     * @param userId 用户id
     * @param passwd 用户密码
     * @param userType 用户类型 新用户 or 老用户
     */
    public boolean checkLogin(String userId, String passwd, String userType) {
        boolean check = false;
        u.setUserId(userId);
        u.setPasswd(passwd);
        u.setUserType(userType);

        try {
            //向服务端发起验证
            socket = new Socket(InetAddress.getByName("82.157.170.131"), 54321);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);

            //接收验证结果

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message msg = (Message) ois.readObject();
            if (msg.getMsgType().equals(MessageType.MESSAGE_LOGIN_SUCCESS)) {
                check = true;

                //创建通信线程
                ClientConnectServiceThread ccst = new ClientConnectServiceThread(socket);
                ccst.start();
                //把线程放入线程集合管理
                ManageClientServicesThread.addClientThread(userId, ccst);


            } else {
                //登录失败，关闭socket
                socket.close();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check;
    }


    //向服务器发起请求，获取所有在线用户

    public void onlineUserList() {

        Message msg = new Message();
        msg.setMsgType(MessageType.MESSAGE_GET_ONLINE_USER);
        try {
            //拿到请求用户的socket
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientServicesThread.getClientThread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //用户退出，通知服务器
    public void logOut() {

        Message msg = new Message();
        msg.setMsgType(MessageType.MESSAGE_CLIENT_EXIT);
        msg.setSender(u.getUserId());

        //发送Message
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientServicesThread.getClientThread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(msg);
            System.out.println(u.getUserId() + "退出了系统");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void sendOfflineMes(String sender,String getter,String co){

    }


}
