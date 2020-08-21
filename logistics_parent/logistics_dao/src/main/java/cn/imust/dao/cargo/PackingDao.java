package cn.imust.dao.cargo;

import cn.imust.domain.cargo.Packing;
import cn.imust.domain.cargo.PackingExample;

import java.util.List;

public interface PackingDao {

    int deleteByPrimaryKey(String packingListId);

    int insert(Packing record);

    int insertSelective(Packing record);

    List<Packing> selectByExample(PackingExample example);

    Packing selectByPrimaryKey(String packingListId);

    int updateByPrimaryKeySelective(Packing record);

    int updateByPrimaryKey(Packing record);
}