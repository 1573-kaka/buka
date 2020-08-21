package cn.imust.service.stat.impl;

import cn.imust.dao.stat.StatDao;
import cn.imust.service.stat.StatService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatDao statDao;


    @Override
    public List<Map> getFactoryData(String companyId) {
        return statDao.getFactoryData(companyId);
    }

    @Override
    public List<Map> getSellData(String companyId) {
        return statDao.getSellData(companyId);
    }

    @Override
    public List<Map> getOnlineData(String companyId) {
        return statDao.getOnlineData(companyId);
    }

    @Override
    public List<Map> getMarketData(String companyId) {
        return statDao.getMarketData(companyId);
    }

    @Override
    public List<Map> getVisitData(String companyId) {
        return statDao.getVisitData(companyId);
    }

    @Override
    public List<Map> getIpOnline(String companyId) {
        return statDao.getIpOnline(companyId);
    }

    @Override
    public List<Map> getPriceOnline() {
        return statDao.getPriceOnline();
    }
}
