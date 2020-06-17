package com.shopping.miaosha.service;

import com.alibaba.druid.util.StringUtils;
import com.shopping.miaosha.dao.miaoshaouder;
import com.shopping.miaosha.dao.miaoshauser;
import com.shopping.miaosha.dao.orderinfo;
import com.shopping.miaosha.daom.miaoshauserdao;
import com.shopping.miaosha.exception.GlobalException;
import com.shopping.miaosha.redis.MiaoshaUserKey;
import com.shopping.miaosha.redis.RedisService;

import com.shopping.miaosha.result.CodeMsg;
import com.shopping.miaosha.util.MD5Util;
import com.shopping.miaosha.util.UUIDUtil;
import com.shopping.miaosha.vo.goodsvo;
import com.shopping.miaosha.vo.loginvo;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import com.shopping.miaosha.dao.miaoshaouder;
import com.shopping.miaosha.daom.orderdao;
import com.shopping.miaosha.redis.OrderKey;
import com.shopping.miaosha.redis.RedisService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class orderserservice {

    @Autowired
    orderdao orderdao;


    @Autowired
    RedisService redisservice;

    public miaoshaouder getMiaoshaOrderByUserIdGoodsId(Long userId, long goodsId) {
//        return orderdao.getMiaoshaOrderByUserIdGoodsId(userId,goodsId);
    return  redisservice.get(OrderKey.getmiaoshaoorderbyuidgid,""+userId+"_"+goodsId,miaoshaouder.class);

    }

    @Transactional
    public orderinfo createOrder(miaoshauser user, goodsvo goods) {
        orderinfo orderInfo = new orderinfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderdao.insert(orderInfo);
        miaoshaouder miaoshaOrder = new miaoshaouder();
        miaoshaOrder.setGoodsid(goods.getId());
        miaoshaOrder.setOrderid(orderId);
        miaoshaOrder.setUserId(user.getId());
        orderdao.insertMiaoshaOrder(miaoshaOrder);
        redisservice.set(OrderKey.getmiaoshaoorderbyuidgid,""+user.getId()+"_"+goods.getId(),miaoshaOrder);
        return orderInfo;
    }

    public orderinfo getorderbyid(long orderId) {
        return orderdao.getorderbyid(orderId);
    }
}
