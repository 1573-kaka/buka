package cn.imust.service.cargo;

import cn.imust.domain.cargo.Packing;
import cn.imust.domain.cargo.PackingExample;
import com.github.pagehelper.PageInfo;

public interface PackingService {
    Packing findById(String id);

    void save(Packing packing);

    void update(Packing packing);

    void delete(String id);

    PageInfo findAll(PackingExample example, int page, int size);

    void cancel(String id);

    void submit(String id);
}
