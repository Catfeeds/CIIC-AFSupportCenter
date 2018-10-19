package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.bo;

import lombok.Data;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:47 2018/9/27
 */
@Data
public class TaskPrintBO {

    private String credentialsType;

    private String credentialsDealType;

    private String credentialsTypeName;

    private String applicantName;

    private String employeeId;

    private Integer count;

    private List<Material> materialList;

    @Data
    public class Material {

        private Long materialId;

        private String materialName;
    }
}
