package com.example.demo1.service.serviceimpl;
import com.example.demo1.dao.ItemDao;

import com.example.demo1.model.ItemModel;
import com.example.demo1.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CrudServiceImpl implements CrudService {
    @Autowired
    private ItemDao itemDao;

    @Override
    public List<ItemModel>  findItemByName(String name) {
        return itemDao.selectItemByName(name);
    }
    @Override
    public List findAll() {
        return  itemDao.findAll();
    }

    @Override
    public void updateByName(String name, Date date, BigDecimal price) {
        itemDao.updateItem(name,date,price);
    }

    @Override
    public void save(String name, Date date, BigDecimal price) {
        itemDao.insertItem(name,date,price);
    }

    @Override
    public void deleteByName(String name) {
        itemDao.deleteItemByName(name);
    }


}
