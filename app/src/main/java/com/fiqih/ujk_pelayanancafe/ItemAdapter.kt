package com.fiqih.ujk_pelayanancafe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(val context: Context, val items: ArrayList<MyActivityModel>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val linlay = view.Linlay
        val tvnama = view.Tv_Nama
        val tvwaktu = view.Tv_Waktu
        val tvtanggal = view.Tv_tgl
        val tvnomeja = view.Tv_Nomeja
        val tvharga = view.Tv_Harga
//        val ivEdit = view.iv_edit
//        val ivDelete = view.iv_delete
    }

    // method untuk membuat view holder
    // inflate = memunculkan data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_row, parent, false
            )
        )
    }

    // memasukkan data ke view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.tvnama.text = item.name
        holder.tvnomeja.text = item.nomeja
        holder.tvtanggal.text = item.tanggal
        holder.tvwaktu.text = item.time
        holder.tvharga.text = item.harga
//        holder.tvemail.text = item.email
//
//        holder.tvphone.text = item.phone
//        holder.tvalamat.text = item.alamat

        if (position % 2 == 0) {
            holder.linlay.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.teal_700
                )
            )
        } else {
            holder.linlay.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }
//
//        holder.ivDelete.setOnClickListener{
//            if (context is MainActivity)
//                context.deleteRecordAlertDialog(item)
//        }
//        holder.ivEdit.setOnClickListener{
//            if(context is MainActivity){
//                context.updateRecordDialog(item)
//            }
//        }


    }


    override fun getItemCount(): Int {
        return items.size
    }
}