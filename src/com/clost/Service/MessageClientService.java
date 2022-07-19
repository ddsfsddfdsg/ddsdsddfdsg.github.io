package com.clost.Service;

import com.clost.QQ_Common.Message;
import com.clost.QQ_Common.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @author clost
 * @date 2022/7/19 15:42
 */
public class MessageClientService {

    /**
     *私聊方法
     * @param content 消息内容
     * @param sender 发送人
     * @param getter 接收人
     */
    public static void sendMessageToOne(String content,String sender,String getter ){
        Message msg = new Message();
        msg.setSender(sender);
        msg.setGetter(getter);
        msg.setContent(content);
        msg.setMsgType(MessageType.MESSAGE_COMM_MES);
        //发送时间
        msg.setSendTime(new Date().toString());
        System.out.println("");
        //发送消息
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                                (ManageClientServicesThread.getClientThread(sender).getSocket().getOutputStream());
            oos.writeObject(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 群发方法
     * @param content 消息内容
     * @param sender 发送人
     */
    public static void sendMessageToAll(String content,String sender){
        Message msg = new Message();
        msg.setSender(sender);
        msg.setContent(content);
        msg.setMsgType(MessageType.MESSAGE_TO_ALL_MES);
        //发送时间
        msg.setSendTime(new Date().toString());
        System.out.println(sender +"对所有人说" + content);
        //发送消息
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientServicesThread.getClientThread(sender).getSocket().getOutputStream());
            oos.writeObject(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
