package com.clost.QQ_view;

import com.clost.QQ_Common.User;
import com.clost.QQ_Common.UserType;
import com.clost.Service.MessageClientService;
import com.clost.Service.UserClientService;
import com.clost.utils.Utility;

/**
 * @author clost
 * @date 2022/7/17 23:08
 */
public class QQView {


    public static void main(String[] args) {
        new QQView().Menu();
        System.exit(0);

    }


    public boolean key = true;//循环控制
    String userId;
    UserClientService userClientService = null;

    /**
     * 登录菜单
     */
    public void Menu() {


        while (key) {
            System.out.println("=======QQ简陋版=======");

            System.out.println("\t\t 1 登录");
            System.out.println("\t\t 2.注册新用户");
            System.out.println("\t\t 9 退出");


            switch (Utility.readString(1)) {
                case "1" -> Login(UserType.USER_REGISTER);
                case "2" -> Login(UserType.USER_NO_REGISTER);
                case "9" -> key = false;
            }


        }
    }

    /**
     * @param userType 用户类型 新用户 or 老用户
     *                 新用户注册，老用户登录
     */

    public void Login(String userType) {
        userClientService = new UserClientService();


        while (key) {
            //注册 or 登录
            System.out.println("请输入用户名: ");
            userId = Utility.readString(50);
            System.out.println(" 请输入密码: ");
            String passwd = Utility.readString(50);

            //验证登录 or 注册新用户
            if (userClientService.checkLogin(userId, passwd, userType)) {
                if (UserType.USER_NO_REGISTER.equals(userType)) {
                    System.out.println("恭喜你注册成功");

                } else {
                    System.out.println("恭喜你登录成功");
                }
                //验证通过进入二级菜单
                After_Login();


            } else {
                //验证失败
                System.out.println("登录失败,请检查账号和密码");

            }

        }

    }

    /**
     * 验证通过后二级菜单
     * 登录 or 注册后 进入二级菜单
     */
    public void After_Login() {


        while (key) {
            System.out.println("=========QQ用户============");
            System.out.println("\t\t 1 显示在线用户");
            System.out.println("\t\t 2 群发消息");
            System.out.println("\t\t 3 私聊");
            System.out.println("\t\t 4 发送文件");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请输入你的选择");

            switch (Utility.readString(1)) {
                case "1" -> userClientService.onlineUserList();
                case "2" -> {
                    System.out.println("请输入你要发送的信息");
                    String content = Utility.readString(120);
                    MessageClientService.sendMessageToAll(content,userId);

                }
                case "3" -> {
                    System.out.println("你想给谁发送消息:");
                    String getter = Utility.readString(30);
                    System.out.println("请输入你要发送的内容:");
                    String content = Utility.readString(100);
                    MessageClientService.sendMessageToOne(content, userId, getter);
                }
                case "4" -> {
                }
                case "9" -> {
                    userClientService.logOut();
                    key = false;
                }


            }

        }


    }

}


