package Util;

import Object.Knowledge;
import com.alibaba.fastjson.JSONReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
* 功能：将json文件解析为list型实体对象*/
public class JsonToList {
    public JsonToList(){
    }
    /*
    * 传入参数：json文件路径
    * 返回参数：List型实体对象*/
    public static List<Knowledge> jsonToList(String pathName) throws FileNotFoundException {
        List<Knowledge> KnowledgeList = new ArrayList<Knowledge>();
        JSONReader reader = new JSONReader(new FileReader(pathName));
        reader.startArray();//数组对象
        while (reader.hasNext()) {
            Knowledge Knowledge = reader.readObject(Knowledge.class);//这边反序列化也是极速
//            Knowledge.showKnowledge();
            KnowledgeList.add(Knowledge);
        }
        reader.endArray();
        reader.close();
        return KnowledgeList;
    }
/*    public static void main(String[] args) throws FileNotFoundException {
        String path = "D:/IdeaProjects/SciKG_min_1.0";
        String pathName = path+".json";
        jsonToList(pathName);
    }*/
}
