package com.platdmit.simplecloudmanager.utilities.charts

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class ServerValueFormatter(private val titles: Map<Float, String>) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase): String {
        return titles[value] ?: ""
    }
}