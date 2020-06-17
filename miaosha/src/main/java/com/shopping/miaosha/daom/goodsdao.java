package com.shopping.miaosha.daom;

import com.shopping.miaosha.dao.goods;
import com.shopping.miaosha.dao.miaosha_goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.shopping.miaosha.vo.goodsvo;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface goodsdao {
    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count>0")
    public int reduceStock(miaosha_goods g);
//    @Select("select g.*,mg.stock_court, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id=#{goodsId}")
//    public static goodsvo getgoodsvobygoodsid(@Param("goodsId") long goodsId) ;

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    public List<goodsvo> listGoodsVo();

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id=#{goodsId}")
    public goodsvo getgoodsvobygoodsid(@Param("goodsId") long goodsId);

//    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
//    public List<goodsvo> listGoodsVo();


}
