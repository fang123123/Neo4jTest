package Object;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*根据json数据格式定义的对象*/
public class Knowledge {
    private int ID;
    private String name;
    private String name_zh;
    private int level;
    private String definition;
    private String definition_zh;
    private List<Integer> child_nodes = new ArrayList<Integer>();
    private int parent;
    private List<expert> experts;
    private List<publication> publications;
    public Knowledge(){
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
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

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public String getDefinition() {
        return definition;
    }
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition_zh() {
        return definition_zh;
    }
    public void setDefinition_zh(String definition_zh) {
        this.definition_zh = definition_zh;
    }

    public void setChild_nodes(List<Integer> child_nodes) {
        this.child_nodes = child_nodes;
    }
    public List<Integer> getChild_nodes() {
        return child_nodes;
    }

    public int getParent() {
        return parent;
    }
    public void setParent(int parent) {
        this.parent = parent;
    }

    public List<expert> getExperts() {
        return experts;
    }
    public void setExperts(List<expert> experts) {
        this.experts = experts;
    }

    public List<publication> getPublications() {
        return publications;
    }
    public void setPublications(List<publication> publications) {
        this.publications = publications;
    }

    public void showKnowledge()
    {
        System.out.println("{");
        System.out.println("\"id\":"+ID+",");
        System.out.println("\"name\":\""+name+"\",");
        System.out.println("\"name_zh\":\""+name_zh+"\",");
        System.out.println("\"level\":"+level+",");
        System.out.println("\"definition\":\""+definition+"\",");
        System.out.println("\"Definition_zh\":\""+definition_zh+"\",");
        System.out.println("\"child_nodes\":"+child_nodes+",");
        System.out.println("\"parent\":"+parent+",");
        System.out.println("\"experts\":[");
        Iterator<expert> it1 =experts.iterator();
        while(it1.hasNext())
        {
            it1.next().show_expert();
        }
        System.out.println("],");
        Iterator<publication> it2 =publications.iterator();
        System.out.println("\"publications:\":[");
        while(it2.hasNext())
        {
            it2.next().show_publication();
        }
        System.out.println("]");
        System.out.println("}");
    }
}