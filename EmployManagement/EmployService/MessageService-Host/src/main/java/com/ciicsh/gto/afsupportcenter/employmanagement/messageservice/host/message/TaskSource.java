package com.ciicsh.gto.afsupportcenter.employmanagement.messageservice.host.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Created by houwanhua on 2018/2/24
 */
@Service
public interface TaskSource {
    String EMP_REPORT_OUTPUT = "emp_employment_output_channel";

    @Output(EMP_REPORT_OUTPUT)
    MessageChannel socReportOutput();
}
