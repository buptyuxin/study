package com.ali.yanmo.study.parser;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DOMParser implements Parser {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void parse(InputStream inputStream) {
        parseDOM(inputStream);
    }
    
    public void parseDOM(InputStream inputStream) {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            
            Element rootElement = document.getRootElement();
            
            if (rootElement.element("bean") != null) {
                List<Element> elements = rootElement.elements("bean");
                for (Element element : elements) {
                    String beanId = element.attributeValue("id");
                    String beanClass = element.attributeValue("class");
                    System.out.println("id:" + beanId);
                    System.out.println("class:" + beanClass);
                    Element propElement = element.element("property");
                    System.out.println(propElement.getName() + ":" + propElement.getTextTrim());
//                    beanMap.put(beanId, beanClass);
                }
            }
        } catch (DocumentException e) {
            logger.info("XML file read error");
            e.printStackTrace();
        }
    }

}
