<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>操作系统算法模拟</title>
    <link href="CSS\index.css" rel="stylesheet" type="text/css">
    <style>
        ul {
            display: none;
        }
    </style>
    <script>
        function toggleList(listId) {
            var list = document.getElementById(listId);
            if (list.style.display === "none") {
                list.style.display = "block";
            } else {
                list.style.display = "none";
            }
        }
    </script>
    <style>
        body {
            background-size:cover;
            background-image: url("/Images/bc.jpg");
        }
    </style>


</head>
<body>
<h1 style="text-align: center;">操作系统算法模拟</h1>

<div style="position:relative;left: 600px">
    <!-- 大条目1 -->
    <h2 onclick="toggleList('list1')" style="background-color: lightblue;width: 300px;text-align: center">进程调度算法模拟</h2>
    <ul id="list1"style="width: 300px; background-color: lightblue;text-align: center">
        <li><a href="http://localhost:8080/Curriculum_Design/PFIFO.jsp" target="_self">先来先服务算法</a></li>
        <li><a href="http://localhost:8080/Curriculum_Design/JSP/PCLOCK.jsp" target="_self">时间片轮转算法</a></li>
        <li><a href="http://localhost:8080/Curriculum_Design/JSP/PPRIORITY.jsp" target="_self">优先级调度算法</a></li>
        <li><a href="http://localhost:8080/Curriculum_Design/JSP/PMLFQ.jsp" target="_self">多级反馈队列算法</a></li>
    </ul>

    <!-- 大条目2 -->
    <h2 onclick="toggleList('list2')" style="background-color: turquoise;width: 300px;text-align: center">磁盘调度算法模拟</h2>
    <ul id="list2" style="width: 300px;background-color: turquoise;text-align: center">
        <li><a href="http://localhost:8080/Curriculum_Design/tFCFS.jsp" target="_self">先来先服务算法</a></li>
        <li><a href="http://localhost:8080/Curriculum_Design/SSTF.jsp" target="_self">最短寻道时间有限算法</a></li>
        <li><a href="http://localhost:8080/Curriculum_Design/SCAN.jsp" target="_self">扫描算法（SCAN）</a></li>
        <li><a href="http://localhost:8080/Curriculum_Design/CSCAN.jsp" target="_self">循环扫描算法</a></li>
    </ul>

    <!-- 大条目3 -->
    <h2 onclick="toggleList('list3')" style="background-color: aquamarine; width: 300px;text-align: center">请求分页置换算法模拟</h2>
    <ul id="list3" style="width: 300px; background-color: aquamarine;text-align: center">
        <li><a href="http://localhost:8080/Curriculum_Design/ReqPage/FIFO.jsp" target="_self">先进先出页面置换</a></li>
        <li><a href="http://localhost:8080/Curriculum_Design/ReqPage/LRU.jsp" target="_self">最近最久未使用置换</a></li>
        <li><a href="http://localhost:8080/Curriculum_Design/ReqPage/LFU.jsp" target="_self">最少使用置换</a></li>
        <li><a href="http://localhost:8080/Curriculum_Design/ReqPage/CLOCK.jsp" target="_self">轮转置换算法（CLOCK)</a></li>
    </ul>
</div>

</body>
</html>
