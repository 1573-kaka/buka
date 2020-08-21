package cn.imust.web.controller.cargo;

import cn.imust.domain.cargo.*;
import cn.imust.service.cargo.FactoryService;
import cn.imust.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cargo/factory")
public class FactoryController extends BaseController {
    @Reference
    private FactoryService factoryService;

    @RequestMapping("/list")
    public String  findAll(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){

        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria criteria = factoryExample.createCriteria();
        criteria.andIdIsNotNull();
        PageInfo pageInfo = factoryService.findPage(factoryExample, page, size);
        request.setAttribute("page",pageInfo);
        return "cargo/factory/factory-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "cargo/factory/factory-add";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        factoryService.delete(id);
        return "redirect:/cargo/factory/list.do";
    }

    @RequestMapping("/edit")
    public  String  edit(Factory factory){
        if(factory.getId()==null){
            factoryService.save(factory);
        }else {
            factoryService.update(factory);
        }
        return "redirect:/cargo/factory/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Factory factory = factoryService.findById(id);
        request.setAttribute("factory",factory);
        return "cargo/factory/factory-update";
    }

    @RequestMapping("/toView")
    public String toView(String id){
        Factory factory=factoryService.findById(id);
        request.setAttribute("factory",factory);
        return "cargo/factory/factory-view";
    }
}
