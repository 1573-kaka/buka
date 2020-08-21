package cn.imust.service.system.impl;

import cn.imust.dao.system.SysLogDao;
import cn.imust.domain.system.SysLog;
import cn.imust.service.system.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public PageInfo findAll(String companyId, int page, int size) {
        PageHelper.startPage(page, size);
        List<SysLog> list = sysLogDao.findAll(companyId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int save(SysLog sysLog) {
        sysLog.setId(UUID.randomUUID().toString());
        return sysLogDao.save(sysLog);
    }
}
