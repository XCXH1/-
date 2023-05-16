package com.example.demo2.mapper;

import com.example.demo2.bean.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommodityMapper {
    List<Commodity> commodityList();

    void add(Commodity commodity);

    void del(int id);

    Commodity getCommodityById(int id);

    void updCommodity(Commodity commodity);
}
