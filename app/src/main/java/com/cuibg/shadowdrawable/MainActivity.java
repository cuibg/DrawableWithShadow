package com.cuibg.shadowdrawable;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cuibg.drawablewithshadow.DrawableWithShadow;
import com.cuibg.drawablewithshadow.LinearGradientConfig;

public class MainActivity extends AppCompatActivity {

    private static final int DRAWABLE_BG_COLOR = Color.parseColor("#90EE90");
    private static final int SHADOW_COLOR = Color.parseColor("#80000080");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewOne = findViewById(R.id.view_one);
        View viewTwo = findViewById(R.id.view_two);
        View viewThree = findViewById(R.id.view_three);

        DrawableWithShadow buildOne = new DrawableWithShadow.Builder()
                .setDrawableBgColor(DRAWABLE_BG_COLOR)
                .setShadowColor(SHADOW_COLOR)
                .setDx(5)
                .setDy(10).build();
        viewOne.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(viewOne, buildOne);

        DrawableWithShadow buildTwo = new DrawableWithShadow.Builder()
                .setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setShadowColor(SHADOW_COLOR)
                .setDx(0)
                .setDy(-20).build();
        viewTwo.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(viewTwo, buildTwo);

        int[] colors = new int[] {Color.parseColor("#191970"),Color.parseColor("#6495ED")};
        DrawableWithShadow buildThree = new DrawableWithShadow.Builder()
                .setLinearGradientConfig(new LinearGradientConfig(colors, LinearGradientConfig.LinearGradientOriention.HORIZONTAL))
                .setShadowColor(SHADOW_COLOR)
                .setDx(5)
                .setDy(24).build();
        viewThree.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(viewThree, buildThree);
    }
}