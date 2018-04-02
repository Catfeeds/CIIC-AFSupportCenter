package com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant;

public interface HfEmpTaskConstant {
//    int PROCESS_CATEGORY_ADD = 1; // 任务单处理类别：雇员新增
//    int PROCESS_CATEGORY_STOP = 2; // 任务单处理类别：雇员终止
//    int PROCESS_CATEGORY_REPAIR = 3; // 任务单处理类别：雇员补缴
//    int PROCESS_CATEGORY_TURN = 4; // 任务单处理类别：雇员翻牌
//    int PROCESS_CATEGORY_ADJUST = 5; // 任务单处理类别：雇员调整
//    int PROCESS_CATEGORY_MODIFY = 6; // 任务单处理类别：雇员更正
//    int PROCESS_CATEGORY_MULTI = 7; // 任务单处理类别：集体操作
//    int PROCESS_CATEGORY_SPEC = 8; // 任务单处理类别：特殊操作
//    int PROCESS_CATEGORY_OTHER = 9; // 任务单处理类别：其它操作

    int TASK_CATEGORY_IN_ADD = 1; // 任务类型：新开
    int TASK_CATEGORY_IN_TRANS_IN = 2; // 任务类型：转入
    int TASK_CATEGORY_IN_OPEN = 3; // 任务类型：启封
    int TASK_CATEGORY_OUT_TRANS_OUT = 4; // 任务类型：转出
    int TASK_CATEGORY_OUT_CLOSE = 5; // 任务类型：封存
    int TASK_CATEGORY_REPAIR = 6; // 任务类型：补缴
    int TASK_CATEGORY_ADJUST = 7; // 任务类型：调整
    int TASK_CATEGORY_TRANSFER_TASK = 8; // 任务类型：转移
    int TASK_CATEGORY_FLOP_ADD = 9; // 任务类型：翻牌新开
    int TASK_CATEGORY_FLOP_TRANS_IN = 10; // 任务类型：翻牌转入
    int TASK_CATEGORY_FLOP_OPEN = 11; // 任务类型：翻牌启封
    int TASK_CATEGORY_FLOP_TRANS_OUT = 12; // 任务类型：翻牌转出
    int TASK_CATEGORY_FLOP_CLOSE = 13; // 任务类型：翻牌封存

//    int DICT_TASK_CATEGORY_IN_ADD = 1; // 字典任务类型：新开
//    int DICT_TASK_CATEGORY_IN_TRANS_IN = 2; // 字典任务类型：转入
//    int DICT_TASK_CATEGORY_IN_OPEN = 3; // 字典任务类型：启封
//    int DICT_TASK_CATEGORY_OUT_TRANS_OUT = 4; // 字典任务类型：转出
//    int DICT_TASK_CATEGORY_OUT_CLOSE = 5; // 字典任务类型：封存
//    int DICT_TASK_CATEGORY_REPAIR = 6; // 字典任务类型：补缴
//    int DICT_TASK_CATEGORY_ADJUST = 7; // 字典任务类型：调整
//    int DICT_TASK_CATEGORY_TRANS_TASK = 8; // 字典任务类型：转移
//    int DICT_TASK_CATEGORY_MULTI_IN = 9; // 字典任务类型：集体转入
//    int DICT_TASK_CATEGORY_MULTI_OUT = 10; // 字典任务类型：集体转出
//    int DICT_TASK_CATEGORY_TURN_CLOSE = 11; // 字典任务类型：翻牌封存
//    int DICT_TASK_CATEGORY_TURN_OPEN = 12; // 字典任务类型：翻牌启封

    int TASK_STATUS_UNHANDLED = 1; // 办理状态：未处理
    int TASK_STATUS_HANDLED = 2; // 办理状态：处理中（已办）
    int TASK_STATUS_COMPLETED = 3; // 办理状态：已完成（已做）
    int TASK_STATUS_REJECTED = 4; // 办理状态：批退
    int TASK_STATUS_NOT_HANDLE = 5; // 办理状态：不需处理

    int HF_TYPE_BASIC = 1; // 公积金类型：基本公积金
    int HF_TYPE_ADDED = 2; // 公积金类型：补充公积金

    int WELFARE_UNIT_INDEPENDENT = 1; // 福利办理方：独立户
    int WELFARE_UNIT_CIIC_LARGE_STORAGE = 2; // 福利办理方：中智大库

    int IS_CHANGE_YES = 1; // 是否变更：是
    int IS_CHANGE_NO = 0; // 是否变更：否
}
