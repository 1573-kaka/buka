package cn.imust.dao.system;

import cn.imust.domain.system.Dept;

import java.util.List;

public interface DeptDao {

    //查询所有部门信息
    List<Dept> findAll(String companyId);

    //保存部门信息
    int save(Dept dept);

    //通过id进行查询
    Dept findById(String id);

    //更新部门信息
    int update(Dept dept);

    //删除
    int delete(String id);
}
