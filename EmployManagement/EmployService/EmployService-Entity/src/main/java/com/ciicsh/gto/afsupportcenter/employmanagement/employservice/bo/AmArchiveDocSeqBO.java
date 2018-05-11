package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import lombok.Data;

/**
 * Created by LiYueLong on 2018/4/24.
 */
@Data
public class AmArchiveDocSeqBO{

    /**
     * 主键
     */
    private Long id;

    /**
     * 1：预留档案类型
     * 2：档案类型
     */
    private Integer type;

    /**
     * 档案类别：A、Aa、B、Bb......Z、Zz
     */
    private String docType;

    /**
     * 编号Seq
     */
    private Integer docSeq;
}
