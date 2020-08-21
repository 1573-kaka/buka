package cn.imust.dao.cargo;

import cn.imust.domain.cargo.ExtCproduct;
import cn.imust.domain.cargo.ExtCproduct;
import cn.imust.domain.cargo.ExtCproductExample;

import java.util.List;

public interface ExtCproductDao {

	List<ExtCproduct> findByProductId(String productId);

	//删除
	int deleteByPrimaryKey(String id);

	//保存
	int insertSelective(ExtCproduct record);

	//条件查询
	List<ExtCproduct> selectByExample(ExtCproductExample example);

	//id查询
	ExtCproduct selectByPrimaryKey(String id);

	//更新
	int updateByPrimaryKeySelective(ExtCproduct record);

	//根据合同id删除
	void deleteByContractId(String contractId);

}