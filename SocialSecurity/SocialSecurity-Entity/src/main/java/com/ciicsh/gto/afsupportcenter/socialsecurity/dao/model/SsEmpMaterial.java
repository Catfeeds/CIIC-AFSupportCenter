package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 雇员材料收缴
 */
@Table(name = "SS_EmpMaterial")
public class SsEmpMaterial extends BasicModel implements Serializable {
    /**
     * 雇员任务单编号
     */
    @Id
    @Column(name = "EmpMaterialId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empMaterialId;

    /**
     * 本地社保的雇员任务单Id
     */
    @Column(name = "EmployeeTaskId")
    private String employeeTaskId;

    /**
     * 材料类型：原件、复印件、扫描件
     */
    @Column(name = "材料类型")
    private Byte 材料类型;

    /**
     * 任务单提交人所属部门Id
     */
    @Column(name = "材料名称")
    private String 材料名称;

    /**
     * 发起时间
     */
    @Column(name = "材料提交时间")
    private LocalDateTime 材料提交时间;

    /**
     * 任务发起人备注
     */
    @Column(name = "材料收到时间")
    private LocalDateTime 材料收到时间;

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
    @Column(name = "备注说明")
    private String 备注说明;

    @Column(name = "材料收到状态")
    private Byte 材料收到状态;

    private static final long serialVersionUID = 1L;

    /**
     * 获取雇员任务单编号
     *
     * @return EmpMaterialId - 雇员任务单编号
     */
    public Long getEmpMaterialId() {
        return empMaterialId;
    }

    /**
     * 设置雇员任务单编号
     *
     * @param empMaterialId 雇员任务单编号
     */
    public void setEmpMaterialId(Long empMaterialId) {
        this.empMaterialId = empMaterialId;
    }

    /**
     * 获取本地社保的雇员任务单Id
     *
     * @return EmployeeTaskId - 本地社保的雇员任务单Id
     */
    public String getEmployeeTaskId() {
        return employeeTaskId;
    }

    /**
     * 设置本地社保的雇员任务单Id
     *
     * @param employeeTaskId 本地社保的雇员任务单Id
     */
    public void setEmployeeTaskId(String employeeTaskId) {
        this.employeeTaskId = employeeTaskId == null ? null : employeeTaskId.trim();
    }

    /**
     * 获取材料类型：原件、复印件、扫描件
     *
     * @return 材料类型 - 材料类型：原件、复印件、扫描件
     */
    public Byte get材料类型() {
        return 材料类型;
    }

    /**
     * 设置材料类型：原件、复印件、扫描件
     *
     * @param 材料类型 材料类型：原件、复印件、扫描件
     */
    public void set材料类型(Byte 材料类型) {
        this.材料类型 = 材料类型;
    }

    /**
     * 获取任务单提交人所属部门Id
     *
     * @return 材料名称 - 任务单提交人所属部门Id
     */
    public String get材料名称() {
        return 材料名称;
    }

    /**
     * 设置任务单提交人所属部门Id
     *
     * @param 材料名称 任务单提交人所属部门Id
     */
    public void set材料名称(String 材料名称) {
        this.材料名称 = 材料名称 == null ? null : 材料名称.trim();
    }

    /**
     * 获取发起时间
     *
     * @return 材料提交时间 - 发起时间
     */
    public LocalDateTime get材料提交时间() {
        return 材料提交时间;
    }

    /**
     * 设置发起时间
     *
     * @param 材料提交时间 发起时间
     */
    public void set材料提交时间(LocalDateTime 材料提交时间) {
        this.材料提交时间 = 材料提交时间;
    }

    /**
     * 获取任务发起人备注
     *
     * @return 材料收到时间 - 任务发起人备注
     */
    public LocalDateTime get材料收到时间() {
        return 材料收到时间;
    }

    /**
     * 设置任务发起人备注
     *
     * @param 材料收到时间 任务发起人备注
     */
    public void set材料收到时间(LocalDateTime 材料收到时间) {
        this.材料收到时间 = 材料收到时间;
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
     * @return 备注说明 - 
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
    public String get备注说明() {
        return 备注说明;
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
     * @param 备注说明 
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
    public void set备注说明(String 备注说明) {
        this.备注说明 = 备注说明 == null ? null : 备注说明.trim();
    }

    /**
     * @return 材料收到状态
     */
    public Byte get材料收到状态() {
        return 材料收到状态;
    }

    /**
     * @param 材料收到状态
     */
    public void set材料收到状态(Byte 材料收到状态) {
        this.材料收到状态 = 材料收到状态;
    }
}