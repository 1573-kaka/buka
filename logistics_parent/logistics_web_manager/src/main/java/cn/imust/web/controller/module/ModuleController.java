package cn.imust.web.controller.module;

import cn.imust.domain.module.Module;
import cn.imust.service.module.ModuleService;
import cn.imust.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {
    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size){
        PageInfo pageInfo = moduleService.findAll(page, size);
        request.setAttribute("page",pageInfo);
        return "module/module-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        List<Module> modules = moduleService.findAll();
        request.setAttribute("menus",modules);
        return "module/module-add";
    }

    @RequestMapping("/edit")
    public String edit(Module module){
        if (StringUtil.isEmpty(module.getId())){
            moduleService.save(module);
        } else {
            moduleService.update(module);
        }
        return "redirect: /module/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Module module = moduleService.findById(id);
        request.setAttribute("module",module);
        List<Module> modules = moduleService.findAll();
        request.setAttribute("menus",modules);
        return "module/module-update";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        moduleService.delete(id);
        return "redirect: /module/list.do";
    }
}
