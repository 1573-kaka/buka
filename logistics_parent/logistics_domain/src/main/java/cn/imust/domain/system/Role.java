package cn.imust.domain.system;

import lombok.Data;

import java.io.Serializable;
@Data
public class Role implements Serializable {

    private String id;
    private String name;
    private String remark;
    private Long orderNo;
	private String companyId;
	private String companyName;

}