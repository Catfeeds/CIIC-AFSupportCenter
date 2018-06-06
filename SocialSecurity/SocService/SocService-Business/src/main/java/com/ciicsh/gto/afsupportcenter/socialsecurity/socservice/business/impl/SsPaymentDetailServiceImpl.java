package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentDetailService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsPaymentDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsPaymentDetail;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 本地社保应付金额交易记录明细表 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsPaymentDetailServiceImpl extends ServiceImpl<SsPaymentDetailMapper, SsPaymentDetail> implements SsPaymentDetailService {

    @Override
    public List<SsPaymentDetail> paymentDetailQuery(SsPaymentDetail ssPaymentDetail) {
        List<SsPaymentDetail> detailList = baseMapper.paymentDetailQuery(ssPaymentDetail);
        ssPaymentDetailListAdd(detailList);
        return detailList;
    }

    private void ssPaymentDetailListAdd(List<SsPaymentDetail> detailList) {
        if (CollectionUtils.isEmpty(detailList)) {
            return;
        }
        Map map = new HashMap<>();
        initBaseData(map);
        List<SsPaymentDetail> targetList = new ArrayList<>();
        SsPaymentDetail targetDetail = null;
        targetList.addAll(detailList);
        for (int i = 0; i < map.size(); i++) {
            String name = map.get(String.valueOf(i + 1)).toString();
            Long c = targetList.stream().filter(detail -> detail.getPaymentItemName().equals(name)).count();
            if (c == 0) {
                targetDetail = new SsPaymentDetail();
                targetDetail.setSeq(String.valueOf(i + 1));
                targetDetail.setPaymentItemName(name);
                targetList.add(targetDetail);
            }
        }
        detailList.clear();
        detailList.addAll(targetList);
        detailList.sort(Comparator.comparing(SsPaymentDetail::getSeq)); // order by SEQ
        // paymentItemName is not equals , so force update at this place , in another way ,set full name when auto create report.
        targetList.stream().filter(detail -> detail.getPaymentItemName().equals("缴纳合计")).findFirst().orElse(new SsPaymentDetail()).setPaymentItemName("缴纳合计（1+2+3+4+5+6-8）");
    }

    private void initBaseData(Map map) {
        map.put("1", "单位应缴纳社会保险费");
        map.put("2", "单位应补缴历年社会保险费");
        map.put("3", "个人应缴纳社会保险费");
        map.put("4", "个人应补缴历月社会保险费");
        map.put("5", "其他应缴纳社会保险费");
        map.put("6", "预缴社会保险费");
        map.put("7", "");
        map.put("8", "单位缓缴社会保险费");
        map.put("9", "缴纳合计");
    }
}
