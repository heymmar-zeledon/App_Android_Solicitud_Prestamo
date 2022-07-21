package com.example.solicitudprestamo

class RegistrosProvider {
    //Aqui mandamos a llamar a los registros de nuestra base de datos SQLite para luego utilizarlos.

    companion object{
        val listprestamos = listOf<DataPrestamos_ReciclerView>(

            //Ejemplo
            DataPrestamos_ReciclerView(
                "Heymmar",
                "Zeledon",
                "18-03134-0",
                88449349,
                "Masculino",
                "Estudiante",
                "Leon",
                15000,
                "17/03/20",
                "19/12/25",
                18000,
                560,
                18
            ),

            DataPrestamos_ReciclerView(
                "Heymmar",
                "Zeledon",
                "18-03134-0",
                88449349,
                "Masculino",
                "Estudiante",
                "Leon",
                15000,
                "17/03/20",
                "19/12/25",
                18000,
                560,
                18
        )
        )
    }
}