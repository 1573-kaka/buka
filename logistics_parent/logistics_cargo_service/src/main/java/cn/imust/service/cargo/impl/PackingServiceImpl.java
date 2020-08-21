package cn.imust.service.cargo.impl;

import cn.imust.dao.cargo.ExportDao;
import cn.imust.dao.cargo.PackingDao;
import cn.imust.domain.cargo.Export;
import cn.imust.domain.cargo.ExportExample;
import cn.imust.domain.cargo.Packing;
import cn.imust.domain.cargo.PackingExample;
import cn.imust.service.cargo.PackingService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PackingServiceImpl implements PackingService {
    @Autowired
    private PackingDao packingDao;
    @Autowired
    private ExportDao exportDao;
    @Override
    public Packing findById(String id) {
        Packing packing = packingDao.selectByPrimaryKey(id);
        String[] exportIds = packing.getExportIds().split(",");
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        List<String> list = CollectionUtils.arrayToList(exportIds);
        criteria.andIdIn(list);
        List<Export> exports = exportDao.selectByExample(exportExample);
        packing.setExports(exports);
        return packing;
    }

    @Override
    public void save(Packing packing) {
        packing.setPackingListId(UUID.randomUUID().toString());
        packing.setState(0L);
        packing.setCreateTime(new Date());
        String[] exports=packing.getExportIds().split(",");
        for (String exportid : exports) {
            Export export = new Export();
            export.setState(3);
            export.setId(exportid);
            exportDao.updateByPrimaryKeySelective(export);
        }
        packingDao.insertSelective(packing);
    }

    @Override
    public void update(Packing packing) {
        packingDao.updateByPrimaryKeySelective(packing);
    }

    @Override
    public void delete(String id) {
        Packing packing = packingDao.selectByPrimaryKey(id);
        String[] exports=packing.getExportIds().split(",");
        for (String exportid : exports) {
            Export export = new Export();
            export.setState(2);
            export.setId(exportid);
            exportDao.updateByPrimaryKeySelective(export);
        }
        packingDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(PackingExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Packing> packings = packingDao.selectByExample(example);
        PageInfo<Packing> pageInfo = new PageInfo<>(packings);
        return pageInfo;
    }

    @Override
    public void cancel(String id) {
        Packing packing = packingDao.selectByPrimaryKey(id);
        packing.setState(0L);
        packingDao.updateByPrimaryKey(packing);
    }

    @Override
    public void submit(String id) {
        Packing packing = packingDao.selectByPrimaryKey(id);
        packing.setState(1L);
        packingDao.updateByPrimaryKey(packing);
    }
}
