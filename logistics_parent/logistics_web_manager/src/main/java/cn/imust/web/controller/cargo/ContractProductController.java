package cn.imust.web.controller.cargo;

import cn.imust.common.utils.UploadUtil;
import cn.imust.domain.cargo.ContractProduct;
import cn.imust.domain.cargo.ContractProductExample;
import cn.imust.domain.cargo.Factory;
import cn.imust.domain.cargo.FactoryExample;
import cn.imust.service.cargo.ContractProductService;
import cn.imust.service.cargo.FactoryService;
import cn.imust.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/cargo/contractProduct")
public class ContractProductController extends BaseController {
    @Reference
    private ContractProductService contractProductService;

    @Reference
    private FactoryService factoryService;

    @RequestMapping(value = "/list", name = " 货物查询")
    public String list(String contractId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){

        //1、通过factoryService查询所有的厂家，条件必须货物
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria factoryCriteria = factoryExample.createCriteria();
        factoryCriteria.andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        //2、factoryList放入request域当中
        request.setAttribute("factoryList", factoryList);

        //3、通过contractProductService查询货物列表
        ContractProductExample example = new ContractProductExample();
        ContractProductExample.Criteria criteria = example.createCriteria();
        criteria.andContractIdEqualTo(contractId);
        PageInfo pageInfo = contractProductService.findAll(example, page, size);
        //4、货物信息放到request域当中
        request.setAttribute("page", pageInfo);

        request.setAttribute("contractId", contractId);
        return "cargo/product/product-list";
    }

    @RequestMapping(value = "/edit", name = " 保存货物")
    public String edit(String contractId, ContractProduct contractProduct, MultipartFile
            productPhoto) throws IOException {
        contractProduct.setCompanyId(companyId);
        contractProduct.setCompanyName(companyName);

        if (!productPhoto.isEmpty()) {
            String imgUrl = UploadUtil.upload(productPhoto.getBytes());
            contractProduct.setProductImage(imgUrl);
        }

        if (StringUtil.isEmpty(contractProduct.getId())){
            contractProductService.save(contractProduct);
        } else {
            contractProductService.update(contractProduct);
        }
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }

    @RequestMapping(value = "/toUpdate")
    public String toUpdate(String id){
        //1、通过factoryService查询所有的厂家，条件必须货物
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria factoryCriteria = factoryExample.createCriteria();
        factoryCriteria.andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        //2、factoryList放入request域当中
        request.setAttribute("factoryList", factoryList);

        //参数：货物的id
        ContractProduct contractProduct = contractProductService.findById(id);
        request.setAttribute("contractProduct", contractProduct);
        return "cargo/product/product-update";
    }

    @RequestMapping(value = "/delete")
    public String delete(String id, String contractId){
        contractProductService.delete(id);
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }

    //进入页面
    @RequestMapping(value = "/toImport", name = " 上传货物")
    public String toImport(String contractId){
        request.setAttribute("contractId", contractId);
        return "cargo/product/product-import";
    }

    @RequestMapping(value = "/import")
    public String importExcel(String contractId, MultipartFile file) throws IOException {

        //1、接收EXCEL的表流化文件
        //2、创建工作薄
        Workbook wb = new XSSFWorkbook(file.getInputStream());   //2007 xml   XSS --> 2007及以上版本
        //3、创建页
        Sheet sheet = wb.getSheetAt(0);

        Object[] strings = new Object[10];
        //4、循环EXCEL表里的数据
        for (int i=1; i<=2; i++){
            Row row = sheet.getRow(i);

            for (int j=1; j<10; j++){
                Cell cell = row.getCell(j);
                strings[j] = getCellValue(cell);
            }

            //5、构造ContractProduct实体类
            //6、批量写入货物表
            ContractProduct contractProduct = new ContractProduct(strings, companyId, companyName);
            contractProduct.setContractId(contractId);
            contractProductService.save(contractProduct);
        }

        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }

    public Object getCellValue(Cell cell){
        Object object = new Object();

        switch (cell.getCellType()){
            case STRING:
                object = cell.getStringCellValue();
                break;
            case BOOLEAN:
                object = cell.getBooleanCellValue();
                break;
            case NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)){
                    object = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                }else{
                    object = cell.getNumericCellValue();
                }
                break;
            case FORMULA:
                break;
        }
        return object;
    }
}
