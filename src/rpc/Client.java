package rpc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        //新建对象，进行序列化传输
        People people = new People("张三", 45);

        //获取主机名
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("无效主机");
        }

        //设置远程主机和端口并进行传输
        Socket socket = null;
        ObjectOutputStream oos = null;
        try {
            socket = new Socket(localhost, 8087);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(people);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接失败");
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
