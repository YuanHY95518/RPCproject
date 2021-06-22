package com.yhy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMainSimple {
    public static void main(String[] args) {
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
            System.out.println((String) ois.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
