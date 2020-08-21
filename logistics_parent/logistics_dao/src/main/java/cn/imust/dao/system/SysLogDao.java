package cn.imust.dao.system;

import cn.imust.domain.system.SysLog;
import java.util.List;

public interface SysLogDao {
    //查询全部
    List<SysLog> findAll(String companyId);

    //添加
    int save(SysLog log);
}