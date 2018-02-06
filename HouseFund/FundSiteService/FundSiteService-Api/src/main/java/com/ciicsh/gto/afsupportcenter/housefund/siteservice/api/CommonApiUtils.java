package com.ciicsh.gto.afsupportcenter.housefund.siteservice.api;

import com.ciicsh.gto.basicdataservice.api.DicItemServiceProxy;
import com.ciicsh.gto.basicdataservice.api.dto.DicItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonApiUtils {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    DicItemServiceProxy dicItemServiceProxy;

    /**
     * 根据ID取得名称
     *
     * @param dicItemId
     * @return
     */
    public DicItemDTO selectByDicItemId(String dicItemId) throws Exception {
        return dicItemServiceProxy.selectByDicItemId(dicItemId);
    }

    /**
     * 根据ID取得名称
     *
     * @param listByDicId
     * @return
     */
    public List<DicItemDTO> listByDicId(String listByDicId) throws Exception {
        return dicItemServiceProxy.listByDicId(listByDicId);
    }

    /**
     * 刷新REDIS中的ID数据
     *
     * @param dicItemId
     * @return
     */
    public void fresh2Redis(String dicItemId) throws Exception {
        dicItemServiceProxy.fresh2Redis(dicItemId);
    }

}
