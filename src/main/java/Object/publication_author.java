package Object;

public class publication_author {
    private String id;
    private String name;

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
    public void show_publication_author()
    {
        System.out.println("{");
        System.out.println("id:\""+id+"\",");
        System.out.println("name:\""+name+"\"");
        System.out.println("}");
    }
}