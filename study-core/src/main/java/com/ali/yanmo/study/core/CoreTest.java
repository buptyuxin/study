package com.ali.yanmo.study.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
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
    
    private Long ll;
    
    public Long getLl() {
        return ll;
    }


    public void setLl(Long ll) {
        this.ll = ll;
    }

    public static void main(String[] args) {
        
        
        
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
            //�ƺ�ֻ�ܴ������·��������
//            Document document = saxReader.read(new File("classpath:"+filePath));
            Document document = saxReader.read(inputStream);
            
            Element rootElement = document.getRootElement();
            Element element = rootElement.element("bean");
            
        } catch (FileNotFoundException e) {
            logger.info("XML file not found");
        } catch (DocumentException e) {
            logger.info("XML file read error");
            e.printStackTrace();
        }
    }
}
