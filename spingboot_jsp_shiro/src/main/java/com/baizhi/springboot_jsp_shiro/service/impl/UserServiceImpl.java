package com.baizhi.springboot_jsp_shiro.service.impl;

import com.baizhi.springboot_jsp_shiro.dao.UserDao;
import com.baizhi.springboot_jsp_shiro.entity.Perms;
import com.baizhi.springboot_jsp_shiro.entity.Role;
import com.baizhi.springboot_jsp_shiro.entity.User;
import com.baizhi.springboot_jsp_shiro.service.UserService;
import com.baizhi.springboot_jsp_shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("usrservice")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void register(User user){
        //处理业务调用dao
        //对明文密码进行MD5+salt+hush散列
        //1.生成随机盐
        String salt= SaltUtils.getsalt(8);
        //2.
        // 将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行MD5+salt+hush散列
        Md5Hash md5Hash=new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUserName(String username) {

        return userDao.findByUserName(username);
    }
    @Override
    public User findRolesByUserName(String username){
        return userDao.findRolesByUserName(username);
    }
    @Override
    public List<Perms> findPermsByRoleId(String id){
        return userDao.findPermsByRoleId(id);
    }
}
