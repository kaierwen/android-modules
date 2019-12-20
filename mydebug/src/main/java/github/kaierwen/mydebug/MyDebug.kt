package github.kaierwen.mydebug

import github.kaierwen.mydebug.beagle.MyBeagle

/**
 * 调试入口
 *
 * @author kevinzhang
 * @since 2019-12-13
 */
class MyDebug {

    companion object {
        @JvmStatic
        fun getMyBeagle(): MyBeagle {
            return MyBeagle.instance
        }
    }
}