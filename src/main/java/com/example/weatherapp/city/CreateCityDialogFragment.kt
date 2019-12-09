package com.example.weatherapp.city

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class CreateCityDialogFragment : DialogFragment() {

    interface CreateCityDialogListner{
        fun onDialogPosClick(cityName: String)
        fun onDialogNegClick()
    }
    var listner : CreateCityDialogFragment? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val builder = AlertDialog.Builder(context)
    val input =  EditText(context)
        with(input){
            inputType = InputType.TYPE_CLASS_TEXT
            hint = "New city name ?"
        }
        builder.setTitle("New city ?")
        return builder.create()
    }

}