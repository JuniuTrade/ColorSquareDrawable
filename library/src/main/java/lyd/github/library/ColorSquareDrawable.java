package lyd.github.library;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by shawn on 17/10/13.
 */

public class ColorSquareDrawable extends Drawable {

    //颜色
    private int mColor;
    //是否显示圆形
    private boolean mIsCircular;
    //左上角弧度
    private int mTopLeftRadius;
    //右上角弧度
    private int mTopRightRadius;
    //左下角弧度
    private int mBottomLeftRadius;
    //右下角弧度
    private int mBottomRightRadius;

    private Paint mPaint;

    /**
     * 圆形
     *
     * @param color
     */
    public ColorSquareDrawable(int color) {
        this(color, true);
    }

    /**
     * 圆形／矩形
     *
     * @param color
     * @param isSquare
     */
    public ColorSquareDrawable(int color, boolean isSquare) {
        this(color, isSquare, 0, 0, 0, 0);
    }

    /**
     * 圆角
     *
     * @param color
     * @param radius
     */
    public ColorSquareDrawable(int color, int radius) {
        this(color, false, radius, radius, radius, radius);
    }

    /**
     * 圆角
     *
     * @param color
     * @param topLeftRadius
     * @param topRightRadius
     * @param bottomLeftRadius
     * @param bottomRightRadius
     */
    public ColorSquareDrawable(int color, int topLeftRadius, int topRightRadius, int bottomLeftRadius, int bottomRightRadius) {
        this(color, false, topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);
    }

    public ColorSquareDrawable(int color, boolean isSquare, int topLeftRadius, int topRightRadius, int bottomLeftRadius, int bottomRightRadius) {
        this.mColor = color;
        this.mIsCircular = isSquare;
        this.mTopLeftRadius = topLeftRadius;
        this.mTopRightRadius = topRightRadius;
        this.mBottomLeftRadius = bottomLeftRadius;
        this.mBottomRightRadius = bottomRightRadius;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        //圆形
        if (mIsCircular) {
            float cx = (getBounds().right - getBounds().left) / 2f;
            float cy = (getBounds().bottom - getBounds().top) / 2f;
            canvas.drawCircle(cx + getBounds().left //圆心X坐标
                    , cy + getBounds().top          //圆心Y坐标
                    , Math.abs(cx) <= Math.abs(cy) ? Math.abs(cx) : Math.abs(cy) //半径
                    , mPaint);
        }
        //矩形
        else if (!mIsCircular
                && mTopLeftRadius == 0
                && mTopRightRadius == 0
                && mBottomLeftRadius == 0
                && mBottomRightRadius == 0) {
            canvas.drawRect(getBounds(), mPaint);
        }
        //圆角
        else {
            canvas.drawRoundRect(new RectF(getBounds()), mTopLeftRadius, mTopRightRadius, mPaint);
        }
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        /**
         * 设置颜色过滤器
         */
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        /**
         * 返回这个drawable是否透明
         * PixelFormat.TRANSLUCENT(半透明)、PixelFormat.TRANSPARENT(透明)、PixelFormat.OPAQUE(不透明)
         * 根据颜色是否设置透明度判断 如＃FF******为不透明 ＃00******为透明 #99******为半透明
         */
        switch (mColor >>> 24) {
            case 255:
                return PixelFormat.OPAQUE;
            case 0:
                return PixelFormat.TRANSPARENT;
        }
        return PixelFormat.TRANSLUCENT;
    }
}
