package com.ciicsh.gto.afsupportcenter.util;

import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

   public static boolean runWithRetries(int maxRetries, long millionSecs, ThrowingTask t) throws Exception {
        int count = 0;
        while (count < maxRetries) {
            try {
                t.run();
                return true;
            }
            catch (Exception e) {
                logger.error(e.getMessage());
                if (++count >= maxRetries) {
                    throw e;
                }
            }
            Thread.sleep(millionSecs);
        }
        return false;
    }
}
