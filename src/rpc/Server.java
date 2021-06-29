package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        //建立服务端连接端口号
        ServerSocket serverSocket = new ServerSocket(8087);

        //监听连接
        Socket socket = serverSocket.accept();

        //读取数据
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.readObject();

        //反射执行
        Class<? extends People> peopleClass = People.class;
        Constructor<? extends People> constructor = peopleClass.getConstructor();
        People people = constructor.newInstance();

        Method showInfo = peopleClass.getMethod("showInfo");

        String result = (String)showInfo.invoke(people);
    }
}
