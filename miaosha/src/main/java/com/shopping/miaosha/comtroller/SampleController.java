package com.shopping.miaosha.comtroller;

import com.shopping.miaosha.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.miaosha.dao.user;
import com.shopping.miaosha.redis.UserKey;
import com.shopping.miaosha.result.Result;
import com.shopping.miaosha.service.userservice;

@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    userservice userservice;

    @Autowired
    RedisService redisService;
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","Joshua");
        return "hello";
    }
    @RequestMapping("/db/get")
    @ResponseBody
    public Result<user> dbget(){
       user user=userservice.getbyid(1);
       return Result.success(user);
    }
    @RequestMapping("/db/t")
    @ResponseBody
    public Result<Boolean> dbtx(){
       userservice.t();
        return Result.success(true);
    }
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<user> redisget(){
        user  user  = redisService.get(UserKey.getById, ""+1, user.class);
        return Result.success(user);
    }
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisset(){
        user user=new user();
        user.setId(1);
        user.setName("1111111");
        redisService.set(UserKey.getById,""+1,user);
         return Result.success(true);
    }
}
