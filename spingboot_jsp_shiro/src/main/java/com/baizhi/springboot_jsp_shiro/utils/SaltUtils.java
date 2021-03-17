package com.baizhi.springboot_jsp_shiro.utils;

import java.util.Random;

public class SaltUtils {
    //生成salt的静态方法
    //@param n
    //@return

    public static String getsalt(int n){
        char[] chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
     StringBuffer sb=new StringBuffer();
      for(int i=0;i<n;i++){
          char aChar = chars[new Random().nextInt(chars.length)];
        sb.append(aChar);
     }
     return sb.toString();
    }

    public static void main(String[] args) {
        String salt=getsalt(8);
        System.out.println(salt);
    }
}

