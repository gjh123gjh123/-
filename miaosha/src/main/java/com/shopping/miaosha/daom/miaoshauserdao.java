package com.shopping.miaosha.daom;

import com.shopping.miaosha.dao.miaoshauser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Mapper
public interface miaoshauserdao {
    @Select("select * from miaosha_user where id = #{id}")
    public miaoshauser getbyid(@Param("id")long id);

    @Update("update miaosha_user set password=#{password} where id ={id}")
    public void update(miaoshauser tobeupdate);
}
