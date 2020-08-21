package cn.imust.service.system.impl;

import cn.imust.dao.system.DeptDao;
import cn.imust.domain.company.Company;
import cn.imust.domain.system.Dept;
import cn.imust.service.system.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public PageInfo findAll(String companyId, int page, int size) {
        PageHelper.startPage(page, size);
        List<Dept> list = deptDao.findAll(companyId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int save(Dept dept) {
        dept.setId(UUID.randomUUID().toString());
        return deptDao.save(dept);
    }

    @Override
    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    @Override
    public int update(Dept dept) {
        return deptDao.update(dept);
    }

    @Override
    public int delete(String id) {
        return deptDao.delete(id);
    }

    @Override
    public List<Dept> findAll(String companyId) {
        return deptDao.findAll(companyId);
    }
}
