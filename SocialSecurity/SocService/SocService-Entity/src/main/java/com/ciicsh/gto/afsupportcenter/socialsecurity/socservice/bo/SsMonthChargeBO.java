package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthCharge;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthChargeItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 月度缴费明细
系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成，
 * </p>
 *
 * @author xsj
 * @since 2018-02-05
 */
public class SsMonthChargeBO extends SsMonthCharge {
        private List<SsMonthChargeItem> ssMonthChargeItemList;

    public List<SsMonthChargeItem> getSsMonthChargeItemList() {
        return ssMonthChargeItemList;
    }

    public void setSsMonthChargeItemList(List<SsMonthChargeItem> ssMonthChargeItemList) {
        this.ssMonthChargeItemList = ssMonthChargeItemList;
    }
}
