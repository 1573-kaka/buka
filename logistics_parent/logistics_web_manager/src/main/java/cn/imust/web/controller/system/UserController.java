package cn.imust.web.controller.system;

import cn.imust.common.utils.Encrypt;
import cn.imust.domain.system.Dept;
import cn.imust.domain.system.Role;
import cn.imust.domain.system.User;
import cn.imust.service.system.DeptService;
import cn.imust.service.system.RoleService;
import cn.imust.service.system.UserService;
import cn.imust.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "5")int size){
        PageInfo pageInfo = userService.findAll(companyId,page, size);
        request.setAttribute("page",pageInfo);
        return "system/user/user-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        List<Dept> list = deptService.findAll(companyId);
        request.setAttribute("deptList",list);
        return "system/user/user-add";
    }

    @RequestMapping("/edit")
    public String edit(User user){
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        if (StringUtil.isEmpty(user.getId())){
            userService.save(user);
        } else {
            userService.update(user);
        }
        return "redirect: /system/user/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        User user = userService.findById(id);
        request.setAttribute("user",user);
        List<Dept> list = deptService.findAll(companyId);
        request.setAttribute("deptList",list);
        return "system/user/user-update";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        userService.delete(id);
        return "redirect: /system/user/list.do";
    }

    @RequestMapping("/roleList")
    public String roleList(String id){
        User user = userService.findById(id);
        request.setAttribute("user", user);

        List<Role> roleList = roleService.findAll(companyId);
        request.setAttribute("roleList", roleList);

        List<Role> userRoleList = roleService.findByUserId(id);
        //6、循环list构建一个userRoleStr
        String userRoleStr = "";
        for (Role role : userRoleList) {
            userRoleStr = userRoleStr + role.getId() + ",";
        }
        request.setAttribute("userRoleStr", userRoleStr);
        return "system/user/user-role";
    }

    @RequestMapping("/changeRole")
    public String changeRole(String userid, String[] roleIds){
        roleService.changeRole(userid, roleIds);
        return "redirect: /system/user/list.do";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(String oldPass, String newPass){
        User user =(User) session.getAttribute("loginUser");
        if(!Encrypt.md5(String.valueOf(oldPass), user.getEmail()).equals(user.getPassword())){
            throw new RuntimeException("原密码错误，修改失败");
        }
        String s = Encrypt.md5(String.valueOf(newPass), user.getEmail());
        user.setPassword(s);
        userService.update(user);
        session.removeAttribute("loginUser");
        return "redirect:/login.jsp";
    }
}
