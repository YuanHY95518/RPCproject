package com.yhy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        //1.向zookeeper订阅服务

        //2.调用服务
        int uid = 10010;
        UserServiceImpl userService1 = new UserServiceImpl();
        IUserService userService = getProxy(uid,userService1);
        System.out.println(userService.getNameById(uid));
    }

    static IUserService getProxy(int uid,UserServiceImpl userService) {
        return (IUserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws IOException, ClassNotFoundException {
                String result = null;
                try {
                    Socket client = new Socket(InetAddress.getByName("localhost"), 10011);
                    //1.连接server
                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                    RpcData rpcData = new RpcData();
                    rpcData.setMethodName("getNameById");
                    rpcData.setInterfaceName("com.yhy.IUserService");
                    rpcData.setFlag("getService");
                    rpcData.setParameterTypes("String");
                    rpcData.setArguments(100);
                    oos.writeObject(rpcData);

                    //设置输入流得到返回信息
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    result = (String) ois.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
        });
    }
}
