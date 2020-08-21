package cn.imust.service.weiXinLogin;

import cn.imust.domain.system.User;

public interface WeiXinLoginService {
    User weixin(String code);
}
