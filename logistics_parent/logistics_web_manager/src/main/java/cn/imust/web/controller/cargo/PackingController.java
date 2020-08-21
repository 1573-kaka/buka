package cn.imust.web.controller.cargo;

import cn.imust.common.utils.BeanMapUtils;
import cn.imust.domain.cargo.*;
import cn.imust.domain.system.User;
import cn.imust.service.cargo.ExportService;
import cn.imust.service.cargo.PackingService;
import cn.imust.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/cargo/packing")
public class PackingController extends BaseController {
    @Reference
    private PackingService packingService;
    @Reference
    private ExportService exportService;

    @RequestMapping("/list")
    public String  findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        PackingExample packingExample = new PackingExample();
        PackingExample.Criteria criteria = packingExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo pageInfo = packingService.findAll(packingExample, page, size);
        request.setAttribute("page",pageInfo);
        return "cargo/packing/packed-list";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        packingService.delete(id);
        return "redirect:/cargo/packing/list";
    }

    @RequestMapping("/edit")
    public  String  edit(Packing packing){
        if(packing.getPackingListId()==null){
            packing.setCompanyId(companyId);
            packing.setCompanyName(companyName);
            packingService.save(packing);
        }else {
            packingService.update(packing);
        }
        return "redirect:/cargo/packing/list";
    }
    @RequestMapping("/exportlist")
    public String exportlist( @RequestParam(defaultValue = "1")Integer page,
                              @RequestParam(defaultValue = "10")Integer size){

        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        /*criteria.andCompanyIdEqualTo(user.getCompanyId());*/
        criteria.andStateEqualTo(2L);
        PageInfo all = exportService.findAll(exportExample, page, size);
        request.setAttribute("page",all);
        return "cargo/packing/packing-list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Packing packing = packingService.findById(id);
        request.setAttribute("packing",packing);
        return "cargo/packing/packed-update";
    }

    @RequestMapping("/toPacking")
    public String toPacking(String id){
        request.setAttribute("id",id);
        return "cargo/packing/packing-toPacking";
    }

    @RequestMapping("/toView")
    public String toView(String id){
        Packing packing=packingService.findById(id);
        request.setAttribute("packing",packing);
        return "cargo/packing/packed-view";
    }

    @RequestMapping("/cancel")
    public String cancel(String id){
        packingService.cancel(id);
        return "redirect:/cargo/packing/list";
    }
    @RequestMapping("/submit")
    public String submit(String id) {
        packingService.submit(id);
        return "redirect:/cargo/packing/list";
    }
    @RequestMapping("/packingPdf")
    public void exportPdf(String id, HttpServletResponse response) throws Exception {
        Packing packing= packingService.findById(id);

        //1.1、将实体类转化为map
        Map<String, Object> packingMap = BeanMapUtils.beanToMap(packing);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(packing.getExports());

        String path = session.getServletContext().getRealPath("/")+"/jasper/packing.jasper";
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, packingMap, dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
