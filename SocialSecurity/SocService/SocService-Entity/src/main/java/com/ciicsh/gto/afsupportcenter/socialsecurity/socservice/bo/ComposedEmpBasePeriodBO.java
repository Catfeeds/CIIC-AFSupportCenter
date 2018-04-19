package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBasePeriod;
import lombok.Data;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Data
public class ComposedEmpBasePeriodBO {
    YearMonth startMonth;
    YearMonth endMonth;
    List<SsEmpBasePeriod> containSsEmpBasePeriods = new ArrayList<>();
}
