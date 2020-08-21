package cn.imust.service.cargo.impl;

import cn.imust.dao.cargo.FactoryDao;
import cn.imust.dao.cargo.GoodsDao;
import cn.imust.domain.cargo.Factory;
import cn.imust.domain.cargo.Goods;
import cn.imust.domain.cargo.GoodsExample;
import cn.imust.service.cargo.GoodsService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodDao;
    @Autowired
    private FactoryDao factoryDao;
    @Override
    public Goods findById(String id) {
        Goods good = goodDao.selectByPrimaryKey(id);
        Factory factory = factoryDao.selectByPrimaryKey(good.getFactoryId());
        good.setFactory(factory);
        return good;
    }

    @Override
    public void save(Goods good) {
        good.setId(UUID.randomUUID().toString());
        goodDao.insertSelective(good);
    }

    @Override
    public void update(Goods goods) {
        goodDao.updateByPrimaryKeySelective(goods);
    }

    @Override
    public void delete(String id) {
        goodDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(GoodsExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Goods> goods = goodDao.selectByExample(example);
        PageInfo<Goods> pageInfo = new PageInfo<>(goods,5);
        return pageInfo;
    }

    @Override
    public List<Goods> findByFid(GoodsExample example) {
        List<Goods> goods = goodDao.selectByExample(example);
        return goods;
    }

}
