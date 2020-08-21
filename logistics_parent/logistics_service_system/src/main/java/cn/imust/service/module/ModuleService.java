package cn.imust.service.module;

import cn.imust.domain.module.Module;
import cn.imust.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ModuleService {
    //分页查询所有
    PageInfo findAll(int page, int size);

    //根据id进行查询
    Module findById(String id);

    //保存
    int save(Module module);

    //更新
    int update(Module module);

    //删除
    int delete(String id);

    //查询所有不分页
    List<Module> findAll();

    //通过角色查找模块信息
    List<Module> findModuleByRoleId(String roleId);

    //更新角色的新模块信息
    void insertRoleModule(String roleid, String moduleIds);

    //通过用户查询用户对应的模块
    List<Module> findByUser(User user);
}
