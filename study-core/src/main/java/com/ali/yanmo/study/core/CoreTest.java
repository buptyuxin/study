package com.ali.yanmo.study.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
        
        Parser parser = new DOMParser();
        String filePath = "beans.xml";
        InputStream inputStream = CoreTest.class.getClassLoader().getResourceAsStream(filePath);
        parser.parse(inputStream);
        
        //test HttpClient
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            System.out.println(EntityUtils.toString(httpResponse.getEntity()));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
