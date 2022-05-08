package com.ning.utils;

public class DynamicDataSourceHolder {
    /**
     * 对应配置文件写的数据库名
     * */
    public final static String DATE_SOURCE_DEVELOPER = "dataSource";
    public final static String DATE_SOURCE_USER = "dataSource1";


    // 用ThreadLocal来设置当前线程使用哪个dataSource
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDataSourceType(String dataSource) {
        contextHolder.set(dataSource);
    }
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

}
