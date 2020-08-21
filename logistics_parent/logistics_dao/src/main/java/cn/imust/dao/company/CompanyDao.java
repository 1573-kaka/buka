package cn.imust.dao.company;

import cn.imust.domain.company.Company;

import java.util.List;

public interface CompanyDao {

    List<Company> findAll();

    int save(Company company);

    Company findById(String id);

    int update(Company company);

    int delete(String id);
}
