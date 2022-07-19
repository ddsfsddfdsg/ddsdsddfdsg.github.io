package com.clost.Service;

import java.util.HashMap;

/**
 * @author clost
 * @date 2022/7/18 0:09
 */
public class ManageClientServicesThread {

    private static HashMap<String,ClientConnectServiceThread> hm = new HashMap<>();

    /**
     * 添加通信线程到线程集合中
     * @param userId 用户名
     * @param clientConnectServiceThread 该用户对应的线程
     */
    public static void addClientThread(String userId,ClientConnectServiceThread clientConnectServiceThread){
        hm.put(userId,clientConnectServiceThread);
    }


    /**
     * 通过userId取出对应线程，根据线程拿到对应socket
     * @param userId
     * @return
     */
    public static ClientConnectServiceThread getClientThread(String userId){

        return hm.get(userId);
    }

}
