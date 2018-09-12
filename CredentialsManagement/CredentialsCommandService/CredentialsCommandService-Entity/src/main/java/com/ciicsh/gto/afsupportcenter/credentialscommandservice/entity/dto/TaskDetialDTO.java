package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:42 2018/1/23
 */
@Data
public class TaskDetialDTO implements Serializable{

    /**
     * 主键：证件办理任务单id
     */
    private Long taskId;
    /**
     * 雇员id
     */
    private String employeeId;
    /**
     * 客户id
     */
    private String companyId;
    /**
     * 雇员类型
     */
    private String templateType;
    /**
     * 基础产品id
     */
    private String basicProductId;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 材料ids
     */
    private String materialIds;
    /**
     * 公司材料
     */
    private String comp;
    /**
     * 婚姻状况
     */
    private String marryStatus;
    /**
     * 是否有随员
     */
    private String hasFollower;
    /**
     * 是否上海户籍
     */
    private String hasShPerson;
    /**
     * 配偶及子女相关材料
     */
    private String familerMaterials;
    /**
     * 申请地址变更材料
     */
    private String applyAddrChange;
    /**
     * 随员类型
     */
    private String followerType;
    /**
     * 住所证明
     */
    private String addr;
    /**
     * 随员
     */
    private String follower;
    /**
     * 子女是否随迁
     */
    private String hasChildFollow;
    /**
     * 配偶是否随迁
     */
    private String hasSpouseFollow;
    /**
     * 婚育状况
     */
    private String married;
    /**
     * 职称材料
     */
    private String jobMaterials;
    /**
     * 是否为科创人才
     */
    private String hasGooder;
    /**
     * 教育经历材料
     */
    private String educate;
    /**
     * 随迁材料
     */
    private String followMaterials;
    /**
     *  不随迁材料
     */
    private String notFollowMaterials;
    /**
     * 证件类型
     */
    private String credentialsType;
    /**
     * 证件办理类型
     */
    private String credentialsDealType;
    /**
     * 学历
     */
    private Integer qualification;
    /**
     * 学位
     */
    private Integer degree;
    /**
     * 学历认定时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date educationTime;
    /**
     * 材料退回时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date materialBackTime;
    /**
     * 催交日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date callsTime;
    /**
     * 申报日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date applyTime;
    /**
     * 居住证年限
     */
    private Integer liveAgeLimit;
    /**
     * 调档函开出时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date shiftLetterSendTime;
    /**
     * 人才退回时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date talentBackTime;
    /**
     * 人才退回原因
     */
    private String talentBackReason;
    /**
     * 办理日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date dealTime;
    /**
     * 收费日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date chargeTime;
    /**
     * 到档时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date receiveFileTime;
    /**
     * 原件退回时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date originalBackTime;
    /**
     * 原件退回原因
     */
    private String originalBackReason;
    /**
     * 积分单打印日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date integralBillPrintTime;
    /**
     * 积分单通知日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date integralBillCallTime;
    /**
     * 雇员批复领取时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date empBackTime;
    /**
     * 收费金额
     */
    private BigDecimal chargeAmount;
    /**
     * 人数
     */
    private String peopleNum;
    /**
     * 付款方式(1：台账、2：现金、3：转账、4：POS机)
     */
    private Integer payType;
    /**
     * 办证公司名称
     */
    private String permitCompanyName;
    /**
     * 手机
     */
    private String telephone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "+8")
    private Date createdTime;
    /**
     * 最后更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;
}
