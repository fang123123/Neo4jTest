package Object;


import java.util.Iterator;
import java.util.List;

public class publication {
    private String id;
    private String title;
    private List<publication_author> authors;
    public publication(){
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<publication_author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<publication_author> authors) {
        this.authors = authors;
    }

    public void show_publication()
    {
        System.out.println("{");
        System.out.println("id:\""+id+"\",");
        System.out.println("title:\""+title+"\",");
        System.out.println("authors\":[");
        Iterator<publication_author> it =authors.iterator();
        while(it.hasNext())
        {
            it.next().show_publication_author();
        }
        System.out.println("]");
        System.out.println("}");
    }
}