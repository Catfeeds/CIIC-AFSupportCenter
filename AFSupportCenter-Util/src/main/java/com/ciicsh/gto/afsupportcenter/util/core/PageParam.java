package com.ciicsh.gto.afsupportcenter.util.core;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * package: com.ciicsh.gto.agentcenter.util.page
 * describe: TODO
 * creat_user: cuixiaoguang
 * creat_date: 2017/12/5
 * creat_time: 10:07
 **/
public class PageParam extends Page{
	// 参数对象，方便转成其他对象
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
