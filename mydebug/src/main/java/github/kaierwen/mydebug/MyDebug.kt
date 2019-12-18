package github.kaierwen.mydebug

import android.app.Application
import android.content.pm.PackageManager
import android.graphics.Color
import android.widget.Toast
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagleCore.configuration.Appearance
import com.pandulapeter.beagleCore.configuration.Trick

/**
 * 调试入口
 *
 * @author kevinzhang
 * @since 2019-12-13
 */
class MyDebug {

    companion object {
        @JvmStatic
        fun beagleInit(app: Application) {
            Beagle.imprint(
                application = app,
                appearance = Appearance(themeResourceId = R.style.BeagleTheme)
            )
            val packageInfo =
                app.packageManager.getPackageInfo(app.packageName, PackageManager.GET_ACTIVITIES)
            var appInfo: StringBuilder = StringBuilder()
            appInfo.append(app.resources.getString(R.string.mydebug_built_time)).append(" -> ")
                .append(BuildConfig.MYDEBUG_BUILD_DATE).append(" ")
                .append(app.resources.getString(R.string.mydebug_built_time_beijing)).append("\n")
                .append(app.resources.getString(R.string.mydebug_package)).append(" -> ")
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
                    Trick.AppInfoButton(),
                    Trick.ScreenshotButton(),
                    Trick.ForceCrashButton(),
                    Trick.KeylineOverlayToggle(
                        gridColor = Color.BLUE
                    ),
                    Trick.ViewBoundsOverlayToggle(
                        color = Color.CYAN
                    ),
                    Trick.DeviceInformationKeyValue()
                )
            )
        }

        private fun String.showToast(app: Application) =
            Toast.makeText(app, this, Toast.LENGTH_SHORT).show()
    }
}