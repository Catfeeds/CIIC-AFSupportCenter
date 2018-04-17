package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpEmployeeBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmTaskParamBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmCompanySetService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpEmployee;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmEmpEmployeeMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.AmEmpEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 * 雇员信息表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2018-04-09
 */

@Service
public class AmEmpEmployeeServiceImpl extends ServiceImpl<AmEmpEmployeeMapper, AmEmpEmployee> implements AmEmpEmployeeService {

    @Autowired
    private IAmCompanySetService amCompanySetService;


    @Override
    public AmEmpEmployeeBO queryAmEmployeeByTaskId(Long empTaskId) {

        List<AmEmpEmployeeBO> list = baseMapper.queryAmEmployeeByTaskId(empTaskId);

        if(list!=null&&list.size()>0){
            AmEmpEmployeeBO amEmpEmployeeBO = list.get(0);

            if(amEmpEmployeeBO.getLaborEndDate()==null)
            {
                amEmpEmployeeBO.setIsUnlimitedContract("是");
            }else{
                amEmpEmployeeBO.setIsUnlimitedContract("否");

                if(amEmpEmployeeBO.getLaborStartDate()!=null){
                    String d = ReasonUtil.getCondemnationYears(amEmpEmployeeBO.getLaborStartDate(),amEmpEmployeeBO.getLaborEndDate());
                    amEmpEmployeeBO.setSendCondemnationYears(d);
                }
            }

            amEmpEmployeeBO.setSex(amEmpEmployeeBO.getGender()==0?"女":"男");


            AmCompanySetBO amCompanySetBO = new AmCompanySetBO();
            amCompanySetBO.setCompanyId(amEmpEmployeeBO.getCompanyId());
            AmCompanySetBO amCompanySetBO1 = amCompanySetService.queryAmCompanySet(amCompanySetBO);
            if(amCompanySetBO1!=null)
            {
                amEmpEmployeeBO.setEmploySpecial(ReasonUtil.removeMark(amCompanySetBO1.getEmploySpecial()));
                amEmpEmployeeBO.setKeyType(amCompanySetBO1.getKeyType());
                amEmpEmployeeBO.setKeyCode(amCompanySetBO1.getKeyCode());
                amEmpEmployeeBO.setKeyPwd(amCompanySetBO1.getKeyPwd());
                amEmpEmployeeBO.setKeyStatus(amCompanySetBO1.getKeyStatus());
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            amEmpEmployeeBO.setLaborStartDateStr(amEmpEmployeeBO.getLaborStartDate()==null?"":sdf.format(amEmpEmployeeBO.getLaborStartDate()));
            amEmpEmployeeBO.setLaborEndDateStr(amEmpEmployeeBO.getLaborEndDate()==null?"":sdf.format(amEmpEmployeeBO.getLaborEndDate()));
            amEmpEmployeeBO.setFirstInCompanyDateStr(amEmpEmployeeBO.getFirstInCompanyDate()==null?"":sdf.format(amEmpEmployeeBO.getFirstInCompanyDate()));
            amEmpEmployeeBO.setFirstInDateStr(amEmpEmployeeBO.getFirstInDate()==null?"":sdf.format(amEmpEmployeeBO.getFirstInDate()));

            return  amEmpEmployeeBO;
        }
        return null;
    }

    @Override
    public AmEmpEmployeeBO queryAmEmployee(AmTaskParamBO amTaskParamBO) {
        List<AmEmpEmployeeBO> list = baseMapper.queryAmEmployee(amTaskParamBO);

        if(list!=null&&list.size()>0){
            AmEmpEmployeeBO amEmpEmployeeBO = list.get(0);

            if(amEmpEmployeeBO.getLaborEndDate()==null)
            {
                amEmpEmployeeBO.setIsUnlimitedContract("是");
            }else{
                amEmpEmployeeBO.setIsUnlimitedContract("否");

                if(amEmpEmployeeBO.getLaborStartDate()!=null){
                    String d = ReasonUtil.getCondemnationYears(amEmpEmployeeBO.getLaborStartDate(),amEmpEmployeeBO.getLaborEndDate());
                    amEmpEmployeeBO.setSendCondemnationYears(d);
                }
            }

            amEmpEmployeeBO.setSex(amEmpEmployeeBO.getGender()==0?"女":"男");


            AmCompanySetBO amCompanySetBO = new AmCompanySetBO();
            amCompanySetBO.setCompanyId(amEmpEmployeeBO.getCompanyId());
            AmCompanySetBO amCompanySetBO1 = amCompanySetService.queryAmCompanySet(amCompanySetBO);
            if(amCompanySetBO1!=null)
            {
                amEmpEmployeeBO.setEmploySpecial(ReasonUtil.removeMark(amCompanySetBO1.getEmploySpecial()));
                amEmpEmployeeBO.setKeyType(amCompanySetBO1.getKeyType());
                amEmpEmployeeBO.setKeyCode(amCompanySetBO1.getKeyCode());
                amEmpEmployeeBO.setKeyPwd(amCompanySetBO1.getKeyPwd());
                amEmpEmployeeBO.setKeyStatus(amCompanySetBO1.getKeyStatus());
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            amEmpEmployeeBO.setLaborStartDateStr(amEmpEmployeeBO.getLaborStartDate()==null?"":sdf.format(amEmpEmployeeBO.getLaborStartDate()));
            amEmpEmployeeBO.setLaborEndDateStr(amEmpEmployeeBO.getLaborEndDate()==null?"":sdf.format(amEmpEmployeeBO.getLaborEndDate()));
            amEmpEmployeeBO.setFirstInCompanyDateStr(amEmpEmployeeBO.getFirstInCompanyDate()==null?"":sdf.format(amEmpEmployeeBO.getFirstInCompanyDate()));
            amEmpEmployeeBO.setFirstInDateStr(amEmpEmployeeBO.getFirstInDate()==null?"":sdf.format(amEmpEmployeeBO.getFirstInDate()));

            return  amEmpEmployeeBO;
        }
        return null;
    }
}