package cn.imust.service.cargo;

import cn.imust.domain.cargo.Goods;
import cn.imust.domain.cargo.GoodsExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodsService {
    Goods findById(String id);

    void save(Goods good);

    void update(Goods good);

    void delete(String id);

    PageInfo findAll(GoodsExample example, int page, int size);

    List<Goods> findByFid(GoodsExample example);
}
