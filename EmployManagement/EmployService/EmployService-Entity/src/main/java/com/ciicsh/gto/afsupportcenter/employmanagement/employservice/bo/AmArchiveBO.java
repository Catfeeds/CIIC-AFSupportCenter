package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchive;
import lombok.Data;

import java.util.List;

@Data
public class AmArchiveBO extends AmArchive {

    private  Boolean  end;

    private  String isFrist;

    private Long[] empTaskIds;

    private Boolean formAdvance=false;

    private String employeeName;

    private String idNum;

    private  String remark;

    private List<Long> employmentIds;

    private  String remarkContent;

}
