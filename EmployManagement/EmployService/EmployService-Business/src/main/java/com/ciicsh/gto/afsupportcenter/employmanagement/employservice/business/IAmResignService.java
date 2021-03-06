package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.TerminateDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmPostBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmResignBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.resignSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpDispatchExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpExplainExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工表 服务类
 * </p>
 */

@Service
public interface IAmResignService extends IService<AmResign> {

    PageRows<AmResignBO> queryAmResign(PageInfo pageInfo);

    List<AmResignBO> taskCount(PageInfo pageInfo);

    List<AmResignBO>  queryAmResignDetail(Map<String, Object> param);

    List<resignSearchExportOpt> queryAmResignList(AmResignBO amResignBO);

    AmResign  saveAmResign(AmResignBO bo);

    Boolean saveAmSend(AmPostBO amPostBO);

    Map<String,Object> batchSaveResign(AmResignBO bo);

    List<AmResignBO> queryResignIds(AmResignBO amResignBO);

    Map<String,Object> batchCheck(AmResignBO amResignBO);

    List<AmResignBO>  jobCount(AmResignBO amEmpTaskBO);

    TerminateDTO getResignByEmpCompanyId(String empCompanyId);

    List<AmEmpExplainExportPageDTO> queryExportOptExplain(AmResignBO amResignBO, Integer employCode);

    List<AmEmpExplainExportPageDTO> queryExportOptExplain(AmResignBO amResignBO);

    List<AmEmpDispatchExportPageDTO> queryExportOptReturn(AmResignBO bo, Integer employCode, Integer pageSize);

    List<AmEmpDispatchExportPageDTO> queryExportOptReturn(AmResignBO bo,Integer pageSize);

    AmResign  saveAmReturn(AmResignBO bo);
}
