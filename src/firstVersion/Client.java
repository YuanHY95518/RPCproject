package firstVersion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //创建传输的对象
        People zhangsan = new People("张三", 18);
        //获取远程主机名
        InetAddress localhost = InetAddress.getByName("localhost");
        //设置远程主机和套接字
        Socket socket = new Socket(localhost, 10010);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(zhangsan);
        oos.close();
    }
}
