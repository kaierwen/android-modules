package github.kaierwen.widget.autolayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 支持maxHeight和maxWidth属性的的继承AutoLayout的布局
 *
 * @author kaiyuan.zhang
 * @see <a href="https://github.com/Carbs0126/MaxHeightView/blob/master/Library/src/main/java/cn/carbs/android/maxheightview/library/MaxHeightView.java"/>
 * @since 2018/5/2
 */
public class AutoMaxSizeLayout extends FrameLayout {

    public AutoMaxSizeLayout(Context context) {
        super(context);
    }

    public AutoMaxSizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoMaxSizeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
