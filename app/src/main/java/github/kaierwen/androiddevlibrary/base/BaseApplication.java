package github.kaierwen.androiddevlibrary.base;

import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.QbSdk;

/**
 * @author zhangky@chinasunfun.com
 * @since 2017/8/22
 */
public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
//            @Override
//            public void onCoreInitFinished() {
//                Logger.d("onCoreInitFinished");
//            }
//
//            @Override
//            public void onViewInitFinished(boolean finished) {
//                Logger.d("finished = " + finished);
//            }
//        });
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
