package com.ciicsh.gto.afsupportcenter.fundjob.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Created by houwanhua on 2018/2/1.
 */
@Service
public interface TaskSink {
    String HF_REPORT_INPUT = "hf_report_input_channel";

    @Input(HF_REPORT_INPUT)
    MessageChannel hfReportInput();
}
