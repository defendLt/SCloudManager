package com.platdmit.simplecloudmanager.domain.models;

import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class ComplexChartsData {
    private LineData mLineData;
    private ValueFormatter mValueFormatter;

    public ComplexChartsData(LineData lineData, ValueFormatter valueFormatter) {
        mLineData = lineData;
        mValueFormatter = valueFormatter;
    }

    public LineData getLineData() {
        return mLineData;
    }

    public ValueFormatter getValueFormatter() {
        return mValueFormatter;
    }
}
