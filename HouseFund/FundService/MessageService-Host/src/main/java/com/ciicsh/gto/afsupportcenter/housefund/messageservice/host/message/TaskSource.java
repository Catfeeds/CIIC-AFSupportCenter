package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public interface TaskSource {
    String HF_REPORT_OUTPUT = "hf_report_output_channel";

    @Output(HF_REPORT_OUTPUT)
    MessageChannel hfReportOutput();
}
