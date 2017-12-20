package com.ciicsh.gto.afsupportcenter.util;

public interface SysConstants {
    enum DraftType {
        BUSIC_PRODUCT(0, "基础产品"),
        PRODUCT(1, "可售单项"),
        PACKAGE(2, "可售套餐");
        private int type;
        private String typeName;
        DraftType(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }
        public static String getNameByType(Integer type) {
            if (type == null)
                return null;
            for (DraftType draftType : DraftType.values()) {
                if (draftType.type == type) {
                    return draftType.typeName;
                }
            }
            return null;
        }
    }

    enum OperateType {
        ADD(0, "新增"),
        EDIT(1, "修改"),
        DELETE(2, "删除"),
        ENABLE(3, "启用"),
        DISABLE(4, "禁用");
        private int type;
        private String typeName;
        OperateType(int type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }
        public static String getNameByType(Integer type) {
            if (type == null)
                return null;
            for (OperateType operateType : OperateType.values()) {
                if (operateType.type == type) {
                    return operateType.typeName;
                }
            }
            return null;
        }
    }

    enum ApprovalStatus {
        PENDING(0, "未审批"),
        PASS(1, "审批通过"),
        REJECT(2, "审批拒绝");
        private int status;
        private String name;
        ApprovalStatus(int status, String name) {
            this.status = status;
            this.name = name;
        }

        public static String getNameByStatus(Integer status) {
            if (status == null)
                return null;
            for (ApprovalStatus approvalStatus : ApprovalStatus.values()) {
                if (approvalStatus.status == status)
                    return approvalStatus.name;
            }
            return null;
        }
    }
}
