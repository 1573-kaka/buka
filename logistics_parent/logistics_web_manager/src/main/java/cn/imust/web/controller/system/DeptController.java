package cn.imust.web.controller.system;

import cn.imust.domain.system.Dept;
import cn.imust.service.system.DeptService;
import cn.imust.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1")int page,
                       @RequestParam(defaultValue = "5")int size){
        PageInfo pageInfo = deptService.findAll(companyId, page, size);
        request.setAttribute("page",pageInfo);
        return "system/dept/dept-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        List<Dept> list = deptService.findAll(companyId);
        request.setAttribute("deptList",list);
        return "system/dept/dept-add";
    }

    @RequestMapping("/edit")
    public String edit(Dept dept){
        dept.setCompanyId(companyId);
        dept.setCompanyName(companyName);
        if (StringUtil.isEmpty(dept.getId())){
            deptService.save(dept);
        } else {
            deptService.update(dept);
        }
        return "redirect: /system/dept/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Dept dept = deptService.findById(id);
        request.setAttribute("dept",dept);
        List<Dept> list = deptService.findAll(companyId);
        request.setAttribute("deptList",list);
        return "system/dept/dept-update";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        deptService.delete(id);
        return "redirect: /system/dept/list.do";
    }
}
