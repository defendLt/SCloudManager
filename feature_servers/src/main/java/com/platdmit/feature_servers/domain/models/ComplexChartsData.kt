package com.platdmit.feature_servers.domain.models

import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.ValueFormatter

data class ComplexChartsData(val lineData: LineData, val valueFormatter: ValueFormatter)