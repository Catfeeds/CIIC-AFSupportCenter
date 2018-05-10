package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 档案序号表,
 * </p>
 *
 * @author LiYueLong
 * @since 2018-04-24
 */
@TableName("am_archive_doc_seq")
public class AmArchiveDocSeq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;

    /**
     * 1：预留档案类型
     * 2：档案类型
     */
	@TableField("type")
	private Integer type;

    /**
     * 档案类别：A、Aa、B、Bb......Z、Zz
     */
	@TableField("doc_type")
	private String docType;

    /**
     * 编号Seq
     */
	@TableField("doc_seq")
	private Integer docSeq;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Integer getDocSeq() {
        return docSeq;
    }

    public void setDocSeq(Integer docSeq) {
        this.docSeq = docSeq;
    }
}
