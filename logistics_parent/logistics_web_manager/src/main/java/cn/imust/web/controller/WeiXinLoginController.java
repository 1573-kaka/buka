package cn.imust.web.controller;

import cn.imust.common.utils.Encrypt;
import cn.imust.common.utils.UtilFuns;
import cn.imust.domain.feedback.Message;
import cn.imust.domain.feedback.MessageExample;
import cn.imust.domain.module.Module;
import cn.imust.domain.system.User;
import cn.imust.service.system.MessageService;
import cn.imust.service.system.UserService;
import cn.imust.service.weiXinLogin.WeiXinLoginService;
import cn.imust.web.shiro.EasyTypeToken;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class WeiXinLoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private WeiXinLoginService weiXinLoginService;

    @RequestMapping("/weixinlogin")
    public String weixinlogin(Model model){
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?";
        url += "appid=wx3bdb1192c22883f3";
        url += "&secret=db9d6b88821df403e5ff11742e799105";
        url += "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = this.httpGet(url);
        String openId = jsonObject.getString("openid");
        if (session.getAttribute("openid")!=null) {
            openId=(String)session.getAttribute("openid");
        }
        User user = userService.findUserByOpenId(openId);
        if (user!=null)
        {
            session.setAttribute("openid",openId);
            EasyTypeToken easyTypeToken=new EasyTypeToken(user.getEmail());
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(easyTypeToken);
                List<Module> moduleByUserId = userService.findModuleByUser(user);
                if (user.getUserName().equals("zhangsan"))
                {
                    MessageExample messageExample=new MessageExample();
                    MessageExample.Criteria criteria = messageExample.createCriteria();
                    criteria.andStateEqualTo(0);
                    List<Message> messages = messageService.selectByExampleWithBLOBs(messageExample);
                    model.addAttribute("messageSize",messages.size());
                    model.addAttribute("message",messages);
                }
                session.setAttribute("modules",moduleByUserId);
                session.setAttribute("loginUser",user);
                return "home/main";
            } catch (AuthenticationException e) {
                model.addAttribute("error","用户名或者密码错误");
                return "forward:login.jsp";
            }
        }
        else
        {
            model.addAttribute("error","请绑定一个账号到该微信");
            model.addAttribute("openId",openId);
            return "forward:/BDWeChat.jsp";
        }
    }
    @RequestMapping("/login/BDWeChat")
    public String BDWeChat(String email, String password, String openId, Model model)
    {
        if (UtilFuns.isEmpty(email)||UtilFuns.isEmpty(password))
        {
            model.addAttribute("openId",openId);
            model.addAttribute("error","请输入用户名和密码");
            return "forward:/BDWeChat.jsp";
        }
        User user = userService.findByEmail(email);
        if (user!=null)
        {
            if (user.getPassword().equals(Encrypt.md5(password,email)))
            {
                user.setOpenid(openId);
                userService.updateOpenId(email, openId);
                model.addAttribute("error","绑定成功请重新扫码登录");
                return "forward:/login.jsp";
            }
        }
        model.addAttribute("openId",openId);
        model.addAttribute("error","用户名或者密码错误");
        return "forward:/BDWeChat.jsp";
    }

    public JSONObject httpGet(String url) {
        JSONObject jsonResult = null;
        try {
            // DefaultHttpClient client = new DefaultHttpClient();
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            // HttpResponse response = client.execute(request);
            CloseableHttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity(),"UTF-8"); //此处设定编码格式
                jsonResult = JSON.parseObject(strResult);
            } else {
                System.out.println("请求出错");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
