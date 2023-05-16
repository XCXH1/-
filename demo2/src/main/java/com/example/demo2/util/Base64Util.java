package com.example.demo2.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class Base64Util {
    public static final String converToBase64(MultipartFile file){
        byte[] bytes = new byte[0];
        try{
            // 将文件的内容读取到字节数组 bytes 中
            bytes = file.getBytes();
            // 使用 Base64 编码将字节数组转换为字符串
            String string = Base64.encodeBase64String(bytes);
            // 移除字符串中的空白字符
            string.replace("[\\s*\t\n\r]","");
            // 在 Base64 编码的字符串前添加了适当的前缀，使其成为一个可以在 HTML 中显示的图像
            string = "data:image/jpeg;base64,"+string;

            return string;
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
