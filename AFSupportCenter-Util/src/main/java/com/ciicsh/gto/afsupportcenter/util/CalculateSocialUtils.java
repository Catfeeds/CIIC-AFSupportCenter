package com.ciicsh.gto.afsupportcenter.util;

import java.math.BigDecimal;

/**
 * The type Calculate social utils.
 *
 * @Description:
 * @author: wujinglei /吴敬磊
 * @date: 2018 /1/2 13:54
 */
public class CalculateSocialUtils {

	/**
	 * Calculate amount big decimal.
	 *
	 * @param base            the base
	 * @param ratio           the ratio
	 * @param fixedAmount     the fixed amount
	 * @param calculateMethod the calculate method
	 * @param roundType       the round type
	 * @return big decimal
	 */
	public static BigDecimal calculateAmount(BigDecimal base, BigDecimal ratio, BigDecimal fixedAmount, Integer calculateMethod, String roundType){
		switch (calculateMethod){
			case 1:
				return calculateByRoundType(fixedAmount,roundType);
			case 2:
				return calculateByRoundType(base.multiply(ratio),roundType);
			case 3:
				return calculateByRoundType(base.multiply(ratio).add(fixedAmount),roundType);
			default:
				return new BigDecimal(0);
		}
	}

	public static BigDecimal calculateByRoundType(BigDecimal amount,String roundType){
		BigDecimal ten = new BigDecimal(10);
		BigDecimal hundred = new BigDecimal(100);
		switch (roundType) {
			// 见分进角
			case "DIT00018":
				return new BigDecimal(Math.ceil(amount.multiply(ten).doubleValue())).divide(ten);
			// 见角进元
			case "DIT00019":
				return new BigDecimal(Math.ceil(amount.doubleValue()));
			// 进位到元
			case "DIT00020":
				return new BigDecimal(Math.ceil(amount.doubleValue()));
			// 进位偶数
			case "DIT00021":
				BigDecimal rs = new BigDecimal(Math.floor(amount.doubleValue()));
				if (rs.intValue() % 2 == 0){
					return rs;
				}else {
					return rs.add(new BigDecimal(1));
				}
			// 舍去角
			case "DIT00022":
				return new BigDecimal(Math.floor(amount.doubleValue()));
			// 舍去分
			case "DIT00023":
				return new BigDecimal(Math.floor(amount.multiply(ten).doubleValue())).divide(ten);
			// 舍去厘
			case "DIT00024":
				return new BigDecimal(Math.floor(amount.multiply(hundred).doubleValue())).divide(hundred);
			// 四舍五入到分
			case "DIT00025":
				return new BigDecimal(Math.round(amount.multiply(hundred).doubleValue())).divide(hundred);
			// 四舍五入到角
			case "DIT00026":
				return new BigDecimal(Math.round(amount.multiply(ten).doubleValue())).divide(ten);
			// 四舍五入到元
			case "DIT00027":
				return new BigDecimal(Math.round(amount.doubleValue()));
			default:
				return amount;
		}
	}

}
