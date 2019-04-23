package Util;
/*
 * 将获取的List<Knowledge> 对象存入csv
 * 存储格式
 * 实体类:
 * knowledge.csv存入"id", "name", "name_zh","level","definition","definition_zh"
 * expert.csv存入"id","name","name_zh","position","h_index","interest"
 * publication.csv存入"id","title
 * 关系类:
 * kg_to_kg.csv存入"id","parent_id"
 * kg_to_expert.csv存入"Kid","Eid"
 * kg_to_publication.csv存入"Kid","Pid"
 * publication_to_expert.csv存入"Pid","Eid"
 * */

import Object.Knowledge;
import Object.expert;
import Object.publication;
import Object.publication_author;
import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonToExcel {

    public static void writeKnowledgeCSV(List<Knowledge> knowledgeList) {
        try {
            //Knowledge.csv路径、表头、表头长度
            String csvFilePathKnowledge = "D:/IdeaProjects/knowledge.csv";
            String[] csvHeadersKnowledge = {"id:ID(Knowledge)", "name", "name_zh", "level", "definition", "definition_zh"};
            int HeadersLengthOfKnowledge = 6;
            // 定义路径，分隔符，编码
            CsvWriter csvWriterKnowledge = new CsvWriter(csvFilePathKnowledge, ',', Charset.forName("UTF-8"));
            // 写表头
            csvWriterKnowledge.writeRecord(csvHeadersKnowledge);
            Iterator<Knowledge> it = knowledgeList.iterator();
            while (it.hasNext()) {
                Knowledge knowledge = it.next();
                String[] csvContent = new String[HeadersLengthOfKnowledge];
                csvContent[0] = String.valueOf(knowledge.getID());
                csvContent[1] = String.valueOf(knowledge.getName());
                csvContent[2] = String.valueOf(knowledge.getName_zh());
                csvContent[3] = String.valueOf(knowledge.getLevel());
                csvContent[4] = String.valueOf(knowledge.getDefinition());
                csvContent[5] = String.valueOf(knowledge.getDefinition_zh());
                //迭代插入记录
                csvWriterKnowledge.writeRecord(csvContent);
            }
            csvWriterKnowledge.close();
//            System.out.println("<--------Knowledge.csv文件写入成功-------->");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeExpertCSV(List<expert> expertList, boolean append) {
        try {
            //Expert.csv路径、表头、表头长度
            String csvFilePathExpert = "D:/IdeaProjects/expert.csv";
            int HeadersLengthOfExpert = 6;
            CsvWriter csvWriterExpert;
            if (!append) {
                String[] csvHeadersExpert = {"id:ID(Expert)", "name", "name_zh", "position", "h_index", "interests"};
                // 定义路径，分隔符，编码
                csvWriterExpert = new CsvWriter(csvFilePathExpert, ',', Charset.forName("UTF-8"));
                // 写表头
                csvWriterExpert.writeRecord(csvHeadersExpert);
            } else {
                BufferedWriter out = new BufferedWriter(new FileWriter(csvFilePathExpert, true));
                csvWriterExpert = new CsvWriter(out, ',');
            }
            Iterator<expert> it1 = expertList.iterator();
            while (it1.hasNext()) {
                expert expert = it1.next();
                String[] csvContent = new String[HeadersLengthOfExpert];
                csvContent[0] = expert.getId();
                csvContent[1] = expert.getName();
                csvContent[2] = expert.getName_zh();
                csvContent[3] = expert.getPosition();
                csvContent[4] = expert.getH_index();
                csvContent[5] = expert.interests.toString();
                //迭代插入记录
                csvWriterExpert.writeRecord(csvContent);
            }
            csvWriterExpert.close();
//            System.out.println("<--------expert.csv文件写入成功-------->");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writePublicatinCSV(List<publication> publicationList , boolean append) {
        try {
            //Publication.csv路径和表头
            String csvFilePathPublication = "D:/IdeaProjects/publication.csv";
            int HeadersLengthOfPublication = 2;
            CsvWriter csvWriterPublication;
            if (!append) {
                String[] csvHeadersPublication = {"id:ID(Publication)", "title"};
                // 定义路径，分隔符，编码
                csvWriterPublication = new CsvWriter(csvFilePathPublication, ',', Charset.forName("UTF-8"));
                // 写表头
                csvWriterPublication.writeRecord(csvHeadersPublication);
            } else {
                BufferedWriter out = new BufferedWriter(new FileWriter(csvFilePathPublication, true));
                csvWriterPublication = new CsvWriter(out, ',');
            }
            Iterator<publication> it1 = publicationList.iterator();
            while (it1.hasNext()) {
                publication publication = it1.next();
                String[] csvContent = new String[HeadersLengthOfPublication];
                csvContent[0] = publication.getId();
                csvContent[1] = publication.getTitle();
                //迭代插入记录
                csvWriterPublication.writeRecord(csvContent);
            }
            csvWriterPublication.close();
//            System.out.println("<--------publication.csv文件写入成功-------->");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeKgToKg(List<Knowledge> knowledgeList) {
        try{
            //Knowledge.csv路径、表头、表头长度
            String csvFilePathKgToKg = "D:/IdeaProjects/kg_to_kg.csv";
            String[] csvHeadersKToKg = {":START_ID(Knowledge)", ":END_ID(Knowledge)"};
            int HeadersLengthOfKgToKg = 2;
            // 定义路径，分隔符，编码
            CsvWriter csvWriterKnowledge = new CsvWriter(csvFilePathKgToKg, ',', Charset.forName("UTF-8"));
            // 写表头
            csvWriterKnowledge.writeRecord(csvHeadersKToKg);
            Iterator<Knowledge> it = knowledgeList.iterator();
            while (it.hasNext()) {
                Knowledge knowledge = it.next();
                String[] csvContent = new String[HeadersLengthOfKgToKg];
                csvContent[0] = String.valueOf(knowledge.getParent());
                csvContent[1] = String.valueOf(knowledge.getID());
                //迭代插入记录
                csvWriterKnowledge.writeRecord(csvContent);
            }
            csvWriterKnowledge.close();
//            System.out.println("<--------kg_to_kg.csv文件写入成功-------->");
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void writeKgToExpert(String Kid,List<expert> expertList,boolean append) {
        try {
            //kg_to_expert.csv路径和表头
            String csvFilePathKgToExpert= "D:/IdeaProjects/kg_to_expert.csv";
            int HeadersLengthOfKgToExpert = 2;
            CsvWriter csvWriterKgToExpert;
            if (!append) {
                String[] csvHeadersKgToExpert = {":START_ID(Knowledge)", ":END_ID(Expert)"};
                // 定义路径，分隔符，编码
                csvWriterKgToExpert = new CsvWriter(csvFilePathKgToExpert, ',', Charset.forName("UTF-8"));
                // 写表头
                csvWriterKgToExpert.writeRecord(csvHeadersKgToExpert);
            } else {
                BufferedWriter out = new BufferedWriter(new FileWriter(csvFilePathKgToExpert, true));
                csvWriterKgToExpert = new CsvWriter(out, ',');
            }
            Iterator<expert> it1 = expertList.iterator();
            while (it1.hasNext()) {
                String[] csvContent = new String[HeadersLengthOfKgToExpert];
                csvContent[0] = Kid;
                csvContent[1] = it1.next().getId();
                //迭代插入记录
                csvWriterKgToExpert.writeRecord(csvContent);
            }
            csvWriterKgToExpert.close();
//            System.out.println("<--------kg_to_expert.csv文件写入成功-------->");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeKgToPublication(String Kid,List<publication> publicationList,boolean append) {
        try {
            //kg_to_publication.csv路径和表头
            String csvFilePathKgToPublication = "D:/IdeaProjects/kg_to_publication.csv";
            int HeadersLengthOfKgToPublication = 2;
            CsvWriter csvWriterKgToPublication;
            if (!append) {
                String[] csvHeadersKgToExpert = {":START_ID(Knowledge)", ":END_ID(Publication)"};
                // 定义路径，分隔符，编码
                csvWriterKgToPublication = new CsvWriter(csvFilePathKgToPublication, ',', Charset.forName("UTF-8"));
                // 写表头
                csvWriterKgToPublication.writeRecord(csvHeadersKgToExpert);
            } else {
                BufferedWriter out = new BufferedWriter(new FileWriter(csvFilePathKgToPublication, true));
                csvWriterKgToPublication = new CsvWriter(out, ',');
            }
            Iterator<publication> it1 = publicationList.iterator();
            while (it1.hasNext()) {
                String[] csvContent = new String[HeadersLengthOfKgToPublication];
                csvContent[0] = Kid;
                csvContent[1] = it1.next().getId();
                //迭代插入记录
                csvWriterKgToPublication.writeRecord(csvContent);
            }
            csvWriterKgToPublication.close();
//            System.out.println("<--------kg_to_publication.csv文件写入成功-------->");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeExpertToPublication(String Pid,List<publication_author> publication_authorList,boolean append){
        try {
            //publication_to_expert.csv路径和表头
            String csvFilePathExpertToPublication = "D:/IdeaProjects/expert_to_publication.csv";
            int HeadersLengthOfExpertToPublication = 2;
            CsvWriter csvWriterExpertToPublication;
            if (!append) {
                String[] csvHeadersPublicationToExpert = {":START_ID(Expert)", ":END_ID(Publication)"};
                // 定义路径，分隔符，编码
                csvWriterExpertToPublication = new CsvWriter(csvFilePathExpertToPublication, ',', Charset.forName("UTF-8"));
                // 写表头
                csvWriterExpertToPublication.writeRecord(csvHeadersPublicationToExpert);
            } else {
                BufferedWriter out = new BufferedWriter(new FileWriter(csvFilePathExpertToPublication, true));
                csvWriterExpertToPublication = new CsvWriter(out, ',');
            }
            Iterator<publication_author> it1 = publication_authorList.iterator();
            while (it1.hasNext()) {
                String[] csvContent = new String[HeadersLengthOfExpertToPublication];
                csvContent[0] = it1.next().getId();
                csvContent[1] = Pid;
                //迭代插入记录
                csvWriterExpertToPublication.writeRecord(csvContent);
            }
            csvWriterExpertToPublication.close();
         //   System.out.println("<--------expert_to_publication.csv文件写入成功-------->");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* public static void main(String[] args) throws FileNotFoundException {
        //将json文件内容读入list
        List<Knowledge> paperList = new ArrayList<Knowledge>();
        String path = "D:/IdeaProjects/SciKG_min_1.0";
        String pathName = path + ".json";
        System.out.println("开始解析json文件");
        long startTime=System.currentTimeMillis();
        paperList = JsonToList.jsonToList(pathName);
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime)/1000;
        System.out.println("解析json文件结束,执行时间："+excTime+"s");
        System.out.println("开始写入csv文件");
        startTime=System.currentTimeMillis();
        //写文件knowledge.csv
        writeKnowledgeCSV(paperList);
        //写文件kg_to_kg.csv
        writeKgToKg(paperList);
        //写文件expert.csv
        Iterator<Knowledge> iter = paperList.iterator();
        int i = 0;
        boolean append = false;
        int j = 0;
        boolean append_1 = false;
        while (iter.hasNext()) {
            i++;
            //从第二个列表开始为追加模式
            if (i == 2)
            {
                append =true;
            }
            Knowledge knowledge = iter.next();
            String Kid = String.valueOf(knowledge.getID());
            List<expert> expertList = knowledge.getExperts();
            List<publication> publicationList = knowledge.getPublications();

            //以追加方式写文件expert.csv
            writeExpertCSV(expertList,append);

            //以追加方式写文件kg_to_expert.csv
            writeKgToExpert(Kid,expertList,append);

            //以追加方式写文件publication.csv
            writePublicatinCSV(publicationList,append);

            //以追加方式写文件kg_to_publication.csv
            writeKgToPublication(Kid,publicationList,append);

            Iterator<publication> iter1 = publicationList.iterator();
            while(iter1.hasNext()){
                j++;
                if(j==2){
                    append_1=true;
                }
                publication publication = iter1.next();
                String Pid = publication.getId();
                List<publication_author> publication_authorList = publication.getAuthors();
                writeExpertToPublication(Pid,publication_authorList,append_1);
            }
        }
        endTime=System.currentTimeMillis();
        excTime=(float)(endTime-startTime)/1000;
        System.out.println("写入csv文件结束,执行时间："+excTime+"s");
    }*/
}
