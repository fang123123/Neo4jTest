<%-- 服务器端注释，设置jsp使用脚本语言为java，服务器响应正文文件格式为text/html，正文编码为UTF-8，--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Util.ConnectDB"%>
<%@ page import="com.opensymphony.xwork2.ActionSupport"%>
<%@ page import="org.neo4j.driver.v1.Driver"%>
<%@ page import="org.neo4j.driver.v1.Record"%>
<%@ page import="org.neo4j.driver.v1.Session"%>
<%@ page import="org.neo4j.driver.v1.StatementResult"%>
<%@ page import="Object.ResearchBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>grade</title>
<style type="text/css">
h1 {
align:center;
color:#696969;
}
li {
list-style-type:none;
}
</style>
</head>
<body>
    <center>
    <h1>专 家 信 息 系 统</h1>
    <jsp:include page="time.jsp" flush="true"></jsp:include>
<%
    ConnectDB con =new ConnectDB();
    Driver driver = con.GetDriver();
    Session sess = driver.session();
    //这里可以直接获取上一个页面提交的表单
    String name = request.getParameter("name");
    StatementResult result = (StatementResult)session.getAttribute("result");
  //  StatementResult result = sess.run("MATCH (a:Expert) WHERE a.name=\'"+ name+ "\' RETURN a.id AS id,a.name AS name");
%>
	<ul>
<%
    if(result.hasNext())
    {
		while(result.hasNext()){
			Record record = result.next();
%>
		<li>
<%
            String expert_id = record.get("expert_id").asString();
			out.print(expert_id);
%>
        </li>
		<li>
<%
			String publication_id = record.get("publication_id").asString();
			out.print(publication_id);
%>
        </li>
<%
        }
    }
 %>
    </table>
<%
    sess.close();//关闭数据库
    driver.close();
%>
    </center>
</body>
</html>