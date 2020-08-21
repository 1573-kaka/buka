package cn.imust.dao.cargo;

import cn.imust.domain.cargo.Factory;
import cn.imust.domain.cargo.FactoryExample;

import java.util.List;
import java.util.Map;

public interface FactoryDao {

	//删除
    int deleteByPrimaryKey(String id);

	//保存
    int insertSelective(Factory record);

	//条件查询
    List<Factory> selectByExample(FactoryExample example);

	//id查询
    Factory selectByPrimaryKey(String id);

	//更新
    int updateByPrimaryKeySelective(Factory record);

    List<Factory> findByMap(Map map);

    List<Factory> findByCompanyId(String companyId);
}