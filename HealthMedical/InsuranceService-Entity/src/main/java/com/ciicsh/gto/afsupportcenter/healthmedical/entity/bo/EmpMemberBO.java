package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

/**
 * 雇员家庭关系--连带人
 *
 * @author xiweizhen
 */
public class EmpMemberBO {
    private Integer empMemberId;
    private String name;

    public Integer getEmpMemberId() {
        return empMemberId;
    }

    public void setEmpMemberId(Integer empMemberId) {
        this.empMemberId = empMemberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmpMemberBO{" +
            "empMemberId=" + empMemberId +
            ", name='" + name + '\'' +
            '}';
    }
}
