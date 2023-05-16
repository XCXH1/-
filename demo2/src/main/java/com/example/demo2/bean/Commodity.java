package com.example.demo2.bean;

import lombok.Data;

@Data
public class Commodity {
    private int id;
    private String commodity_name;
    private double commodity_price;
    private String commodity_img;
    private String commodity_status;
    private String commodity_num;
}
