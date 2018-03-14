package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfBujiaoListMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfBujiaoList;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsRequestDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsResposeDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.SSPItemDTO;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
//import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
//import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeQueryDTO;
import com.ciicsh.gto.basicdataservice.api.dto.EmptyDicItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class TestController  extends BasicController<CommonApiUtils> {

//    @PostConstruct
    private void testInit() {
        try {
//            DicItemDTO dto = business.selectByDicItemId(DictUtil.DICT_ID_FUND);
//            EmployeeQueryDTO eto = new EmployeeQueryDTO();
//            eto.setEmployeeId("267584041510661");
//            eto.setBusinessType(1);
//            com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeInfoDTO> result0 = business.getEmployeeInfo(eto);

//            GetSSPItemsRequestDTO getSSPItemsRequestDTO = new GetSSPItemsRequestDTO();
//            getSSPItemsRequestDTO.setSsPolicyId("SBZ1800020");
//            getSSPItemsRequestDTO.setPayAccountType(1);
//            getSSPItemsRequestDTO.setEffectiveMonth("201805");
//            com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult<GetSSPItemsResposeDTO> result = business.getRoundingType(getSSPItemsRequestDTO);
//            List<SSPItemDTO> list = result.getData().getItems();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
