package com.service.impl;

import com.service.PythonService;
import com.websocket.WebsocketEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.TextMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class PythonServiceImpl implements PythonService {


    @Autowired
    WebsocketEndPoint websocketEndPoint;
    @Override
    public void runPython() {
        System.out.println("run---------");
//        String python = "/Users/johnwang/Desktop/DSC180A/";
//        String cmd = "sh /Users/johnwang/Desktop/DSC180A/running.sh";

        // String python = "../../DSC180A-Capstone-Project/";
        // String cmd = "sh ../../DSC180A-Capstone-Project/running.sh";
        String python = "../../../../../../../phrase-mining/";
        String cmd = "sh ../../../../../../../phrase-mining/running.sh";
        ../../../../

        //String cmd = "python3 "+ "/Users/johnwang/Desktop/" +"for.py";
        System.out.println(cmd);
        try{
            //Runtime.getRuntime().exec("cd /Users/johnwang/Desktop/DSC180b-Capstone-Project/");
            Process proc = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                websocketEndPoint.sendMessage(new TextMessage(line));
            }

            in.close();
            proc.waitFor();
        }catch(IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
