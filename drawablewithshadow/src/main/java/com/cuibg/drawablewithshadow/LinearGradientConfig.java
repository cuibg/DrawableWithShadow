package com.cuibg.drawablewithshadow;

public class LinearGradientConfig {
    private  int[] linearGradientColors;
    private LinearGradientOriention linearGradientOriention;

    public LinearGradientConfig(int[] linearGradientColors, LinearGradientOriention linearGradientOriention) {
        this.linearGradientColors = linearGradientColors;
        this.linearGradientOriention = linearGradientOriention;
    }

    public int[] getLinearGradientColors() {
        return linearGradientColors;
    }

    public void setLinearGradientColors(int[] linearGradientColors) {
        this.linearGradientColors = linearGradientColors;
    }

    public LinearGradientOriention getLinearGradientOriention() {
        return linearGradientOriention;
    }

    public void setLinearGradientOriention(LinearGradientOriention linearGradientOriention) {
        this.linearGradientOriention = linearGradientOriention;
    }

    public enum LinearGradientOriention{
        VERTICAL,HORIZONTAL
    }
}
