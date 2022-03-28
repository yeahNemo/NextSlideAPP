package com.nemo.wearos02.socketHandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketUtil {
    //负责加工Socket 并试图发送

    public static void updateSocketData(Socket socket, String data){
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(data);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendSocket(String ip, String port){
        try {
            Socket socket = new Socket(ip, Integer.parseInt(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
