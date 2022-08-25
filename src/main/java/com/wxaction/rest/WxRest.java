package com.wxaction.rest;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wxaction.config.MsgConfig;
import com.wxaction.config.WeatherConfig;
import com.wxaction.config.WxConfig;
import com.wxaction.constants.CommonConstants;
import com.wxaction.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

/**
 *     {{DATE.DATA}}
 *
 *     城市：{{CITY.DATA}}
 *
 *     天气：{{WEATHER.DATA}}
 *
 *     白天气温：{{LOW_OC.DATA}} ℃
 *
 *     夜间气温：{{HEIGN_OC.DATA}} ℃
 *
 *     我们相恋：{{LOVE_DAY.DATA}} 天
 *
 *     距离你的生日：{{YOUR_BRITHDAY.DATA}} 天 距离我的生日：{{MY_BRITHDAY.DATA}} 天
 *
 *     留言：
 *
 *
 *     {{COMMENT.DATA}}
 */

/**
 * @Program: wx-action
 * @Author: ytq
 * @Date: 2022/08/23 15:13:01
 */
@RestController
public class WxRest {
    private WxConfig wxConfig;
    private WeatherConfig weatherConfig;
    private MsgConfig msgConfig;

    @Autowired
    public void setMsgConfig(final MsgConfig msgConfig) {
        this.msgConfig = msgConfig;
    }

    @Autowired
    public void setWxConfig(final WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    @Autowired
    public void setWeatherConfig(final WeatherConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void run() {
        for (final String openId : msgConfig.getScheduleSendOpenId()) {
            send(openId, null);
        }
    }

    @GetMapping("/send")
    public Map<String, Object> send(String toUser, String templateId) {
        final String result = HttpUtil.get(
                String.format(CommonConstants.WX_ACCESS_TOKEN_URL,
                        wxConfig.getAppid(), wxConfig.getSecret()));
        final Gson gson = new GsonBuilder().create();
        final Map<String, Object> resultMap = gson.fromJson(result,
                new TypeToken<Map<String, Object>>() {
                }.getType());
        final Map<String, Object> paramMap = gson.fromJson(new InputStreamReader(
                        Objects.requireNonNull(
                                this.getClass().getClassLoader().getResourceAsStream("template-json"))),
                new TypeToken<Map<String, Object>>() {
                }.getType());
        if (toUser != null) {
            paramMap.put("touser", toUser);
        }
        if (templateId != null) {
            paramMap.put("template_id", templateId);
        }
        final String weather =
                HttpUtil.get(String.format(
                        CommonConstants.WEATHER_QUERY_URL
                        , weatherConfig.getAppid(), weatherConfig.getSecret(),
                        weatherConfig.getCity()));
        final Map<String, Object> weatherMap = gson.fromJson(weather,
                new TypeToken<Map<String, Object>>() {
                }.getType());

        Object[] params =
                new Object[] {
                        weatherMap.get("date") + " " + weatherMap.get("week"),
                        weatherConfig.getCity(),
                        weatherMap.get("wea"),
                        weatherMap.get("tem_day"),
                        weatherMap.get("tem_night"),
                        CommonUtil.dateBetweenByUnit(CommonConstants.LOVE_DATE,
                                DateUtil.date(),
                                DateUnit.DAY),
                        CommonUtil.nextBirthdayDays(CommonConstants.BIRTHDAY),
                        CommonUtil.nextBirthdayDays(CommonConstants.YOUR_BIRTHDAY),
                        msgConfig.getSuggest()};

        final String sendResult = HttpUtil.post(String.format(
                CommonConstants.WX_SEND_BY_TPL_MSG_URL,
                resultMap.get("access_token").toString()),
                String.format(gson.toJson(paramMap), params));
        return gson.fromJson(sendResult,
                new TypeToken<Map<String, Object>>() {
                }.getType());
    }
}
