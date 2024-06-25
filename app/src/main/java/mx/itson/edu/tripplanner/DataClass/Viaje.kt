package mx.itson.edu.tripplanner.DataClass

import java.time.LocalDate

data class Viaje(
    var destino:String,
    var actividades: List<Actividad>,
    var presupuestoEstimado:Float,
    var fechaInicio:LocalDate
)
