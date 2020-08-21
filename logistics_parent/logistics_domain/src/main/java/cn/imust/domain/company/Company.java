package cn.imust.domain.company;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Company implements Serializable {

    private String id; //VARCHAR(40)ID
    private String name; //varchar(255)公司名称
    private Date expirationDate; //dateTIme到期时间
    private String address; //varchaR(255)公司地址
    private String licenseId; //varchar(255)营业执照-图片
    private String representative; //VARCHaR(255)法人代表
    private String phone; //VARCHAR(255)公司电话
    private String companySize; //varchar(255)公司规模
    private String industry; //vARCHar(255)所属行业
    private String remarks; //varCHAR(255)备注
    private Integer state; //int(2)状态
    private Double balance; //double当前余额
    private String city; //varchar(20)

    private String adminName;
    private String adminEmail;
    private String ishave;
}