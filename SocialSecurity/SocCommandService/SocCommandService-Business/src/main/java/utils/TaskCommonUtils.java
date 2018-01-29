package utils;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.api.dto.TaskSheetRequestDTO;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import org.springframework.util.Assert;

import java.time.LocalDate;

public class TaskCommonUtils {

    /**
     * 处理工作流结果
     * @param result
     */
    public static void handleWorkflowResult(Result result){
        //int code，接口调用成功=0，错误码=其他值
        //T object，具体返回值  TRUE,FALSE
        //String error，字符串错误码，可选
        //String message，错误消息
        Assert.notNull(result,"任务单操作调用工作流结果为空");
        if(0!=result.getCode())throw new BusinessException(result.getMessage());
    }

    /**
     * 任务单处理调用工作流
     * @param taskId
     * @param commonApiUtils
     * @param assignee
     * @return
     */
    public static void completeTask(String taskId,CommonApiUtils commonApiUtils,String assignee){
        TaskSheetRequestDTO taskSheetRequestDTO = new TaskSheetRequestDTO();
        taskSheetRequestDTO.setTaskId(taskId);
        taskSheetRequestDTO.setAssignee(assignee);
        try {
            Result result =commonApiUtils.completeTask(taskSheetRequestDTO);
            handleWorkflowResult(result);
        } catch (Exception e) {
            throw new BusinessException("调用工作流异常");
        }
    }

    /**
     * 获得进位方式
     */
    public static DicItemDTO getRoundTypeFromApi(CommonApiUtils commonApiUtils,String dicItemId) {
        try {
            DicItemDTO dicItemDTO = commonApiUtils.selectByDicItemId(dicItemId);
            Assert.isNull(dicItemDTO,"进位方式为空");
            // roundType = dicItemDTO.getDicId();
            return dicItemDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("调用工作流异常");
        }

    }
    /**
     *获得年月日 的字符串
     * @param now
     * @return
     */
    public static StringBuffer getMonthStr(LocalDate now) {
        StringBuffer sb = new StringBuffer();
        if (now.getMonth().getValue() <= 9) {
            return sb.append(now.getYear()).append(0).append(now.getMonth().getValue());
        } else {
            return sb.append(now.getYear()).append(now.getMonth().getValue());
        }
    }
    /**
     * 获得下个月的int 型
     *
     * 在循环年月的时候可以使用
     * @param i
     * @return
     */
    public static int getNextMonthInt(int i) {
        return Integer.parseInt(getNextMonth(i));
    }

    /**
     * 获得参数的下个月 字符串
     * @param date
     * @return
     */
    public static String getNextMonth(Integer date) {
        StringBuffer sb = new StringBuffer();
        String dateStr = date.toString();
        int length = dateStr.length();
        String year = dateStr.substring(0,4);
        String day = dateStr.substring(length - 2,length);
        if (12 == Integer.parseInt(day)) {
            return sb.append(Integer.valueOf(year) + 1).append("01").toString();
        }  else {
            return sb.append(date + 1).toString();
        }
    }

    /**
     * 获得截断月份
     * 参数月的上月
     *
     * @param date
     */
    public static String getLastMonth(Integer date) {
        StringBuffer sb = new StringBuffer();
        String dateStr = date.toString();
        int length = dateStr.length();
        String year = dateStr.substring(0, 4);
        String day = dateStr.substring(length - 2, length);
        if (1 == Integer.parseInt(day)) {
            return sb.append(Integer.valueOf(year) - 1).append(12).toString();
        } else {
            return sb.append(date - 1).toString();
        }
    }

    /**
     * 克隆对象
     * @param object 要克隆的对象
     * @param z 要克隆的类型
     * @return 克隆的对象
     */
    public static <T> T cloneObjet(T object, Class z) {
        return (T) JSONObject.parseObject(JSONObject.toJSON(object).toString(), z);
    }
}
