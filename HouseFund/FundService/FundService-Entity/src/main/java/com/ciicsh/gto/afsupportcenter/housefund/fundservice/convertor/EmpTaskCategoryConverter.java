package com.ciicsh.gto.afsupportcenter.housefund.fundservice.convertor;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskRejectExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;

public class EmpTaskCategoryConverter {

    public static void convertCategoriesFromDict(HfEmpTaskBo hfEmpTaskBo) {
        int[] categories = convertCategoriesFromDict(hfEmpTaskBo.getDictTaskCategory());
        hfEmpTaskBo.setProcessCategory(categories[0]);
        hfEmpTaskBo.setTaskCategory(categories[1]);
    }

    public static int convertDictItemFromCategories(HfEmpTaskRejectExportBo hfEmpTaskRejectExportBo) {
        int[] categories = { hfEmpTaskRejectExportBo.getProcessCategory(), hfEmpTaskRejectExportBo.getTaskCategory() };
        return convertDictItemFromCategories(categories);
    }

    public static int convertDictItemFromCategories(HfEmpTaskExportBo hfEmpTaskExportBo) {
        int[] categories = { hfEmpTaskExportBo.getProcessCategory(), hfEmpTaskExportBo.getTaskCategory() };
        return convertDictItemFromCategories(categories);
    }

    public static int convertDictItemFromCategories(HfEmpTaskHandleBo hfEmpTaskHandleBo) {
        int[] categories = { hfEmpTaskHandleBo.getProcessCategory(), hfEmpTaskHandleBo.getTaskCategory() };
        return convertDictItemFromCategories(categories);
    }

    public static int[] convertCategoriesFromDict(int dictItemValue) {
        int[] categories = new int[2];

        switch (dictItemValue) {
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_ADD:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_ADD;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_IN_ADD;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_TRANS_IN:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_ADD;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_IN_OPEN:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_ADD;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_TRANS_OUT:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_STOP;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_OUT_CLOSE:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_STOP;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_REPAIR:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_REPAIR;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_REPAIR;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_ADJUST:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_ADJUST;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_ADJUST;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_TRANS_TASK:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_OTHER;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_TRANS_TASK;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_MULTI_IN:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_MULTI;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_IN_TRANS_IN;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_MULTI_OUT:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_MULTI;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_OUT_TRANS_OUT;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_TURN_CLOSE:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_TURN;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_OUT_CLOSE;
                break;
            case HfEmpTaskConstant.DICT_TASK_CATEGORY_TURN_OPEN:
                categories[0] = HfEmpTaskConstant.PROCESS_CATEGORY_TURN;
                categories[1] = HfEmpTaskConstant.TASK_CATEGORY_IN_OPEN;
                break;
            default:
                break;
        }
        return categories;
    }

    public static int convertDictItemFromCategories(int[] categories) {
        int dictItemValue = 0;

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
                switch (categories[1]) {
                    case HfEmpTaskConstant.TASK_CATEGORY_ADJUST:
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_ADJUST;
                        break;
                    default:
                        break;
                }
                break;
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
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_MULTI_IN;
                        break;
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
                        dictItemValue = HfEmpTaskConstant.DICT_TASK_CATEGORY_TURN_CLOSE;
                        break;
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
