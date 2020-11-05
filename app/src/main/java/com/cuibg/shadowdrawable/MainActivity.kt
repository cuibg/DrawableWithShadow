package com.cuibg.shadowdrawable

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.cuibg.drawablewithshadow.DrawableWithShadow
import com.cuibg.drawablewithshadow.LinearGradientConfig

class MainActivity : AppCompatActivity() {
    private val viewOne: View by lazy {
        findViewById(R.id.view_one) as View
    }

    private val viewTwo: View by lazy {
        findViewById(R.id.view_two) as View
    }

    private val viewThree: View by lazy {
        findViewById(R.id.view_three) as View
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val colors1 = intArrayOf(DRAWABLE_BG_COLOR, Color.parseColor("#6495ED"))
        val buildOne = DrawableWithShadow.Builder()
                .setShadowColor(SHADOW_COLOR)
                .setLinearGradientConfig(LinearGradientConfig(colors1, LinearGradientConfig.LinearGradientOriention.VERTICAL))
                .setDx(5)
                .setDy(10).build()
        viewOne.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        ViewCompat.setBackground(viewOne, buildOne)
        val buildTwo = DrawableWithShadow.Builder()
                .setBitmap(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setShadowColor(SHADOW_COLOR)
                .setDx(0)
                .setDy(-20).build()
        viewTwo.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        ViewCompat.setBackground(viewTwo, buildTwo)
        val colors = intArrayOf(Color.parseColor("#191970"), Color.parseColor("#6495ED"))
        val buildThree = DrawableWithShadow.Builder()
                .setLinearGradientConfig(LinearGradientConfig(colors, LinearGradientConfig.LinearGradientOriention.HORIZONTAL))
                .setShadowColor(SHADOW_COLOR)
                .setDx(5)
                .setDy(24).build()
        viewThree.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        ViewCompat.setBackground(viewThree, buildThree)
    }

    companion object {
        private val DRAWABLE_BG_COLOR = Color.parseColor("#90EE90")
        private val SHADOW_COLOR = Color.parseColor("#80000080")
    }
}