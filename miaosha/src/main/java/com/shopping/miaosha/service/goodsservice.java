package com.shopping.miaosha.service;

import com.shopping.miaosha.dao.goods;
import com.shopping.miaosha.vo.goodsvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping.miaosha.dao.miaosha_goods;


import com.shopping.miaosha.daom.goodsdao;

import java.util.List;

@Service
public class goodsservice {

    @Autowired
    goodsdao gd;

        public void reduceStock(goodsvo goods) {
            miaosha_goods g=new miaosha_goods();
            g.setGoodsId(goods.getId());
            gd.reduceStock(g);
        }




    public List<goodsvo> listgoodsvo(){

        return gd.listGoodsVo();
    }

    public goodsvo getgoodsvobygoodsid(long goodsId) {
        return gd.getgoodsvobygoodsid(goodsId);
    }
}
