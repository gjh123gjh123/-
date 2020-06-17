package com.shopping.miaosha.service;

import com.alibaba.druid.util.StringUtils;
import com.shopping.miaosha.exception.GlobalException;
import com.shopping.miaosha.redis.MiaoshaUserKey;
import com.shopping.miaosha.redis.RedisService;
import com.shopping.miaosha.result.CodeMsg;
import com.shopping.miaosha.util.MD5Util;
import com.shopping.miaosha.util.UUIDUtil;
import com.shopping.miaosha.vo.loginvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping.miaosha.daom.miaoshauserdao;
import com.shopping.miaosha.dao.miaoshauser;
import com.shopping.miaosha.redis.MiaoshaUserKey;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class miaoshauserservice {

    public static final String COOKIE_NAME_TOKEN="token";
    @Autowired
    miaoshauserdao miaoshauserdao;
    @Autowired
    RedisService redisService;

    public miaoshauser getbyid(long id){
        miaoshauser user=redisService.get(MiaoshaUserKey.getbyid,""+id,miaoshauser.class);
        if(user!=null){
            return user;
        }
        user=miaoshauserdao.getbyid(id);
        if(user!=null){
            redisService.set(MiaoshaUserKey.getbyid,""+id,user);
        }
        return user;
    }

    public boolean login(HttpServletResponse response,loginvo loginvo) {
        if(loginvo==null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile=loginvo.getMobile();
        String formPass=loginvo.getPassword();
        //判断手机号是否存在
        System.out.println(mobile);
        miaoshauser user=getbyid(Long.parseLong(mobile));
        System.out.println(user);
        if(user==null){
            throw new GlobalException (CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass=user.getPassword();
        String saltDB=user.getSalt();
        String calcPass=MD5Util.formPassToDBPass(formPass,saltDB);
        System.out.println(dbPass+"!!!!!!!!!!aaaaaaaaaa!!!!"+calcPass);
         if(!calcPass.equals(dbPass)){
             throw new GlobalException(CodeMsg.PASSWORD_ERROR);
         }

//         String token= UUIDUtil.uuid();
//         redisService.set(MiaoshaUserKey.token,token,user);
//        Cookie cookie=new Cookie(COOKIE_NAME_TOKEN,token);
//        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
//        cookie.setPath("/");
//        response.addCookie(cookie);
        String token=UUIDUtil.uuid();
        addCookie(response,token,user);
         return true;
    }

    private void addCookie(HttpServletResponse response,String token,miaoshauser user){
        redisService.set(MiaoshaUserKey.token,token,user);
        Cookie cookie=new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public miaoshauser getbytoken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        miaoshauser user =redisService.get(MiaoshaUserKey.token,token,miaoshauser.class);
        if(user!=null){
            addCookie(response,token,user);
        }

        return user;

    }
    public boolean updatepassword(String tooken,long id,String frompass){
        miaoshauser user=getbyid(id);
        if(user==null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }


        miaoshauser tobeupdate=new miaoshauser();
        tobeupdate.setId(id);
        tobeupdate.setPassword(MD5Util.formPassToDBPass(frompass,user.getSalt()));
        miaoshauserdao.update(tobeupdate);


        redisService.delete(MiaoshaUserKey.getbyid,""+id);
        user.setPassword(tobeupdate.getPassword());
        redisService.set(MiaoshaUserKey.token,tooken,user);
        return true;
    }
}
