package com.example.solicitudprestamo

import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Toolbar{

    fun showToolbar(activity: AppCompatActivity, title: String, upButton: Boolean ){
        activity.setSupportActionBar(activity.findViewById(R.id.toolbar))
        activity.supportActionBar?.title = title
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}