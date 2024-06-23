package mx.itson.edu.tripplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class DetallesViaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_viaje)

        val pieChart = findViewById<PieChart>(R.id.pieChart)

        // Datos de ejemplo
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(30f, "Category 1"))
        entries.add(PieEntry(20f, "Category 2"))
        entries.add(PieEntry(50f, "Category 3"))

        val dataSet = PieDataSet(entries, "Example Data")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()

        val data = PieData(dataSet)
        pieChart.data = data

        // Configuración opcional
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.setDrawHoleEnabled(false)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setEntryLabelTextSize(12f)
        pieChart.animateY(1000)

        // Refresca el gráfico
        pieChart.invalidate()

    }


}