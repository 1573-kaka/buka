package cn.imust.dao.test.system.dept;

import cn.imust.dao.system.DeptDao;
import cn.imust.domain.system.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-*.xml")
public class DeptDaoTest {
    @Autowired
    private DeptDao deptDao;

    @Test
    public void testFindAll(){
        List<Dept> list = deptDao.findAll("1");
        System.out.println(list.toString());
    }
}
