package com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 13:43 2018/2/12
 */
@Repository
public interface CompanyMapper extends BaseMapper<Company>{
    /**
     * 查询客户列表
     * @param page
     * @param company
     * @return
     */
    List<Company> select(Page page, Company company);
}
