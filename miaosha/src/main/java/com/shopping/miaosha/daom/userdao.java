package com.shopping.miaosha.daom;

import com.shopping.miaosha.dao.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface userdao {
    @Select("select * from user where id = #{id}")
    public user getById(@Param("id")int id	);

    @Insert("insert into user(id, name)values(#{id}, #{name})")
    public int insert(user user);
}
