package com.baizhi.springboot_jsp_shiro.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component//不要忘记注解
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    //根据bean的名字获取工厂中指定的bean对象
    public static Object getBean(String beanName){
       Object bean=context.getBean(beanName);
        System.out.println("获取的bean对象为"+bean);
       return bean;
        //return context.getBean(beanName);
    }
}
