package cn.imust.service.system;

import cn.imust.domain.system.SysLog;
import com.github.pagehelper.PageInfo;

public interface SysLogService {
    PageInfo findAll(String companyId, int page, int size);

    int save(SysLog sysLog);
}
