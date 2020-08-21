package cn.imust.service.weiXinLogin.impl;

import cn.imust.common.utils.HttpUtils;
import cn.imust.dao.system.UserDao;
import cn.imust.domain.system.User;
import cn.imust.service.weiXinLogin.WeiXinLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeiXinLoginServiceImpl implements WeiXinLoginService {
    private static final String APP_ID="wx3bdb1192c22883f3";
    private static final String SECRET="db9d6b88821df403e5ff11742e799105";
    private static final String ACCESSTOKENURL="https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String WXINFOURL="https://api.weixin.qq.com/sns/userinfo";
    @Autowired
    private UserDao userDao;

    @Override
    public User weixin(String code) {
        User user = null;
        //1.根据code获取access_token和openId
        String atUtl = ACCESSTOKENURL + "?code="+code+"&appid="+APP_ID+"&secret="+SECRET+"&grant_type=authorization_code";
        System.out.println(atUtl);
        Map<String, Object> map1 = HttpUtils.sendGet(atUtl);
        Object access_token = map1.get("access_token");
        Object openid = map1.get("openid").toString();
        if(access_token == null && openid == null) {
            return user;
        }

        //2.根据openId判断用户是否存在
        user = userDao.findByOpenid(openid.toString());
        if(user != null) {
            System.out.println("返回数据库中的用户对象");
            //3.如果用户存在返回用户信息
            return user;
        }else{
            System.out.println("查询微信数据库");
            //4.如果用户不存在，根据access_token和openId获取微信用户信息
            String wxurl = WXINFOURL + "?access_token=" + access_token +"&openid="+openid;
            Map<String, Object> map2 = HttpUtils.sendGet(wxurl);
            Object nickname = map2.get("nickname");
            Object headimgurl = map2.get("headimgurl");
            if(nickname == null || headimgurl == null) {
                return user;
            }
            //5.将微信用户信息保存到数据库，返回用户数据
            user = userDao.findById("002108e2-9a10-4510-9683-8d8fd1d374ef");
        }
        return user;
    }
}
