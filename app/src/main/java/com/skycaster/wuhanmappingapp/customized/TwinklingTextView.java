package com.skycaster.wuhanmappingapp.customized;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

/**
 * Created by 廖华凯 on 2017/8/2.
 */

public class TwinklingTextView extends android.support.v7.widget.AppCompatTextView{
    private LinearGradient mLinearGradient;
    private TextPaint mTextPaint;
    private Matrix mMatrix;
    private int mTranslate;
    private int mMeasuredWidth;
    private GradientAutoCycleListener mListener;

    public TwinklingTextView(Context context) {
        this(context,null);
    }

    public TwinklingTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TwinklingTextView(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mMeasuredWidth = getMeasuredWidth();
                mLinearGradient=new LinearGradient(
                        0,
                        0,
                        mMeasuredWidth,
                        0,
                        new int[]{Color.RED,Color.YELLOW,Color.BLUE},
                        null,
                        Shader.TileMode.MIRROR
                        );
                mTextPaint=getPaint();
                mTextPaint.setShader(mLinearGradient);
                mMatrix=new Matrix();
            }
        });
    }

    public void setAutoCycleListener(GradientAutoCycleListener listener){
        mListener=listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTranslate+=mMeasuredWidth/20;
        mMatrix.setTranslate(mTranslate,0);
        mLinearGradient.setLocalMatrix(mMatrix);
        if(mTranslate>=mMeasuredWidth&&mListener!=null){
            mListener.onCycleComplete();
            mListener=null;
        }else {
            postInvalidateDelayed(50);
        }
    }

    public interface GradientAutoCycleListener{
        void onCycleComplete();
    }
}
