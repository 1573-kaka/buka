package cn.imust.service.cargo.impl;

import cn.imust.dao.cargo.*;
import cn.imust.domain.cargo.*;
import cn.imust.service.cargo.ExportService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//报运单的主表service
@Service
public class ExportServiceImpl implements ExportService{

    @Autowired
    private ExportDao exportDao;  //报运单dao
    @Autowired
    private ExportProductDao exportProductDao;
    @Autowired
    private  ContractProductDao contractProductDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ExtEproductDao extEproductDao;


    @Override
    public Export findById(String id) {
        return exportDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Export export) {
        // 保存报运单
        export.setId(UUID.randomUUID().toString());
        export.setState(0);
        // 通过合同 IDs 查询所有的购销合同
        String[] contractIds = export.getContractIds().split(",");
        String contractNo = "";
        int proNum = 0;
        int extNum = 0;
        for (String contractId : contractIds) {
            Contract contract = contractDao.selectByPrimaryKey(contractId);
            contract.setState(2);
            contractNo = contractNo + " ";
            contractDao.updateByPrimaryKeySelective(contract);

            // 将所有的购销合同货物写入到报运单商品中
            ContractProductExample contractProductExample = new ContractProductExample();
            ContractProductExample.Criteria criteria = contractProductExample.createCriteria();
            criteria.andContractIdEqualTo(contractId);
            List<ContractProduct> contractProducts = contractProductDao.selectByExample(contractProductExample);
            Map<String, String> map = new HashMap<String, String>();

            for (ContractProduct contractProduct : contractProducts) {
                ExportProduct exportProduct = new ExportProduct();
                BeanUtils.copyProperties(contractProduct, exportProduct);
                exportProduct.setId(UUID.randomUUID().toString());
                exportProduct.setExportId(export.getId());
                map.put(contractProduct.getId(),exportProduct.getId());
                exportProductDao.insertSelective(exportProduct);
                proNum = proNum + contractProduct.getCnumber();
            }

            ExtCproductExample extCproductExample = new ExtCproductExample();
            ExtCproductExample.Criteria criteria1 = extCproductExample.createCriteria();
            criteria1.andContractIdEqualTo(contractId);
            List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);
            for (ExtCproduct extCproduct : extCproducts) {
                ExtEproduct extEproduct = new ExtEproduct();
                BeanUtils.copyProperties(extCproduct,extEproduct);
                extEproduct.setId(UUID.randomUUID().toString());
                extEproduct.setExportId(export.getId());
                extEproduct.setExportProductId(map.get(extCproduct.getContractProductId()));
                extEproductDao.insertSelective(extEproduct);
                extNum = extNum + extCproduct.getCnumber();
            }
        }
        // 将所有的购销合同附件写入到报运单附件中
        export.setCustomerContract(contractNo);
        export.setProNum(proNum);
        //设置报运单的附件数量
        export.setExtNum(extNum);
        //保存报运单
        exportDao.insertSelective(export);
    }

    @Override
    public void update(Export export) {
        //1 、保存报运单主体
        exportDao.updateByPrimaryKeySelective(export);
        //2 、循环保存货物内容
        if (export.getExportProducts()!=null){
            List<ExportProduct> exportProducts = export.getExportProducts();
            for (ExportProduct exportProduct : exportProducts) {
                exportProductDao.updateByPrimaryKeySelective(exportProduct);
            }
        }
    }

    @Override
    public void delete(String id) {
        //删除所有报运单货物的附件
        extEproductDao.deleteByExportId(id);
        //删除所有报运单货物
        exportProductDao.deleteByExportId(id);
        //删除报运单
        exportDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExportExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Export> list = exportDao.selectByExample(example);
        return new PageInfo(list);
    }
}
