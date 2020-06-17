package com.shopping.miaosha.daom;

import com.shopping.miaosha.dao.miaoshaouder;
import com.shopping.miaosha.dao.orderinfo;
import com.shopping.miaosha.vo.goodsvo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.ws.WebServiceRef;
import java.util.List;

@Mapper
@Component
public interface orderdao {

    @Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
    miaoshaouder getMiaoshaOrderByUserIdGoodsId(@Param("userId") Long userId,@Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
   public long insert(orderinfo orderInfo);
    @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    public int insertMiaoshaOrder(miaoshaouder miaoshaOrder);

    @Select("select * from order_info where id = #{orderId}")
    public orderinfo getorderbyid(long orderId);
}
