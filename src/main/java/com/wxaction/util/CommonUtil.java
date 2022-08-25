package com.wxaction.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @Program: wx-action
 * @Author: ytq
 * @Date: 2022/08/25 13:34:49
 */
public class CommonUtil {

    /**
     * 距离下次生日天数
     *
     * @param birthday 你的生日 MM-dd 形式
     * @return 当前距离你生日的天数
     */
    public static long nextBirthdayDays(String birthday) {
        final int compare = DateUtil.compare(DateUtil.parse(birthday, "MM-dd"),
                DateUtil.parse(DateUtil.format(DateUtil.date(), "MM-dd"), "MM-dd"));
        if (compare == 0) {
            return 0;
        }
        if (compare < 0) {
            return DateUtil.between(DateUtil.date()
                    ,
                    DateUtil.parseDate(
                            (DateUtil.date().getField(DateField.YEAR) + 1) + "-" + birthday),
                    DateUnit.DAY);
        }
        return DateUtil.between(DateUtil.date()
                ,
                DateUtil.parseDate(DateUtil.date().getField(DateField.YEAR) + "-" + birthday),
                DateUnit.DAY);
    }

    public static long dateBetweenByUnit(String dateStr, Date date, DateUnit dateUnit) {
        return DateUtil.between(DateUtil.parse(dateStr), date,
                dateUnit);
    }
}
