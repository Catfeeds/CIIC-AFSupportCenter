package com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate;

import com.ciicsh.gto.identityservice.api.dto.SmRoleInfoDTO;
import com.ciicsh.gto.identityservice.api.dto.response.UserInfoResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class UserContext {
    private static ThreadLocal<UserInfoResponseDTO> threadLocal = new ThreadLocal();

    /**
     * 设置用户信息
     *
     * @param userInfoBO
     */
    public static void setUser(UserInfoResponseDTO userInfoBO) {
        threadLocal.set(userInfoBO);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserInfoResponseDTO getUser() {
        return threadLocal.get();
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public static String getUserId() {
        return threadLocal.get().getUserId();
    }

    /**
     * 获取用户角色id信息
     *
     * @return
     */
    public static List<String> getRoleIds() {
        List<String> roleIdList = new ArrayList<>();
        for(SmRoleInfoDTO item : getUser().getSmRoleInfos()){
            roleIdList.add(item.getRoleCode());
        }
        return roleIdList;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static String getUserName() {
        try{
            return threadLocal.get().getLoginName();
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 移除ThreadLocal中的用户信息
     */
    public static void remove() {
        threadLocal.remove();
    }

}
