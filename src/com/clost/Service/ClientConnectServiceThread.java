package com.clost.Service;

import com.clost.QQ_Common.Message;
import com.clost.QQ_Common.MessageType;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author clost
 * @date 2022/7/17 23:56
 */
public class ClientConnectServiceThread extends Thread {

    private Socket socket;


    public ClientConnectServiceThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //等待读取服务器消息
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) ois.readObject();


                switch (msg.getMsgType()){
                    case MessageType.MESSAGE_RET_ONLINE_USER ->{
                        String[] onlineUsers = msg.getContent().split(" ");
                        for (int i = 0; i < onlineUsers.length; i++) {
                            System.out.println("用户:" + onlineUsers[i]);
                        }
                    }
                    case MessageType.MESSAGE_COMM_MES ->{
                        //普通消息打印到控制台
                        System.out.println("\n" + msg.getSender() + "对" + msg.getGetter() + "说 : " + msg.getContent());
                    }
                    case MessageType.MESSAGE_TO_ALL_MES -> {
                        System.out.println("\n" + msg.getSender() +"对大家说: " +msg.getContent());
                    }
                    case MessageType.MESSAGE_FILE_MES -> {
                        System.out.println("\n你收到了来自" + msg.getSender() + "发送的文件!==> 文件保存到电脑的: " + msg.getDest());
                        FileOutputStream fos = new FileOutputStream(msg.getDest());
                        fos.write(msg.getFileBytes());
                        fos.close();
                        System.out.println("保存成功");
                    }
                }




            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            }
        }
    }




    public Socket getSocket() {
        return socket;
    }

}
