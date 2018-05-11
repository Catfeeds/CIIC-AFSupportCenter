package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmRemarkBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmRemark;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/1/30.
 */
public interface IAmRemarkService extends IService<AmRemark> {

    PageRows<AmRemarkBO> queryAmRemark(PageInfo pageInfo);

    boolean deleteAmRemark(Long remarkId);

    List<AmRemarkBO> getAmRemakList(AmRemarkBO amRemarkBO);
}
