package cn.imust.service.system;

import cn.imust.domain.system.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    //分页，查哪个企业，页码，当前页条数
    PageInfo findAll(String companyId, int page, int size);

    //查询所有角色，用于父角色列表选择
    List<Role> findAll(String companyId);

    //保存角色信息
    int save(Role Role);

    //通过id进行查询
    Role findById(String id);

    //更新用户信息
    int update(Role Role);

    //通过id进行删除
    int delete(String id);

    //通过用户id查询用户所有角色
    List<Role> findByUserId(String id);

    //更新用户新角色
    void changeRole(String userid, String[] roleIds);


}
