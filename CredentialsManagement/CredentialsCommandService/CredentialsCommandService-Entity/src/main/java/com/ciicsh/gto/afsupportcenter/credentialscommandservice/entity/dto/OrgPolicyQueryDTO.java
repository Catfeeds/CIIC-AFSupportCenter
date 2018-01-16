package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 17:15 2018/1/16
 */
public class OrgPolicyQueryDTO {

    /**
     * 机构名称
     */
    private String name;
    /**
     * 证件类型(1：积分办理、2：居住证B证、3：留学生落户、4：居转户、5：夫妻分局、6：人才引进)
     */
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OrgPolicyQueryDTO{" +
            "name='" + name + '\'' +
            ", type=" + type +
            '}';
    }
}
