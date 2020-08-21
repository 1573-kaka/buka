package cn.imust.service.system.impl;

import cn.imust.dao.system.RoleDao;
import cn.imust.domain.system.Role;
import cn.imust.service.system.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    //分页查询所有
    @Override
    public PageInfo findAll(String companyId, int page, int size) {
        PageHelper.startPage(page, size);
        List<Role> list = roleDao.findAll(companyId);
        return new PageInfo(list);
    }

    @Override
    public List<Role> findAll(String companyId) {
        return roleDao.findAll(companyId);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public int save(Role role) {
        role.setId(UUID.randomUUID().toString());
        return roleDao.save(role);
    }

    @Override
    public int update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public int delete(String id) {
        return roleDao.delete(id);
    }

    @Override
    public List<Role> findByUserId(String id) {
        return roleDao.findByUserId(id);
    }

    @Override
    public void changeRole(String userid, String[] roleIds) {
        //通过userid进行删除
        roleDao.deleteByUserId(userid);
        //循环roleIds,写入用户角色中间表
        for (String roleId : roleIds) {
            roleDao.insertUserRole(userid, roleId);
        }
    }
}
