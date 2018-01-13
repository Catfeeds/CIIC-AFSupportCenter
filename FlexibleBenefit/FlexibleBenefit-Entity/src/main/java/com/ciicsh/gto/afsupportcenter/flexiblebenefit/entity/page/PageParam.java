package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.page;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 分页查询参数
 *
 * @author xiweizhen
 */
public class PageParam extends Page {
    /**
     * 参数对象，方便转成其他对象
     */
    private JSONObject jsonObjectParams = new JSONObject();

    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public JSONObject getJsonObjectParams() {
        return jsonObjectParams;
    }

    public void setJsonObjectParams(JSONObject jsonObjectParams) {
        this.jsonObjectParams = jsonObjectParams;
    }
}
