package com.platdmit.simplecloudmanager.app.helpers.charts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.Map;

public class serverValueFormatter extends ValueFormatter {
    private final Map<Float, String> mTitles;
    public serverValueFormatter(Map<Float, String> titles) {
        mTitles = titles;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        return mTitles.get(value);
    }
}
