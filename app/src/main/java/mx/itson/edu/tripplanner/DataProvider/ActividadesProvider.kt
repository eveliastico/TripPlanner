package mx.itson.edu.tripplanner.DataProvider

import mx.itson.edu.tripplanner.DataClass.Actividad

class ActividadesProvider {
    companion object{
        val actividadesPlaneadasList = listOf<Actividad>(
            Actividad(
                "KAYAK",
                250f
            ),
            Actividad(
                "Buceo",
                100f
            ),
            Actividad(
                "Paseo En Bote",
                300f
            ),
            Actividad(
                "Razers",
                550f
            ),
            Actividad(
                "Fiesta",
                350f
            )
        )
    }
}