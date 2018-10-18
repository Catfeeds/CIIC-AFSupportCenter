package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpEmployeeBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmTaskParamBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmEmpEmployeeMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpEmployee;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployeChange;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private IAmEmployeChangeService  amEmployeChangeService;

    @Autowired
    private IAmEmploymentService amEmploymentService;

    @Override
    public AmEmpEmployeeBO queryAmEmployeeByTaskId(Long empTaskId,Integer type) {

        List<AmEmpEmployeeBO> list = baseMapper.queryAmEmployeeByTaskId(empTaskId);

        if(list!=null&&list.size()>0){
            AmEmpEmployeeBO amEmpEmployeeBO = list.get(0);

            if(amEmpEmployeeBO.getLaborEndDate()==null)
            {
                amEmpEmployeeBO.setIsUnlimitedContract("是");
                amEmpEmployeeBO.setSendCondemnationYears("无固定期限合同");
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
                String special="";
                try {
                    if(type==0)
                    {
                        special = amCompanySetBO1.getEmploySpecial()==null?"":amCompanySetBO1.getEmploySpecial();
                    }else if(type==1){
                        special = amCompanySetBO1.getRefuseSpecial()==null?"":amCompanySetBO1.getRefuseSpecial();
                    }else if(type==2){
                        special = amCompanySetBO1.getEmploySpecial()==null?"":amCompanySetBO1.getEmploySpecial()+amCompanySetBO1.getRefuseSpecial()==null?"":amCompanySetBO1.getRefuseSpecial()+amCompanySetBO1.getArchiveSpecial()==null?"":amCompanySetBO1.getArchiveSpecial();
                    }
                    if(!StringUtil.isEmpty(special))
                    {
                        String str[] = special.split(",");
                        amEmpEmployeeBO.setSpeacilStr(Arrays.asList(str));
                    }
                    amEmpEmployeeBO.setEmploySpecial(special);
                } catch (Exception e) {

                }

                amEmpEmployeeBO.setPhone(amCompanySetBO1.getPhone());
                amEmpEmployeeBO.setPostCode(amCompanySetBO1.getPostCode());
                amEmpEmployeeBO.setRecipient(amCompanySetBO1.getRecipient());
                amEmpEmployeeBO.setMailAdress(amCompanySetBO1.getMailAdress());

                amEmpEmployeeBO.setMailContinue(amCompanySetBO1.getMailContinue());
            }

//            AmEmployeChange amEmployeChange = amEmployeChangeService.getEmployeeChange(empTaskId);
//            if(null!=amEmployeChange)
//            {
//                Map<String,Object> param = new HashMap<>();
//                param.put("empTaskId",empTaskId);
//                List<AmEmploymentBO> amEmploymentBOList = amEmploymentService.queryAmEmployment(param);
//                //如果重在用工信息，则提醒改动在页面
//                if(null!=amEmploymentBOList&&amEmploymentBOList.size()>0)
//                {
//                    amEmpEmployeeBO.setIsChange(1);
//                }
//                if("all".equals(amEmployeChange.getType()))
//                {
//                    amEmpEmployeeBO.setInDate(amEmployeChange.getInDate());
//                    amEmpEmployeeBO.setLaborStartDate(amEmployeChange.getLaborStartDate());
//                    amEmpEmployeeBO.setLaborEndDate(amEmployeChange.getLaborEndDate());
//                }else if("laborDate".equals(amEmployeChange.getType())){
//                    amEmpEmployeeBO.setLaborStartDate(amEmployeChange.getLaborStartDate());
//                    amEmpEmployeeBO.setLaborEndDate(amEmployeChange.getLaborEndDate());
//                }else if("inDate".equals(amEmployeChange.getType())){
//                    amEmpEmployeeBO.setInDate(amEmployeChange.getInDate());
//                }
//            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            amEmpEmployeeBO.setLaborStartDateStr(amEmpEmployeeBO.getLaborStartDate()==null?"":sdf.format(amEmpEmployeeBO.getLaborStartDate()));
            amEmpEmployeeBO.setLaborEndDateStr(amEmpEmployeeBO.getLaborEndDate()==null?"":sdf.format(amEmpEmployeeBO.getLaborEndDate()));
            amEmpEmployeeBO.setFirstInCompanyDateStr(amEmpEmployeeBO.getFirstInCompanyDate()==null?"":sdf.format(amEmpEmployeeBO.getFirstInCompanyDate()));
            amEmpEmployeeBO.setFirstInDateStr(amEmpEmployeeBO.getFirstInDate()==null?"":sdf.format(amEmpEmployeeBO.getFirstInDate()));
            if(1==type||2==type){
                amEmpEmployeeBO.setShow(true);
            }

            return  amEmpEmployeeBO;
        }
        return null;
    }


}
