package com.ciicsh.gto.afsupportcenter.healthmedical.business.enums;

/**
 * <p>
 * 枚举常量
 * </p>
 *
 * @author chenpb
 * @since 2018-01-30
 */
public interface SysConstants {

    /**
     * 雇员付款常量
     */
    enum JobConstants {
        AF_SYS_MANAGEMENT(1, "外企内控中心"),
        HEALTH_MEDICAL_DEPT(2, "健康医疗部"),
        SYSTEM_ZH(1, "系统"),
        SYSTEM_EN(2, "system"),
        INDIVIDUAL(1, "个人"),
        FINANCE_NOT(0, "否"),
        AF_EMPLOYEE_PAYMENT(11, "AF雇员报销"),
        MEDICAL_CLAIMS(12, "医疗理赔报销"),
        PAY_WAY(3, "转账"),
        ACTIVE(1, "可用"),
        DATE_FORMAT(1, "yyyy-MM-dd"),
        PRESIDENT(1, "总经理"),
        LEADER(1, "分管领导"),
        DEPARTMENT_MANAGER(1, "部门经理"),
        REVIEWER(1, "审核人");

        private int code;
        private String name;
        JobConstants(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
    }


    /**
     * 申请状态
     */
    enum ApplyStatus {
        APPLY(1, "未审核"),
        REFUSE(2, "已批退"),
        AUDITED(3, "已审核未同步"),
        SYNC(4, "已同步"),
        PAYMENT(5, "已支付"),
        BACK(6, "财务退回"),
        REFUND(7, "银行退票"),
        COMPLETE(8, "已完成");
        private int code;
        private String name;
        ApplyStatus(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
    }

    /**
     * Batch常量
     */
    enum BatchStatus {
        ADD(1, "新建");

        private int code;
        private String name;
        BatchStatus(int code, String name) {
            this.code = code;
            this.name = name;
        }
        public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
    }

    /**
     * 业务ID
     */
    enum BusinessId {
        SUPPLY_MEDICAL(0, "补充医疗理赔"),
        UNINSURED_MEDICAL(1, "未投保医疗理赔"),
        EMPLOYEE_PAYMENT(2, "雇员付款");

        private int id;
        private String name;

        BusinessId(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }

}
