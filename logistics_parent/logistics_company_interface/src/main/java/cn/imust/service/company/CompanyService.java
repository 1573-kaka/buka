package cn.imust.service.company;

import cn.imust.domain.company.Company;
import com.github.pagehelper.PageInfo;

import java.util.List;

//service接口
public interface CompanyService {

    //查询所有企业
    List<Company> findAll();

    //保存企业信息
    int save(Company company);

    //通过企业id查询企业信息
    Company findById(String id);

    //修改企业信息
    int update(Company company);

    //根据id删除企业信息
    int delete(String id);

    //通过PageHelper进行分页
    PageInfo findByHelper(int page, int size);

}
