package com.example.demo1.service;

import com.example.demo1.model.ItemModel;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CrudService {
    List<ItemModel> findItemByName(String name);
    List findAll();
    void updateByName(String name, Date date, BigDecimal price);
    void save(String name, Date date, BigDecimal price);
    void deleteByName(String name);

}
