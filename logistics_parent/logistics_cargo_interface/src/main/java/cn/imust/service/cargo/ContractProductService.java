package cn.imust.service.cargo;


import cn.imust.domain.cargo.ContractProduct;
import cn.imust.domain.cargo.ContractProductExample;
import cn.imust.domain.vo.ContractProductVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ContractProductService {

	PageInfo findAll(ContractProductExample example, int page, int size);

	void save(ContractProduct contractProduct);

	void update(ContractProduct contractProduct);

	void delete(String id);

	ContractProduct findById(String id);

	void saveAll(List<ContractProduct> list);

	List<ContractProductVo> findByShipTime(String inputDate);
}
