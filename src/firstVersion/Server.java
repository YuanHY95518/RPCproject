package firstVersion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //创建服务端套接字,只需要设置端口即可
        ServerSocket serverSocket = new ServerSocket(10010);
        Socket socket = serverSocket.accept();
        //监听接口
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        People  people = null;
        try {
            people = (People) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("无效对象");
        }
        Class<? extends People> peopleClass = people.getClass();
        Method showInfo = peopleClass.getMethod("showInfo");
        showInfo.invoke(people);
        socket.close();
        serverSocket.close();
    }
}
