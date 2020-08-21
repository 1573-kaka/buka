package cn.imust.service.cargo;

import cn.imust.domain.cargo.Contract;
import cn.imust.domain.cargo.ContractExample;
import com.github.pagehelper.PageInfo;

public interface ContractService {

    //分页查询
    PageInfo findAll(ContractExample example, int page, int size);

    //保存合同信息
    void save(Contract contract);

    //通过id进行查询
    Contract findById(String id);

    //更新合同信息
    void update(Contract contract);

    //通过id进行删除
    void delete(String id);
}
