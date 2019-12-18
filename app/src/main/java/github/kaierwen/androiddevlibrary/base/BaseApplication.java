package github.kaierwen.androiddevlibrary.base;

import androidx.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import github.kaierwen.mydebug.MyDebug;
import github.kaierwen.androiddevlibrary.BuildConfig;

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
        if (BuildConfig.DEBUG) {
            MyDebug.beagleInit(this);
        }
    }
}
