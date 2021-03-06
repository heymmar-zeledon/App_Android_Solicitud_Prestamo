package com.example.solicitudprestamo


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.solicitudprestamo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toolbar().showToolbar(
            this,
            "Registro de cliente",
            false
        )

        val spinner = findViewById<Spinner>(R.id.spinnerSexo)
        val lista = resources.getStringArray(R.array.Spinner_items)
        val adaptadorSpinner = ArrayAdapter(this,android.R.layout.simple_spinner_item,lista)
        spinner.adapter = adaptadorSpinner


        binding.buttonContinuar.setOnClickListener{
            var errors = false
            val nombre =  binding.InputTextNombre.text.toString()
            if(nombre.isEmpty()){
                binding.InputTextNombre.error = "Campo Vacio"
                errors = true
            }
            val apellido = binding.inputTextApellido.text.toString()
            if(apellido.isEmpty()){
                binding.inputTextApellido.error = "Campo Vacio"
                errors = true
            }
            val sexo = binding.spinnerSexo.selectedItem.toString()
            val telefono = binding.editTextPhone.text.toString()
            if(telefono.isEmpty()){
                binding.editTextPhone.error =  "Campo Vacio"
                errors = true
            }
            val cedula = binding.inputTextCedula.text.toString()
            if(cedula.isEmpty()){
                binding.inputTextCedula.error =  "Campo Vacio"
                errors = true
            }
            val ocupacion = binding.inputTextOcupacion.text.toString()
            if(ocupacion.isEmpty()){
                binding.inputTextOcupacion.error =  "Campo Vacio"
                errors = true
            }
            val direccion = binding.inputTextDireccion.text.toString()
            if(direccion.isEmpty()){
                binding.inputTextDireccion.error =  "Campo Vacio"
                errors = true
            }
            if(!errors){
                val cPrest = ClientePrestamo(nombre,apellido,sexo,telefono,cedula,ocupacion, direccion)
                registroCreditoView(cPrest)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_contextual, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.opcion_1){
            Toast.makeText(this,"Visualizando Registros",
                Toast.LENGTH_LONG).show()
            startActivity(Intent(this, ReciclerViewRegistros::class.java ))
        }

        if(item.itemId == R.id.opcion_2){
            Toast.makeText(this,"Aqui veremos el menu principal",
                Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registroCreditoView(dat: ClientePrestamo){
        val intent = Intent(this, RegistryCredit::class.java)
        val bund = Bundle()
        bund.putParcelable("ClienteDat",dat)
        intent.putExtras(bund)
        startActivity(intent)
    }
}
