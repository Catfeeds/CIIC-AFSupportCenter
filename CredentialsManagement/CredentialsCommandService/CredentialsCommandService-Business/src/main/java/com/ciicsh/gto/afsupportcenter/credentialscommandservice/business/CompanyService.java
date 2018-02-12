package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Company;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 13:38 2018/2/12
 */
public interface CompanyService extends IService<Company>{

    /**
     * 获取客户列表
     * @param page
     * @param company
     * @return
     */
    List<Company> select(Page page, Company company);
}
