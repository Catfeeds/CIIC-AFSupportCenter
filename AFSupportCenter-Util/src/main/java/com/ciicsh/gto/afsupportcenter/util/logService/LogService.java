package com.ciicsh.gto.afsupportcenter.util.logService;

import com.ciicsh.gto.logservice.api.dto.LogReqDTO;
import com.ciicsh.gto.logservice.api.page.Page;

/**
 * 日志服务接口
 *
 * @author 孙坚
 * @since 2018-03-08
 */
public interface LogService {

    /**
     * add debug log
     *
     * @param logContext
     */
    void debug(LogContext logContext);

    /**
     * add info log
     *
     * @param logContext
     */
    void info(LogContext logContext);

    /**
     * add warn log
     *
     * @param logContext
     */
    void warn(LogContext logContext);

    /**
     * add error log
     *
     * @param logContext
     */
    void error(LogContext logContext);

    /**
     * add fatal log
     *
     * @param logContext
     */
    void fatal(LogContext logContext);

}
