package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.ImportFeedbackDateBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HFImportFeedbackDateService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskTransferService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HFImportFeedbackDateServiceImpl implements HFImportFeedbackDateService {
    @Autowired
    private HfEmpTaskTransferService hfEmpTaskTransferService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    @Override
    public void handleDataList(List<ImportFeedbackDateBO> dataList) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            List<HfEmpTask> hfEmpTaskList = new ArrayList<>();
            for (ImportFeedbackDateBO importFeedbackDateBO : dataList) {
                List<Long> empTaskIds = importFeedbackDateBO.getEmpTaskIds();

                if (CollectionUtils.isNotEmpty(empTaskIds)) {
                    for (Long empTaskId : empTaskIds) {
                        HfEmpTask hfEmpTask = new HfEmpTask();
                        hfEmpTask.setEmpTaskId(empTaskId);
                        hfEmpTask.setFeedbackDate(LocalDate.parse(importFeedbackDateBO.getFeedbackDate(), formatter));
                        hfEmpTask.setModifiedTime(LocalDateTime.now());
                        hfEmpTask.setModifiedBy(UserContext.getUserId());
                        hfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
                        hfEmpTaskList.add(hfEmpTask);
                    }
                }
            }
            hfEmpTaskTransferService.updateBatchById(hfEmpTaskList, 3000);
        }
    }
}
