package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.dto.emptask;

import java.util.List;

/**
 * 批退 请求参数
 */
public class RejectionParamDTO {

    // 批退 id 列表
    private List<Long> ids;
    // 批退备注
    private String remark;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
