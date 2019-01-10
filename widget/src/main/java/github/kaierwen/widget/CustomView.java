package github.kaierwen.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zhangky@chinasunfun.com
 * @since 2017/8/4
 */
public class CustomView extends View {

    private Paint mPaint;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        float center = canvasWidth / 2;//中心点
        float gridInterval = center / 15;//网格等分，30等分

        mPaint.setColor(Color.parseColor("#66333333"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        for (int i = 0; i < 30; i++) {
            canvas.drawLine(gridInterval / 2 + i * gridInterval, 0, gridInterval / 2 + i * gridInterval, canvasHeight, mPaint);//画竖线
            canvas.drawLine(0, gridInterval / 2 + i * gridInterval, canvasWidth, gridInterval / 2 + i * gridInterval, mPaint);//画横线
        }

        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.drawCircle(canvasWidth / 2, canvasWidth / 2, canvasWidth / 4, mPaint);

//        mPaint.setStyle(Paint.Style.FILL);
        canvas.translate(canvasWidth / 8, canvasWidth / 8);
        canvas.scale(0.5f, 0.5f);
        RectF rectF = new RectF(canvasWidth / 4, canvasWidth / 4, canvasWidth / 2, canvasWidth / 2);
        canvas.drawRoundRect(rectF, canvasWidth / 4, canvasWidth / 32, mPaint);

        canvas.translate(-canvasWidth / 8, -canvasWidth / 8);
        canvas.rotate(0, canvasWidth / 2, 0);
        mPaint.setTextSize(150);
        canvas.drawText("发反反复复凤飞飞", 0, 0, mPaint);
    }
}
