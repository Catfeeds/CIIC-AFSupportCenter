package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 本地社保的雇员任务单 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpTaskServiceImpl extends ServiceImpl<SsEmpTaskMapper, SsEmpTask> implements ISsEmpTaskService {

    @Override
    public PageRows<SsEmpTaskDTO> employeeOperatorQuery(PageInfo pageInfo) {
        SsEmpTaskDTO dto = pageInfo.toJavaObject(SsEmpTaskDTO.class);
        handleTaskCategory(dto);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.employeeOperatorQuery(dto));
    }

    /**
     * 处理雇员任务单数据
     *
     * @param dto
     */
    private void handleTaskCategory(SsEmpTaskDTO dto) {
        // 操作类型，用于区分操作
        Integer operatorType = dto.getOperatorType();

        // 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
        {
            // 任务处理类型
            Integer taskCategory = dto.getTaskCategory();
            Integer[] taskCategories;
            if (taskCategory == null) {
                switch (operatorType) {
                    // 日常操作
                    case 1:
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 7};
                        break;
                    // 特殊操作
                    case 2:
                        taskCategories = new Integer[]{6, 8, 9};
                        break;
                    default:// 日常操作
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 7};

                }
            } else {
                taskCategories = new Integer[]{taskCategory};
            }

            dto.setTaskCategories(taskCategories);
            dto.setTaskCategory(null);
        }

        // 任务处理状态:、1 本月未处理、2 下月未处理、3 处理中、4 已完成、5 批退，其中未处理需要特别处理
        {
            // 任务处理状态
            Integer taskStatus = dto.getTaskStatus();
            Integer[] taskStatuses;
            if (taskStatus == null) {
                switch (operatorType) {
                    // 本月未处理
                    case 1:
                        taskStatuses = new Integer[]{taskStatus};
                        break;
                    // 下月未处理
                    case 2:
                        taskStatuses = new Integer[]{taskStatus};
                        break;
                    case 3:
                    case 4:
                    case 5:
                        taskStatuses = new Integer[]{3, 4, 5};
                        break;
                    default:// 未处理
                        taskStatuses = new Integer[]{1, 2};

                }
            } else {
                taskStatuses = new Integer[]{taskStatus};
            }
            dto.setTaskStatuses(taskStatuses);
        }

    }

}
