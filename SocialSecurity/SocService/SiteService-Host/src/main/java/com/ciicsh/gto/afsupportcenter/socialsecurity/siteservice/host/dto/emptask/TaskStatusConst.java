package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.dto.emptask;

/**
 * 任务状态
 */
public interface TaskStatusConst {

    int CURRENT_MONTH = 1;// 本月未处理
    int NEXT_MONTH = 2;// 下月未处理
    int PROCESSING = 3;// 处理中
    int FINISH = 4;// 已完成
    int REJECTION = 5;// 批退
}
