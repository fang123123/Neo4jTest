package action;
/*
* 判断数据库中是否存在对应数据*/
import Util.ConnectDB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import Object.ResearchBean;

import java.util.Map;

public class ResearchAction extends ActionSupport {
    /*
    * 与Spring的IOC注入一样
    * 会自动通过setter()方法注入属性*/
    private String expert_name;
    private String publication_title;
    private String keyword;
    private Map session;
    public String mess="";
    public String execute() throws Exception {
        String Status="error";
        if(expert_name.isEmpty()&&publication_title.isEmpty()&&keyword.isEmpty())
        {
            mess="提示：输入为空！！！";
        }
        else{
            String sql = new String();
            if(!expert_name.isEmpty()&&!publication_title.isEmpty()) {
                sql="MATCH (a:Expert)-[:published]->(b:Publication) WHERE " +
                        "a.name=\'"+ expert_name+ "\'AND b.title =\'"+publication_title+"\' "+
                        "RETURN a.id AS expert_id,b.id AS publication_id";
            }
            else if(!expert_name.isEmpty()&&publication_title.isEmpty()){
                sql="MATCH (a:Expert) WHERE " +
                        "a.name=\'" + expert_name+ "\' RETURN a.id AS expert_id";
            }
            else if(expert_name.isEmpty()&&!publication_title.isEmpty()){
                sql="MATCH (a:Publication) WHERE " +
                        "a.title=\'" + publication_title+ "\' RETURN a.id AS publication_id";
            }
            else{
               // sql="MATCH ()"
            }
            ConnectDB con =new ConnectDB();
            Driver driver = con.GetDriver();
            Session sess = driver.session();
            StatementResult result = sess.run(sql);
            if(result.hasNext())
            {
                session = (Map)ActionContext.getContext().getSession();
                session.put("result",result);
                Status= "success";
            }
            else
            {
                mess="搜索结果不存在！";
            }
            sess.close();//关闭数据库
            driver.close();
        }
        return Status;
    }

    public String getExpert_name() {
        return expert_name;
    }
    public void setExpert_name(String expert_name) {
        this.expert_name = expert_name;
    }

    public String getPublication_title() {
        return publication_title;
    }
    public void setPublication_title(String publication_title) {
        this.publication_title = publication_title;
    }

    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
