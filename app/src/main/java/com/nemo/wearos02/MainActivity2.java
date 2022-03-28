package com.nemo.wearos02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nemo.wearos02.socketHandler.SocketUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class MainActivity2 extends SlideBackActivity {

    String ip;
    int port;
    String method = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //初始化ip 和 port
        Intent intent = getIntent();
        String ipAndPort = intent.getStringExtra("ipAndPort");
        String[] split = ipAndPort.split(":");
        System.out.println("页面2OnCreate"+split[0]+" "+split[1]);
        ip = split[0];
        port = Integer.parseInt(split[1]);
        //new InitialThread().start();
    }

    public void sendTCP(View view) {
        //下一页
        try {
            method = "1";
            new ConnectionThread().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTCP2(View view) {
        try {
            method = "2";
            new ConnectionThread().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ConnectionThread extends Thread {
        @Override
        public void run() {
            try {
                Socket tSocket = new Socket(ip, port);
                OutputStream out = tSocket.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
                bw.write(method);
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    想要在Android设备上提示连接是否成功但是失败了！
//    class InitialThread extends Thread{
//        @Override
//        public void run() {
//            System.out.println("进入了InitialThread的Run方法！！！");
//            try {
//                Socket tSocket = new Socket(ip, port);
//                BufferedReader br = new BufferedReader(new InputStreamReader(tSocket.getInputStream()));
//                String msgFromServer = br.readLine();
//                System.out.println(msgFromServer);
//                if(msgFromServer!=null){
//                    ((Button)findViewById(R.id.textView)).setText("连接成功！");
//                }
//            } catch (Exception e) {
//                System.out.println("出错了！");
//                e.printStackTrace();
//            }
//        }
//    }
}