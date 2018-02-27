package com.ciicsh.gto.afsupportcenter.housefund.siteservice.constant;

public interface HfEmpArchiveConstant {
    int ARCHIVE_TASK_STATUS_HANDLED = 1; // 任务单公积金状态：已办
    int ARCHIVE_TASK_STATUS_COMPLETED = 2; // 任务单公积金状态：已做
    int ARCHIVE_TASK_STATUS_CLOSED = 3; // 任务单公积金状态：封存

    int ARCHIVE_STATUS_UNHANDLED = 0; // 公积金状态：未办理
    int ARCHIVE_STATUS_HANDLED = 1; // 公积金状态：已办
    int ARCHIVE_STATUS_COMPLETED = 2; // 公积金状态：已做
    int ARCHIVE_STATUS_CLOSED = 3; // 公积金状态：封存
}
