package com.wxaction.constants;

/**
 * @Program: wx-action
 * @Author: ytq
 * @Date: 2022/08/25 13:46:54
 */
public class CommonConstants {
    public static final String BIRTHDAY = "xx-xx";
    public static final String YOUR_BIRTHDAY = "xx-xx";
    public static final String LOVE_DATE = "xxxx-xx-xx";

    public static final String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    public static final String WX_SEND_BY_TPL_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    public static final String WEATHER_QUERY_URL = "https://v0.yiketianqi.com/free/day?appid=%s&appsecret=%s&unescape=1&city=%s";
}
