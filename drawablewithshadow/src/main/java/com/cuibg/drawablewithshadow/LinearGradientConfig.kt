package com.cuibg.drawablewithshadow

class LinearGradientConfig(var linearGradientColors: IntArray,
                           var linearGradientOriention: LinearGradientOriention) {

    enum class LinearGradientOriention {
        VERTICAL, HORIZONTAL
    }

}