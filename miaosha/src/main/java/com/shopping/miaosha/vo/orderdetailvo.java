package com.shopping.miaosha.vo;

import com.shopping.miaosha.dao.orderinfo;

public class orderdetailvo {
    private goodsvo goods;
    private orderinfo order;

    public goodsvo getGoods() {
        return goods;
    }

    public void setGoods(goodsvo goods) {
        this.goods = goods;
    }

    public orderinfo getOrder() {
        return order;
    }

    public void setOrder(orderinfo order) {
        this.order = order;
    }
}
