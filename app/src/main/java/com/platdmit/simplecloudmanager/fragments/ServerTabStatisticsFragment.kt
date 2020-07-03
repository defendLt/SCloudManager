package com.platdmit.simplecloudmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.vm.StatisticsViewModel
import com.platdmit.simplecloudmanager.vm.factory.SingleElementViewModelFactory
import com.platdmit.data.api.implement.ApiServerRepoImp
import com.platdmit.simplecloudmanager.SCMApp
import com.platdmit.domain.converters.implement.StatisticConvertImp
import com.platdmit.domain.helpers.ContentUpdateService
import com.platdmit.domain.models.ComplexChartsData
import com.platdmit.domain.repo.ServerStatisticsRepo
import com.platdmit.domain.repo.implement.ServerRepoImp
import com.platdmit.domain.repo.implement.UpdateScheduleRepImp
import kotlinx.android.synthetic.main.fragment_server_tab_statistics.*

class ServerTabStatisticsFragment(
        private val mTitle: String = "empty"
) : Fragment(), ServerTabFragment<ServerTabStatisticsFragment> {
    private lateinit var mStatisticsViewModel: StatisticsViewModel
    private var mCpuChart: LineChart? = null
    private var mRamChart: LineChart? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mStatisticsViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(StatisticsViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    SingleElementViewModelFactory(
                            ServerRepoImp(
                                    ApiServerRepoImp(SCMApp.actualApiKeyService),
                                    SCMApp.db,
                                    StatisticConvertImp(),
                                    ContentUpdateService(UpdateScheduleRepImp(SCMApp.db))
                            ), ServerStatisticsRepo::class.java, requireArguments().getLong("ELEMENT_ID")
                    )).get(StatisticsViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_server_tab_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chartStyleInit()
        cpu_chart.setScaleEnabled(false)
        ram_chart.setScaleEnabled(false)

        mStatisticsViewModel.cpuDataLiveData.observe(viewLifecycleOwner, Observer { updateCpuData(it) })
        mStatisticsViewModel.ramDataLiveData.observe(viewLifecycleOwner, Observer { updateRamData(it) })
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabStatisticsFragment {
        return ServerTabStatisticsFragment()
    }

    private fun updateCpuData(cpuData: ComplexChartsData) {
        try {
            cpu_chart.data = cpuData.lineData
            cpu_chart.xAxis.valueFormatter = cpuData.valueFormatter
            cpu_chart.invalidate()
        } catch (e: NullPointerException) {
        }
    }

    private fun updateRamData(ramData: ComplexChartsData) {
        try {
            ram_chart.data = ramData.lineData
            ram_chart.xAxis.valueFormatter = ramData.valueFormatter
            ram_chart.invalidate()
        } catch (e: NullPointerException) {
        }
    }

    private fun chartStyleInit() {
        try {
            val chartDataColor = requireContext().getColor(R.color.colorBaseLight)
            val chartDescString = resources.getString(R.string.chart_cpu_desc)

            cpu_chart.description.text = chartDescString
            cpu_chart.description.textColor = chartDataColor
            cpu_chart.legend.isEnabled = false
            cpu_chart.xAxis.textColor = chartDataColor
            cpu_chart.axisLeft.textColor = chartDataColor

            ram_chart.description.text = chartDescString
            ram_chart.description.textColor = chartDataColor
            ram_chart.legend.isEnabled = false
            ram_chart.xAxis.textColor = chartDataColor
            ram_chart.axisLeft.textColor = chartDataColor

            cpu_chart.animateXY(3000, 3000)
            ram_chart.animateXY(3000, 3000)

        } catch (e: NullPointerException) {
        }
    }

    companion object {
        private val TAG = ServerTabStatisticsFragment::class.java.simpleName
    }
}