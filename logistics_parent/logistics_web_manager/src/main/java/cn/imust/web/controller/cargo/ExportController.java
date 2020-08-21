package cn.imust.web.controller.cargo;

import cn.imust.common.utils.BeanMapUtils;
import cn.imust.domain.cargo.*;
import cn.imust.service.cargo.ContractService;
import cn.imust.service.cargo.ExportProductService;
import cn.imust.service.cargo.ExportService;
import cn.imust.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cargo/export")
public class ExportController extends BaseController {
    @Reference
    private ExportService exportService;
    @Reference
    private ContractService contractService;
    @Reference
    private ExportProductService exportProductService;

    @RequestMapping("/contractList")
    public String contractList(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer size){
        ContractExample contractExample = new ContractExample();
        ContractExample.Criteria criteria = contractExample.createCriteria();
        criteria.andStateEqualTo(1);
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo pageInfo = contractService.findAll(contractExample, page, size);
        request.setAttribute("page",pageInfo);
        return "cargo/export/export-contractList";
    }

    @RequestMapping(value = "/list")
    public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size){
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo pageInfo = exportService.findAll(exportExample, page, size);
        request.setAttribute("page", pageInfo);
        return "cargo/export/export-list";
    }

    @RequestMapping("/toExport")
    public String toExport(String id){
        request.setAttribute("id",id);
        return "cargo/export/export-toExport";
    }

    @RequestMapping(value = "/edit")
    public String edit(Export export, String contractIds){
        export.setCompanyId(companyId);
        export.setCompanyName(companyName);
        export.setContractIds(contractIds);

        if (StringUtil.isEmpty(export.getId())){
            exportService.save(export);
        }else{
            exportService.update(export);
        }
        return "redirect:/cargo/export/list.do";
    }

    @RequestMapping("/toView")
    public String toView(String id){
        Export export = exportService.findById(id);
        request.setAttribute("export", export);
        return "cargo/export/export-view";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Export export = exportService.findById(id);
        request.setAttribute("export",export);
        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> all = exportProductService.findAll(exportProductExample);
        request.setAttribute("eps",all);
        return "cargo/export/export-update";
    }

    @RequestMapping(value = "/submit")
    public String submit(String id){
        Export export = exportService.findById(id);
        export.setState(1);
        exportService.update(export);

        String[] contractIds = export.getContractIds().split(",");
        for (String contractId : contractIds) {
            Contract contract = contractService.findById(contractId);
            contract.setState(3);
            contractService.update(contract);
        }
        return "redirect:/cargo/export/list.do";
    }
    @RequestMapping(value = "/cancel")
    public String cancel(String id){
        Export export = exportService.findById(id);
        export.setState(0);
        exportService.update(export);

        String[] contractIds = export.getContractIds().split(",");
        for (String contractId : contractIds) {
            Contract contract = contractService.findById(contractId);
            contract.setState(2);
            contractService.update(contract);
        }
        return "redirect:/cargo/export/list.do";
    }

    @RequestMapping("/exportPdf")
    public void exportPdf(String id,HttpServletResponse response) throws Exception {
        Export export = exportService.findById(id);

        export.setCustomerContract("123123123");

        //1.1、将实体类转化为map
        Map<String, Object> exportMap = BeanMapUtils.beanToMap(export);

        //2、通过id查询exportProduct报运单商品信息，返回list
        ExportProductExample exportProductExample = new ExportProductExample();
        ExportProductExample.Criteria criteria = exportProductExample.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> list = exportProductService.findAll(exportProductExample);

        // [{productNo: 123, packingUnit: 1}, {productNo: 1234, packingUnit: 12}]

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        String path = session.getServletContext().getRealPath("/")+"/jasper/export.jasper";
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, exportMap, dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @RequestMapping(value = "/exportE", name = "电子上报")
    public String exportE(String id){
        Export export = exportService.findById(id);
        export.setState(2);
        exportService.update(export);

        String[] contractIds = export.getContractIds().split(",");
        for (String contractId : contractIds) {
            Contract contract = contractService.findById(contractId);
            contract.setState(4);
            contractService.update(contract);
        }
        return "redirect:/cargo/export/list.do";
    }
}
