<%@ page import="Class.Public_Variable" contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Bool" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>时间片转转算法</title>
    <link href="http://localhost:8080/Curriculum_Design/CSS/PCLOCK.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="father1">
        <div class="indexbtn">
            <form action="http://localhost:8080/Curriculum_Design/index.jsp">
                <button class="btn">首  页</button>
            </form>
        </div>

        <div class="title">
            <h1>时间片轮转算法</h1>
        </div>
    </div>

    <div class="container" style="display: flex">
        <div class="input" style="position: relative;width: 600px;height: 300px">
            <form action="/Curriculum_Design/PCLOCKServlet" method="POST">
                <label>进 程 名 :</label>
                <input type="text" name="name"><br><br>
                <label>到达时间:</label>
                <input type="text" name="arrivalTime"><br><br>
                <label>运行时间:</label>
                <input type="text" name="burstTime"><br><br>
                <label>时 间 片:</label>
                <input type="text" name="timeSlices"><br><br>
                <%--提交表单到服务器--%>
                <input type="submit" οnclick="javascript:window.location.reload();" value="初 始 化" name="initialize">
                <input type="submit" value="默   认" name="systemdv">
                <input type="submit" value="添   加" name="add">
                <input type="submit" value="运   行" name="execute">
            </form>
            <br><br>
        </div>

        <div class="out" style="position: relative;width: 600px;height: 295px;right: -175px">
            <textarea style="width: 100%;height: 100%;font-size: 20px">${message}</textarea>
        </div>
    </div>



    <div>
        <div style="width: 40%; float: left;" >
            <table border="1">
                <tr>
                    <th style="text-align: center">进程名</th>
                    <th style="text-align: center">到达时间</th>
                    <th style="text-align: center">运行时间</th>
                </tr>

                <c:forEach var="process1" items="${process1}">
                    <tr>
                        <td style="text-align: center">${process1.name}</td>
                        <td style="text-align: center">${process1.arrivalTime}</td>
                        <td style="text-align: center">${process1.burstTime}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div style="width: 60%;float: right;">
            <table border="2">
                <tr>
                    <th style="text-align: center">进程名</th>
                    <th style="text-align: center">到达时间</th>
                    <th style="text-align: center">运行时间</th>
                    <th style="text-align: center">等待时间</th>
                    <th style="text-align: center">结束时间</th>
                    <th style="text-align: center">周转时间</th>
                    <th style="text-align: center">带权周转时间</th>
                </tr>
                <%
                    String b = (String) request.getAttribute("flag");
                    if(b != null && b.equals("true")){
                %>
                <c:forEach var="process" items="${process}">
                    <tr>
                        <td style="text-align: center">${process.name}</td>
                        <td style="text-align: center">${process.arrivalTime}</td>
                        <td style="text-align: center">${process.burstTime}</td>
                        <td style="text-align: center">${process.waitTime}</td>
                        <td style="text-align: center">${process.finishTime}</td>
                        <td style="text-align: center">${process.turnaroundTime}</td>
                        <td style="text-align: center">${process.weighted_turnaroundTime}</td>
                    </tr>
                </c:forEach>
                <%
                    }
                %>
            </table>
            <table>
                <%--输出平均周转时间和平均带权周转时间--%>
                <%
                    Public_Variable public_variable = (Public_Variable) request.getAttribute("public_variable");
                    if(public_variable != null){
                %>
                <tr>
                    <td style="text-align: center">平均周转时间: <%= public_variable.getAverage_turnaroundTime() %></td>
                    <td style="text-align: center">平均带权周转时间: <%= public_variable.getAverage_weighted_turnaroundTime() %></td>
                </tr>
                <%--<p>平均周转时间: <%= public_variable.getAverage_turnaroundTime() %></p>--%>
                <% }else { %>
                <tr>
                    <td style="text-align: center">平均周转时间:</td>
                    <td style="text-align: center">平均带权周转时间:</td>
                </tr>
                <%--<p>平均周转时间:</p>--%>
                <%
                    }
                %>
            </table>
        </div>
    </div>
</body>
</html>
