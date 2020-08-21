package cn.imust.service.system;

import cn.imust.domain.system.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeptService {
    //分页查询某个企业信息
    PageInfo findAll(String companyId, int page, int size);

    int save(Dept dept);

    Dept findById(String id);

    int update(Dept dept);

    int delete(String id);

    //查询所有部门，用于父部门
    List<Dept> findAll(String companyId);
}
