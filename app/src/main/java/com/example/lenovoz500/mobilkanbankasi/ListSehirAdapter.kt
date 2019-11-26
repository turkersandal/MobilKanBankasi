package com.example.lenovoz500.mobilkanbankasi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListSehirAdapter(var mCtx: Context, var layoutResId:Int, var grupList:List<Bagisci>)
    : ArrayAdapter<Bagisci>(mCtx,layoutResId,grupList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(mCtx)
        val view = layoutInflater.inflate(layoutResId,null)
        val bagisci = grupList[position]

        var adSoyad = view.findViewById<TextView>(R.id.lstSehirAdSoyad)
        var grup = view.findViewById<TextView>(R.id.lstSehirGrup)
        var sehir = view.findViewById<TextView>(R.id.lstSehirCity)


        adSoyad.text = bagisci.adSoyad
        grup.text = bagisci.grup
        sehir.text =bagisci.sehir

        return view
    }



}