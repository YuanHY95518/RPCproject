package com.yhy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        //1.将服务注册到注册中心

        //2.开启服务
        ServerSocket serverSocket = new ServerSocket(10011);
        Socket server = serverSocket.accept();
        ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
        RpcData rpcData = (RpcData) ois.readObject();
        System.out.println(rpcData);
        System.out.println("开启服务");

        //假装解析了rpc数据

        String result = null;
        //反射调用服务
        //if (rpcData.getInterfaceName() == "com.yhy.IUserService"){
            Class<?> userServiceClass = Class.forName("com.yhy.UserServiceImpl");
            Constructor<?> constructor = userServiceClass.getConstructor();
            UserServiceImpl userService = (UserServiceImpl)constructor.newInstance();
            result = userService.getNameById(rpcData.getArguments());
        //}
        //服务端返回结果
        ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
        oos.writeUTF(result);

        //这里要刷新缓冲区将数据发送出去
        oos.flush();
        System.in.read();
        //System.in.read();
    }
}
