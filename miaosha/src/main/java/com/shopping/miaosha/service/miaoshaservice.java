package com.shopping.miaosha.service;

import com.shopping.miaosha.dao.miaoshauser;
import com.shopping.miaosha.dao.orderinfo;
import com.shopping.miaosha.daom.goodsdao;
import com.shopping.miaosha.vo.goodsvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class miaoshaservice {

    @Autowired
    goodsdao gd;

    @Autowired
    orderserservice orderserservice;

    @Autowired
    goodsservice goodsservice;

    public List<goodsvo> listgoodsvo(){

        return gd.listGoodsVo();
    }
@Transactional
    public goodsvo getgoodsvobygoodsid(long goodsId) {
        return gd.getgoodsvobygoodsid(goodsId);
    }

    public orderinfo miaosha(miaoshauser user, goodsvo goods) {
        //减库存 下订单 写入秒杀订单
        //减库存
        goodsservice.reduceStock(goods);
        //order_info maiosha_order
        //下订单 写入秒杀订单
        return orderserservice.createOrder(user, goods);
    }
}
