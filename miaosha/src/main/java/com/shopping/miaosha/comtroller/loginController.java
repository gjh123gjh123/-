package com.shopping.miaosha.comtroller;

import com.shopping.miaosha.dao.user;
import com.shopping.miaosha.redis.RedisService;
import com.shopping.miaosha.redis.UserKey;
import com.shopping.miaosha.result.CodeMsg;
import com.shopping.miaosha.result.Result;
import com.shopping.miaosha.service.userservice;
import com.shopping.miaosha.util.ValidatatorUtil;
import com.shopping.miaosha.service.miaoshauserservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shopping.miaosha.vo.*;
import com.alibaba.druid.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")

public class loginController {

    private static Logger log= LoggerFactory.getLogger(Controller.class);
    @Autowired
    userservice userservice;

    @Autowired
    RedisService redisService;

    @Autowired
    miaoshauserservice miaoshauserservice;

    @RequestMapping("to_login")
    public String tologin(){
             return "login";
    }
    @RequestMapping("do_login")
    @ResponseBody
    public Result<Boolean> dologin(HttpServletResponse response, @Valid loginvo loginvo){
        log.info(loginvo.toString());
//        //参数校验
//        String passinput=loginvo.getPassword();
//        String moblie=loginvo.getMobile();
//        if(StringUtils.isEmpty(passinput)){//密码是否为空
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if(StringUtils.isEmpty(moblie)){//手机号是否符合格式
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if(!ValidatatorUtil.isMobile(moblie)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
        //登陆
        miaoshauserservice.login(response,loginvo);
        return Result.success(true);
    }
}
