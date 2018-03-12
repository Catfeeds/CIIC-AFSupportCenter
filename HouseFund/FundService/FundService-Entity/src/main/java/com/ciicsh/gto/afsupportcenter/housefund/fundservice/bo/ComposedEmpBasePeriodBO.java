package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBasePeriod;
import lombok.Data;

import java.time.YearMonth;
import java.util.List;

/**
 * 雇员费用段组合对象
 *
 */
@Data
public class ComposedEmpBasePeriodBO {
    YearMonth startMonth;
    YearMonth endMonth;
    List<HfArchiveBasePeriod> containsHfArchiveBasePeriods;
}
