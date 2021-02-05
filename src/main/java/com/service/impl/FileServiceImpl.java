package com.service.impl;

import com.entity.Result;
import com.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Result fileTransfer(MultipartFile file) {
        String text = "";

        Map<String, Object> data = null;
        try {
            //存放路径
            file.transferTo(new File("/Users/johnwang/Desktop/DSC180A/data/raw/input.txt"));
            InputStreamReader read = new InputStreamReader(
                    file.getInputStream(),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);

            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                text += lineTxt + "\n";
            }
            System.out.println(text);
        } catch (IOException e) {
            return new Result(1,"error",null);
        } finally {
            data = new HashMap<>();
            data.put("text", text);
            return new Result(0, "success", data);
        }
    }
    public Result fileRead(String path) {
        String all = "";
        String single = "";
        String multi = "";
        List<Map<String,Object>> wordCloudList = new ArrayList<>();
        Map<String, Object> data = null;
        String autoPhrase = "/Users/johnwang/Desktop/DSC180A/data/outputs/AutoPhrase.txt";
        String autoPhraseSingle = "/Users/johnwang/Desktop/DSC180A/data/outputs/AutoPhrase_single-word.txt";
        String autoPhraseMulti = "/Users/johnwang/Desktop/DSC180A/data/outputs/AutoPhrase_multi-words.txt";
        try {
            //读取AutoPhrase.txt
            File textFile = new File(autoPhrase);
//            System.out.println(textFile==null);
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(textFile),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);

            int linNum = 0;
            String lineTxt = null;
            Map<String,Object> wordCloud = null;
            while((lineTxt = bufferedReader.readLine()) != null){
//                System.out.println(lineTxt);
                all += lineTxt + "\n";
                if(linNum++ <20) {
                    String[] wordAndValue = lineTxt.split("\t");
                    wordCloud = new HashMap<>();
                    wordCloud.put("name", wordAndValue[1]);
                    wordCloud.put("value", new Double(wordAndValue[0]));
                    wordCloudList.add(wordCloud);
                }
                else {
                    String[] wordAndValue = lineTxt.split("\t");
                    wordCloud = new HashMap<>();
                    wordCloud.put("name", wordAndValue[1]);
                    wordCloud.put("value", new Double(wordAndValue[0]));
                    wordCloudList.add(wordCloud);
                }
            }
            bufferedReader.close();
            read.close();
            System.out.println(all);

            //读取AutoPhrase_single-word.txt
            textFile = new File(autoPhraseSingle);
            read = new InputStreamReader(new FileInputStream(textFile),"utf-8");
            bufferedReader = new BufferedReader(read);
            while((lineTxt = bufferedReader.readLine()) != null){
                single += lineTxt + "\n";
            }
            bufferedReader.close();
            read.close();
            System.out.println(single);

            //读取AutoPhrase_multi-words.txt
            textFile = new File(autoPhraseMulti);
            read = new InputStreamReader(new FileInputStream(textFile),"utf-8");
            bufferedReader = new BufferedReader(read);
            while((lineTxt = bufferedReader.readLine()) != null){
                multi += lineTxt + "\n";
            }
            bufferedReader.close();
            read.close();
            System.out.println(multi);

            //读取图片
            String srcFile = "/Users/johnwang/Desktop/DSC180A/data/outputs/multi_value_distribution.png";
            String destFile = "/Users/johnwang/Desktop/python/src/main/webapp/img/multi_value_distribution.png";
            String tempFile =path+"img/multi_value_distribution.png";
//            System.out.println(srcFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
            System.out.println(bufferedInputStream==null);
            BufferedOutputStream destFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destFile)));
            BufferedOutputStream tempFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(tempFile)));
            System.out.println(path);
            byte[] buffer = new byte[1024];
            int len;
            //读写流
            while ((len = bufferedInputStream.read(buffer)) != -1)
            {
//                System.out.println("------------------------------");
                destFileBufferedOutputStream.write(buffer,0,len);
                tempFileBufferedOutputStream.write(buffer,0,len);
            }
            //关闭流
            destFileBufferedOutputStream.close();
            tempFileBufferedOutputStream.close();
            bufferedInputStream.close();

            srcFile = "/Users/johnwang/Desktop/DSC180A/data/outputs/single_value_distribution.png";
            destFile = "/Users/johnwang/Desktop/python/src/main/webapp/img/single_value_distribution.png";
            tempFile =path+"img/single_value_distribution.png";
            System.out.println(tempFile);
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
            destFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(destFile)));
            tempFileBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(tempFile)));

            //读写流
            while ((len = bufferedInputStream.read(buffer)) != -1)
            {
//                System.out.println("---------------------------------");
                destFileBufferedOutputStream.write(buffer,0,len);
                tempFileBufferedOutputStream.write(buffer,0,len);
            }

            //关闭流
            destFileBufferedOutputStream.close();
            tempFileBufferedOutputStream.close();
            bufferedInputStream.close();

        } catch (IOException e) {
            return new Result(1,"error",null);
        } finally {
            data = new HashMap<>();
            data.put("all", all);
            data.put("single",single);
            data.put("multi",multi);
            data.put("wordCloud",wordCloudList);
            return new Result(0, "success", data);
        }
    }
}
