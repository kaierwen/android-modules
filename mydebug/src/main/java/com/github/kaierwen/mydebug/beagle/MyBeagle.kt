package com.github.kaierwen.mydebug.beagle

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Point
import android.view.WindowManager
import android.widget.Toast
import com.github.kaierwen.mydebug.BuildConfig
import com.github.kaierwen.mydebug.R
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagleCore.configuration.Appearance
import com.pandulapeter.beagleCore.configuration.Trick

/**
 * beagle库入口
 *
 * @author kevinzhang
 * @since 2019/12/19
 */
class MyBeagle {

    companion object {
        val instance = MyBeagle()
    }

    /**
     * 初始化
     * @param app  Application
     */
    fun init(app: Application) {
        init(app, "")
    }

    /**
     * 初始化
     * @param app  Application
     * @param buildTime 编译时间
     */
    fun init(app: Application, buildTime: String) {
        var drawerWidth = getScreenWidth(app) * 0.85 //set drawerWidth 85% of screenWidth
        Beagle.imprint(
            application = app,
            appearance = Appearance(
                themeResourceId = R.style.BeagleTheme,
                drawerWidth = drawerWidth.toInt()
            )
        )
        val packageInfo =
            app.packageManager.getPackageInfo(app.packageName, PackageManager.GET_ACTIVITIES)
        var appInfo: StringBuilder = StringBuilder()
        if (buildTime.isNotEmpty()) {
            appInfo.append(app.resources.getString(R.string.mydebug_built_time)).append(" -> ")
                .append(buildTime).append(" ")
                .append(app.resources.getString(R.string.mydebug_built_time_beijing)).append("\n")
        }
        appInfo.append(app.resources.getString(R.string.mydebug_package)).append(" -> ")
            .append(app.packageName).append("\n")
            .append(app.resources.getString(R.string.mydebug_version_name)).append(" -> v")
            .append(packageInfo.versionName).append("\n")
            .append(app.resources.getString(R.string.mydebug_version_code))
            .append(" -> ").append(packageInfo.versionCode)

        Beagle.learn(
            listOf(
                Trick.Header(
                    title = app.resources.getString(R.string.app_name),
                    text = appInfo.toString()
                ),
                Trick.AppInfoButton(app.resources.getString(R.string.mydebug_app_info)),
                Trick.ScreenshotButton(app.resources.getString(R.string.mydebug_screen_shot)),
                Trick.ForceCrashButton(app.resources.getString(R.string.mydebug_force_crash)),
                Trick.KeylineOverlayToggle(
                    app.resources.getString(R.string.mydebug_show_grid),
                    gridColor = Color.BLUE
                ),
                Trick.ViewBoundsOverlayToggle(
                    app.resources.getString(R.string.mydebug_show_bounds),
                    color = Color.CYAN
                ),
                Trick.DeviceInformationKeyValue(app.resources.getString(R.string.mydebug_device_info))
            )
        )
    }

    private fun String.showToast(app: Application) =
        Toast.makeText(app, this, Toast.LENGTH_SHORT).show()

    /**
     * see https://github.com/Blankj/AndroidUtilCode/blob/master/lib/utilcode/src/main/java/com/blankj/utilcode/util/ScreenUtils.java
     */
    private fun getScreenWidth(app: Application): Int {
        val windowManager: WindowManager =
            app.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var point = Point()
        windowManager.defaultDisplay.getRealSize(point)
        return point.x
    }

    fun getVersion(): String {
        return BuildConfig.VERSION_NAME
    }

    fun getBeagle(): Beagle {
        return Beagle
    }

    /**
     * Tries to close the current Activity's debug drawer. For proper UX this should be used in onBackPressed() to block any other logic if it returns true.
     *
     * @param activity - The current [Activity] instance.
     * @return - True if the drawer was open, false otherwise
     */
    fun dismiss(activity: Activity): Boolean {
        return Beagle.dismiss(activity)
    }
}