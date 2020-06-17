package com.shopping.miaosha.comtroller;

import com.shopping.miaosha.dao.miaoshauser;
import com.shopping.miaosha.redis.RedisService;
import com.shopping.miaosha.result.CodeMsg;
import com.shopping.miaosha.result.Result;
import com.shopping.miaosha.service.goodsservice;
import com.shopping.miaosha.service.miaoshauserservice;
import com.shopping.miaosha.vo.goodsvo;
import com.shopping.miaosha.service.orderserservice;
import com.shopping.miaosha.dao.miaoshaouder;
import com.shopping.miaosha.service.miaoshaservice;
import com.shopping.miaosha.dao.orderinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/miaosha")
public class miaoshacontroller{
    private static Logger log = LoggerFactory.getLogger(Controller.class);
    @Autowired
    miaoshauserservice userservice;

    @Autowired
    RedisService redisService;

    @Autowired
    com.shopping.miaosha.service.goodsservice goodsservice;
    @Autowired
    orderserservice orderserservice;
    @Autowired
    miaoshaservice miaoshaservice;


    @RequestMapping(value = "/do_miaosha",method = RequestMethod.POST)
    @ResponseBody
    public Result<orderinfo>miaosha(Model model, miaoshauser user, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
       if(user==null){
           return Result.error(CodeMsg.SERVER_ERROR);
       }
       //判断库存
       goodsvo goods=goodsservice.getgoodsvobygoodsid(goodsId);
       int stock=goods.getStockCount();
       if(stock<=0){
          return Result.error(CodeMsg.MIAO_SHA_OVER);
       }
        //判断是否已经秒杀到了
         miaoshaouder order = orderserservice.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
          return  Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //减库存 下订单 写入秒杀订单
        orderinfo orderInfo = miaoshaservice.miaosha(user, goods);
        return Result.success(orderInfo);
    }
    }
