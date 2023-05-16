package com.example.demo2.controller;

import com.example.demo2.bean.Commodity;
import com.example.demo2.service.CommodityService;
import com.example.demo2.service.Impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @RequestMapping("commodityList")
    @ResponseBody   // 将返回数据分装为json数据格式
    public Map<String,Object> commodityList(){
        return commodityService.commodityList();
    }

    @RequestMapping("/commodity_toadd")
    public String toadd(Commodity commodity){
        return "add";
    }

    @RequestMapping("/add")
    public String add(Commodity commodity){
        int i = commodityService.add(commodity);
        return "redirect:/user/index";
    }

    @RequestMapping("/del")
    public String del(int id){
        commodityService.del(id);
        return "redirect:/user/index";
    }

    @RequestMapping("/upd")
    @ResponseBody
    public ModelAndView upd(int id){
        // 初始化一个模型视图对象，视图名称设置为upd(对应html)，参数为id，
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upd");
        modelAndView.addObject("id",id);
        //DispatcherServlet 将这个 ModelAndView 对象传递给视图解析器
        //视图解析器会根据视图名称查找对应的视图（html），并将模型数据传递给视图进行渲染。
        //最终，DispatcherServlet 将渲染后的视图作为响应返回给客户端
        return modelAndView;
    }

    @RequestMapping("/getCommodityById")
    @ResponseBody   // 将返回数据分装为json数据格式
    public Map<String,Object> getCommodityById(int id){
        HashMap<String,Object> map = new HashMap<>();
        Commodity commodity = commodityService.getCommodityById(id);
        map.put("data",commodity);
        return map;
    }

    @RequestMapping("/updCommodity")
    public String updCommodity(Commodity commodity){
        commodityService.updCommodity(commodity);
        return "redirect:/user/index";
    }
}
