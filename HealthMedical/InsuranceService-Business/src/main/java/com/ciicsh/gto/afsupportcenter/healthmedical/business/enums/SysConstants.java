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
    enum PaymentJob {
        AF_SYS_MANAGEMENT(1, "外企内控"),
        SYSTEM_ZH(1, "系统"),
        SYSTEM_EN(2, "system"),
        INDIVIDUAL(1, "个人"),
        FINANCE(0, "否"),
        BUSINESS(11, "  AF雇员报销"),
        PAY_WAY(3, "转账"),
        ACTIVE(1, "可用"),
        DATE_FORMAT(1, "yyyy-MM-dd"),
        PRESIDENT(1, "总经理"),
        LEADER(1, "分管领导"),
        DEPARTMENT_MANAGER(1, "部门经理"),
        REVIEWER(1, "审核人");

        private int code;
        private String name;
        PaymentJob(int code, String name) {
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
     * MsgCode
     */
    enum MsgCode {
        SUCCESS("0", "SUCCESS"),
        REMOTE_INVOCATION_ERROR("300", "远程服务接口调用异常"),
        SERVICE_BUSY("201", "服务器繁忙，请稍后再试！"),
        ILLEGAL_ARGUMENT("400", "非法的请求参数");

        private String code;
        private String msg;

        private MsgCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return this.code;
        }

        public String getMsg() {
            return this.msg;
        }
    }

    /**
     * 业务ID
     */
    enum BusinessId {
        EMPLOYEE_PAYMENT(2, "雇员付款");

        private int id;
        private String name;

        private BusinessId(int id, String name) {
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
