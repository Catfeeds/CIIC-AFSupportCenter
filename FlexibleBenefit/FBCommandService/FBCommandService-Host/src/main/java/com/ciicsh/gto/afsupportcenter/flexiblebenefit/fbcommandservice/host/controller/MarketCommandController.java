package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.MarketGrantApprovalBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.*;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.Result;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.MarketActivityDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto.MarketApplyDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.*;
import com.ciicsh.gto.sheetservice.api.SheetServiceProxy;
import com.ciicsh.gto.sheetservice.api.dto.request.MissionRequestDTO;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiweizhen
 */
@RestController
@RequestMapping("/marketCommandService")
public class MarketCommandController {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(MarketCommandController.class);

    private static final Integer AGREE = 1;
    private static final Integer DISAGREE = 2;

    @Autowired
    private MarketActivityCommandService marketActivityCommandService;
    @Autowired
    private ApplyRecordCommandService applyRecordCommandService;
    @Autowired
    private ApplyRecordDetailCommandService applyRecordDetailCommandService;
    @Autowired
    private ApplyMarketActivityRecordCommandService applyMarketActivityRecordCommandService;
    @Autowired
    private ApprovalStepCommandService approvalStepCommandService;
    /**
     * 对接任务单系统方法
     */
    @Autowired
    private SheetServiceProxy sheetServiceProxy;

