package cn.imust.service.system.impl;

import cn.imust.common.utils.Encrypt;
import cn.imust.dao.module.ModuleDao;
import cn.imust.dao.system.UserDao;
import cn.imust.domain.module.Module;
import cn.imust.domain.system.User;
import cn.imust.service.system.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ModuleDao moduleDao;

    @Override
    public PageInfo findAll(String companyId, int page, int size) {
        PageHelper.startPage(page, size);
        List<User> list = userDao.findAll(companyId);
        return new PageInfo(list);
    }

    //查询所有用户，用于父用户列表选择
    @Override
    public List<User> findAll(String companyId) {
        return userDao.findAll(companyId);
    }

    @Override
    public int save(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(Encrypt.md5(user.getPassword(), user.getEmail()));
        return userDao.save(user);
    }

    //通过id进行查询
    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    //更新用户信息
    @Override
    public int update(User user) {
        user.setPassword(Encrypt.md5(user.getPassword(), user.getEmail()));
        return userDao.update(user);
    }

    @Override
    public int delete(String id) {
        return userDao.delete(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public int updateOpenId(String email, String openId) {
        return userDao.updateOpenId(email,openId);
    }

    @Override
    public User findUserByOpenId(String openId) {
        return userDao.findByOpenid(openId);
    }

    @Override
    public List<Module> findModuleByUser(User user) {
        List<Module> modules=null;
        if(user.getDegree()==0||user.getDegree()==1){
            modules = moduleDao.findByDegree(user.getDegree());
        }else{
            modules=moduleDao.findModuleByUserId(user.getId());
        }
        return modules;
    }
}
