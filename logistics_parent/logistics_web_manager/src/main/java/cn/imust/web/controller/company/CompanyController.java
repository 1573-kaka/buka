package cn.imust.web.controller.company;

import cn.imust.domain.company.Company;
import cn.imust.service.company.CompanyService;
import cn.imust.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;


@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Reference
    private CompanyService companyService;

    //列表查询
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size){
        PageInfo pageInfo = companyService.findByHelper(page, size);
        request.setAttribute("page",pageInfo);
        return "company/company-list";
    }

    @RequestMapping("/save")
    public String save(Data data){
        return "success";
    }

    //新建企业
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "company/company-add";
    }

    @RequestMapping("/edit")
    public String edit(Company company){
        if (StringUtil.isEmpty(company.getId())){
            //新建
            companyService.save(company);
        } else {
            //修改
            companyService.update(company);
        }
        return "redirect: /company/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Company company = companyService.findById(id);
        request.setAttribute("company",company);
        return "company/company-update";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        companyService.delete(id);
        return "redirect: /company/list.do";
    }

    /*@RequestMapping("/getAdmin")
    public String getAdmin(String id){
        companyService.buildAdmin(id);
        return "redirect: /company/list.do";
    }*/
}
