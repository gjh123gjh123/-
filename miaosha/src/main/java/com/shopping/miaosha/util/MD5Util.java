package com.shopping.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    //对明文字段
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    private static final String salt="1a2b3c4d";//固定，与密码拼装
    public static String inputPassFormPass(String inputPass){
        String str="" + salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }
    public static String formPassToDBPass(String formPass,String salt){
       String str="" + salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
       System.out.println(md5(str)+"jjjjjjjj");
       return md5(str);
    }
    public static String inputPassToDbPass(String input,String saltDB){
        String formPass=inputPassFormPass(input);
        String dbPass=formPassToDBPass(formPass,saltDB);
        return dbPass;
    }
    public static void main(String []args){
        System.out.println("gjhgjhgjhgggggg"+inputPassFormPass("123456"));
//        System.out.println("gjhgjhgjhgggggg"+formPassToDBPass(inputPassFormPass("123456"),"1a2b3c4d"));
        //    System.out.println(inputPassToDbPass("123456","1a2b3c4d"));
    }
}
