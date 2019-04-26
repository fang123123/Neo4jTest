package Util;

import Object.Knowledge;
import Object.expert;
import Object.publication;

import org.neo4j.driver.v1.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

/*
 * 连接Neo4j数据库
 * */
public class ConnectDB {
    //创建一个driver对象
    private Driver CreateDriver() {
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "19961228."));
    }
    /*
     *连接数据库测试*/
    public void test() {
        Driver driver = CreateDriver();
        Session session = driver.session();
        System.out.println("start test!");
        session.run("CREATE (a:Person {name: {name}, title: {title}})",
                parameters("name", "Arthur001", "title", "King001"));

        StatementResult result = session.run("MATCH (a:Person) WHERE a.name = {name} " +
                        "RETURN a.name AS name, a.title AS title",
                parameters("name", "Arthur001"));
        while (result.hasNext()) {
            Record record = result.next();
            System.out.println(record.get("title").asString() + " " + record.get("name").asString());
        }
        session.close();
        driver.close();
        System.out.println("end test!");
    }
    /*
     * 将javabean存入数据库
     * 经过比较还是选择存入csv格式再使用neo4j-import导入*/
 /*   public void save(List<Knowledge> KnowledgeList) {
        Driver driver = CreateDriver();
        Session session = driver.session();
        Iterator<Knowledge> iter = KnowledgeList.iterator();
        int i=0;//Knowledge节点总数
        int j=0;//Expert节点总数
        int k=0;//论文
        System.out.println("start save!");
        while(iter.hasNext())
        {
            Knowledge Knowledge = iter.next();
            *//*
     *将Knowledge标签对象存入数据库
     *若存在，则跳过，不存在则存入
     * *//*
            StatementResult result = session.run("MATCH (a:Knowledge) WHERE a.id="+ Knowledge.getID()+ " RETURN a");
            if ( !result.hasNext() ) {
                i++;
                session.run("CREATE (a:Knowledge{" +
                                "id: {id}, name: {name},name_zh: {name_zh},level: {level}," +
                                "definition: {definition},definition_zh: {definition_zh}})",
                        parameters("id", Knowledge.getID(), "name", Knowledge.getName(),
                                "name_zh", Knowledge.getName_zh(), "level", Knowledge.getLevel(),
                                "definition", Knowledge.getDefinition(), "definition_zh", Knowledge.getDefinition_zh())
                );
            }
            *//*
     * 将Expert标签对象存入数据库
     * 若存在，则跳过，不存在则存入*//*
            Iterator<expert>  iter1=Knowledge.getExperts().iterator();
            while(iter1.hasNext())
            {
                expert expert = iter1.next();
                List<String> interests = new ArrayList<String>();
                interests = expert.getInterests();
                Iterator<String> iter2 = interests.iterator();
                StringBuffer stringBuffer = new StringBuffer();
                if(iter2.hasNext()) {
                    stringBuffer.append(iter2.next());
                    while (iter2.hasNext()) {
                        stringBuffer.append("," + iter2.next());
                    }
                }
                else
                {
                    stringBuffer.append("null");
                }
                *//*
     * 将Expert标签对象存入数据库，此时可能会有重复现象出现，插入时需要检查
     * *//*
                StatementResult result2 = session.run("MATCH (b:Expert) WHERE b.id=\'"+expert.getId()+"\' RETURN b");
                if( !result2.hasNext() )
                {
                    j++;
                    session.run(  "CREATE (b:Expert{" +
                                    "id: {id}, name: {name},name_zh: {name_zh}," +
                                    "position: {position},h_index: {h_index}," +
                                    "interests: {interests}})",
                            parameters("id",expert.getId(),"name",expert.getName(),
                                    "name_zh",expert.getName_zh(),"position",expert.getPosition(),
                                    "h_index",expert.getH_index(),"interests",stringBuffer.toString())
                    );
                }
            }
            *//*
     * 将Publication标签对象存入数据库
     * 若存在，则跳过，不存在则存入
     * *//*
            Iterator<publication> iter3 = Knowledge.getPublications().iterator();
            while(iter3.hasNext())
            {
                publication publication = iter3.next();
                *//*
     * 将Expert标签对象存入数据库，此时可能会有重复现象出现，插入时需要检查*//*
                StatementResult result2 = session.run("MATCH (c:Publication) WHERE c.id=\'"+publication.getId()+"\' RETURN c");
                if( !result2.hasNext() )
                {
                    k++;
                    session.run(  "CREATE (c:Publication{" +
                                    "id: {id}, title: {title}})",
                            parameters("id",publication.getId(),"title",publication.getTitle())
                    );
                }
            }
        }
        System.out.println("知识总条数："+i);
        System.out.println("专家总数："+j);
        System.out.println("论文总数："+k);
        session.close();
        driver.close();
        System.out.println("end save!");
    }*/
   /* public void update(List<Knowledge> KnowledgeList) {
            Driver driver = CreateDriver();
            Session session = driver.session();

            StatementResult result = session.run("MATCH (a:" + code.getLabel() + ") WHERE a." + code.getWhere() + " SET a." + code.getUpdate() + " return COUNT(a)");

            while (result.hasNext())
            {
                Record record = result.next();
                restfulResult.setData(record.fields().get(0).value().toString());
            }

            session.close();
            driver.close();

        }catch(Exception e){
            restfulResult.setResult(Constants.RESULT_STATE_ERROR);
            restfulResult.setMessage(e.getMessage());
        }

        CommUtils.printDataJason(response, restfulResult);
    }

    @RequestMapping(value = "delete")
    public void delete(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody Code code) {
        RestfulResult restfulResult = new RestfulResult();

        try{
            Driver driver = createDrive();
            Session session = driver.session();
            session.run( "match (n) where ID(n) = " + code.getId() +" detach delete n");

            session.close();
            driver.close();

        }catch(Exception e){
            restfulResult.setResult(Constants.RESULT_STATE_ERROR);
            restfulResult.setMessage(e.getMessage());
        }

        CommUtils.printDataJason(response, restfulResult);
    }
*/
    //查询
    public StatementResult CreateSearch(String sql) {
        StatementResult result;
        Driver driver = CreateDriver();
        Session session = driver.session();
        result = session.run(sql);
        session.close();
        driver.close();
        return result;
    }
}
