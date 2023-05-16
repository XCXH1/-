import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import Class.Process;
import Class.Public_Variable;
import Class.Process_Sort;

@WebServlet(name = "PFIFOServlet", value = "/PFIFOServlet")
public class PFIFOServlet extends HttpServlet {
    public static int current_time = 0; //计数系统当前时间
    public static String flag  = null; //标识位，控制jsp代码执行哪一段
    Public_Variable public_variable = new Public_Variable();
    Process[] temp = new Process[20];
    public static TreeMap<Integer, List<String>> treemap = new TreeMap<>();
    String message = "进程调度过程如下:\n";
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String add = request.getParameter("add");
        String execute = request.getParameter("execute");
        String initialize = request.getParameter("initialize");
        String systemdv = request.getParameter("systemdv");

        if(initialize != null){
            temp = new Process[20];
            public_variable.init();
            treemap = new TreeMap<>();
            message = "进程调度过程如下:\n";
        }
        if(add!=null && execute == null){
            // 获取输入的进程信息
            String name = request.getParameter("name");
            Integer arrivalTime = Integer.parseInt(request.getParameter("arrivalTime"));
            Integer burstTime = Integer.parseInt(request.getParameter("burstTime"));

            // 将进程加入数组
            temp[public_variable.getPnum()] = new Process(name, arrivalTime, burstTime, public_variable.getPnum()+1);
            public_variable.setPnum();  //进程计数更新
            // 用一个大小刚好的数组装进程
            Process[] process1 = new Process[public_variable.getPnum()];
            for(int i=0;i<public_variable.getPnum();i++){
                process1[i] = temp[i];
            }
            flag = "false";
            request.setAttribute("flag",flag);
            request.setAttribute("process1",process1);
        }

        // 用系统默认值
        if(systemdv != null){
            String name[] = new String[]{"a","b","c"};
            Integer arrivalTime[] = new Integer[]{2,3,6};
            Integer burstTime[] = new Integer[]{5,3,1};

            for(int i=0;i<3;i++){
                temp[i] = new Process(name[i],arrivalTime[i],burstTime[i],i+1);
                public_variable.setPnum();
            }

            // 用一个大小刚好的数组装进程
            Process[] process1 = new Process[public_variable.getPnum()];
            for(int i=0;i<public_variable.getPnum();i++){
                process1[i] = temp[i];
            }
            flag = "false";
            request.setAttribute("flag",flag);
            request.setAttribute("process1",process1);
        }

        if(add==null && execute!=null){  //计算结果
            // 用一个大小刚好的数组装进程
            Process[] process = new Process[public_variable.getPnum()];
            // 用于保持输出添加的进程信息
            Process[] process1 = new Process[public_variable.getPnum()];
            for(int i=0;i<public_variable.getPnum();i++){
                process[i] = temp[i];
                process1[i] = temp[i];
            }
            // 排序进程
            Process_Sort process_sort = new Process_Sort();
            process_sort.Sort1(process);
            for(int i=0;i<public_variable.getPnum();i++){   //加入就绪队列
                readyQueue.add(process[i]);
                // 将过程信息加入map容器
                pmadd(1,process[i].getArrivalTime(),process[i].getName());
            }
            // 处理数据
            scheduleProcesses(public_variable);
            // 计算平均周转时间
            public_variable.calculateAverage_turnaroundTime();
            // 计算平均带权周转时间
            public_variable.calculateAverage_weighted_turnaroundTime();
            // 发送数据
            flag = "true";
            // 处理过程信息
            for(Map.Entry<Integer,List<String>> entry : treemap.entrySet()){
                List<String> values = entry.getValue();
                for (String value : values){
                    message += value;
                }
            }
            message += "所有进程调度完毕。\n";

            request.setAttribute("message",message);
            request.setAttribute("flag",flag);
            request.setAttribute("process",process);
            request.setAttribute("process1",process1);
            request.setAttribute("public_variable",public_variable);
        }
        // 转发到jsp网页
        request.getRequestDispatcher("/PFIFO.jsp").forward(request,response);
    }

    // 就绪队列
    private static Queue<Process> readyQueue = new LinkedList<Process>();

    // 已完成队列
    private static Queue<Process> finishedQueue = new LinkedList<Process>();

    // 进程列表
    private static List<Process> processes = new ArrayList<Process>();

    // 进程调度方法
    private static void scheduleProcesses(Public_Variable public_variable) {
        while (!readyQueue.isEmpty()) {
            Process process = readyQueue.remove();
            String meg = "";    // 存储过程信息
            // 计算结束时间
            if(current_time >= process.getArrivalTime()){
                process.setFinishTime(current_time+process.getBurstTime());
                // 添加开始执行的过程信息
                pmadd(2,current_time,process.getName());
            }else{
                process.setFinishTime(process.getArrivalTime()+ process.getBurstTime());
                // 添加开始执行的过程信息
                pmadd(2,process.getArrivalTime(),process.getName());
            }
            // 添加服务结束过程信息
            pmadd(3,process.getFinishTime(),process.getName());

            // 更新当前时间
            current_time = process.getFinishTime();
            // 计算等待时间
            process.setWaitTime(process.getFinishTime()- process.getBurstTime()- process.getArrivalTime());
            // 计算周转时间
            process.setTurnaroundTime(process.getFinishTime()-process.getArrivalTime());
            // 计算带权周转时间
            process.setWeighted_turnaroundTime(process.getTurnaroundTime()/process.getBurstTime());
            // 记录总周转时间
            public_variable.updateAverage_turnaroundTime(process.getTurnaroundTime());
            // 记录总带权周转时间
            public_variable.updateAverage_weighted_turnaroundTime(process.getWeighted_turnaroundTime());
        }
    }

    // 过程信息添加函数
    private static void pmadd(int p, int time, String name){
        String msg = null;
        // 进程进入就绪队列
        if (p == 1){
            msg = "T="+time+": 进程"+name+"进入就绪队列。\n";
        }else if(p == 2){
            msg = "T="+time+": 进程"+name+"开始执行。\n";
        }else {
            msg = "T="+time+": 进程"+name+"完成所有服务。\n";
        }

        if (!treemap.containsKey(time)){
            treemap.put(time,new ArrayList<>());
        }
        treemap.get(time).add(msg);
    }
}