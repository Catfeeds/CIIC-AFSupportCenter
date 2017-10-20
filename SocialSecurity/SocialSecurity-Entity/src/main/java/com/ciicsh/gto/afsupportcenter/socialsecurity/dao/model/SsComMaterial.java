package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 企业材料收缴
 */
@Table(name = "SS_ComMaterial")
public class SsComMaterial extends BasicModel implements Serializable {
    /**
     * 雇员任务单编号
     */
    @Id
    @Column(name = "ComMaterialId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comMaterialId;

    /**
     * 本地社保的雇员任务单Id
     */
    @Column(name = "CompanyTaskId")
    private String companyTaskId;

    /**
     * 材料类型：原件、复印件、扫描件
     */
    @Column(name = "MaterialType")
    private Integer materialType;

    /**
     * 任务单提交人所属部门Id
     */
    @Column(name = "MaterialName")
    private String materialName;

    /**
     * 发起时间
     */
    @Column(name = "SubmitTime")
    private LocalDateTime submitTime;

    /**
     * 任务发起人备注
     */
    @Column(name = "ReceiveTime")
    private LocalDateTime receiveTime;

    /**
     * 
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     */
    @Column(name = "Remark")
    private String remark;

    @Column(name = "Status")
    private Integer status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取雇员任务单编号
     *
     * @return ComMaterialId - 雇员任务单编号
     */
    public Long getComMaterialId() {
        return comMaterialId;
    }

    /**
     * 设置雇员任务单编号
     *
     * @param comMaterialId 雇员任务单编号
     */
    public void setComMaterialId(Long comMaterialId) {
        this.comMaterialId = comMaterialId;
    }

    /**
     * 获取本地社保的雇员任务单Id
     *
     * @return CompanyTaskId - 本地社保的雇员任务单Id
     */
    public String getCompanyTaskId() {
        return companyTaskId;
    }

    /**
     * 设置本地社保的雇员任务单Id
     *
     * @param companyTaskId 本地社保的雇员任务单Id
     */
    public void setCompanyTaskId(String companyTaskId) {
        this.companyTaskId = companyTaskId == null ? null : companyTaskId.trim();
    }

    /**
     * 获取材料类型：原件、复印件、扫描件
     *
     * @return MaterialType - 材料类型：原件、复印件、扫描件
     */
    public Integer getMaterialType() {
        return materialType;
    }

    /**
     * 设置材料类型：原件、复印件、扫描件
     *
     * @param materialType 材料类型：原件、复印件、扫描件
     */
    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    /**
     * 获取任务单提交人所属部门Id
     *
     * @return MaterialName - 任务单提交人所属部门Id
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * 设置任务单提交人所属部门Id
     *
     * @param materialName 任务单提交人所属部门Id
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    /**
     * 获取发起时间
     *
     * @return SubmitTime - 发起时间
     */
    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置发起时间
     *
     * @param submitTime 发起时间
     */
    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 获取任务发起人备注
     *
     * @return ReceiveTime - 任务发起人备注
     */
    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置任务发起人备注
     *
     * @param receiveTime 任务发起人备注
     */
    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 获取
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     *
     * @return Remark - 
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     *
     * @param remark 
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}