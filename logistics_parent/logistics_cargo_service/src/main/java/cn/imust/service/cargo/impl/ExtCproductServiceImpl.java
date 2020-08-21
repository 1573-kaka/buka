package cn.imust.service.cargo.impl;

import cn.imust.dao.cargo.ContractDao;
import cn.imust.dao.cargo.ExtCproductDao;
import cn.imust.domain.cargo.Contract;
import cn.imust.domain.cargo.ExtCproduct;
import cn.imust.domain.cargo.ExtCproductExample;
import cn.imust.service.cargo.ExtCProductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class ExtCproductServiceImpl implements ExtCProductService{

    @Autowired
    private ExtCproductDao extCproductDao;

    @Autowired
    private ContractDao contractDao;

    //分页查询
    @Override
    public PageInfo findAll(ExtCproductExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<ExtCproduct> list = extCproductDao.selectByExample(example);
        return new PageInfo(list);
    }

    //保存附件信息
    @Override
    public void save(ExtCproduct extCproduct) {
        //1、计算一下附件的金额
        double amount = 0.0d;
        if (extCproduct.getCnumber()!=null&&extCproduct.getPrice()!=null){
            amount = extCproduct.getCnumber()*extCproduct.getPrice();
        }
        //2、设置附件的金额
        extCproduct.setAmount(amount);
        //2.1、设置附件的ID
        extCproduct.setId(UUID.randomUUID().toString());
        //3、保存附件
        extCproductDao.insertSelective(extCproduct);

        //4、通过合同ID查询合同实体类
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        //5、计算合同里的附件数量（合同本身的附件数量+本次附件的数量）
        contract.setExtNum(contract.getExtNum() + extCproduct.getCnumber());
        //6、计算合同里的总金额（合同本身的总金额+本次附件的金额）
        contract.setTotalAmount(contract.getTotalAmount() + amount);
        //7、更新合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    //通过ID查询
    @Override
    public ExtCproduct findById(String id) {
        return extCproductDao.selectByPrimaryKey(id);
    }

    //更新
    @Override
    public void update(ExtCproduct extCproduct) {
        //1、计算附件的金额
        double amount = 0.0d;
        if (extCproduct.getCnumber()!=null&&extCproduct.getPrice()!=null){
            amount = extCproduct.getCnumber()*extCproduct.getPrice();
        }
        //2、设置附件的金额
        extCproduct.setAmount(amount);
        //3、通过合同id查找合同的实体类
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        ExtCproduct oldExtc = extCproductDao.selectByPrimaryKey(extCproduct.getId());
        //4、计算合同的金额（合同本身金额-以前附件金额+本次附件金额）
        contract.setTotalAmount(contract.getTotalAmount()-oldExtc.getAmount()+amount);
        //5、计算合同附件的数量（合同本身的数量-以前附件的数量+本次附件的数量）
        contract.setExtNum(contract.getExtNum()-oldExtc.getCnumber()+extCproduct.getCnumber());
        //6、保存附件
        extCproductDao.updateByPrimaryKeySelective(extCproduct);
        //7、更新合同
        contractDao.updateByPrimaryKeySelective(contract);
    }

    //删除
    @Override
    public void delete(String id) {
        //1、通过id查询附件
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        //2、通过附件实体类获取要删除的附件金额
        double amount = extCproduct.getAmount();
        //3、通过附件实体类获取要删除的附件数量
        int extNum = extCproduct.getCnumber();
        //4、获取合同
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        //5、计算一下合同的金额
        contract.setTotalAmount(contract.getTotalAmount()-amount);
        //6、计算一下合同的附件数量
        contract.setExtNum(contract.getExtNum()-extNum);
        //7、删除附件
        extCproductDao.deleteByPrimaryKey(id);
        //8、更新合同
        contractDao.updateByPrimaryKeySelective(contract);
    }
}
