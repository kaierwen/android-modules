package github.kaierwen.widget.text;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

/**
 * 下方带选中下划线的控件
 *
 * @author kaiyuan.zhang
 * @since 2018/11/7
 */
public class CheckLabelText extends LinearLayout implements CompoundButton.OnCheckedChangeListener {

    public CheckLabelText(Context context) {
        super(context);
    }

    public CheckLabelText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckLabelText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
