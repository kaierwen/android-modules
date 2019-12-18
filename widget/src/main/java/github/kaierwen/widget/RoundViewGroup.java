package github.kaierwen.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 带有圆角的View，适用于所有View
 *
 * @author zhangky@chinasunfun.com
 * @since 2017/8/16
 */
public class RoundViewGroup extends ViewGroup {

    private static final float RADIUS_IN_PIXELS = 200f;
    private float mRoundX = RADIUS_IN_PIXELS;
    private float mRoundY = RADIUS_IN_PIXELS;

    public RoundViewGroup(Context context) {
        this(context, null);
    }

    public RoundViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        setWillNotDraw(false);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //https://stackoverflow.com/questions/5574212/android-view-clipping
        Path clipPath = new Path();
        clipPath.addRoundRect(new RectF(canvas.getClipBounds()), mRoundX, mRoundY, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
