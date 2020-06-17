package com.shopping.miaosha.redis;

import org.springframework.stereotype.Service;


public class MiaoshaUserKey extends BasePrefix {
    public static final int Token_EXPIRE=3600*24*2;
    private MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    public static MiaoshaUserKey token =new MiaoshaUserKey(Token_EXPIRE,"tk");
//    public static MiaoshaUserKey mobile=new MiaoshaUserKey(MOBILE_EXPIRE,"mobile");
    public static MiaoshaUserKey getbyid =new MiaoshaUserKey(0,"id");




}
