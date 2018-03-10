package com.zhugefang.viewdemo.tools;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 * 兼容gson解析
 */
public class ObjectUtil {

    /**
     * 解析对象
     *
     * @param obj 待解析对象
     * @param tClass 对象model
     */
    public static <T> T changeT(Object obj, Class<T> tClass) {
        try {

            Gson gson = new Gson();
            String s = gson.toJson(obj);
            T t = gson.fromJson(s, tClass);
            return t;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }


    /**
     * 解析数组
     *
     * @param tClass 数组item对象
     */
    public static <T> ArrayList<T> changeToList(Object obj, Class<T[]> tClass) {
        try {
            Gson gson = new Gson();
            String s = gson.toJson(obj);
            T[] t = gson.fromJson(s, tClass);
            List<T> ts = Arrays.asList(t);
            if (ts == null) {
                return null;
            }
            ArrayList<T> list = new ArrayList<>(ts);
            return list;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
