package cn.imust.web.controller;

import cn.imust.domain.company.Company;
import cn.imust.service.company.CompanyService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplyController {
    @Reference
    private CompanyService companyService;

    @RequestMapping(value = "/apply")
    public @ResponseBody String apply(Company company){
        try {
            //调用companySerivce接口,save方法保存企业
            company.setState(0);
            companyService.save(company);
            return "1";
        }catch (Exception e){
            return "2";
        }
    }
}
