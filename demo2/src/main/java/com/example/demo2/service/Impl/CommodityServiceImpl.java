package com.example.demo2.service.Impl;

import com.example.demo2.bean.Commodity;
import com.example.demo2.mapper.CommodityMapper;
import com.example.demo2.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Map<String, Object> commodityList() {
        List<Commodity> list = commodityMapper.commodityList();
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    @Override
    public int add(Commodity commodity) {
        commodityMapper.add(commodity);
        return 0;
    }

    @Override
    public void del(int id) {
        commodityMapper.del(id);
    }

    @Override
    public Commodity getCommodityById(int id) {
        return commodityMapper.getCommodityById(id);
    }

    @Override
    public void updCommodity(Commodity commodity) {
        commodityMapper.updCommodity(commodity);
    }
}
