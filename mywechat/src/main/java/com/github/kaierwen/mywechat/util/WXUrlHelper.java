package com.github.kaierwen.mywechat.util;

/**
 * 微信请求链接
 *
 * @author kevinzhang
 * @since 2019-12-05
 */

public class WXUrlHelper {

    public static String getWxApiUrl(String appId, String secret, String wxCode) {
        return String.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=%s&secret=%s&code=%s&grant_type=authorization_code", appId, secret, wxCode);
    }
}
