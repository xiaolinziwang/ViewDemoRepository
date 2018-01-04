package com.zhugefang.viewdemo.manongmeiriyiti;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/12/18.
 */

public class DomainComparator implements Comparator<DoMain> {
    @Override
    public int compare(DoMain o1, DoMain o2) {
        //if (o1.mString.compareTo(o2.mString) > 0) {
        //    return 1;
        //} else if (o1.mString.compareTo(o2.mString) == 0) {
        //    return 0;
        //} else {
        //    return -1;
        //}
        return o1.mString.compareTo(o2.mString);
    }
}
