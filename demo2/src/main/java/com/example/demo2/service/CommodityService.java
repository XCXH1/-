package com.example.demo2.service;

import com.example.demo2.bean.Commodity;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface CommodityService {
    Map<String, Object> commodityList();

    int add(Commodity commodity);

    void del(int id);

    Commodity getCommodityById(int id);

    void updCommodity(Commodity commodity);
}
