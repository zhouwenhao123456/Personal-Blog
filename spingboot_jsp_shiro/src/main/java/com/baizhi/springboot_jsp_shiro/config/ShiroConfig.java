package com.baizhi.springboot_jsp_shiro.config;
import com.baizhi.springboot_jsp_shiro.cache.RedisCacheManager;
import com.baizhi.springboot_jsp_shiro.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/*
 *用来整合shiro框架相关的配置类
 *
*/
@Configuration
public class ShiroConfig {
    //创建shiroFilter,负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
       //配置系统的受限资源
        Map<String,String> map=new HashMap<String, String>();
        //map.put("/index.jsp","authc");//authc 请求这个资源需要认证和授权
        map.put("/**","authc");//   "/**"代表全部资源

        //配置系统的公共资源
        //Map<String,String> map=new HashMap<String, String>();
        map.put("/user/login","anon");//为了避免出现死循环，需要把login设置为公共资源，指定url可以匿名访问
        map.put("/user/register","anon");
        map.put("/regist.jsp","anon");
        //map.put("/css","anon");
        //map.put("/img","anon");
        //map.put("/js","anon");
        //map.put("/bootstrap-3.35-dist","anon");
        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;

    }
    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置realm
        defaultWebSecurityManager.setRealm(getRealm());
        return defaultWebSecurityManager;
    }

    //创建自定义的realm
    @Bean
    public Realm getRealm() {
        CustomerRealm customerRealm=new CustomerRealm();
        //修改凭证校验匹配器,即修改校验规则，（按照系统默认的随机盐和密码(明文)的组合方式组合并散列后的结果和数据库中的密文(password)进行比较，一般默认salt在明文前）
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        //设置加密算法为MD5
        credentialsMatcher.setHashAlgorithmName("Md5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);

        //开启缓存管理
        //customerRealm.setCacheManager(new EhCacheManager());

        //开启缓存管理
        customerRealm.setCacheManager(new RedisCacheManager());
        //开启全局缓存
        customerRealm.setCachingEnabled(true);

        //开启认证缓存
        customerRealm.setAuthenticationCachingEnabled(true);

        //给认证缓存起别名
        customerRealm.setAuthenticationCacheName("authenticationCache");

        //开启授权缓存
        customerRealm.setAuthorizationCachingEnabled(true);

        //给授权缓存起别名
        customerRealm.setAuthorizationCacheName("authorizationCache");

        return customerRealm;
    }

/*  //创建安全管理器

    在ShiroConfig中，创建安全管理器，使用@Bean的时候，参数(Realm realm) 报红，
    提示：Could not autowire. There is more than one bean of 'Realm' type.
    但是我启动能够正常启动没有任何问题。。
    原因：因为在依赖中有同名的bean,
         不要在参数列表声明，直接用方法获取就可以。
         defaultWebSecurityManager.setRealm(getRealm());
         或者给getrealm方法加@primary注解标识
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置realm
        defaultWebSecurityManager.setRealm(getRealm());
        return defaultWebSecurityManager;
    }

    //创建自定义的realm
    @Bean
    @Primary
    public Realm getRealm() {
        CustomerRealm customerRealm=new CustomerRealm();
        //修改凭证校验匹配器,即修改校验规则，（按照系统默认的随机盐和密码(明文)的组合方式组合并散列后的结果和数据库中的密文(password)进行比较，一般默认salt在明文前）
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        //设置加密算法为MD5
        credentialsMatcher.setHashAlgorithmName("Md5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }
* */
}