package com.example.lenovoz500.mobilkanbankasi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.bagisci_onay_dialog.view.*
import kotlinx.android.synthetic.main.kan_secim_dialog.view.*
import kotlinx.android.synthetic.main.secim_activity.*

class SecimActivity :AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secim_activity)

        //Firebase database ve authenticcation nesneleri türetiliyor
        var mAuth = FirebaseAuth.getInstance().currentUser
        var uid = mAuth?.uid
        var database = FirebaseDatabase.getInstance().getReference("Bagiscilar/$uid")

        btn_bagisci.setOnClickListener {

            //Kulanıcının bağışçı olup olmadığının komtrolü.
            database.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {

                    if(p0!!.exists()){

                        var control = p0.getValue(Bagisci::class.java)
                        if(!control!!.bagis_durum){
                            //Kullanıcı bağışçı değil ise bağışçı olma kısmı başlıyor

                            val alertBagisOnay = LayoutInflater.from(this@SecimActivity).inflate(R.layout.bagisci_onay_dialog,null)
                            val builder = AlertDialog.Builder(this@SecimActivity).setTitle("Bağışçı Ol").setView(alertBagisOnay)
                            var alert = builder.show()



                            alertBagisOnay.btn_bagis_onay.setOnClickListener {


                                if(alertBagisOnay.checkBox.isChecked
                                    && alertBagisOnay.checkBox2.isChecked
                                    && alertBagisOnay.checkBox3.isChecked
                                    && alertBagisOnay.checkBox4.isChecked
                                    && alertBagisOnay.checkBox5.isChecked){

                                    //Database sınıfından oluşturlan nesne ile kullanıcı oluşturma fonksiyonu çağrılıyor
                                    var dataBase = Database()
                                    dataBase.fireDatabase = database
                                    dataBase.createDonor(uid.toString(),this@SecimActivity,alert)


                                }else{
                                    Toast.makeText(this@SecimActivity,"Lütfen seçenekleri tekrar gözden geçiriniz.",Toast.LENGTH_SHORT).show()
                                }
                            }

                        }
                        else{
                            Toast.makeText(this@SecimActivity,"Zaten Bağışçısınız.",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        btn_kan_ara.setOnClickListener {
        //Kan arama bölümü
            val alertKanAramaSecim = LayoutInflater.from(this).inflate(R.layout.kan_secim_dialog,null)
            val builder = AlertDialog.Builder(this@SecimActivity).setView(alertKanAramaSecim).setTitle("KAN ARAMA SECİM")
            val alert = builder.show()

            alertKanAramaSecim.btnListGrup.setOnClickListener {
            //Gruba göre aranacaksa ListByGroup aktivitisine geçiş yapılıyor
                val intent = Intent(this,ListByGroup::class.java)
                startActivity(intent)

            }
            alertKanAramaSecim.btnListSehir.setOnClickListener {
                //Gruba göre aranacaksa ListByCity aktivitisine geçiş yapılıyor

                val intent = Intent(this,ListByCity::class.java)
                startActivity(intent)
            }



        }


    }

}