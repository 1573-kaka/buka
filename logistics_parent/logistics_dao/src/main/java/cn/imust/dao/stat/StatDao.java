package cn.imust.dao.stat;

import java.util.List;
import java.util.Map;

public interface StatDao {

    //厂家销售数据
    List<Map> getFactoryData(String companyId);

    //商品销售数据
    List<Map> getSellData(String companyId);

    //在线人数
    List<Map> getOnlineData(String companyId);

    //市场价格统计
    List<Map> getMarketData(String companyId);

    //用户登录情况
    List<Map> getVisitData(String companyId);

    List<Map> getIpOnline(String companyId);

    List<Map> getPriceOnline();
}
