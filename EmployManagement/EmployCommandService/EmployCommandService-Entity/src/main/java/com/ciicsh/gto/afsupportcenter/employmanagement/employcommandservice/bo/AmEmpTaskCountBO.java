package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

/**
 * Created by zhangzhiwen on 2018/1/24.
 */
public class AmEmpTaskCountBO {
    private Integer noSign;
    private Integer finished;
    private Integer employSuccess;
    private Integer employFailed;
    private Integer employCancel;
    private Integer other;
    private Integer amount;

    public Integer getNoSign() {
        return noSign;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public void setNoSign(Integer noSign) {
        this.noSign = noSign;
    }



    public Integer getEmploySuccess() {
        return employSuccess;
    }

    public void setEmploySuccess(Integer employSuccess) {
        this.employSuccess = employSuccess;
    }

    public Integer getEmployFailed() {
        return employFailed;
    }

    public void setEmployFailed(Integer employFailed) {
        this.employFailed = employFailed;
    }

    public Integer getEmployCancel() {
        return employCancel;
    }

    public void setEmployCancel(Integer employCancel) {
        this.employCancel = employCancel;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
