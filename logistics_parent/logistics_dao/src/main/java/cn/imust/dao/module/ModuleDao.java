package cn.imust.dao.module;

import cn.imust.domain.module.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleDao {

    //根据id查询
    Module findById(String moduleId);

    //根据id删除
    int delete(String moduleId);

    //添加用户
    int save(Module module);

    //更新用户
    int update(Module module);

    //查询全部
    List<Module> findAll();

    //通过角色查找模块信息
    List<Module> findModuleByRoleId(String roleId);

    //通过角色删除所有角色的模块信息
    void deleteByRoleId(String roleid);

    //写入角色的新模块信息
    int insertRoleModule(@Param("roleId") String roleid, @Param("moduleId") String moduleId);

    List<Module> findByDegree(Integer belong);

    List<Module> findModuleByUserId(String id);
}