package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 14:54 2018/1/20
 */
@Data
public class EmpQueryDTO implements Serializable{

    private Integer pageNum;

    private Integer pageSize;

    private String companyId;

    private String employeeId;

    private String employeeName;

    private String idNum;

    private Integer status;

    private String type;

}
