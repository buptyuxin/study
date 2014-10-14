package com.ali.yanmo.study.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class CoreTest {
    static private DefaultListableBeanFactory beanFactory;
    
    static private Logger logger = LoggerFactory.getLogger(CoreTest.class);
    
    static private Map<String, String> beanMap = new HashMap<>();
    
    private Long ll;
    
    public Long getLl() {
        return ll;
    }


    public void setLl(Long ll) {
        this.ll = ll;
    }

    public static void main(String[] args) {
        
        String filePath = "/";
        parseDOM(filePath);
        
        beanFactory = new DefaultListableBeanFactory();
        GenericBeanDefinition bd = new GenericBeanDefinition();
        
        Map<String, String> original = new HashMap<String, String>();
        original.put("ll", "15");
        
        bd.setBeanClass(CoreTest.class);
        bd.setPropertyValues(new MutablePropertyValues(original));
        
        beanFactory.registerBeanDefinition("coreTest", bd);
        
        CoreTest coreTest = (CoreTest)beanFactory.getBean(CoreTest.class);
        
        System.out.println(coreTest.ll);
    }
    
    public static void parseDOM(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(new File("classpath:"+filePath));
            SAXReader saxReader = new SAXReader();
            //似乎只能传入绝对路径？？？换成inputStream了
//            Document document = saxReader.read(new File("classpath:"+filePath));
            Document document = saxReader.read(inputStream);
            
            Element rootElement = document.getRootElement();
            
            String pattern = "bean";
            if (rootElement.element(pattern) != null) {
                List<Element> elements = rootElement.elements(pattern);
                for (Element element : elements) {
                    Element idElement = element.element("id");
                    System.out.println(idElement.getName() + ":" + idElement.getTextTrim());
                    Element classElement = element.element("class");
                    System.out.println(classElement.getName() + ":" + classElement.getTextTrim());
                    beanMap.put(idElement.getTextTrim(), classElement.getTextTrim());
                }
            }
            
        } catch (FileNotFoundException e) {
            logger.info("XML file not found");
        } catch (DocumentException e) {
            logger.info("XML file read error");
            e.printStackTrace();
        }
    }
}
