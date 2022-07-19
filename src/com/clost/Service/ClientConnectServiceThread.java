package com.clost.Service;

import com.clost.QQ_Common.Message;
import com.clost.QQ_Common.MessageType;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


/**
 * @author clost
 * @date 2022/7/17 23:56
 */
public class ClientConnectServiceThread extends Thread{

    private Socket socket;


    public ClientConnectServiceThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        //等待读取服务器消息
        while (true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) ois.readObject();

                if (msg.getMsgType().equals(MessageType.MESSAGE_RET_ONLINE_USER)){

                    String[] onlineUsers = msg.getContent().split(" ");
                    for (int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("用户:" + onlineUsers[i]);
                    }
                }else {
                    System.out.println("是其他类型的message, 暂时不处理....");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket(){
        return socket;
    }

}
