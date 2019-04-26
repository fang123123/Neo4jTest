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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-" crossorigin="anonymous">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
    <link rel="stylesheet" href="resource\pure-layout-marketing\css\layouts\content.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script>
        function showinfo(values){
            if(values=="1"){
                $("#fuzzy-query").css("display","block");
                $("#exact-query").css("display","none");

            }else{
                $("#exact-query").css("display","block");
                $("#fuzzy-query").css("display","none");
            }
        }
    </script>
</head>
<body>
    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
        <a class="pure-menu-heading" href="">科技知识图谱</a>
        <ul class="pure-menu-list">
            <li class="pure-menu-item pure-menu-selected"><a href="#" class="pure-menu-link">Home</a></li>
            <li class="pure-menu-item"><a href="#" class="pure-menu-link">Tour</a></li>
            <li class="pure-menu-item"><a href="#" class="pure-menu-link">Sign Up</a></li>
        </ul>
    </div>
    <div class="splash-container">
        <jsp:include page="time.jsp" flush="true"></jsp:include>
        <div class="splash">
           <form action="research" method="post" class="pure-form">
                <div class="splash-search-select">
                    <select id="search-type" onchange="showinfo(this[selectedIndex].value)">
                      <option value="1">模糊查询</option>
                      <option value="2">精确查询</option>
                    </select>
                </div>
                <div id="fuzzy-query" class="splash-search-text">
                   <input type="text"  name="expert_name" placeholder="如：Deng Cai的论文" class="splash-search-text-input">
                </div>
                <div id="exact-query" class="splash-search-text" style="display: none">
                    <input type="text" name="expert_name" placeholder="专家姓名">
                    <input type="text" name="publication_title" placeholder="论文名">
                    <input type="text" name="keyword" placeholder="关键词">
                </div>
                <div class="splash-search-button">
                    <button type="submit" class="pure-button pure-button-primary" >Search</button>
                </div>
            </form>
            <s:property value="mess"/>
        </div>
    </div>
    <div class="content-wrapper">

        <div class="content-list">
            <div class="content-item">
                <div class="claimfooter">
                    <div ng-click="getClaimedDetail(expert)" class="avatar">
                        <img ng-show="expert.avatarUrl" ng-src="{{expert.avatarUrl||'/autoload/images/avatar.png'}}" alt="专家库-专家">
                        <div ng-show="!expert.avatarUrl" class="surname">{{expert.name.substr(0, 1)}}</div>
                        <figure class="circle"></figure>
                    </div>
                    <div class="claimfooterright">
                        <a ng-click="getClaimedDetail(expert)" class="name">{{expert.name}}</a>
                        <!--<input type="checkbox" ng-click="select($index, expert)"-->
                        <!--ng-model="expert.selected" class="ui input select">-->
                        <input ng-if="expert.candidateflag != 0" type="checkbox" ng-click="select($index, expert)" ng-model="expert.selected" class="selectsty ui input select">
                    </div>
                </div>
            <%
                //获取result结果
                StatementResult result = (StatementResult)session.getAttribute("result");
                if(result.hasNext())
                {
                    while(result.hasNext()){
                    Record record = result.next();
            %>
                <div class="segment">
                    <div class="organization">专家id：
            <%
                        String expert_id = record.get("expert_id").asString();
                        out.print(expert_id);
            %>
                    </div>
                    <div class="major">论文id：
            <%
                        String publication_id = record.get("publication_id").asString();
                        out.print(publication_id);
            %>
                    </div>
                    <div class="chart">
                        <div class="ui blue small labels">
                        <a ng-repeat="keyword in expert.keyword.split(';')|limitTo:10 track by $index"
                            ng-if="keyword" ng-click="clickKeyword(keyword)"
                            class="ui basic label">{{keyword}}</a>
                            <div ng-if="!expert.keyword">无研究关键词</div>
                        </div>
                    </div>
                </div>
            <%
                    }
                }
            %>
            </div>
            <pager ng-if="experts!=null" page-count="claimedExpertList_countPage" current-page="claimedExpertList_curPage"
            on-page-change="onClaimedExpertListPageChange(claimedExpertList_curPage)" first-text="首页"
            next-text="下一页" prev-text="上一页" last-text="尾页" ng-if="claimedExpertList_countPage&gt;1"></pager>
        </div>
    </div>
</body>
</html>