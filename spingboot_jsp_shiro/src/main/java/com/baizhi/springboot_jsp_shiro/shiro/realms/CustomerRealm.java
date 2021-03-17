package com.baizhi.springboot_jsp_shiro.shiro.realms;

import com.baizhi.springboot_jsp_shiro.entity.Perms;
import com.baizhi.springboot_jsp_shiro.entity.Role;
import com.baizhi.springboot_jsp_shiro.entity.User;
import com.baizhi.springboot_jsp_shiro.service.UserService;
import com.baizhi.springboot_jsp_shiro.utils.ApplicationContextUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component//不要忘记注解
//自定义realm
public class CustomerRealm extends AuthorizingRealm{
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        String primaryPrincipal=(String) principals.getPrimaryPrincipal();
        System.out.println("调用授权认证："+primaryPrincipal);
        //根据主身份信息获取角色和权限信息
        UserService userService=(UserService) ApplicationContextUtils.getBean("usrservice");
       User user=userService.findRolesByUserName(primaryPrincipal);
        if(!CollectionUtils.isEmpty(user.getRoles())){
          SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
          user.getRoles().forEach(role ->{simpleAuthorizationInfo.addRole(role.getName());
          //权限信息
              List<Perms> perms=userService.findPermsByRoleId(role.getId());
              if(!CollectionUtils.isEmpty(perms)){
                  perms.forEach(perms1->{simpleAuthorizationInfo.addStringPermission(perms1.getName());});
              }
          });
          return simpleAuthorizationInfo;
        }
        return null;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("===============================");
        String principal= (String) token.getPrincipal();
        //SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo();

        //在工厂中获取service对象

        //问题：传入UserService获取不到service对象
        UserService userService=(UserService) ApplicationContextUtils.getBean("usrservice");//见UserServiceImpl的别名
        System.out.println(userService);
        //这里可以从数据库查出user对象
        User user=userService.findByUserName(principal);
        //取出user对象的值返回验证信息(用户名，密文，随机盐，realm名)
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        }
        return null;
    }
}
