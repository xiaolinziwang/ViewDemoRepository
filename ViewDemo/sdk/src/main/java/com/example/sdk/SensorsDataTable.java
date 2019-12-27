package com.example.sdk;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2019-12-27 18:01
 * 修改人：chunlinwang
 * 修改时间：2019-12-27 18:01
 * 修改备注：
 */

/*public*/ enum SensorsDataTable {
    APP_STARTED("app_started"), APP_PAUSED_TIME("app_paused_time"), APP_END_STATE("app_end_state");


    SensorsDataTable(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    private String name;
}

