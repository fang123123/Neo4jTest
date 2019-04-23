<%-- 服务器端注释，设置jsp使用脚本语言为java，服务器响应正文文件格式为text/html，正文编码为UTF-8，
     指定jsp源文件保存时的编码为UTF-8，用于jsp引擎解码，并转为Unicode编码(java类在内存中是以Unicode进行编码)--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
    <%=new Date() %>
</body>
</html>
