package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFStatementCompareBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.EmployeeIdPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.EmployeeSysAmountPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.FundStatementItemPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfStatementComparePO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公积金对账主表 Mapper 接口
 * </p>
 */
public interface HfStatementCompareMapper extends BaseMapper<HfStatementComparePO> {

    /**
     * 根据筛选条件查询公积金对账单记录。
     * @param hfMonth
     * @param hfComAccount
     * @return
     */
    List<HFStatementCompareBO> getHFStatementCompareRecord(@Param("hfMonth") String hfMonth, @Param("hfComAccount") String hfComAccount);

    /**
     * 删除对账单
     * @param statementId
     * @return
     */
    int delStatement(@Param("statementId") long statementId);

    /**
     * 根据对账单Id获取对账单详情
     * @param statementId
     * @return
     */
    List<FundStatementItemPO> getStatementItems(@Param("statementId") long statementId);

    /**
     * 删除对账单结果数据
     * @param statementId
     * @return
     */
    int delStatementResult(@Param("statementId") long statementId);

    /**
     * 根据筛选条件获取雇员Id和姓名信息
     * @param employeeName
     * @param idNum
     * @param empAccount
     * @return
     */
    EmployeeIdPO getEmployeeIdFromArchive(@Param("employeeName") String employeeName,@Param("idNum") String idNum,
                                                @Param("empAccount") String empAccount);

    /**
     * 根据筛选条件获取员工的系统金额列表
     * @param comAccountId
     * @param hfMonth
     * @return
     */
    @MapKey("employeeId")
    Map<String,EmployeeSysAmountPO> getEmployeeSysAmount(@Param("hfType")Integer hfType, @Param("comAccountId") long comAccountId, @Param("hfMonth") String hfMonth);
}
