package com.ciicsh.gto.afsupportcenter.housefund.fundservice.convertor;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskRejectExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import org.apache.commons.lang.StringUtils;

public class EmpTaskCategoryConverter {
    public static Integer convertDictItemFromCategories(HfEmpTaskRejectExportBo hfEmpTaskRejectExportBo) {
        Integer[] categories = { hfEmpTaskRejectExportBo.getProcessCategory(), hfEmpTaskRejectExportBo.getTaskCategory() };
        return convertDictItemFromCategories(categories);
    }

    public static Integer convertDictItemFromCategories(HfEmpTaskExportBo hfEmpTaskExportBo) {
        Integer[] categories = { hfEmpTaskExportBo.getProcessCategory(), hfEmpTaskExportBo.getTaskCategory() };
        return convertDictItemFromCategories(categories);
    }

    public static Integer convertDictItemFromCategories(HfEmpTaskHandleBo hfEmpTaskHandleBo) {
        Integer[] categories = { hfEmpTaskHandleBo.getProcessCategory(), hfEmpTaskHandleBo.getTaskCategory() };
        return convertDictItemFromCategories(categories);
    }

    public static void convertCategoriesFromDict(HfEmpTaskBo hfEmpTaskBo) {
        switch (hfEmpTaskBo.getDictTaskCategory()) {
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_ADD:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_ADD);
                hfEmpTaskBo.setTaskCategory(HfEmpTaskConstant.TASK_CATEGORY_IN_ADD);
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_TRANS_IN:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_ADD);
                hfEmpTaskBo.setTaskCategory(HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN);
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_OPEN:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_ADD);
                hfEmpTaskBo.setTaskCategory(HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN);
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_TRANS_OUT:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_STOP);
                hfEmpTaskBo.setTaskCategory(HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT);
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_CLOSE:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_STOP);
                hfEmpTaskBo.setTaskCategory(HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE);
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_REPAIR:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_REPAIR);
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_ADJUST:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_ADJUST);
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_TRANS_TASK:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_OTHER);
                hfEmpTaskBo.setTaskCategory(HfEmpTaskConstant.TASK_CATEGORY_TRANS_TASK);
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_MULTI_IN:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_MULTI);
                hfEmpTaskBo.setIncludeTaskCategories(
                    StringUtils.join(new Integer[] {
                        HfEmpTaskConstant.TASK_CATEGORY_IN_ADD,
                        HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN,
                        HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN
                    }, ','));
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_MULTI_OUT:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_MULTI);
                hfEmpTaskBo.setIncludeTaskCategories(
                    StringUtils.join(new Integer[] {
                        HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE,
                        HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT
                    }, ','));
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_TURN_CLOSE:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_TURN);
                hfEmpTaskBo.setIncludeTaskCategories(
                    StringUtils.join(new Integer[] {
                        HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE,
                        HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT
                    }, ','));
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_TURN_OPEN:
                hfEmpTaskBo.setProcessCategory(HfEmpTaskConstant.PROCESS_CATEGORY_TURN);
                hfEmpTaskBo.setIncludeTaskCategories(
                    StringUtils.join(new Integer[] {
                        HfEmpTaskConstant.TASK_CATEGORY_IN_ADD,
                        HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN,
                        HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN
                    }, ','));
                break;
            default:
                break;
        }
    }

    public static Integer convertDictItemFromCategories(Integer[] categories) {
        Integer dictItemValue = null;

        switch (categories[0]) {
            case HfEmpTaskConstant.PROCESS_CATEGORY_ADD:
                switch (categories[1]) {
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_ADD;
                        break;
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_TRANS_IN;
                        break;
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_OPEN;
                        break;
                    default:
                        break;
                }
                break;
            case HfEmpTaskConstant.PROCESS_CATEGORY_STOP:
                switch (categories[1]) {
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_TRANS_OUT;
                        break;
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_CLOSE;
                        break;
                    default:
                        break;
                }
                break;
            case HfEmpTaskConstant.PROCESS_CATEGORY_REPAIR:
                switch (categories[1]) {
                    case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_REPAIR;
                        break;
                    default:
                        break;
                }
                break;
            case HfEmpTaskConstant.PROCESS_CATEGORY_ADJUST:
//                switch (categories[1]) {
//                    case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_ADD;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_TRANS_IN;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_OPEN;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_ADJUST:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_ADJUST;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_TRANS_OUT;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_CLOSE;
//                        break;
//                    default:
//                        break;
//                }
                break;
//            case HfEmpTaskConstant.PROCESS_CATEGORY_MODIFY:
//                switch (categories[1]) {
//                    case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_ADD;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_TRANS_IN;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_OPEN;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_REPAIR:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_REPAIR;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_ADJUST:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_ADJUST;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_TRANS_OUT;
//                        break;
//                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
//                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_CLOSE;
//                        break;
//                    default:
//                        break;
//                }
//                break;
            case HfEmpTaskConstant.PROCESS_CATEGORY_OTHER:
                switch (categories[1]) {
                    case HfEmpTaskConstant.TASK_CATEGORY_TRANS_TASK:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_TRANS_TASK;
                        break;
                    default:
                        break;
                }
                break;
            case HfEmpTaskConstant.PROCESS_CATEGORY_MULTI:
                switch (categories[1]) {
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_MULTI_IN;
                        break;
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_MULTI_OUT;
                        break;
                    default:
                        break;
                }
                break;
            case HfEmpTaskConstant.PROCESS_CATEGORY_TURN:
                switch (categories[1]) {
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE:
                    case HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_TURN_CLOSE;
                        break;
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN:
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_ADD:
                    case HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_TURN_OPEN;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return dictItemValue;
    }
}
