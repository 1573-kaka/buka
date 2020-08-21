package cn.imust.dao.cargo;

import cn.imust.domain.cargo.ExtEproduct;
import cn.imust.domain.cargo.ExtEproductExample;

import java.util.List;

public interface ExtEproductDao {

    int deleteByPrimaryKey(String id);

    int insertSelective(ExtEproduct record);

    List<ExtEproduct> selectByExample(ExtEproductExample example);

    ExtEproduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExtEproduct record);

    void deleteByExportId(String id);
}