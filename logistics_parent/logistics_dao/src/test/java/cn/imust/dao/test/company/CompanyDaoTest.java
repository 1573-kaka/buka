package cn.imust.dao.test.company;

import cn.imust.dao.company.CompanyDao;
import cn.imust.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-*.xml")
public class CompanyDaoTest {
    @Autowired
    private CompanyDao companyDao;

    @Test
    public void testFindAll(){
        List<Company> list = companyDao.findAll();
        System.out.println(list.toString());
    }

}
