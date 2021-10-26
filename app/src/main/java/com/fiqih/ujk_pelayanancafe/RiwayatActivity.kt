package com.fiqih.ujk_pelayanancafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_riwayat.*
import java.util.ArrayList

class RiwayatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        setupListofDataIntoRecyclerView()
    }


private fun getItemsList(): ArrayList<MyActivityModel> {
    val databaseHandler: DatabaseHandler = DatabaseHandler(this)
    val activityList: ArrayList<MyActivityModel> = databaseHandler.viewActivity()

    return activityList
}

/**
 * Method to show data to recyclerView
 */
private fun setupListofDataIntoRecyclerView() {
    if(getItemsList().size > 0){
        rv_item.visibility = View.VISIBLE
        TV_No_Desc.visibility = View.GONE

        rv_item.layoutManager = LinearLayoutManager(this)
        val itemAdapter = ItemAdapter(this, getItemsList())
        rv_item.adapter = itemAdapter
    }else{
        rv_item.visibility = View.GONE
        TV_No_Desc.visibility = View.VISIBLE
    }
}

}