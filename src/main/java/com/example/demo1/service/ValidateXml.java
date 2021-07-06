package com.example.demo1.service;

import com.example.demo1.VO.ItemVo;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.springframework.stereotype.Service;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public interface ValidateXml {
    public ItemVo validateXMLByXSD(Document document);
}
