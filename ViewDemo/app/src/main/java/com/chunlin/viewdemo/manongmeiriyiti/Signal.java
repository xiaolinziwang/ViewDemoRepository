package com.chunlin.viewdemo.manongmeiriyiti;

/**
 * 项目名称：ViewDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/3/3 12:30
 * 修改人：Administrator
 * 修改时间：2018/3/3 12:30
 * 修改备注：
 */
public enum Signal {
    /*
    *
    * 原始的接口定义常量
public interface IConstants {
    String MON = "Mon";
    String TUE = "Tue";
    String WED = "Wed";
    String THU = "Thu";
    String FRI = "Fri";
    String SAT = "Sat";
    String SUN = "Sun";
}
    *
    *
    *
    * 这段代码实际上调用了4次 Enum(String name, int ordinal)：
new Enum<EnumTest>("RED",0);
new Enum<EnumTest>("GREEN",1);
new Enum<EnumTest>("BLANK",2);
new Enum<EnumTest>("YELLOW",2);
    */
    MON, TUE, WED, THU, FRI, SAT, SUN;
}
