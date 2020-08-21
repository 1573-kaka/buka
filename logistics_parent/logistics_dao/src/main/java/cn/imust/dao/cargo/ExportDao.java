package cn.imust.dao.cargo;

import cn.imust.domain.cargo.Export;
import cn.imust.domain.cargo.ExportExample;

import java.util.List;

public interface ExportDao {

    List<Export> selectByExample(ExportExample example);

    Export selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Export export);

    void insertSelective(Export export);

    int deleteByPrimaryKey(String id);
}