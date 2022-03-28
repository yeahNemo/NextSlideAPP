package com.nemo.wearos02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void confirm_clicked(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        String ipAndPort = ((EditText)findViewById(R.id.inputEdit)).getText().toString();
        if(!ipAndPort.equals("")){
            //判断输入是否合法
            String regexForIP = "^(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))$";
            String regexForPort = "^[0-9]{4}";

            Pattern patternIP = Pattern.compile(regexForIP);
            Pattern patternPort = Pattern.compile(regexForPort);
            Matcher matcherIP = patternIP.matcher(ipAndPort.split(":")[0]);
            Matcher matcherPort = null;
            try {
                matcherPort = patternPort.matcher(ipAndPort.split(":")[1]);
            } catch (Exception e) {
                System.out.println("没有:");
                Button button = (Button) findViewById(R.id.button);
                button.setText("CheckInput");
                return;
            }

            if(matcherIP.matches() && matcherPort.matches()){
                intent.putExtra("ipAndPort",ipAndPort);
                System.out.println(ipAndPort);
                //跳转
                startActivity(intent);
            }else{
                System.out.println("ip不合法");
                Button button = (Button) findViewById(R.id.button);
                button.setText("CheckInput");
            }

        }else{
             Button button = (Button) findViewById(R.id.button);
             button.setText("检查后重试");
        }



    }
}