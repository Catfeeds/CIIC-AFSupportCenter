package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by zhangzhiwen on 2018/5/24.
 */
@Data
public class EmployeeBatchBO {
    private List<Long> empTaskIds;

    private String employWay;
    private LocalDate receiveDate;

    private LocalDate employDate;

    private LocalDate openAfDate;

    private  String handleType;

    private String employStyle;

    private String employProperty;

    private String remarkContent;

}
