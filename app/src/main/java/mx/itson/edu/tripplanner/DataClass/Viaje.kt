package mx.itson.edu.tripplanner.DataClass

import java.time.LocalDate
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Viaje(
    var id: Long,
    var destino:String,
    var actividades: List<Actividad>,
    var presupuestoEstimado:Float,
    var fechaInicio:LocalDate
)
