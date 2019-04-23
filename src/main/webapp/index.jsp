<%-- 服务器端注释，设置jsp使用脚本语言为java，服务器响应正文文件格式为text/html，正文编码为UTF-8，
     指定jsp源文件保存时的编码为UTF-8，用于jsp引擎解码，并转为Unicode编码(java类在内存中是以Unicode进行编码)--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body{
    background-image:url(index-bg.jpg);
    background-repeat:no-repeat;
    background-size:cover;
}
input{
    border: 1px solid #ccc;
    padding: 7px 0px;
    border-radius: 3px; /*css3属性IE不支持*/
    padding-left:5px;
}
</style>
</head>
<body>
	<div id="header" color = "#000000">
		<center>
			<h1 style="color:#696969">科技专家库</h1>
		</center>
	</div>
	<div>
		<center>
		<form action="research" method="post">
			<table>
			<tr>
				<td><input type="text" name="expert_name" placeholder="专家姓名"></td>
				<td><input type="text" name="publication_title" placeholder="论文名"></td>
				<td><input type="text" name="keyword" placeholder="关键词"></td>
				<td><input type="submit" value="开始搜索"</td>
			</tr>
			</table>
		</form>
		<s:property value="mess"/>
		</center>
	</div>
	<%
	    int count = 0;
	    //判断是否是该网站的第一个访客
	    if(application.getAttribute("count")==null){
	        count++;
	        application.setAttribute("count",count);
	    }else{
	        count = (Integer)application.getAttribute("count");
	        count++;
	        application.setAttribute("count",count);
	        out.println("欢迎光临，您是第"+count+"为访客！");
	    }
	%>
</body>
</html>
