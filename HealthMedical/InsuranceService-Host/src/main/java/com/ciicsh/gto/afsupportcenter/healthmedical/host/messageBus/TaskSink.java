package com.ciicsh.gto.afsupportcenter.healthmedical.host.messageBus;

import com.ciicsh.gto.sheetservice.api.MsgConstants;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Created by bill on 17/10/23.
 */
@Service
public interface TaskSink {

    @Input(MsgConstants.AFCompanyCenter.AF_EMP_IN)
    MessageChannel EmpIn();


    String Financial_Rejected="pay-apply-pay-status-stream-input-channel";
    @Input(Financial_Rejected)
    MessageChannel receiveFinancialRejected();

    String Return_Ticket="pay-apply-return-ticket-stream_input_channel";
    @Input(Return_Ticket)
    MessageChannel receiveReturn_Ticket();


}
