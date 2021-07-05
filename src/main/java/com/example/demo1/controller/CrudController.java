package com.example.demo1.controller;

import com.example.demo1.VO.ItemVo;
import com.example.demo1.model.ItemModel;
import com.example.demo1.service.CrudService;
import com.example.demo1.service.ValidateXml;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;



@Controller
public class CrudController {
    
    private static final Log logger = LogFactory.getLog(CrudController.class);
    @Autowired
    private CrudService crudService;

    @Autowired
    private ValidateXml validateXml;
    private ItemVo itemVo;
    @RequestMapping(value="/receivexml", method= RequestMethod.POST,
            consumes = "application/xml",produces ="application/xml")
    @ResponseBody
    public ItemVo receivexml(@RequestBody String str)
            throws  DocumentException, ParseException {
        //将接收到的xml请求转换为string存储
        logger.info("接受XML数据成功！");
        Document doc = (Document) DocumentHelper.parseText(str);

        //转换为document类型，用SAX进行解析，之后使用SAXValidator将xml与xsd进行校验
        validateXml.validateXMLByXSD(doc);
        //读取xml文件中各个元素信息
        Element root = doc.getRootElement();
        //获取根元素下的所有子元素（通过迭代器）
        Iterator<Element> it = root.elementIterator();
        Element oeprationElement = it.next();
        //获取操作类型
        String operation = oeprationElement.getText();

        //根据操作类型，执行对应的CRUD方法
        if(operation.equals("insert") || operation.equals("INSERT")){
            Element item  = it.next();
            String name = item.element("name").getText();
            Element date1 = item.element("date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date =  format.parse(date1.getText());
            Element price1 = item.element("price");
            BigDecimal price= new BigDecimal(price1.getText());
            crudService.save(name,date,price);
            itemVo = new ItemVo();
            itemVo = castList2Vo(crudService.findItemByName(name).get(0));
            itemVo.setMsg("新增数据成功！");

        }

        //如果判断为delete，执行删除方法
        if(operation.equals("delete") || operation.equals("DELETE")){
            Element item  = it.next();
            String name = item.element("name").getText();
            crudService.deleteByName(name);
            itemVo = new ItemVo();
            itemVo.setMsg("数据删除成功");

        }

        //如果判断为update，执行update
        if(operation.equals("update") || operation.equals("UPDATE")){
            Element item  = it.next();
            String name = item.element("name").getText();
            Element date1 = item.element("date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date =  format.parse(date1.getText());
            Element price1 = item.element("price");
            BigDecimal price= new BigDecimal(price1.getText());
            crudService.updateByName(name,date,price);
            itemVo = new ItemVo();
            itemVo = castList2Vo(crudService.findItemByName(name).get(0));
            itemVo.setMsg("更新成功！");

        }

        //如果判断为select，执行查询
        if(operation.equals("select") || operation.equals("SELECT")){
            Element item  = it.next();
            String name = item.element("name").getText();
            itemVo = new ItemVo();
            itemVo = castList2Vo(crudService.findItemByName(name).get(0));
            itemVo.setMsg("查询成功！");
        }
        return itemVo;
    }
    ItemVo castList2Vo(ItemModel itemModel){
        ItemVo itemVo = new ItemVo();
        itemVo.setName(itemModel.getName());
        itemVo.setDate(itemModel.getDate());
        itemVo.setPrice(itemModel.getPrice());
        return itemVo;
    }
    }



