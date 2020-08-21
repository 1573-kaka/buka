package cn.imust.service.cargo.impl;

import cn.imust.dao.cargo.FactoryDao;
import cn.imust.domain.cargo.Factory;
import cn.imust.domain.cargo.FactoryExample;
import cn.imust.service.cargo.FactoryService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FactoryServiceImpl implements FactoryService{

    @Autowired
    private FactoryDao factoryDao;

    public List<Factory> findAll(String companyId) {
        return factoryDao.findByCompanyId(companyId);
    }

    @Override
    public List<Factory> findAll(FactoryExample example) {
        return factoryDao.selectByExample(example);
    }

    @Override
    public void save(Factory factory) {
        String id = UUID.randomUUID().toString();
        id = id.replace("_","").toUpperCase();
        factory.setId(id);
        factory.setCreateTime(new Date());
        factory.setUpdateTime(new Date());
        factoryDao.insertSelective(factory);
    }

    @Override
    public void update(Factory factory) {
        factory.setUpdateTime(new Date());
        factoryDao.updateByPrimaryKeySelective(factory);
    }

    @Override
    public void delete(String id) {
        factoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public Factory findById(String id) {
        return factoryDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findPage(FactoryExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Factory> factories = factoryDao.selectByExample(example);
        PageInfo<Factory> pageInfo = new PageInfo<>(factories, 5);
        return pageInfo;
    }

    @Override
    public List<Factory> findByMap(Map map) {
        return factoryDao.findByMap(map);
    }
}
