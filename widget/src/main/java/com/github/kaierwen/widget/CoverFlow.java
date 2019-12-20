package com.github.kaierwen.widget;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;

public class CoverFlow extends Gallery {

    // mCamera是用来做类3D效果处理，比如Z轴方向上的平移，绕Y轴的旋转等  
    private Camera mCamera = new Camera();
    // mMaxRotationAngle是图片绕Y轴最大旋转角度，也就是屏幕最边上那两张图片的旋转角度  
    private int mMaxRotationAngle = 50;
    // mMaxZoom是图片在Z轴平移的距离，视觉上看上进心来就是放大缩小的效果  
    private int mMaxZoom = -200;
    private int mCoveflowCenter;
    private boolean mAlphaMode = true;
    private boolean mCircleMode = true;
    private SoundPool soundPool;
    private int curSlection = 2;
    Map<Integer, Integer> soundMap = new HashMap<Integer, Integer>();

    public CoverFlow(Context context) {
        super(context);
        this.setStaticTransformationsEnabled(true);
        initSoundMap(context);
    }

    public CoverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setStaticTransformationsEnabled(true);
        initSoundMap(context);
    }

    public CoverFlow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setStaticTransformationsEnabled(true);
        initSoundMap(context);
    }

    private void initSoundMap(Context context) {
        // 创建 SoundPool对象设置最多容纳10个音频。音频的品质为5     
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        // load方法加载音频文件返回对应的ID     
//        soundMap.put(1, soundPool.load(context, R.raw.wheel, 1));
    }

    public int getMaxRotationAngle() {
        return mMaxRotationAngle;
    }

    public void setMaxRotationAngle(int maxRotationAngle) {
        mMaxRotationAngle = maxRotationAngle;
    }

    public boolean getCircleMode() {
        return mCircleMode;
    }

    public void setCircleMode(boolean isCircle) {
        mCircleMode = isCircle;
    }

    public boolean getAlphaMode() {
        return mAlphaMode;
    }

    public void setAlphaMode(boolean isAlpha) {
        mAlphaMode = isAlpha;
    }

    public int getMaxZoom() {
        return mMaxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        mMaxZoom = maxZoom;
    }

    private int getCenterOfCoverflow() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
                + getPaddingLeft();
    }

    // 获取视图中心  
    private static int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth() / 2;
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction,
                                  Rect previouslyFocusedRect) {
        Log.e("ssss", "onFocusChanged");
        // TODO Auto-generated method stub  
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }


    // 重写Garray方法 ，产生层叠和放大效果  
    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        // TODO Auto-generated method stub  
        final int childCenter = getCenterOfView(child);
        final int childWidth = child.getWidth();
        int rotationAngle = 0;
        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX);

        if (childCenter == mCoveflowCenter) {
            transformImageBitmap(child, t, 0, 0);
        } else {
            rotationAngle = (int) (((float) (mCoveflowCenter - childCenter) / childWidth) * mMaxRotationAngle);
            transformImageBitmap(
                    child,
                    t,
                    rotationAngle,
                    (int) Math.floor((mCoveflowCenter - childCenter)
                            / (childWidth == 0 ? 1 : childWidth)));
        }
        return true;
    }

    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCoveflowCenter = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * Transform the Image Bitmap by the Angle passed
     */
    private void transformImageBitmap(View child, Transformation t, int rotationAngle, int d) {
        mCamera.save();
        child.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        child.setRotation(-mTargetDegree);
        child.setRotation(-90);
        final Matrix imageMatrix = t.getMatrix();
        final int imageHeight = child.getLayoutParams().height;
        final int imageWidth = child.getLayoutParams().width;
        final int rotation = Math.abs(rotationAngle);

        // mCamera.translate(0.0f, 0.0f, 100.0f);  
        // As the angle of the view gets less, zoom in  
        // if (rotation <= mMaxRotationAngle) {  
        float zoomAmount = (float) (-140 + (rotation * 2));
        if (rotationAngle < 0) {
            mCamera.translate((float) (-rotation * 0.5),  
                    /*(float) (-rotation * 0.3) + 5*/0, zoomAmount);
        } else {
            mCamera.translate((float) rotation, /*(float) (-rotation * 0.3) + 5*/0,
                    zoomAmount);
        }
        Log.i("info", "---------------------->" + rotationAngle);
        if (mCircleMode) {
//          if (rotation < 40) {  
//              mCamera.translate(0.0f, (100 - rotation * 2.5f), 0.0f);  
//          } else {  
            mCamera.translate(0.0f, (50 - rotation * 1.5f), 0.0f);
//          }  
        }
        // }  
        // mCamera.rotateY(rotationAngle);  
        mCamera.getMatrix(imageMatrix);
        imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
        imageMatrix.postTranslate((imageWidth / 2), (imageHeight / 2));
        mCamera.restore();
    }

    // 重载视图显示顺序让左到中间显示，再到右到中间显示  
    protected int getChildDrawingOrder(int childCount, int i) {
        // Current selected index.
        int selectedIndex = getSelectedItemPosition() - getFirstVisiblePosition();
        //播放声音  
        //Log.e("ssss","curSlection="+curSlection+" getSelectedItemPosition()="+getSelectedItemPosition());  
        if (curSlection != getSelectedItemPosition()) {
            soundPool.play(soundMap.get(1), 1, 1, 1, 0, 1);
            curSlection = getSelectedItemPosition();
        }


        if (selectedIndex < 0) {
            return i;
        }

        if (i < selectedIndex) {
            return i;
        } else if (i >= selectedIndex) {
            return childCount - 1 - i + selectedIndex;
        } else {
            return i;
        }  
        /*long t = getSelectedItemId(); 
        int h = getSelectedItemPosition(); 
        Log.e("getChildDrawingOrder","i="+i+"  childCount="+childCount); 
        if (i < childCount / 2 ) { 
            return i; 
        } 
        return childCount - i - 1 + childCount / 2;*/
    }

}  