    /**
     * 单条查询
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id) {
        try {
            MarketActivityPO entity = marketActivityCommandService.selectById(id);
            logger.info("根据主键查询活动信息");
            return ResultGenerator.genSuccessResult(entity);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 活动新增
     *
     * @param marketActivityDTO
     * @return
     */
    @PostMapping("/marketActivityAdd")
    public Result marketActivityAdd(@RequestBody MarketActivityDTO marketActivityDTO) {
        MarketActivityPO marketActivityPO = new MarketActivityPO();
        BeanUtils.copyProperties(marketActivityDTO, marketActivityPO);
        try {
            boolean flag = marketActivityCommandService.insert(marketActivityPO);
            logger.info("活动新增");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 活动修改
     *
     * @param marketActivityDTO
     * @return
     */
    @PostMapping("/marketActivityUpdate")
    public Result marketActivityUpdate(@RequestBody MarketActivityDTO marketActivityDTO) {
        MarketActivityPO marketActivityPO = new MarketActivityPO();
        BeanUtils.copyProperties(marketActivityDTO, marketActivityPO);
        try {
            boolean flag = marketActivityCommandService.updateById(marketActivityPO);
            logger.info("活动修改");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 活动申请
     *
     * @param list
     * @return
     */
    @PostMapping(value = "/insertMarketApplyList", consumes = {"application/json"})
    public Result insertMarketApplyList(@RequestBody List<MarketApplyDTO> list) {
        try {
            int t = 0;
            ApplyRecordPO applyRecordPO = new ApplyRecordPO();
            /**前端校验数据大于0条*/
            BeanUtils.copyProperties(list.get(0), applyRecordPO);
            applyRecordPO.setApplyType(2);
            applyRecordCommandService.insert(applyRecordPO);
            logger.info("新增活动申请ApplyRecordPO主键" + applyRecordPO.getApplyRecordId());

            for (MarketApplyDTO item : list) {
                ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();
                ApplyMarketActivityRecordPO applyMarketActivityRecordPO = new ApplyMarketActivityRecordPO();
                BeanUtils.copyProperties(item, applyRecordDetailPO);
                BeanUtils.copyProperties(item, applyMarketActivityRecordPO);

                /**返回申请主键*/
                applyRecordDetailPO.setApplyRecordId(applyRecordPO.getApplyRecordId());
                applyRecordDetailCommandService.insert(applyRecordDetailPO);
                logger.info("新增活动申请ApplyRecordPO主键" + applyRecordDetailPO.getApplyRecordDetailId());

                /**返回申请详情主键*/
                applyMarketActivityRecordPO.setApplyRecordDetailId(applyRecordDetailPO.getApplyRecordDetailId());
                applyMarketActivityRecordCommandService.insert(applyMarketActivityRecordPO);

                /**添加一条审批记录*/
                ApprovalStepPO approvalStep = new ApprovalStepPO();
                approvalStep.setApplyRecordDetailId(applyRecordDetailPO.getApplyRecordDetailId());
                approvalStepCommandService.insert(approvalStep);

                /**对接任务单系统*/
                MissionRequestDTO missionRequestDTO = new MissionRequestDTO();
                missionRequestDTO.setMissionId(applyRecordDetailPO.getApplyRecordDetailId().toString());
                missionRequestDTO.setProcessDefinitionKey("gift_apply");
                com.ciicsh.gto.commonservice.util.dto.Result restResult = sheetServiceProxy.startProcess(missionRequestDTO);
                Map<String, String> startProcessResponseMap = (Map<String, String>) restResult.getObject();
                logger.info(String.valueOf("code:" + restResult.getCode() + ",message:") + restResult.getMessage());
                logger.info(startProcessResponseMap.toString());
                logger.info("sale系统收到启动流程接口返回：" + missionRequestDTO.toString());

                t++;
            }
            boolean flag = (t == list.size());
            logger.info("新增活动申请");
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }

    /**
     * 审批活动申请
     *
     * @param list
     * @return
     */
    @PostMapping(value = "/approvalMarketApplyList", consumes = {"application/json"})
    public Result approvalMarketApplyList(@RequestBody List<MarketGrantApprovalBO> list) {
        try {
            int t = 0;
            for (MarketGrantApprovalBO item : list) {
                ApplyRecordDetailPO applyRecordDetailPO = new ApplyRecordDetailPO();
                ApplyMarketActivityRecordPO applyMarketActivityRecordPO = new ApplyMarketActivityRecordPO();
                BeanUtils.copyProperties(item, applyRecordDetailPO);
                BeanUtils.copyProperties(item, applyMarketActivityRecordPO);

                /**更新数据*/
                applyRecordDetailCommandService.updateById(applyRecordDetailPO);
                applyMarketActivityRecordCommandService.updateById(applyMarketActivityRecordPO);

                List<ApprovalStepPO> approvalStepList = approvalStepCommandService.selectList(applyRecordDetailPO.getApplyRecordDetailId());
                /**去最后一条审批记录，更新数据*/
                ApprovalStepPO approvalStep = approvalStepList.get(approvalStepList.size() - 1);
                //审批时间
                approvalStep.setApproveTime(new Date());
                approvalStep.setApproveRemark(item.getApproveRemark());
                approvalStep.setApproveAction(applyRecordDetailPO.getApprovalStatus());
                approvalStepCommandService.updateById(approvalStep);

                /**对接任务单系统*/
                TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
                taskRequestDTO.setTaskId(applyRecordDetailPO.getTaskId());
                Map<String, Object> variable = new HashMap<>(10);
                if (AGREE.equals(applyRecordDetailPO.getApprovalStatus())) {
                    //同意
                    variable.put("action", "approval");
                } else if (DISAGREE.equals(applyRecordDetailPO.getApprovalStatus())) {
                    //拒绝
                    variable.put("action", "reject");
                }
                taskRequestDTO.setVariables(variable);
                com.ciicsh.gto.commonservice.util.dto.Result restResult = sheetServiceProxy.completeTask(taskRequestDTO);
                Map<String, String> startProcessResponseMap = (Map<String, String>) restResult.getObject();
                logger.info(String.valueOf("code:" + restResult.getCode() + ",message:") + restResult.getMessage());
                logger.info(startProcessResponseMap.toString());
                logger.info("sale系统收到启动流程接口返回：" + taskRequestDTO.toString());

                t++;
            }
            boolean flag = (t == list.size());
            return ResultGenerator.genSuccessResult(flag);
        } catch (Exception e) {
            return ResultGenerator.genServerFailResult();
        }
    }
}
