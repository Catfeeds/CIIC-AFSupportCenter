package com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant;

public interface HfEmpTaskConstant {
    int TASK_CATEGORY_IN_ADD = 1; // 任务类型：新增（新开）
    int TASK_CATEGORY_IN_TRANS_IN = 2; // 任务类型：新增（转入）
    int TASK_CATEGORY_IN_OPEN = 3; // 任务类型：新增（启封）
    int TASK_CATEGORY_ADJUST = 4; // 任务类型：调整
    int TASK_CATEGORY_ADJUST_OPEN = 5; // 任务类型：(待定)
    int TASK_CATEGORY_REPAIR = 6; // 任务类型：补缴
    int TASK_CATEGORY_OUT_TRANS_OUT = 7; // 任务类型：离职（转出）
    int TASK_CATEGORY_OUT_CLOSE = 8; // 任务类型：离职（封存）
    int TASK_CATEGORY_TRANS_TASK = 9; // 任务类型：转移
    int TASK_CATEGORY_SPEC_TASK = 10; // 任务类型：特殊任务
    int TASK_CATEGORY_IN_MULTI_TRANS_IN = 11; // 任务类型：集体转入
    int TASK_CATEGORY_OUT_MULTI_TRANS_OUT = 12; // 任务类型：集体转出
    int TASK_CATEGORY_TURN_OVER = 13; // 任务类型：翻牌

    int TASK_STATUS_UNHANDLED = 1; // 办理状态：未处理
    int TASK_STATUS_HANDLED = 2; // 办理状态：处理中（已办）
    int TASK_STATUS_COMPLETED = 3; // 办理状态：已完成（已做）
    int TASK_STATUS_REJECTED = 4; // 办理状态：批退
    int TASK_STATUS_NOT_HANDLE = 5; // 办理状态：不需处理

    int HF_TYPE_BASIC = 1; // 公积金类型：基本公积金
    int HF_TYPE_ADDED = 2; // 公积金类型：补充公积金
}
