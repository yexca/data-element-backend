package com.yexca.context;

public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentEmpId(Long empId) {
        threadLocal.set(empId);
    }

    public static Long getCurrentEmpId() {
        return threadLocal.get();
    }

    public static void removeCurrentUsername() {
        threadLocal.remove();
    }

}
