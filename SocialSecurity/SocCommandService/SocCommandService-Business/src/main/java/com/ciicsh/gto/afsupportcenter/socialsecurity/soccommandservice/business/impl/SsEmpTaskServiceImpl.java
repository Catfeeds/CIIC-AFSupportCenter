package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public PageRows<SsEmpTaskBO> employeeOperatorQuery(PageInfo pageInfo) {
        SsEmpTaskBO dto = pageInfo.toJavaObject(SsEmpTaskBO.class);
        handleTaskCategory(dto);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.employeeOperatorQuery(dto));
    }

    @Override
    public List<SsEmpTask> queryTaskByEmpArchiveId(String empArchiveId) {
        return baseMapper.queryTaskByEmpArchiveId(empArchiveId);
    }

    /**
     * 处理雇员任务单数据
     *
     * @param dto
     */
    private void handleTaskCategory(SsEmpTaskBO dto) {
        // 操作类型，用于区分操作。1 日常操作、2 特殊操作， 默认日常操作
        Integer operatorType = Optional.ofNullable(dto.getOperatorType()).orElse(1);
        dto.setOperatorType(operatorType);

        // 任务类型，DicItem.DicItemValue 1:新进：2：转入 3调整 4 补缴 5 转出 6终止 7退账 8 提取
        {
            // 任务处理类型
            Integer taskCategory = dto.getTaskCategory();
            Integer[] taskCategories;
            if (taskCategory == null) {
                switch (operatorType) {
                    // 日常操作
                    case 1:
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 6,7};
                        break;
                    // 特殊操作
                    case 2:
                        taskCategories = null;//现在特殊任务只有状态为9的 后面sql已经写死为9
                        break;
                    default:// 日常操作
                        taskCategories = new Integer[]{1, 2, 3, 4, 5, 6,7};

                }
            } else {
                taskCategories = new Integer[]{taskCategory};
            }

            dto.setTaskCategories(taskCategories);
            dto.setTaskCategory(null);
        }

    }

}

