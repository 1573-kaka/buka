package cn.imust.service.cargo;

import cn.imust.domain.cargo.ExportProduct;
import cn.imust.domain.cargo.ExportProductExample;

import java.util.List;

public interface ExportProductService {

	ExportProduct findById(String id);

	void save(ExportProduct exportProduct);

	void update(ExportProduct exportProduct);

	void delete(String id);

	List<ExportProduct> findAll(ExportProductExample example);

    List<ExportProduct> findByExportId(String id);
}
