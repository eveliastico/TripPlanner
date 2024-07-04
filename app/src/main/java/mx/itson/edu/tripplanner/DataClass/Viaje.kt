package mx.itson.edu.tripplanner.DataClass

import java.time.LocalDate
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Viaje(
    var id: String = "",
    var userId: String = "",
    var destino:String = "",
    var actividades: List<Actividad> = emptyList(),
    var presupuestoEstimado:Float = 0f,
    var fechaInicio: String = ""
)
