
package com.example.solicitudprestamo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.solicitudprestamo.databinding.ActivityRegistroCreditoBinding
import java.text.SimpleDateFormat
import java.util.*


class RegistryCredit : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var adaptadorSpinner: ArrayAdapter<String>
    private var bool: Boolean = false
    private lateinit var Viewbinding: ActivityRegistroCreditoBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Viewbinding = ActivityRegistroCreditoBinding.inflate(layoutInflater)
        setContentView(Viewbinding.root)

        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mToolbar.setNavigationOnClickListener {
            showDialog()
        }

        val spinner = findViewById<Spinner>(R.id.spinnerInteres)
        val lista: Array<String> = resources.getStringArray(R.array.Spinner_items_credits)
        adaptadorSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        spinner.onItemSelectedListener = this
        spinner.adapter = adaptadorSpinner
        spinner.setSelection(0)

        val client = intent.extras?.getParcelable<ClientePrestamo>("ClienteDat")

        if(client==null){
            Toast.makeText(this,"Los datos no fueron Guardados!",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Cliente: "+ client.Nombre + " " + client.Apellido + " Guardado correctamente!",Toast.LENGTH_LONG).show()
            val msjDetails = findViewById<TextView>(R.id.detallesCliente)

            val clientDetName = "Nombre: " + client.Nombre
            val clientDetApellido = "Apellido: " + client.Apellido
            val clientDetCedula = "Cedula: " + client.Cedula
            val msjView = getText(R.string.detalles_del_cliente).toString()
            msjDetails.text = msjView + "\n\n" +
                    clientDetName + "\n\n" +
                    clientDetApellido + "\n\n" +
                    clientDetCedula
        }

        val dater = findViewById<TextView>(R.id.textViewFechaInicio)
        val zone = "GMT-6"
        val dformat = "dd/MM/yyyy"
        val fech = obtenerFechaConFormato(dformat, zone)
        dater.text = fech

        val editTextNumberMes = findViewById<EditText>(R.id.editTextNumber)
        editTextNumberMes.addTextChangedListener(object : TextWatcher {
            val plazo = findViewById<EditText>(R.id.editTextNumber)
            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable) {

                val monto = findViewById<EditText>(R.id.editTextMonto)
                val cuotaFinal = findViewById<TextView>(R.id.textViewMontoCuotaR)
                if (monto.text.toString().trim() == "") {
                    cuotaFinal.text = "0.00 NIO"
                }
                else if(plazo.text.toString().trim() == ""){
                    cuotaFinal.text = "0.00 NIO"
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val daterfinish = findViewById<TextView>(R.id.textViewFechaFinal)

                if (plazo.text.toString().trim() == "") {
                    plazo.error = "No puede quedar vacio"
                    daterfinish.text = "Dia/Mes/Año"
                } else {
                    val milisec = milisegundos()
                    val msecplazo: Long = plazo.text.toString().toLong()
                    val milseccalc: Long = 2629743833 * msecplazo
                    val fechafin: Long = milisec + milseccalc
                    val dateString = timet(fechafin)
                    daterfinish.text = dateString

                    val monto = findViewById<EditText>(R.id.editTextMonto)
                    val cuotaFinal = findViewById<TextView>(R.id.textViewMontoCuotaR)
                    if(monto.text.toString().trim() == ""){
                        monto.error = "Campo vacio"
                        cuotaFinal.text = "0.00 NIO"
                    }
                    else{
                        val montoFin = findViewById<TextView>(R.id.textViewMontoTotal)
                        val montoF = montoFin.text.toString().toFloat()
                        val plazoF = plazo.text.toString().toFloat()
                        val rCuota = calcCuota(montoF,plazoF)
                        cuotaFinal.text = rCuota.toString()
                    }
                }
            }
        })

        val montoFinal = findViewById<TextView>(R.id.textViewMontoTotal)
        val monto = findViewById<EditText>(R.id.editTextMonto)
        monto.addTextChangedListener(object : TextWatcher {

            val cuotaFinal = findViewById<TextView>(R.id.textViewMontoCuotaR)
            val plazo = findViewById<EditText>(R.id.editTextNumber)
            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable) {

                if (monto.text.toString().trim() == "") {
                    cuotaFinal.text = "0.00 NIO"
                }
                else if(plazo.text.toString().trim() == ""){
                    cuotaFinal.text = "0.00 NIO"
                }

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (monto.text.toString().trim() == "") {
                    spinner.isEnabled = true
                    monto.error = "No puede quedar vacio"
                    montoFinal.text = "0.00 NIO"
                } else {
                    val numSpinner = validarSpinner()
                    spinner.setSelection(numSpinner)
                    if(numSpinner == 0){
                        spinner.isEnabled = false
                    }
                    if(numSpinner == 1){
                        spinner.isEnabled = false
                    }
                    if(numSpinner == 2){
                        spinner.isEnabled = true
                    }

                    val montoF = findViewById<TextView>(R.id.textViewMontoTotal)
                    val valMonto: Float = monto.text.toString().toFloat()
                    val interes: String = spinner.selectedItem.toString()
                    val interesint: Float = interes.toFloat()
                    val montTotal: Float = montoTotal(valMonto, interesint)
                    montoF.text = montTotal.toString()

                    val cuotaFinal = findViewById<TextView>(R.id.textViewMontoCuotaR)
                    val plazo = findViewById<EditText>(R.id.editTextNumber)
                    if(plazo.text.toString().trim() == ""){
                        plazo.error = "Campo Vacio"
                        cuotaFinal.text = "0.00 NIO"
                    }
                    else{
                        val plazoF = plazo.text.toString().toFloat()
                        val rcuota = calcCuota(valMonto,plazoF)
                        cuotaFinal.text = rcuota.toString()
                    }
                }
            }
        })

        val guardarPrestamo = findViewById<Button>(R.id.buttonsubmit)
        guardarPrestamo.setOnClickListener {
            val client2 = intent.extras?.getParcelable<ClientePrestamo>("ClienteDat")

            if(client2==null){
                Toast.makeText(this,"Los datos no fueron Guardados!",Toast.LENGTH_LONG).show()
            }
            //Validaciones finales antes de enviar a la base de datos.
            var errors = false
            if(monto.text.toString().isEmpty()){
                monto.error = "Campo Vacio"
                errors = true
            }
            else if(Viewbinding.editTextNumber.text.toString().isEmpty()){
                Viewbinding.editTextNumber.error = "Campo Vacio"
                errors = true
            }

            if(!errors){
                var objPrestamo = client2?.let { it1 -> DataPrestamos_ReciclerView(
                    it1.Nombre,
                    it1.Apellido,
                    it1.Cedula,
                    it1.Telefono.toInt(),
                    it1.Sexo,
                    it1.Ocupacion,
                    it1.Direccion,
                    Viewbinding.editTextMonto.text.toString().toInt(),
                    dater.text.toString(),
                    Viewbinding.textViewFechaFinal.text.toString(),
                    Viewbinding.textViewMontoTotal.text.toString().toInt(),
                    Viewbinding.textViewMontoCuotaR.text.toString().toInt(),
                    Viewbinding.editTextNumber.text.toString().toInt()
                )
                }
            }
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun obtenerFechaConFormato(formato: String?, zonaHoraria: String?): String? {
        val calendar = Calendar.getInstance()
        val date = calendar.time
        val sdf = SimpleDateFormat(formato)
        sdf.timeZone = TimeZone.getTimeZone(zonaHoraria)
        return sdf.format(date)
    }

    private fun milisegundos(): Long {
        val date = Date()
        return date.time
    }

    @SuppressLint("SimpleDateFormat")
    private fun timet(timeinMillies: Long): String? {
        val date: String?
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        date = formatter.format(Date(timeinMillies))
        return date
    }

    private fun montoTotal(montoT: Float, interesI: Float): Float {
        val intCalcInteres: Float = interesI / 100
        val intCalc2 = montoT * intCalcInteres
        return montoT + intCalc2
    }

    private fun calcCuota(Monto: Float,cuota: Float): Float{
        return Monto / cuota
    }

    @SuppressLint("SetTextI18n")
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (!bool) {
            bool = true
        } else {
            val plazo = findViewById<EditText>(R.id.editTextNumber)
            val cuotaFinal = findViewById<TextView>(R.id.textViewMontoCuotaR)
            val monto = findViewById<EditText>(R.id.editTextMonto)
            val montoFinal = findViewById<TextView>(R.id.textViewMontoTotal)

            if( plazo.text.toString().trim().isEmpty() && monto.text.toString().trim().isEmpty()){
                plazo.error = "Campo Vacio"
                cuotaFinal.text = "0.00 NIO"
                monto.error = "Campo Vacio"
                cuotaFinal.text = "0.00 NIO"
            } else if(monto.text.toString().trim().isEmpty()){
                monto.error = "Campo Vacio"
                cuotaFinal.text = "0.00 NIO"
            }else if(plazo.text.toString().trim().isEmpty()) {
                plazo.error = "Campo Vacio"
                cuotaFinal.text = "0.00 NIO"
                val interes = adaptadorSpinner.getItem(p2).toString().toFloat()
                val montText = monto.text.toString().toFloat()
                val montTotal: Float = montoTotal(montText, interes)
                montoFinal.text = montTotal.toString()
            }else{
                val plazoF = plazo.text.toString().toFloat()
                val interes = adaptadorSpinner.getItem(p2).toString().toFloat()
                val montText = monto.text.toString().toFloat()
                val montTotal: Float = montoTotal(montText, interes)
                montoFinal.text = montTotal.toString()
                val rCuota = calcCuota(montTotal,plazoF)
                cuotaFinal.text = rCuota.toString()
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun validarSpinner(): Int {
        val monto = findViewById<EditText>(R.id.editTextMonto)
        val montoI: Int = monto.text.toString().toInt()
        var num = 0
        if(montoI <= 10000){
            num = 0
        }
        if(montoI in 10001..20000){
            num = 1
        }
        if(montoI > 20000){
            num = 2
        }
        return num
    }

    private fun showDialog(){
        lateinit var dialog: AlertDialog

        // Nueva instancia de alert dialog builder object
        val builder = AlertDialog.Builder(this)

        // Titulo
        builder.setTitle("¿Estas seguro que deseas salir?")

        // Set a message for alert dialog
        builder.setMessage("¡Se borraran todos los datos introducidos!")


        // Botones
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> super.onSupportNavigateUp()
                //DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(this,"Negativo/Boton no pulsado",
                //   Toast.LENGTH_LONG).show()
                DialogInterface.BUTTON_NEUTRAL -> Toast.makeText(this,"Continuacion del préstamo",
                    Toast.LENGTH_LONG).show()
            }
        }

        // alert dialog positivo/boton de aceptar
        builder.setPositiveButton("Aceptar",dialogClickListener)

        // alert dialog negativo/boton de rechazar
        //builder.setNegativeButton("Rechazar",dialogClickListener)

        // alert dialog neutral/cancel button
        builder.setNeutralButton("Cancelar",dialogClickListener)


        // Inicializando el AlertDialog usando el builder object
        dialog = builder.create()

        // Despliegue del alert dialog
        dialog.show()
    }

}