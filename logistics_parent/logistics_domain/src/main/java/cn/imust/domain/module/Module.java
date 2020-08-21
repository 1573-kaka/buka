package cn.imust.domain.module;


import lombok.Data;

import java.io.Serializable;
@Data
public class Module implements Serializable {

    private String id;
    private String parentId;
    private String parentName;
    private String name;
    private Integer layerNum;
    private Integer isLeaf;
    private String ico;
    private String cpermission;
    private String curl;

    /** 
     * 0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态
     */ 
    private Integer ctype;

    /** 
     * 1启用0停用
     */ 
    private Integer state;

    /** 
     * 从属关系
     *  0：sass系统内部菜单
     *  1：租用企业菜单
     */ 
    private Integer belong;
    private String cwhich;
    private Integer quoteNum;
    private String remark;
    private Integer orderNo;
}