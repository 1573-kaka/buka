package cn.imust.web.shiro;

import cn.imust.common.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * 密码比较方法 -- 密码比较器
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //转化token，取用户输入密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //upToken.getPassword()是一个数组，转化为String
        String userPassword = String.valueOf(upToken.getPassword());
        String email = upToken.getUsername();

        //加密
        String md5Password = Encrypt.md5(userPassword,email);

        //通过AuthenticationInfo，取数据库密码
        String dbPassword = String.valueOf(info.getCredentials());
        //比较俩个密码
        return md5Password.equals(dbPassword);
    }
}
