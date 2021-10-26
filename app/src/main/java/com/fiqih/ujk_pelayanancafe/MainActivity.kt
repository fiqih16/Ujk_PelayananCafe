package com.fiqih.ujk_pelayanancafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var btnBlackCoffe: Button
    private lateinit var btnCappucino: Button
    private lateinit var btnSparklingTea:Button
    private lateinit var btnBatagor: Button
    private lateinit var btnCireng: Button
    private lateinit var btnNasgor: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnBlackCoffe = findViewById(R.id.btnBlackCoffe)
        btnCappucino = findViewById(R.id.btnCappucino)
        btnSparklingTea = findViewById(R.id.btnSparkTea)
        btnBatagor = findViewById(R.id.btnBatagor)
        btnCireng = findViewById(R.id.btnCireng)
        btnNasgor = findViewById(R.id.btnNasgor)


        btnBlackCoffe.setOnClickListener {
            val intent = Intent(this,BlackCoffeActivity::class.java)
            startActivity(intent)
        }

        btnCappucino.setOnClickListener {
            val intent = Intent(this,CapucinoActivity::class.java)
            startActivity(intent)
        }

        btnSparklingTea.setOnClickListener {
            val intent = Intent(this,SparklingTeaActivity::class.java)
            startActivity(intent)
        }

        btnBatagor.setOnClickListener {
            val intent = Intent(this,BatagorActivity::class.java)
            startActivity(intent)
        }

        btnCireng.setOnClickListener {
            val intent = Intent(this,CirengActivity::class.java)
            startActivity(intent)
        }

        btnNasgor.setOnClickListener {
            val intent = Intent(this,NasgorActivity::class.java)
            startActivity(intent)
        }



        btnRiwayat.setOnClickListener {
            val intent = Intent(this,RiwayatActivity::class.java)
            startActivity(intent)
        }
    }
}