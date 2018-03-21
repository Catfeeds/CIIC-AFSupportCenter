package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.user;

/**
 * 用户Context类，用于保存和提取登录的用户信息
 *
 * @author sunjian
 * @since 2018-3-5
 */
public class UserContext {
    private static ThreadLocal<UserInfoBO> threadLocal = new ThreadLocal();

    /**
     * 设置用户信息
     *
     * @param userInfoBO
     */
    public static void setUser(UserInfoBO userInfoBO) {
        threadLocal.set(userInfoBO);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserInfoBO getUser() {
        return threadLocal.get();
    }

    /**
     * 移动ThreadLocal中的用户信息
     */
    public static void remove() {
        threadLocal.remove();
    }
}
