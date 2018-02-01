package com.ciicsh.gto.afsupportcenter.socjob.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Created by houwanhua on 2018/2/1.
 */
@Service
public interface TaskSink {
    String SOC_REPORT_INPUT = "soc_report_input_channel";

    @Input(SOC_REPORT_INPUT)
    MessageChannel socReportInput();
}
