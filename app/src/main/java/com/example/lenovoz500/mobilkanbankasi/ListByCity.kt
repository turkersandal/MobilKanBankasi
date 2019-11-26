package com.example.lenovoz500.mobilkanbankasi

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.dialog_info.view.*
import kotlinx.android.synthetic.main.dialog_olustur.view.*
import kotlinx.android.synthetic.main.list_sehir_activity.*

class ListByCity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_sehir_activity)

        var kanListesi : MutableList<Bagisci>
        kanListesi = mutableListOf()

        var firebaseDatabase = FirebaseDatabase.getInstance().getReference("Bagiscilar")

        var sehirler = arrayOf(
            "ADANA","ADIYAMAN","AFYONKARAHİSAR","AĞRI","AMASYA","ANKARA","ANTALYA","ARTVİN","AYDIN", "BALIKESİR","BİLECİK",
            "BİNGÖL","BİTLİS","BOLU","BURDUR","BURSA","ÇANAKKALE","ÇANKIRI","ÇORUM","DENİZLİ","DİYARBAKIR","EDİRNE","ELAZIĞ",
            "ERZİNCAN","ERZURUM","ESKİŞEHİR","GAZİANTEP","GİRESUN","GÜMÜŞHANE","HAKKARİ","HATAY","ISPARTA","MERSİN","İSTANBUL",
            "İZMİR","KARS","KASTAMONU","KAYSERİ","KIRKLARELİ","KIRŞEHİR","KOCAELİ","KONYA","KÜTAHYA","MALATYA","MANİSA",
            "KAHRAMANMARAŞ","MARDİN","MUĞLA","MUŞ","NEVŞEHİR","NİĞDE","ORDU","RİZE","SAKARYA","SAMSUN","SİİRT","SİNOP","SİVAS",
            "TEKİRDAĞ","TOKAT","TRABZON","TUNCELİ","ŞANLIURFA","UŞAK","VAN","YOZGAT","ZONGULDAK","AKSARAY","BAYBURT","KARAMAN",
            "KIRIKKALE","BATMAN","ŞIRNAK","BARTIN","ARDAHAN","IĞDIR","YALOVA","KARABüK","KİLİS","OSMANİYE","DÜZCE")
       spinner_list_sehir.adapter= ArrayAdapter<String>(this@ListByCity,android.R.layout.simple_list_item_1,sehirler)

        btn_list_sehir.setOnClickListener {
            //Database sınıfından listByCity fonksiyonunu çağrılıyor ve veriler listeleniyor.
            var dataBase = Database()
            dataBase.fireDatabase = firebaseDatabase
            dataBase.listByCity(kanListesi,spinner_list_sehir.selectedItem.toString(),listview_list_sehir,this@ListByCity)
        }


    }

}