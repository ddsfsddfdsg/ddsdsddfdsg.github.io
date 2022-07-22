package com.clost.Service;

import com.clost.QQ_Common.Message;
import com.clost.QQ_Common.MessageType;

import java.io.*;

/**
 * @author clost
 * @date 2022/7/20 8:47
 */
public class FileClientService {


    /**
     * 发送文件方法
     * @param src 发送文件路径
     * @param desc 接收文件保存路径
     * @param sender 发送人
     * @param getter 接收人
     */
    public void sendFile(String src,String desc,String sender,String getter){

        Message msg = new Message();
        msg.setMsgType(MessageType.MESSAGE_FILE_MES);
        msg.setSender(sender);
        msg.setGetter(getter);
        msg.setSrc(src);
        msg.setDest(desc);

        //读取文件，封装到msg中
        System.out.println("");
        FileInputStream fis = null;
        byte[] fileBytes = new byte[(int)new File(src).length()];

        try {
            fis = new FileInputStream(src);
            fis.read(fileBytes);
            msg.setFileBytes(fileBytes);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\n你给" + getter + "发送文件到对方电脑的" + desc );

        //发送文件

        try {
            ObjectOutputStream oos = new ObjectOutputStream
                            (ManageClientServicesThread.getClientThread(sender).getSocket().getOutputStream());

            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
