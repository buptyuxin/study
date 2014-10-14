package com.ali.yanmo.study.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import com.ali.yanmo.study.parser.DOMParser;
import com.ali.yanmo.study.parser.Parser;

public class CoreTest {
    
    static private DefaultListableBeanFactory beanFactory;
    
    static private Logger logger = LoggerFactory.getLogger(CoreTest.class);
    
    static private Map<String, String> beanMap = new HashMap<>();
    static private Parser parser = new DOMParser();
    
    private Long ll;
    
    private Integer ii;
    
    public Long getLl() {
        return ll;
    }

    public void setLl(Long ll) {
        this.ll = ll;
    }
    
    public Integer getIi() {
        return ii;
    }
    
    public void SetIi(Integer ii) {
        this.ii = ii;
    }

    public static void main(String[] args) {
        
        String filePath = "beans.xml";
        InputStream inputStream = CoreTest.class.getClassLoader().getResourceAsStream(filePath);
        parser.parseDOM(inputStream);
        
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
}
