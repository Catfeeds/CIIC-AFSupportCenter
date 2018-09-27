package com.ciicsh.gto.afsupportcenter.cmjob.task;

import com.ciicsh.gto.logservice.api.LogServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 10:23 2018/9/27
 */
@Component
public class BaseController {

    @Value("${app.id}")
    protected String appId;

    @Autowired
    protected LogServiceProxy logServiceProxy;
}
