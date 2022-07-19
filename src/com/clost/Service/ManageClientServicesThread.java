package com.clost.Service;

import java.util.HashMap;

/**
 * @author clost
 * @date 2022/7/18 0:09
 */
public class ManageClientServicesThread {

    private static HashMap<String,ClientConnectServiceThread> hm = new HashMap<>();

    //添加通信线程到线程集合中
    public static void addClientThread(String userId,ClientConnectServiceThread clientConnectServiceThread){
        hm.put(userId,clientConnectServiceThread);
    }

    //通过use取出对应线程，根据线程拿到对应socket
    public static ClientConnectServiceThread getClientThread(String userId){

        return hm.get(userId);
    }

}
