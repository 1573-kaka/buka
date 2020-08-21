package cn.imust.service.cargo.impl;

import cn.imust.dao.cargo.ContractDao;
import cn.imust.dao.cargo.ContractProductDao;
import cn.imust.dao.cargo.ExtCproductDao;
import cn.imust.domain.cargo.*;
import cn.imust.domain.vo.ContractProductVo;
import cn.imust.service.cargo.ContractProductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class ContractProductServiceImpl implements ContractProductService{

    @Autowired
    private ContractProductDao contractProductDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ExtCproductDao extCproductDao;

    //分页查询所有货物信息
    @Override
    public PageInfo findAll(ContractProductExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<ContractProduct> list = contractProductDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void save(ContractProduct contractProduct) {
        //1、计算一下货物的金额
        double amount = 0.0d;
        if (contractProduct.getCnumber()!=null&&contractProduct.getPrice()!=null){
            amount = contractProduct.getCnumber() * contractProduct.getPrice();
        }
        //2、设置货物的金额
        contractProduct.setId(UUID.randomUUID().toString());
        contractProduct.setAmount(amount);
        //3、保存货物
        contractProductDao.insertSelective(contractProduct);

        //4、通过contractProduct.contractId取合同实体
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());

        if (contract.getTotalAmount()==null){
            contract.setTotalAmount(amount);
        }else {
            //5、计算一下合同的金额（合同以前的金额+本次货物的金额）
            contract.setTotalAmount(contract.getTotalAmount()+amount);
        }

        if (contract.getProNum()==null){
            contract.setProNum(contractProduct.getCnumber());
        }else {
            //6、计算一下合同的货物数量（合同以前的货物数量+本次货物数量）
            contract.setProNum(contract.getProNum()+contractProduct.getCnumber());
        }

        //7、更新合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public ContractProduct findById(String id) {
        return contractProductDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(ContractProduct contractProduct) {
        //1、计算一下货物的金额
        double amount = 0.0d;
        if (contractProduct.getCnumber()!=null&&contractProduct.getPrice()!=null){
            amount = contractProduct.getCnumber() * contractProduct.getPrice();
        }
        //2、设置货物的金额
        contractProduct.setAmount(amount);

        //3、通过contractProduct.contractId取合同实体
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());

        //4、通过contractProduct.getId()查询一下以前的货物
        ContractProduct oldCp = contractProductDao.selectByPrimaryKey(contractProduct.getId());

        //5、计算一下合同的金额（合同以前的金额 - 以前的货物金额 + 本次的货物金额）
        contract.setTotalAmount(contract.getTotalAmount() - oldCp.getAmount() + amount);
        contract.setProNum(contract.getProNum() - oldCp.getCnumber() + contractProduct.getCnumber());

        //6、保存货物
        contractProductDao.updateByPrimaryKeySelective(contractProduct);
        //7、更新合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    //id是货物id
    @Override
    public void delete(String id) {
        //1、获取货物下的所有附件
        ExtCproductExample example = new ExtCproductExample();
        ExtCproductExample.Criteria criteria = example.createCriteria();
        criteria.andContractProductIdEqualTo(id);
        List<ExtCproduct> extcList = extCproductDao.selectByExample(example);

        double amount = 0.0d;
        int extNum = 0;
        //2、循环货物下的所有附件调用附件的删除方法
        for (ExtCproduct extCproduct : extcList) {
            amount += extCproduct.getAmount();
            extNum += extCproduct.getCnumber();
            extCproductDao.deleteByPrimaryKey(extCproduct.getId());
        }

        //通过id查询货物实体类
        ContractProduct contractProduct = contractProductDao.selectByPrimaryKey(id);
        //通过实体类获取删除的货物金额
        double proAmount = contractProduct.getAmount();
        //通过实体类获取删除的货物数量
        int proNum = contractProduct.getCnumber();

        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        //计算一下合同金额，（合同以前的金额 - 本次删除货物金额 - 本次删除的附件金额）
        contract.setTotalAmount(contract.getTotalAmount() - proAmount - amount);
        //计算一下合同货物数量，（合同以前的货物数量 - 本次删除的货物数量）
        contract.setProNum(contract.getProNum() - proNum);
        //计算一下合同附件数量，（合同以前的附件数量 - 本次删除的附件数量）
        contract.setExtNum(contract.getExtNum() - extNum);
        //通过id删除一个货物
        contractProductDao.deleteByPrimaryKey(id);
        //更新合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    // 批量保存货物
    @Override
    public void saveAll(List<ContractProduct> list) {
        for (ContractProduct contractProduct : list) {
            this.save(contractProduct);
        }
    }

    @Override
    public List<ContractProductVo> findByShipTime(String inputDate) {
        return contractProductDao.findByShipTime(inputDate);
    }
}
