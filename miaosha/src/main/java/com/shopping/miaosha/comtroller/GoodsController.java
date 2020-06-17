package com.shopping.miaosha.comtroller;

import com.alibaba.druid.util.StringUtils;
import com.shopping.miaosha.dao.goods;
import com.shopping.miaosha.dao.miaoshauser;
import com.shopping.miaosha.redis.GoodsKey;
import com.shopping.miaosha.redis.RedisService;
import com.shopping.miaosha.result.Result;
import com.shopping.miaosha.service.miaoshauserservice;
import com.shopping.miaosha.vo.goodsdetailvo;
import com.shopping.miaosha.service.userservice;
import com.shopping.miaosha.vo.loginvo;
import org.apache.catalina.core.ApplicationContext;
import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.shopping.miaosha.service.goodsservice;
import com.shopping.miaosha.vo.goodsvo;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController{
    private static Logger log= LoggerFactory.getLogger(Controller.class);
    @Autowired
    miaoshauserservice userservice;

    @Autowired
    RedisService redisService;

    @Autowired
    goodsservice goodsservice;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;



    @RequestMapping(value = "/to_list",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response,Model model, miaoshauser user){
        model.addAttribute("user",user);
       //查询商品列表
        List<goodsvo> goodslist=goodsservice.listgoodsvo();
        model.addAttribute("goodsList",goodslist);
      // return "goods_list";
          //取缓存
          String html=redisService.get(GoodsKey.getGoodsList,"",String.class);
          if(!StringUtils.isEmpty(html)){
              return html;
          }
        IWebContext ctx =new WebContext(request,response,
                request.getServletContext(),request.getLocale(),model.asMap());
          //手动渲染

        html=thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
        //System.out.println(html);
        if(!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsList,"",html);
        }
        return html;
    }


//    @RequestMapping(value = "/to_detail/(goodsId)",produces = "text/html;charset=UTF-8")
//    @ResponseBody
//    public String detail(HttpServletRequest request, HttpServletResponse response,Model model, miaoshauser user,@PathVariable("goodsId")long goodsId){
//        model.addAttribute("user",user);
//
//        //取缓存
//        String html=redisService.get(GoodsKey.getGoodsDetail,""+goodsId,String.class);
//        if(!StringUtils.isEmpty(html)){
//            return html;
//        }
//        //手动渲染
//
//        goodsvo goods=goodsservice.getgoodsvobygoodsid(goodsId);
//       model.addAttribute("goods",goods);
//       long startAt=goods.getStartDate().getTime();
//       long endAt=goods.getEndDate().getTime();
//       long now=System.currentTimeMillis();
//       int miaoshaStatus=0;
//        int remiaoshaStatus=0;
//        if(now<startAt){
//            miaoshaStatus=0;
//            remiaoshaStatus=(int)((startAt-now)/1000);
//        }else if(now>endAt){
//            miaoshaStatus=2;
//            remiaoshaStatus=-1;
//        }else {
//            miaoshaStatus=1;
//            remiaoshaStatus=0;
//        }
//        model.addAttribute("miaoshaStatus", miaoshaStatus);
//        model.addAttribute("remiaoshaStatus", remiaoshaStatus);
//        IWebContext ctx =new WebContext(request,response,
//                request.getServletContext(),request.getLocale(),model.asMap());
//        //手动渲染
//
//        html=thymeleafViewResolver.getTemplateEngine().process("goods_detail",ctx);
//        if(!StringUtils.isEmpty(html)){
//            redisService.set(GoodsKey.getGoodsDetail,""+goodsId,html);
//        }
//        return html;
//    }



//    @RequestMapping("/to_detail/{goodsId}")
//    public String detail(Model model, miaoshauser user, @PathVariable("goodsId")long goodsId){
//
//        goodsvo goods=goodsservice.getgoodsvobygoodsid(goodsId);
//        model.addAttribute("goods",goods);
//
//        long startAt=goods.getStartDate().getTime();
//        long endAt=goods.getEndDate().getTime();
//        long now=System.currentTimeMillis();
//
//        int miaoshaStatus=0;
//        int remiaoshaStatus=0;
//        if(now<startAt){
//            miaoshaStatus=0;
//            remiaoshaStatus=(int)((startAt-now)/1000);
//        }else if(now>endAt){
//            miaoshaStatus=2;
//            remiaoshaStatus=-1;
//        }else {
//            miaoshaStatus=1;
//            remiaoshaStatus=0;
//        }
//        model.addAttribute("miaoshaStatus", miaoshaStatus);
//        model.addAttribute("remiaoshaStatus", remiaoshaStatus);
//        return "goods_detail";
//    }

//    @RequestMapping(value="/detail2/{goodsId}")
//    @ResponseBody
//    public Result<goodsdetailvo> detail2(HttpServletRequest request, HttpServletResponse response, Model model, miaoshauser user,
//                                        @PathVariable("goodsId")long goodsId) {
//        goodsvo goods = goodsservice.getgoodsvobygoodsid(goodsId);
//        long startAt = goods.getStartDate().getTime();
//        long endAt = goods.getEndDate().getTime();
//        long now = System.currentTimeMillis();
//        int miaoshaStatus = 0;
//        int remainSeconds = 0;
//        if(now < startAt ) {//秒杀还没开始，倒计时
//            miaoshaStatus = 0;
//            remainSeconds = (int)((startAt - now )/1000);
//        }else  if(now > endAt){//秒杀已经结束
//            miaoshaStatus = 2;
//            remainSeconds = -1;
//        }else {//秒杀进行中
//            miaoshaStatus = 1;
//            remainSeconds = 0;
//        }
//        goodsdetailvo vo = new goodsdetailvo();
//        vo.setGoods(goods);
//        vo.setUser(user);
//        vo.setRemiaoshaStatus(remainSeconds);
//        vo.setMiaoshaStatus(miaoshaStatus);
//        return Result.success(vo);
//    }
    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<goodsdetailvo> detail(miaoshauser user,@PathVariable("goodsId")long goodsId){
        System.out.println("++++++++++++++++++++++++++++++++++");
        goodsvo goods=goodsservice.getgoodsvobygoodsid(goodsId);
       long startAt=goods.getStartDate().getTime();
       long endAt=goods.getEndDate().getTime();
       long now=System.currentTimeMillis();
       int miaoshaStatus=0;
        int remiaoshaStatus=0;
        if(now<startAt){
            miaoshaStatus=0;
            remiaoshaStatus=(int)((startAt-now)/1000);
        }else if(now>endAt){
            miaoshaStatus=2;
            remiaoshaStatus=-1;
        }else {
            miaoshaStatus=1;
            remiaoshaStatus=0;
        }
goodsdetailvo vo=new goodsdetailvo();
        vo.setGoods(goods);
        vo.setUser(user);
        vo.setRemiaoshaStatus(remiaoshaStatus);
        vo.setMiaoshaStatus(miaoshaStatus);
        System.out.println(goods.toString()+"aaaaaaaaaaaaaaa"+user.toString());
        return  Result.success(vo);
    }



}
