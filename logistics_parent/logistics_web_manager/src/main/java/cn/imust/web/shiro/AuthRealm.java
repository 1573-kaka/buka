package cn.imust.web.shiro;

import cn.imust.domain.module.Module;
import cn.imust.domain.system.User;
import cn.imust.service.module.ModuleService;
import cn.imust.service.system.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取安全数据User对象
        User user = (User) principalCollection.getPrimaryPrincipal();
        //通过User对象拿到菜单
        List<Module> modules = moduleService.findByUser(user);
        //构建set集合，放入菜单
        Set<String> set = new HashSet<>();
        for (Module module : modules) {
            set.add(module.getName());
        }
        //返回AuthorizationInfo里有一个 set集合stringPermissions
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(set);
        return info;
    }

    /**
     * 身份认证
     * @param token
     * @return
     * @throws AuthenticationException 安全数据的集合
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //将token转换为upToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //通过upToken获取用户名，email
        String email = upToken.getUsername();

        //通过email查找用户实体，放入安全数据
        User user = userService.findByEmail(email);

        // Object principal 安全数据，User
        // Object credentials 用户的数据库密码
        // String realmName 可以随意取名，但是我们一般用类名
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;
    }
}
