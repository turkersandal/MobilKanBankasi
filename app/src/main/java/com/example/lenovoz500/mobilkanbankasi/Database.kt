package com.example.lenovoz500.mobilkanbankasi


import android.content.Context
import android.view.LayoutInflater
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.dialog_info.view.*

class Database {

    var fireDatabase = FirebaseDatabase.getInstance().getReference()

    //Şehire göre aramadaki sonuçları döndürür
    fun listByCity(kanListesi:MutableList<Bagisci>,city:String,list:ListView,ctx:Context){

        fireDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){
                    kanListesi.clear()
                    for(h in p0.children){
                        var bagisci = h.getValue(Bagisci::class.java)

                        if(bagisci!!.bagis_durum == true) {
                            if (bagisci!!.sehir ==city) {
                                kanListesi.add(bagisci!!)
                            }
                        }

                    }
                }
                val adapter = ListSehirAdapter(ctx,R.layout.list_sehir,kanListesi)
                list.adapter = adapter

                if(kanListesi.count() == 0){
                    Toast.makeText(ctx,"Bir şey bulunamadı.", Toast.LENGTH_SHORT).show()
                }

                list.setOnItemClickListener {parent , view, position, id ->

                    var bagis = kanListesi[position]

                    var alertInfo = LayoutInflater.from(ctx).inflate(R.layout.dialog_info,null)
                    val builder = android.app.AlertDialog.Builder(ctx).setTitle("Bağışçı Bilgileri").setView(alertInfo)
                    val alertInfoo = builder.show()


                    alertInfo.textViewAdS.text = bagis.adSoyad
                    alertInfo.textViewEmailS.text=bagis.email
                    alertInfo.textViewKanS.text=bagis.grup
                    alertInfo.textViewSehirS.text = bagis.sehir
                    alertInfo.textViewYasS.text = bagis.yas
                    alertInfo.textViewTelefonS.text = bagis.telefon


                }

            }
        })



    }
    //Gruba göre aramadaki sonuçları döndürür
    fun listByGrup(kanListesi: MutableList<Bagisci>, group:String, list:ListView, ctx: Context){
        fireDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    kanListesi.clear()
                    for (h in p0.children) {
                        var bagisci = h.getValue(Bagisci::class.java)
                        if(bagisci!!.bagis_durum == true) {
                            if (bagisci!!.grup == group) {
                                kanListesi.add(bagisci!!)
                            }
                        }
                    }
                    val adapter = ListGroupAdapter(ctx,R.layout.list_grup,kanListesi)
                    list.adapter = adapter
                    if(kanListesi.count() == 0){
                        Toast.makeText(ctx,"Maalesef bağışçı bulunamadı.",Toast.LENGTH_SHORT).show()
                    }
                    list.setOnItemClickListener {parent , view, position, id ->

                        var bagis = kanListesi[position]

                        var alertInfo = LayoutInflater.from(ctx).inflate(R.layout.dialog_info,null)
                        val builder = android.support.v7.app.AlertDialog.Builder(ctx).setTitle("Bağışçı Bilgileri").setView(alertInfo)
                        val alertInfoo = builder.show()


                        alertInfo.textViewAdS.text = bagis.adSoyad
                        alertInfo.textViewEmailS.text=bagis.email
                        alertInfo.textViewKanS.text=bagis.grup
                        alertInfo.textViewSehirS.text = bagis.sehir
                        alertInfo.textViewYasS.text = bagis.yas.toString()
                        alertInfo.textViewTelefonS.text = bagis.telefon


                    }
                }
            }
        })


    }
    //Bağışçı oluşturma metodu.
    fun createDonor(id:String,ctx:Context,alert: android.support.v7.app.AlertDialog){
        fireDatabase.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){


                    var user = p0.getValue(Bagisci::class.java)


                    var newUser = Bagisci(id,user?.adSoyad.toString(),user?.grup.toString(),user?.sehir.toString(),
                        user?.telefon.toString(),user?.email.toString(), user?.yas.toString(),true)
                    var newDatabase = FirebaseDatabase.getInstance().getReference("Bagiscilar")

                    newDatabase.child(id).setValue(newUser).addOnSuccessListener {

                        Toast.makeText(ctx,"Tebrikler, Bağışçı oldunuz.",Toast.LENGTH_SHORT).show()
                        alert.cancel()

                    }
                }
            }
        })




    }
}