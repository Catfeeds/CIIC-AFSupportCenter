package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.dataauth;

import java.io.Serializable;
import java.util.Objects;

/**
 * 系统用户信息DTO
 */
public class AmUserInfoDTO implements Serializable {

    // 用户Id
    private String userId;

    // 姓名
    private String displayName;

    // 1-后台管理用户 2-公有云用户 3-系统普通用户
    private Byte userType;

    // 工号
    private String employeeNumber;

    // 邮箱
    private String email;

    // 用户状态（1未启用，2启用，3禁用）
    private Integer userStatus;


    private static final long serialVersionUID = 1L;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmUserInfoDTO that = (AmUserInfoDTO) o;
        return Objects.equals(userId, that.userId) &&
            Objects.equals(displayName, that.displayName) &&
            Objects.equals(userType, that.userType) &&
            Objects.equals(employeeNumber, that.employeeNumber) &&
            Objects.equals(email, that.email) &&
            Objects.equals(userStatus, that.userStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId,displayName, userType, employeeNumber, email, userStatus);
    }
}