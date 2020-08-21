package cn.imust.service.system;

import cn.imust.domain.module.Module;
import cn.imust.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    //分页，查哪个企业，页码，当前页条数
    PageInfo findAll(String companyId, int page, int size);

    //查询所有部门，用于父部门列表选择
    List<User> findAll(String companyId);

    //保存部门信息
    int save(User user);

    //通过id进行查询
    User findById(String id);

    //更新用户信息
    int update(User user);

    //通过id进行删除
    int delete(String id);

    //通过email查询用户实体类
    User findByEmail(String email);

    User findUserByOpenId(String openId);

    int updateOpenId(String email,String openId);

    List<Module> findModuleByUser(User user);
}
