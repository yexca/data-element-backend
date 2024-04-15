package com.yexca.context;

public class BaseContext {

    public static ThreadLocal<Long> empThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<Long> roleIdThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<Long> userThreadLocal = new ThreadLocal<>();



    public static void setCurrentEmpId(Long empId) {
        empThreadLocal.set(empId);
    }

    public static Long getCurrentEmpId() {
        return empThreadLocal.get();
    }

    public static void setCurrentRoleId(Long roleId) {
        roleIdThreadLocal.set(roleId);
    }

    public static Long getCurrentRoleId() {
        return roleIdThreadLocal.get();
    }

    public static void setCurrentUserId(Long userId) {
        userThreadLocal.set(userId);
    }

    public static Long getCurrentUserId() {
        return userThreadLocal.get();
    }

}
