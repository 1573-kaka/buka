package cn.imust.service.cargo.impl;

import cn.imust.dao.cargo.ContractDao;
import cn.imust.dao.cargo.ContractProductDao;
import cn.imust.dao.cargo.ExtCproductDao;
import cn.imust.domain.cargo.*;
import cn.imust.service.cargo.ContractService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ContractServiceImpl implements ContractService{

    @Autowired
    private ContractDao contractDao;   //合同的dao

    @Autowired
    private ContractProductDao contractProductDao;    //货物的dao

    @Autowired
    private ExtCproductDao extCproductDao;    //附件的dao

    @Override
    public PageInfo findAll(ContractExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Contract> list = contractDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void save(Contract contract) {
        // 固定值需要传入
        contract.setId(UUID.randomUUID().toString());
        contract.setCreateTime(new Date());
        contract.setUpdateTime(new Date());

        contract.setState(0); //0- 草稿
        contract.setProNum(0); // 货物数量，作为草稿来讲是 0
        contract.setExtNum(0); // 附件数量，也是0
        contractDao.insertSelective(contract);
    }

    @Override
    public Contract findById(String id) {
        return contractDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Contract contract) {
        contractDao.updateByPrimaryKeySelective(contract);
    }

    //合同ID
    @Override
    public void delete(String id) {

        //第二种：
        //直接调用附件dao删除所有附件，条件是合同id
        extCproductDao.deleteByContractId(id);

        //直接调用货物dao删除所有货物，条件是合同id
        contractProductDao.deleteByContractId(id);

        contractDao.deleteByPrimaryKey(id);
    }
}
