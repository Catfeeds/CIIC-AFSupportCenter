package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpPreInputBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HFImportEmpPreInputService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpPreInputService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpPreInput;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HFImportEmpPreInputServiceImpl implements HFImportEmpPreInputService {
    @Autowired
    HfEmpPreInputService hfEmpPreInputService;

    @Override
    public void handleDataList(List<HfEmpPreInputBO> dataList) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            List<HfEmpPreInput> addList = new ArrayList<>();
            List<HfEmpPreInput> modifyList = new ArrayList<>();

            for (HfEmpPreInputBO hfEmpPreInputBO : dataList) {
                HfEmpPreInput modifyEmpPreInput = hfEmpPreInputBO.getModifyHfEmpPreInput();
                HfEmpPreInput addEmpPreInput = hfEmpPreInputBO.getAddHfEmpPreInput();

                if (modifyEmpPreInput != null) {
                    modifyEmpPreInput.setModifiedBy(UserContext.getUserId());
                    modifyEmpPreInput.setModifiedTime(LocalDateTime.now());
                    modifyList.add(modifyEmpPreInput);
                } else if (addEmpPreInput != null) {
                    addEmpPreInput.setCreatedBy(UserContext.getUserId());
                    addEmpPreInput.setModifiedBy(UserContext.getUserId());
                    addList.add(addEmpPreInput);
                }
            }

            if (addList.size() > 0) {
                hfEmpPreInputService.insertBatch(addList, 3000);
            }
            if (modifyList.size() > 0) {
                hfEmpPreInputService.updateBatchById(modifyList, 3000);
            }
        }
    }
}
