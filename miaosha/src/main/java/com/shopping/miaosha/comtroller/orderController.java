package com.shopping.miaosha.comtroller;

import com.shopping.miaosha.dao.miaoshauser;
import com.shopping.miaosha.redis.RedisService;
import com.shopping.miaosha.result.CodeMsg;
import com.shopping.miaosha.result.Result;
import com.shopping.miaosha.service.goodsservice;
import com.shopping.miaosha.service.miaoshauserservice;
import com.shopping.miaosha.vo.orderdetailvo;
import com.shopping.miaosha.service.orderserservice;
import com.shopping.miaosha.dao.orderinfo;
import com.shopping.miaosha.vo.goodsvo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class orderController {
    private static Logger log= LoggerFactory.getLogger(Controller.class);
    @Autowired
    miaoshauserservice userservice;

    @Autowired
    RedisService redisService;

    @Autowired
    goodsservice goodsservice;

    @Autowired
    orderserservice orderserservice;


    @RequestMapping("/detail")
    @ResponseBody
    public Result<orderdetailvo> info(Model Model, miaoshauser user, @RequestParam("orderId")long orderId){

        if(user==null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
    orderinfo order=orderserservice.getorderbyid(orderId);
        if(order==null){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId=order.getGoodsId();
        goodsvo goods=goodsservice.getgoodsvobygoodsid(goodsId);
        orderdetailvo vo=new orderdetailvo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);

    }
}
