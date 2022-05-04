package com.example.solicitudprestamo


import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.solicitudprestamo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    private fun registroCreditoView(dat: ClientePrestamo){
        val intent = Intent(this, RegistryCredit::class.java)
        val bund = Bundle()
        bund.putParcelable("ClienteDat",dat)
        intent.putExtras(bund)
        startActivity(intent)
    }
}
