package com.fiqih.ujk_pelayanancafe

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_black_coffe.*
import kotlinx.android.synthetic.main.activity_black_coffe.btnR
import kotlinx.android.synthetic.main.activity_black_coffe.btnhome
import kotlinx.android.synthetic.main.activity_black_coffe.et_harga
import kotlinx.android.synthetic.main.activity_black_coffe.et_jam
import kotlinx.android.synthetic.main.activity_black_coffe.et_nomeja
import kotlinx.android.synthetic.main.activity_black_coffe.et_tgl
import kotlinx.android.synthetic.main.activity_black_coffe.tv_BlackTea
import kotlinx.android.synthetic.main.activity_capucino.*
import java.text.SimpleDateFormat
import java.util.*

class CapucinoActivity : AppCompatActivity() {
    val cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capucino)
        btnR.setOnClickListener {
            addRecord()
        }

        btnhome2.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        //Untuk menyembunyikan keyboard ketika pertama kali dipilih
        et_tgl.inputType = InputType.TYPE_NULL
        et_jam.inputType = InputType.TYPE_NULL

        //Aksi yang akan dijalankan ketika tanggal telah terpilih
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DATE, dayOfMonth)

                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                et_tgl.setText(sdf.format(cal.time))
            }
        }
        //aksi yang akan dijalankan ketika timepicker di pilih
        val timeSetListener = object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                cal.set(Calendar.HOUR, hourOfDay)
                cal.set(Calendar.MINUTE, minute)

                val myFormat = "HH:mm"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                et_jam.setText(sdf.format(cal.time))
            }

        }
        // when you click on the edit text, show DatePickerDialog that is set with OnDateSetListener
        et_tgl.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {

                DatePickerDialog(this@CapucinoActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })
        et_jam.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {

                TimePickerDialog( this@CapucinoActivity,
                    timeSetListener,
                    cal.get(Calendar.HOUR),
                    cal.get(Calendar.MINUTE),true).show()
            }

        })
    }


    private fun addRecord() {
        val name = tv_BlackTea.text.toString()
        val tanggal = et_tgl.text.toString()
        val jam = et_jam.text.toString()
        val nomeja = et_nomeja.text.toString()
        val dateTime = "${tanggal}(${jam})"
        val harga = et_harga.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (!tanggal.isEmpty() && !nomeja.isEmpty() && !jam.isEmpty() && !harga.isEmpty()) {
            val status =
                databaseHandler.addActivity(MyActivityModel(0, name, nomeja, jam, tanggal, harga))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record Saved", Toast.LENGTH_LONG).show()

            }
        } else {
            Toast.makeText(
                applicationContext,
                "Datetime or Description cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}