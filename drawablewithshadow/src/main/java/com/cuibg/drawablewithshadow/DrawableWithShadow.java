package com.cuibg.drawablewithshadow;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author cuibg
 * <warning>
 * Before you use DrawableWithShadow, you should set your #View.setLayerType(View.LAYER_TYPE_SOFTWARE, null).
 * Because hardware acceleration can not be assumed it;
 * </warning>
 */
public class DrawableWithShadow extends Drawable {
    /**
     * shdow blur radius
     */
    private final int shadowRadius;
    /**
     * the shadow offset x
     */
    private final int dx;
    /**
     * the shadow offset y
     */
    private final int dy;
    /**
     * shadow color ,this color must be contain alpha
     */
    private final int shadowColor;
    /*
     * corner radius
     */
    private final int cornerRadius;
    /**
     * drawalbe background color
     */
    private final int drawableBgColor;
    private final LinearGradientConfig linearGradientConfig;
    /**
     * shadow paint
     */
    private Paint shadowPaint;

    /**
     * background paint
     */
    private Paint drawableBgPaint;
    private RectF rectF;

    private DrawableWithShadow(int shadowRadius,
                               int dx,
                               int dy,
                               int shadowColor,
                               int cornerRadius,
                               int drawableBgColor,
                               LinearGradientConfig linearGradientConfig) {
        this.shadowRadius = shadowRadius;
        this.dx = dx;
        this.dy = dy;
        this.shadowColor = shadowColor;
        this.cornerRadius = cornerRadius;
        this.drawableBgColor = drawableBgColor;
        this.linearGradientConfig = linearGradientConfig;

        initPaint();
    }

    private void initPaint() {
        initShadowPaint();
        initDrawableBgPaint();
    }

    private void initShadowPaint() {
        shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(Color.TRANSPARENT);
        shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor);
    }

    private void initDrawableBgPaint() {
        drawableBgPaint = new Paint();
        drawableBgPaint.setAntiAlias(true);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left + shadowRadius - dx,
                top + shadowRadius - dy,
                right - shadowRadius - dx,
                bottom - shadowRadius - dy);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, shadowPaint);
        if (linearGradientConfig != null &&
                linearGradientConfig.getLinearGradientColors() != null) {
            LinearGradientConfig.LinearGradientOriention linearGradientOriention = linearGradientConfig.getLinearGradientOriention();
            int[] linearGradientColors = linearGradientConfig.getLinearGradientColors();
            if (linearGradientColors != null) {
                if (linearGradientOriention == LinearGradientConfig.LinearGradientOriention.HORIZONTAL) {
                    drawableBgPaint.setShader(new LinearGradient(rectF.left, rectF.height() / 2, rectF.right, rectF.height() / 2, linearGradientColors, null, Shader.TileMode.CLAMP));
                }
                if (linearGradientOriention == LinearGradientConfig.LinearGradientOriention.VERTICAL) {
                    drawableBgPaint.setShader(new LinearGradient(rectF.width() / 2, rectF.top, rectF.width() / 2, rectF.bottom, linearGradientColors, null, Shader.TileMode.CLAMP));
                }
            }

        } else {
            drawableBgPaint.setColor(drawableBgColor);
        }
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, drawableBgPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        shadowPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        shadowPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public static final class Builder {

        private int shadowRadius = 24;
        private int dx;
        private int dy;
        private int shadowColor = Color.parseColor("#804169E1");
        private int cornerRadius = 10;
        private int drawableBgColor = Color.parseColor("#00CED1");
        private Bitmap bitmap;
        private LinearGradientConfig linearGradientConfig;

        public Builder setShadowRadius(int shadowRadius) {
            this.shadowRadius = shadowRadius;
            return this;
        }

        public Builder setDx(int dx) {
            this.dx = dx;
            return this;
        }

        public Builder setDy(int dy) {
            this.dy = dy;
            return this;
        }

        public Builder setShadowColor(int shadowColor) {
            this.shadowColor = shadowColor;
            return this;
        }

        public Builder setCornerRadius(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            return this;
        }

        public Builder setDrawableBgColor(int drawableBgColor) {
            this.drawableBgColor = drawableBgColor;
            return this;
        }

        public Builder setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Builder setLinearGradientConfig(LinearGradientConfig linearGradientConfig) {
            this.linearGradientConfig = linearGradientConfig;
            return this;
        }

        public DrawableWithShadow build() {
            return new DrawableWithShadow(shadowRadius,
                    dx,
                    dy,
                    shadowColor,
                    cornerRadius, drawableBgColor,
                    linearGradientConfig);
        }
    }
}
