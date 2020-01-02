package com.github.kaierwen.mywechat;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.github.kaierwen.mywechat.util.Util;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.net.URL;

/**
 * 微信sdk入口
 */
public class MyWechat {
    public static final String TAG = MyWechat.class.getSimpleName();

    private static final int THUMB_SIZE = 150;

    // IWXAPI 是第三方app和微信通信的openApi接口
    private static IWXAPI sApi;
    private static Application sApp;
    private static BroadcastReceiver sWxReceiver;

    public static void init(Application application, final String wxAppId) {
        if (application == null) {
            throw new RuntimeException("application is null!");
        }
        if (TextUtils.isEmpty(wxAppId)) {
            throw new RuntimeException("wxAppId is null!");
        }
        sApp = application;
        if (sWxReceiver == null) {
            sWxReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    // 将应用的appId注册到微信
                    Log.i(TAG, "WxAPI registerApp " + wxAppId);
                    sApi.registerApp(wxAppId);
                }
            };
        }
        sApi = WXAPIFactory.createWXAPI(sApp, wxAppId, true);// 通过WXAPIFactory工厂，获取IWXAPI的实例
        sApp.registerReceiver(sWxReceiver, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
        boolean registerSuccess = sApi.registerApp(wxAppId);
        Log.i(TAG, "registerSuccess = " + registerSuccess);

        int wxAppSupportAPI = sApi.getWXAppSupportAPI();
        Log.i(TAG, "wxAppSupportAPI = " + wxAppSupportAPI);
    }

    public static void release() {
        if (sApp != null) {
            sApp.unregisterReceiver(sWxReceiver);
        }
    }

    /**
     * 注册到微信
     *
     * @param context
     * @param wxAppId
     */
    public static void regToWx(Context context, final String wxAppId) {
        if (context == null || TextUtils.isEmpty(wxAppId)) return;
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        if (sApi == null)
            sApi = WXAPIFactory.createWXAPI(context, wxAppId, true);

        // 将应用的appId注册到微信
        sApi.registerApp(wxAppId);
    }

    /**
     * 先检查是否已经初始化
     */
    private static void check() {
        if (sApi == null) {
            throw new RuntimeException("WXAPI is null, call MyWechat.init() or MyWechat.regToWx() method first!");
        }
    }

    /**
     * 第一步：请求 CODE
     *
     * @param state (可选填) 用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止 csrf 攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加 session 进行校验
     * @see <a href="https://developers.weixin.qq.com/doc/oplatform/Mobile_App/WeChat_Login/Development_Guide.html"/>
     */
    public static void sendOauthRequest(String state) {
        check();
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = state;
        sApi.sendReq(req);
    }

    /**
     * 调起微信发送消息页面，发给文本给对话，分享和朋友圈等
     *
     * @param title
     * @param scene 微信场景，只支持以下值: {@link SendMessageToWX.Req#WXSceneSession}，{@link SendMessageToWX.Req#WXSceneTimeline}，{@link SendMessageToWX.Req#WXSceneFavorite}，{@link SendMessageToWX.Req#WXSceneSpecifiedContact}
     */
    public static void shareText(String title, int scene) {
        if (scene == SendMessageToWX.Req.WXSceneSession
                || scene == SendMessageToWX.Req.WXSceneTimeline
                || scene == SendMessageToWX.Req.WXSceneFavorite
                || scene == SendMessageToWX.Req.WXSceneSpecifiedContact) {
            check();
            //初始化一个 WXTextObject 对象，填写分享的文本内容
            WXTextObject textObj = new WXTextObject();
            textObj.text = title;

            //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = textObj;
            msg.description = title;

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("text");
            req.message = msg;
            req.scene = scene;
            //调用api接口，发送数据到微信
            sApi.sendReq(req);
        }
    }

    /**
     * 调起微信发送消息页面，发给网页给对话，分享和朋友圈等
     *
     * @param title 标题
     * @param desc  描述
     * @param url   分享出去之后的链接
     * @param icon  分享之后的图标，一般使用app的logo，生成icon代码如下：<br /> {@code Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.send_img);}
     * @param scene 微信场景，只支持以下值: {@link SendMessageToWX.Req#WXSceneSession}，{@link SendMessageToWX.Req#WXSceneTimeline}，{@link SendMessageToWX.Req#WXSceneFavorite}，{@link SendMessageToWX.Req#WXSceneSpecifiedContact}
     */
    public static void shareWebPage(String title, String desc, String url, Bitmap icon, int scene) {
        if (scene == SendMessageToWX.Req.WXSceneSession
                || scene == SendMessageToWX.Req.WXSceneTimeline
                || scene == SendMessageToWX.Req.WXSceneFavorite
                || scene == SendMessageToWX.Req.WXSceneSpecifiedContact) {
            check();
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = url;
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = title;
            msg.description = desc;

            Bitmap thumbBmp = Bitmap.createScaledBitmap(icon, THUMB_SIZE, THUMB_SIZE, true);
            icon.recycle();
            msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            req.scene = scene;
            sApi.sendReq(req);
        }
    }

    /**
     * 调起微信发送消息页面，发给网页给对话，分享和朋友圈等
     *
     * @param title 标题
     * @param desc  描述
     * @param url   分享出去之后的链接
     * @param icon  分享之后的图标，此图片是一个http/https的网络图片
     * @param scene 微信场景，只支持以下值: {@link SendMessageToWX.Req#WXSceneSession}，{@link SendMessageToWX.Req#WXSceneTimeline}，{@link SendMessageToWX.Req#WXSceneFavorite}，{@link SendMessageToWX.Req#WXSceneSpecifiedContact}
     */
    public static void shareWebPage(String title, String desc, String url, String icon, int scene) {
        if (scene == SendMessageToWX.Req.WXSceneSession
                || scene == SendMessageToWX.Req.WXSceneTimeline
                || scene == SendMessageToWX.Req.WXSceneFavorite
                || scene == SendMessageToWX.Req.WXSceneSpecifiedContact) {
            check();
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = url;
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = title;
            msg.description = desc;

            try {
                Bitmap oriBitmap = BitmapFactory.decodeStream(new URL(icon).openStream());//网络原图
                Bitmap thumbBmp = Bitmap.createScaledBitmap(oriBitmap, THUMB_SIZE, THUMB_SIZE, true);//图片压缩成150*150
                oriBitmap.recycle();
                msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            req.scene = scene;
            sApi.sendReq(req);
        }
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 调起微信客户端支付
     *
     * @see <a href="https://pay.weixin.qq.com/wiki/doc/api/app/app_jw.php?chapter=8_5"/>
     */
    public static void startPay(String appId, String partnerId, String prepayId, String sign) {
        check();
        if (BuildConfig.DEBUG) {
            StringBuilder builder = new StringBuilder();
            builder.append("appId = ").append(appId).append("\n")
                    .append("partnerId = ").append(partnerId).append("\n")
                    .append("prepayId = ").append(prepayId).append("\n")
                    .append("sign = ").append(sign);
            Log.d(TAG, "startPay() \n" + builder.toString());
        }
        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerId;
        request.prepayId = prepayId;
        request.packageValue = "Sign=WXPay";
        request.nonceStr = "1101000000140429eb40476f8896f4c9";
        request.timeStamp = "1398746574";
        request.sign = sign;
        sApi.sendReq(request);
    }
}
