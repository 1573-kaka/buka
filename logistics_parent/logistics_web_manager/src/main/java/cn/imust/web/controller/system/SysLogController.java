package cn.imust.web.controller.system;

import cn.imust.domain.system.SysLog;
import cn.imust.service.system.SysLogService;
import cn.imust.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/system/log")
public class SysLogController extends BaseController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "20")int size){
        PageInfo pageInfo = sysLogService.findAll(companyId, page, size);
        request.setAttribute("page", pageInfo);
        return "system/log/log-list";
    }

    @RequestMapping("/edit")
    public String edit(SysLog sysLog){
        sysLog.setCompanyId(companyId);
        sysLog.setCompanyName(companyName);
        sysLogService.save(sysLog);
        return "redirect: /system/log/list.do";
    }
}
