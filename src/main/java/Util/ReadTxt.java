package Util;

import java.io.*;
/*
*输入参数：文件名 String类型
*返回参数：文件内容 String类型
*/
public class ReadTxt {
    public static String readTxt(String filePath) {
        StringBuffer lineBuffer = new StringBuffer();
        try {
            File file = new File(filePath);
            if(file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                String JsonText = null;
                while ((JsonText = br.readLine()) != null) {
                    System.out.println(JsonText);
                    lineBuffer.append(JsonText);
                }
                br.close();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
        return lineBuffer.toString();
    }
/*测试样例
    public static void main(String[] args) {
        System.out.println(readTxt("D://IdeaProjects//123.txt"));
    }*/
}
