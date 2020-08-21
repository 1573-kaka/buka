package cn.imust.service.company.impl;

import cn.imust.common.utils.Encrypt;
import cn.imust.dao.company.CompanyDao;
import cn.imust.dao.system.UserDao;
import cn.imust.domain.company.Company;
import cn.imust.service.company.CompanyService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UserDao userDao;

    //查询所有企业
    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public int save(Company company) {
        company.setId(UUID.randomUUID().toString());
        return companyDao.save(company);
    }

    @Override
    public Company findById(String id) {
        return companyDao.findById(id);
    }

    @Override
    public int update(Company company) {
        return companyDao.update(company);
    }

    @Override
    public int delete(String id) {
        return companyDao.delete(id);
    }

    @Override
    public PageInfo findByHelper(int page, int size) {
        PageHelper.startPage(page, size);
        List<Company> list = companyDao.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /*@Override
    public void buildAdmin(String id) {
        Company company = companyDao.findById(id);
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(company.getAdminEmail());
        user.setUserName(company.getAdminName());
        user.setStation("企业管理员");
        user.setPassword(Encrypt.md5("123456",company.getAdminEmail()));
        user.setState(1);
        user.setCompanyId(company.getId());
        user.setCompanyName(company.getName());
        user.setGender("0");
        user.setTelephone(company.getPhone());
        user.setDegree(1);
        userDao.save(user);
        company.setIshave("1");
        companyDao.update(company);
        String content="尊敬的"+company.getAdminName()+"先生（女士）,欢迎使用平头物流管理系统,我们以为你注册了企业管理员,用户名为"+company.getAdminEmail()+",密码为:123456";
        amqpTemplate.convertAndSend("build.admin",content);
    }*/

}