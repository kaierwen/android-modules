package com.github.kaierwen.mywechat.bean;

/**
 * 第二步：通过 code 获取 access_token
 * <p>
 * 通过请求
 * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
 * <p>
 * 返回的数据Bean
 *
 * @author kevinzhang
 * @see <a href="https://developers.weixin.qq.com/doc/oplatform/Mobile_App/WeChat_Login/Development_Guide.html"/>
 * @since 2019-12-05
 */
public class MyWXAuthInfo {


    /**
     * access_token : ACCESS_TOKEN
     * expires_in : 7200
     * refresh_token : REFRESH_TOKEN
     * openid : OPENID
     * scope : SCOPE
     * unionid : o6_bmasdasdsad6_2sgVt7hMZOPfL
     */

    private String access_token;//接口调用凭证
    private long expires_in;//access_token 接口调用凭证超时时间，单位（秒）
    private String refresh_token;//用户刷新 access_token
    private String openid;//授权用户唯一标识
    private String scope;//用户授权的作用域，使用逗号（,）分隔
    private String unionid;//当且仅当该移动应用已获得该用户的 userinfo 授权时，才会出现该字段

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
