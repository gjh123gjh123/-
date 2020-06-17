package com.shopping.miaosha.service;

import com.shopping.miaosha.dao.user;
import com.shopping.miaosha.daom.userdao;
import com.shopping.miaosha.result.CodeMsg;
import com.shopping.miaosha.vo.loginvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
@Component
public class userservice {

    @Autowired
    userdao userdao;
    public user getbyid(int id){
        return userdao.getById(id);
    }

    //@Transactional
    public boolean t(){
        user u1=new user();
        u1.setId(2);
        u1.setName("222");
        userdao.insert(u1);

        user u2=new user();
        u2.setId(1);
        u2.setName("111");
        userdao.insert(u2);
        return true;
    }


}
