package cn.imust.service.cargo;

import cn.imust.domain.cargo.Factory;
import cn.imust.domain.cargo.FactoryExample;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface FactoryService {
    //查询所有工厂信息
    List<Factory> findAll(FactoryExample example);

    void save(Factory factory);

    void update(Factory factory);

    void delete(String id);

    Factory findById(String id);

    //查询所有
    PageInfo findPage(FactoryExample example, int page, int size);

    public List<Factory> findByMap(Map map);
}
