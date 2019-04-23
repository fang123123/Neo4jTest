package Object;

import java.util.List;

public class expert {
    public String id;
    public String name;
    public String name_zh;
    public String position;
    // private String experts_aff;//专家的隶属关系
    public String h_index;//专家h指数
    public List<String> interests;
    public expert(){
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName_zh() {
        return name_zh;
    }
    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public String getH_index() {
        return h_index;
    }
    public void setH_index(String h_index) {
        this.h_index = h_index;
    }

    public List<String> getInterests() {
        return interests;
    }
    public void setInterest_list(List<String> interests) {
        this.interests = interests;
    }

    public void show_expert(){
        System.out.println("{");
        System.out.println("\"id\":\""+id+"\",");
        System.out.println("\"name\":\""+name+"\",");
        System.out.println("\"name_zh:\""+name_zh+"\"");
        System.out.println("\"position\":\""+position+"\",");
        System.out.println("\"h_index\":"+h_index+",");
        System.out.println("\"interests\":"+interests+",");
        System.out.println("}");
    }
}