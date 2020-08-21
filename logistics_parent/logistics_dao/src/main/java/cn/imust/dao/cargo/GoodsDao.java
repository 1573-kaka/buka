package cn.imust.dao.cargo;

import cn.imust.domain.cargo.Goods;
import cn.imust.domain.cargo.GoodsExample;

import java.util.List;

public interface GoodsDao {

    int deleteByPrimaryKey(String id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}