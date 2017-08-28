package com.yrkj.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by xuenianxiang on 2017/8/28.
 */
public class TxtUtils {

    @SuppressWarnings("rawtypes")
    public static File writeTxtFile(List<String> contentList , String fileName)
            throws IOException {

        String filePath = "txt";

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
            System.out.println("文件夹已创建");
        }
        //定义文件名格式并创建
        File txtFile = File.createTempFile(fileName, ".txt", new File(filePath));
        //先读取原有文件内容，然后进行写入操作
        FileWriter writer = null;

        String content = "";

        for (String url:contentList){
            content = content + url + "\r\n";
        }

        try {
            writer.write(content);

        }catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }

        return txtFile;
    }
}
