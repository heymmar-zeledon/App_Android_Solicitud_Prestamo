package com.example.solicitudprestamo

import android.os.Parcel
import android.os.Parcelable

class ClientePrestamo : Parcelable{

    var Nombre:String =  ""
    var Apellido:String = ""
    var Sexo:String = ""
    var Telefono: String = ""
    var Cedula:String = ""
    var Ocupacion:String = ""
    var Direccion:String = ""

    constructor(nombre: String, apellido: String, sexo: String, telefono: String, cedula: String, ocupacion: String, direccion: String )
    {
        this.Nombre = nombre
        this.Apellido = apellido
        this.Sexo = sexo
        this.Telefono = telefono
        this.Cedula = cedula
        this.Ocupacion = ocupacion
        this.Direccion = direccion
    }

    constructor(p0: Parcel){
        this.Nombre = p0.readString().toString()
        this.Apellido = p0.readString().toString()
        this.Sexo = p0.readString().toString()
        this.Telefono = p0.readString().toString()
        this.Cedula = p0.readString().toString()
        this.Ocupacion = p0.readString().toString()
        this.Direccion = p0.readString().toString()
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(Nombre)
        p0?.writeString(Apellido)
        p0?.writeString(Sexo)
        p0?.writeString(Telefono)
        p0?.writeString(Cedula)
        p0?.writeString(Ocupacion)
        p0?.writeString(Direccion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClientePrestamo> {
        override fun createFromParcel(parcel: Parcel): ClientePrestamo {
            return ClientePrestamo(parcel)
        }

        override fun newArray(size: Int): Array<ClientePrestamo?> {
            return arrayOfNulls(size)
        }
    }

}