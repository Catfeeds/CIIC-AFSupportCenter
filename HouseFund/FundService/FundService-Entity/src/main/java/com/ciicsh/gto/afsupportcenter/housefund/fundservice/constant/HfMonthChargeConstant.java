package com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant;

public interface HfMonthChargeConstant {
    int PAYMENT_TYPE_NORMAL = 1; // 标准（正常汇缴）
    int PAYMENT_TYPE_NEW = 2; // 开户
    int PAYMENT_TYPE_TRANS_IN = 3; // 转入
    int PAYMENT_TYPE_OPEN = 4; // 启封
    int PAYMENT_TYPE_ADJUST_OPEN = 5; // 调整启封
    int PAYMENT_TYPE_REPAIR = 6; // 补缴
    int PAYMENT_TYPE_TRANS_OUT = 7; // 转入
    int PAYMENT_TYPE_CLOSE = 8; // 封存
    int PAYMENT_TYPE_ADJUST_CLOSE = 9; // 调整封存
    int PAYMENT_TYPE_DEL = 10; // 销户
    int PAYMENT_TYPE_DIFF_REPAIR = 11; // 差额补缴
}
