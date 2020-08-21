package cn.imust.web.controller.system;

import cn.imust.domain.module.Module;
import cn.imust.domain.system.Role;
import cn.imust.service.module.ModuleService;
import cn.imust.service.system.RoleService;
import cn.imust.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "5")int size){
        PageInfo pageInfo = roleService.findAll(companyId, page, size);
        request.setAttribute("page", pageInfo);
        return "system/role/role-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "system/role/role-add";
    }

    @RequestMapping("/edit")
    public String edit(Role role){
        role.setCompanyId(companyId);
        role.setCompanyName(companyName);
        if (StringUtil.isEmpty(role.getId())){
            roleService.save(role);
        } else {
            roleService.update(role);
        }
        return "redirect: /system/role/list.do";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(String id){
        Role role = roleService.findById(id);
        request.setAttribute("role", role);
        return "system/role/role-update";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        roleService.delete(id);
        return "redirect: /system/role/list.do";
    }

    @RequestMapping(value = "/roleModule", name = "给角色分配菜单，跳转菜单权限页面")
    public String roleModule(String roleid){
        Role role = roleService.findById(roleid);
        request.setAttribute("role", role);
        return "system/role/role-module";
    }

    @RequestMapping("/getZtreeNodes")
    public @ResponseBody List<Map> getZtreeNodes(String roleid){
        //查询所有模块
        List<Module> modules = moduleService.findAll();

        //通过roleId拿到该角色的模块信息
        List<Module> moduleByRoleId = moduleService.findModuleByRoleId(roleid);

        //构建zNodes
        List<Map> zNodes = new ArrayList<Map>();
        for (Module module : modules) {
            Map map = new HashMap();
            map.put("id",module.getId());
            map.put("pId",module.getParentId());
            map.put("name",module.getName());

            //如果该角色有module这个模块，map.put"checked"
            if (moduleByRoleId.contains(module)){
                map.put("checked",true);
            }
            zNodes.add(map);
        }
        return zNodes;
    }

    @RequestMapping(value = "/updateRoleModule",name = "更新角色的新模块信息")
    public String updateRoleModule(String roleid, String moduleIds){
        moduleService.insertRoleModule(roleid, moduleIds);
        return "redirect: /system/role/list.do";
    }
}
