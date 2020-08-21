package cn.imust.dao.cargo;

import cn.imust.domain.cargo.ExportProduct;
import cn.imust.domain.cargo.ExportProductExample;

import java.util.List;

public interface ExportProductDao {

    int deleteByPrimaryKey(String id);

    int insertSelective(ExportProduct record);

    List<ExportProduct> selectByExample(ExportProductExample example);

    ExportProduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExportProduct record);

    void deleteByExportId(String id);

    List<ExportProduct> findByExportId(String exportId);
}