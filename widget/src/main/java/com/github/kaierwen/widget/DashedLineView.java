package com.github.kaierwen.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.kaierwen.widget.R;

/**
 * 虚线View
 *
 * @author kaiyuan.zhang
 * @see <a href="https://github.com/sslcs/DashLine"/>
 * @see <a href="https://github.com/ZQiang94/DashedLine"/>
 * @see <a href="https://stackoverflow.com/questions/20583298/creating-horizontal-and-vertical-dotted-lines-in-android"/>
 * @since 2018/2/28
 */
public class DashedLineView extends View {

    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    private Paint mPaint;
    private int orientation;
    int dashGap, dashLength, dashThickness;
    int color;

    public DashedLineView(Context context) {
        this(context, null);
    }

    public DashedLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashedLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速，由于硬件加速导致虚线绘制不出来，see https://issuetracker.google.com/issues/36945767
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DashedLineView, 0, 0);
        try {
            dashGap = a.getDimensionPixelSize(R.styleable.DashedLineView_dashGap, 5);
            dashLength = a.getDimensionPixelSize(R.styleable.DashedLineView_dashLength, 5);
            dashThickness = a.getDimensionPixelSize(R.styleable.DashedLineView_dashThickness, 3);
            color = a.getColor(R.styleable.DashedLineView_color, 0xff000000);
            orientation = a.getInt(R.styleable.DashedLineView_orientation, ORIENTATION_HORIZONTAL);
        } finally {
            a.recycle();
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dashThickness);
        mPaint.setPathEffect(new DashPathEffect(new float[]{dashLength, dashGap,}, 0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (orientation == ORIENTATION_HORIZONTAL) {
            float center = getHeight() * 0.5f;
            canvas.drawLine(0, center, getWidth(), center, mPaint);
        } else {
            float center = getWidth() * 0.5f;
            canvas.drawLine(center, 0, center, getHeight(), mPaint);
        }
    }
}
