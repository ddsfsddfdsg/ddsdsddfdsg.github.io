package com.clost.QQ_Common;



public interface MessageType {
    String MESSAGE_LOGIN_SUCCESS = "1";//登录成功
    String MESSAGE_LOGIN_FAIL = "2";//登录失败
    String MESSAGE_COMM_MES = "3";//普通消息
    String MESSAGE_GET_ONLINE_USER = "4";//要求返回在线用户列表
    String MESSAGE_RET_ONLINE_USER = "5";//返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6";//客户端请求退出
    String MESSAGE_TO_ALL_MES = "7";//群发消息
    String MESSAGE_FILE_MES = "8";//文件消息
    String MESSAGE_OFFLINE_MES = "9";//离线留言
}
