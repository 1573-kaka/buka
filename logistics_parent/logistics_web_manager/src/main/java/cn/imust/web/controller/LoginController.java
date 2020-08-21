package cn.imust.web.controller;


import cn.imust.domain.feedback.Message;
import cn.imust.domain.feedback.MessageExample;
import cn.imust.domain.module.Module;
import cn.imust.domain.system.User;
import cn.imust.service.module.ModuleService;
import cn.imust.service.system.MessageService;
import cn.imust.service.system.UserService;
import cn.imust.service.weiXinLogin.WeiXinLoginService;
import cn.imust.web.shiro.EasyTypeToken;
import com.github.pagehelper.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Enumeration;
import java.util.List;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

	/*@RequestMapping("/login")
	public String login(String email,String password) {
	    //1.判断传入的参数
        if (StringUtil.isEmpty(email) || StringUtil.isEmpty(password)){
            //2.如果email或者password有一项没输，跳转登录页面并给予友情提示
            request.setAttribute("error", "请输入用户名或密码");
            return "forward:login.jsp";
        }

        //3.通过email查询用户实体类
        User user = userService.findByEmail(email);

        //4.查到了user =! null，数据库密码 == 输入密码
        if (user != null && user.getPassword().equals(password)){
            //通过用户，查找用户对应的模块
            List<Module> modules = moduleService.findByUser(user);
            session.setAttribute("modules", modules);
            //5.写入session，跳转页面
            session.setAttribute("loginUser", user);
            return "home/main";
        }

        //6.如果没有查到user或者密码不对，跳转到登录页面并给予友情提示
        request.setAttribute("error", "请输入正确用户名或密码");
		return "forward:login.jsp";
	}*/

    //通过shiro进行登录
    @RequestMapping("/login")
    public String login(String email,String password) {
        try {
            //1、创建subject
            Subject subject = SecurityUtils.getSubject();
            //1.1、通过email,password创建token
            UsernamePasswordToken upToken = new UsernamePasswordToken(email, password);
            //2、利用subject.login方法进行登录
            subject.login(upToken);  //两个方法：1、身份认证；2、密码比较

            //3、通过subject取到安全数据（user对象存入安全数据）
            User user = (User) subject.getPrincipal();

            if (user!=null){
                //4、放入到session
                session.setAttribute("loginUser", user);
                List<Module> moduleList = moduleService.findByUser(user);
                session.setAttribute("modules", moduleList);

                Enumeration attributeNames = session.getAttributeNames();
                while (attributeNames.hasMoreElements()){
                    String string = (String) attributeNames.nextElement();
                    System.out.println("-----------------------"+string);
                    System.out.println(session.getAttribute(string));
                }

                //5、进行登录
                return "home/main";
            }else {
                request.setAttribute("error", "用户不存在！");
                return "forward:login.jsp";
            }
        }catch (Exception e){
            request.setAttribute("error", "用户名或密码不正确！");
            return "forward:login.jsp";
        }
    }

    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
        /*session.removeAttribute("loginUser");
        session.removeAttribute("modules");*/
        SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home(){
	    return "home/home";
    }

}
