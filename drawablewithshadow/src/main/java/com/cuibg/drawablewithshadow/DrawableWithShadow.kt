package com.cuibg.drawablewithshadow

import android.graphics.*
import android.graphics.drawable.Drawable
import com.cuibg.drawablewithshadow.LinearGradientConfig.LinearGradientOriention

/**
 * @author cuibg
 * <warning>
 * Before you use DrawableWithShadow, you should set your #View.setLayerType(View.LAYER_TYPE_SOFTWARE, null).
 * Because hardware acceleration can not be assumed it;
</warning> *
 */
class DrawableWithShadow private constructor(
        /**
         * shdow blur radius
         */
        private val shadowRadius: Int,
        /**
         * the shadow offset x
         */
        private val dx: Int,
        /**
         * the shadow offset y
         */
        private val dy: Int,
        /**
         * shadow color ,this color must be contain alpha
         */
        private val shadowColor: Int,
        /*
     * corner radius
     */
        private val cornerRadius: Int,
        /**
         * drawalbe background color
         */
        private val drawableBgColor: Int,
        private val linearGradientConfig: LinearGradientConfig?) : Drawable() {

    /**
     * shadow paint
     */
    private var shadowPaint: Paint? = null

    /**
     * background paint
     */
    private var drawableBgPaint: Paint? = null
    private var rectF: RectF? = null

    init {
        initPaint()
    }

    private fun initPaint() {
        initShadowPaint()
        initDrawableBgPaint()
    }

    private fun initShadowPaint() {
        shadowPaint = Paint()
        shadowPaint!!.isAntiAlias = true
        shadowPaint!!.color = Color.TRANSPARENT
        shadowPaint!!.setShadowLayer(shadowRadius.toFloat(), dx.toFloat(), dy.toFloat(), shadowColor)
    }

    private fun initDrawableBgPaint() {
        drawableBgPaint = Paint()
        drawableBgPaint!!.isAntiAlias = true
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        rectF = RectF((left + shadowRadius - dx).toFloat(),
                (top + shadowRadius - dy).toFloat(),
                (right - shadowRadius - dx).toFloat(),
                (bottom - shadowRadius - dy).toFloat())
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(rectF!!, cornerRadius.toFloat(), cornerRadius.toFloat(), shadowPaint!!)
        if (linearGradientConfig != null &&
                linearGradientConfig.linearGradientColors != null) {
            val linearGradientOriention = linearGradientConfig.linearGradientOriention
            val linearGradientColors = linearGradientConfig.linearGradientColors
            if (linearGradientColors != null) {
                if (linearGradientOriention == LinearGradientOriention.HORIZONTAL) {
                    drawableBgPaint!!.shader = LinearGradient(rectF!!.left, rectF!!.height() / 2, rectF!!.right, rectF!!.height() / 2, linearGradientColors, null, Shader.TileMode.CLAMP)
                }
                if (linearGradientOriention == LinearGradientOriention.VERTICAL) {
                    drawableBgPaint!!.shader = LinearGradient(rectF!!.width() / 2, rectF!!.top, rectF!!.width() / 2, rectF!!.bottom, linearGradientColors, null, Shader.TileMode.CLAMP)
                }
            }
        } else {
            drawableBgPaint!!.color = drawableBgColor
        }
        canvas.drawRoundRect(rectF!!, cornerRadius.toFloat(), cornerRadius.toFloat(), drawableBgPaint!!)
    }

    override fun setAlpha(alpha: Int) {
        shadowPaint!!.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        shadowPaint!!.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    class Builder {
        private var shadowRadius = 24
        private var dx = 0
        private var dy = 0
        private var shadowColor = Color.parseColor("#804169E1")
        private var cornerRadius = 10
        private var drawableBgColor = Color.parseColor("#00CED1")
        private var bitmap: Bitmap? = null
        private var linearGradientConfig: LinearGradientConfig? = null
        fun setShadowRadius(shadowRadius: Int): Builder {
            this.shadowRadius = shadowRadius
            return this
        }

        fun setDx(dx: Int): Builder {
            this.dx = dx
            return this
        }

        fun setDy(dy: Int): Builder {
            this.dy = dy
            return this
        }

        fun setShadowColor(shadowColor: Int): Builder {
            this.shadowColor = shadowColor
            return this
        }

        fun setCornerRadius(cornerRadius: Int): Builder {
            this.cornerRadius = cornerRadius
            return this
        }

        fun setDrawableBgColor(drawableBgColor: Int): Builder {
            this.drawableBgColor = drawableBgColor
            return this
        }

        fun setBitmap(bitmap: Bitmap?): Builder {
            this.bitmap = bitmap
            return this
        }

        fun setLinearGradientConfig(linearGradientConfig: LinearGradientConfig?): Builder {
            this.linearGradientConfig = linearGradientConfig
            return this
        }

        fun build(): DrawableWithShadow {
            return DrawableWithShadow(shadowRadius,
                    dx,
                    dy,
                    shadowColor,
                    cornerRadius, drawableBgColor,
                    linearGradientConfig)
        }
    }

}