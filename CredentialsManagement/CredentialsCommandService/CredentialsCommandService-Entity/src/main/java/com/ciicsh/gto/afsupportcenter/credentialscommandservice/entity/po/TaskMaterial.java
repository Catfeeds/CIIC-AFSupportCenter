package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description: 证件任务单材料
 * @Date: Created in 15:13 2018/1/15
 */
@TableName("cm_task_material")
public class TaskMaterial extends Model<TaskMaterial> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键：任务单材料id
     */
    @TableId(value="task_material_id", type= IdType.AUTO)
    private Long taskMaterialId;
    /**
     * 任务单id
     */
    @TableField("task_id")
    private String taskId;
    /**
     * 雇员id
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 客户id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 材料ids
     */
    @TableField("material_ids")
    private String materialIds;
    /**
     * 公司材料
     */
    @TableField("comp")
    private String comp;
    /**
     * 婚姻状况
     */
    @TableField("marry_status")
    private String marryStatus;
    /**
     * 是否有随员
     */
    @TableField("has_follower")
    private String hasFollower;
    /**
     * 配偶及子女相关材料
     */
    @TableField("familer_materials")
    private String familerMaterials;
    /**
     * 申请地址变更材料
     */
    @TableField("apply_addr_change")
    private String applyAddrChange;
    /**
     * 随员类型
     */
    @TableField("follower_type")
    private String followerType;
    /**
     * 住所证明
     */
    @TableField("addr")
    private String addr;
    /**
     * 随员
     */
    @TableField("follower")
    private String follower;
    /**
     * 子女是否随迁
     */
    @TableField("has_child_follow")
    private String hasChildFollow;
    /**
     * 配偶是否随迁
     */
    @TableField("has_spouse_follow")
    private String hasSpouseFollow;
    /**
     * 婚育状况
     */
    @TableField("married")
    private String married;
    /**
     * 职称材料
     */
    @TableField("job_materials")
    private String jobMaterials;
    /**
     * 是否为科创人才
     */
    @TableField("has_gooder")
    private String hasGooder;
    /**
     * 教育经历材料
     */
    @TableField("educate")
    private String educate;
    /**
     * 随迁材料
     */
    @TableField("follow_materials")
    private String followMaterials;
    /**
     *  不随迁材料
     */
    @TableField("not_follow_materials")
    private String notFollowMaterials;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 修改者登录名
     */
    @TableField("modified_by")
    private String modifiedBy;

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getMarryStatus() {
        return marryStatus;
    }

    public void setMarryStatus(String marryStatus) {
        this.marryStatus = marryStatus;
    }

    public String getHasFollower() {
        return hasFollower;
    }

    public void setHasFollower(String hasFollower) {
        this.hasFollower = hasFollower;
    }

    public String getFamilerMaterials() {
        return familerMaterials;
    }

    public void setFamilerMaterials(String familerMaterials) {
        this.familerMaterials = familerMaterials;
    }

    public String getApplyAddrChange() {
        return applyAddrChange;
    }

    public void setApplyAddrChange(String applyAddrChange) {
        this.applyAddrChange = applyAddrChange;
    }

    public String getFollowerType() {
        return followerType;
    }

    public void setFollowerType(String followerType) {
        this.followerType = followerType;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getHasChildFollow() {
        return hasChildFollow;
    }

    public void setHasChildFollow(String hasChildFollow) {
        this.hasChildFollow = hasChildFollow;
    }

    public String getHasSpouseFollow() {
        return hasSpouseFollow;
    }

    public void setHasSpouseFollow(String hasSpouseFollow) {
        this.hasSpouseFollow = hasSpouseFollow;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getJobMaterials() {
        return jobMaterials;
    }

    public void setJobMaterials(String jobMaterials) {
        this.jobMaterials = jobMaterials;
    }

    public String getHasGooder() {
        return hasGooder;
    }

    public void setHasGooder(String hasGooder) {
        this.hasGooder = hasGooder;
    }

    public String getEducate() {
        return educate;
    }

    public void setEducate(String educate) {
        this.educate = educate;
    }

    public String getFollowMaterials() {
        return followMaterials;
    }

    public void setFollowMaterials(String followMaterials) {
        this.followMaterials = followMaterials;
    }

    public String getNotFollowMaterials() {
        return notFollowMaterials;
    }

    public void setNotFollowMaterials(String notFollowMaterials) {
        this.notFollowMaterials = notFollowMaterials;
    }

    public Long getTaskMaterialId() {
        return taskMaterialId;
    }

    public void setTaskMaterialId(Long taskMaterialId) {
        this.taskMaterialId = taskMaterialId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getMaterialIds() {
        return materialIds;
    }

    public void setMaterialIds(String materialIds) {
        this.materialIds = materialIds;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.taskMaterialId;
    }

    @Override
    public String toString() {
        return "TaskMaterial{" +
            "taskMaterialId=" + taskMaterialId +
            ", taskId='" + taskId + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", companyId='" + companyId + '\'' +
            ", materialIds='" + materialIds + '\'' +
            ", comp='" + comp + '\'' +
            ", marryStatus='" + marryStatus + '\'' +
            ", hasFollower='" + hasFollower + '\'' +
            ", familerMaterials='" + familerMaterials + '\'' +
            ", applyAddrChange='" + applyAddrChange + '\'' +
            ", followerType='" + followerType + '\'' +
            ", addr='" + addr + '\'' +
            ", follower='" + follower + '\'' +
            ", hasChildFollow='" + hasChildFollow + '\'' +
            ", hasSpouseFollow='" + hasSpouseFollow + '\'' +
            ", married='" + married + '\'' +
            ", jobMaterials='" + jobMaterials + '\'' +
            ", hasGooder='" + hasGooder + '\'' +
            ", educate='" + educate + '\'' +
            ", followMaterials='" + followMaterials + '\'' +
            ", notFollowMaterials='" + notFollowMaterials + '\'' +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
