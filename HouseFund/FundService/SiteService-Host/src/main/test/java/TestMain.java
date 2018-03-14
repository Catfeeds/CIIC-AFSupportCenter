
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfBujiaoListMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfBujiaoList;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TestMain {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    public static void main(String[] arg) {
//        YearMonth start = YearMonth.parse("201709", formatter);
//        YearMonth end = YearMonth.parse("201708", formatter);
//        long months = start.until(end, ChronoUnit.MONTHS);
//        System.out.println(months);
//         Integer a = null;
//
//        switch (a) {
//            case  1:
//                System.out.println("1");
//                break;
//            default:
//                break;
//        }

//        System.out.println(String.format("客户编号：%1$s雇员编号：%2$s的任务单已被删除", "203242", "L3r3234"));
BigDecimal b = new BigDecimal("716.06");
        BigDecimal aa = CalculateSocialUtils.calculateByRoundType(b.divide(new BigDecimal("0.057")).setScale(2, BigDecimal.ROUND_HALF_UP), 7);
        System.out.println(aa);
        System.out.println(b);
    }
}
