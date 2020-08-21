package cn.imust.web.controller.stat;

import cn.imust.service.stat.StatService;
import cn.imust.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/stat")
public class StatController extends BaseController {

    @Reference
    private StatService statService;

    @RequestMapping(value = "/toCharts")
    public String toCharts(String chartsType){
        return "stat/stat-"+chartsType;
    }


    @RequestMapping(value = "/getFactoryData")
    public @ResponseBody List<Map> getFactoryData(){
        return statService.getFactoryData(companyId);
    }

    @RequestMapping(value = "/getSellData")
    public @ResponseBody List<Map> getSellData(){
        return statService.getSellData(companyId);
    }

    @RequestMapping(value = "/getOnlineData")
    public @ResponseBody List<Map> getOnlineData(){
        return statService.getOnlineData(companyId);
    }

    @RequestMapping(value = "/getMarketData")
    public @ResponseBody List<Map> getMarketData(){
        return statService.getMarketData(companyId);
    }

    @RequestMapping(value = "/getVisitData")
    public @ResponseBody List<Map> getVisitData(){
        return statService.getVisitData(companyId);
    }

    @RequestMapping(value = "/getIpData")
    public @ResponseBody List<Map> getIpOnline(){
        return statService.getIpOnline(companyId);
    }

    @RequestMapping(value = "/getPriceData")
    public @ResponseBody List<Map> getPriceOnline(){
        return statService.getPriceOnline();
    }
}
