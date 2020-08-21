package cn.imust.dao.cargo;

import cn.imust.domain.cargo.ContractProduct;
import cn.imust.domain.cargo.ContractProductExample;
import cn.imust.domain.vo.ContractProductVo;

import java.util.List;

public interface ContractProductDao {

	//删除
    int deleteByPrimaryKey(String id);

	//保存
    int insertSelective(ContractProduct contractProduct);

	//条件查询
    List<ContractProduct> selectByExample(ContractProductExample example);

	//id查询
    ContractProduct selectByPrimaryKey(String id);

	//更新
    int updateByPrimaryKeySelective(ContractProduct contractProduct);

    //通过合同id删除
    int deleteByContractId(String contractId);

    List<ContractProductVo> findByShipTime(String inputDate);
}