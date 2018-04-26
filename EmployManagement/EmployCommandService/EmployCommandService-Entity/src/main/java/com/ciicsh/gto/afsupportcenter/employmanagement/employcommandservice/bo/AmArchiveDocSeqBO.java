package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
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
