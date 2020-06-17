package com.shopping.miaosha.vo;

import com.shopping.miaosha.dao.miaoshauser;

public class goodsdetailvo {
    private int miaoshaStatus=0;
    private int remiaoshaStatus=0;
    private goodsvo goods;
    private miaoshauser user;

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemiaoshaStatus() {
        return remiaoshaStatus;
    }

    public void setRemiaoshaStatus(int remiaoshaStatus) {
        this.remiaoshaStatus = remiaoshaStatus;
    }

    public goodsvo getGoods() {
        return goods;
    }

    public void setGoods(goodsvo goods) {
        this.goods = goods;
    }

    public miaoshauser getUser() {
        return user;
    }

    public void setUser(miaoshauser user) {
        this.user = user;
    }
}
