package cn.imust.service.cargo.impl;

import cn.imust.dao.cargo.ExportProductDao;
import cn.imust.domain.cargo.ExportProduct;
import cn.imust.domain.cargo.ExportProductExample;
import cn.imust.service.cargo.ExportProductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import sun.nio.ch.Net;

import java.util.List;

@Service
public class ExportProductServiceImpl implements ExportProductService{

    @Autowired
    private ExportProductDao exportProductDao;

    @Override
    public ExportProduct findById(String id) {
        return exportProductDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(ExportProduct exportProduct) {

    }

    @Override
    public void update(ExportProduct exportProduct) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<ExportProduct> findAll(ExportProductExample example) {
        return exportProductDao.selectByExample(example);
    }

    //根据报运单 ID 查询报运单下所有货物
    @Override
    public List<ExportProduct> findByExportId(String exportId) {
        return exportProductDao.findByExportId(exportId);
    }
}
