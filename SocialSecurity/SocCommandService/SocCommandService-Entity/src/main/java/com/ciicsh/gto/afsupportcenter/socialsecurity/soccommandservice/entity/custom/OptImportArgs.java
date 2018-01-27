package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by houwanhua on 2018/1/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OptImportArgs {
    private MultipartFile file;
    private String ssMonth;
    private String fileType;
    private Long comAccountId;
}
