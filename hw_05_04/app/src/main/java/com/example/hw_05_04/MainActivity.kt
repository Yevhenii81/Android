package com.example.hw_05_04

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw_05_04.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLineChart()
        loadBitcoinData()
    }

    private fun setupLineChart() {
        binding.lineChart.apply {
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            setDrawGridBackground(false)

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                textColor = Color.WHITE
            }

            axisLeft.textColor = Color.WHITE
            axisRight.isEnabled = false

            legend.textColor = Color.WHITE
        }
    }

    private fun loadBitcoinData() {
        val years = listOf("2020", "2021", "2022", "2023", "2024", "2025", "2026")
        val prices = listOf(8000f, 34000f, 20000f, 16500f, 42000f, 95000f, 92000f)

        val entries = ArrayList<Entry>()
        for (i in prices.indices) {
            entries.add(Entry(i.toFloat(), prices[i]))
        }

        val dataSet = LineDataSet(entries, "Bitcoin Price (USD)").apply {
            color = Color.parseColor("#FF9800")
            setCircleColor(Color.parseColor("#FF9800"))
            lineWidth = 2.5f
            circleRadius = 6f
            setDrawCircleHole(true)
            circleHoleColor = Color.parseColor("#121212")
            setDrawValues(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawFilled(true)
            fillColor = Color.parseColor("#FF9800")
            fillAlpha = 70
        }

        binding.lineChart.data = LineData(dataSet)
        binding.lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(years)

        binding.lineChart.animateX(1200)
        binding.lineChart.invalidate()
    }
}