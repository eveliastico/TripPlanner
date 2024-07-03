package mx.itson.edu.tripplanner.DataProvider

import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.DataClass.Viaje
import java.time.LocalDate

class ViajesProvider {
    companion object{
        val viajesList = listOf<Viaje>(
            Viaje(
                1,
                "CANADA",
                listOf(
                    Actividad("KAYAK", 100f),
                    Actividad("Buceo", 200f)
                ),
                30000f,
                LocalDate.of(2023, 12, 8)
            ),
            Viaje(
                2,
                "MEXICO",
                listOf(
                    Actividad("KAYAK", 100f),
                    Actividad("Buceo", 200f)
                ),
                10000f,
                LocalDate.of(2023, 12, 8)
            ),
            Viaje(
                3,
                "EUA",
                listOf(
                    Actividad("KAYAK", 100f),
                    Actividad("Buceo", 200f)
                ),
                20000f,
                LocalDate.of(2023, 12, 8)
            ),
            Viaje(
                4,
                "FRANCIA",
                listOf(
                    Actividad("KAYAK", 100f),
                    Actividad("Buceo", 200f)
                ),
                50000f,
                LocalDate.of(2023, 12, 8)
            )
        )
    }
}