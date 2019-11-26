package com.example.lenovoz500.mobilkanbankasi
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.list_grup_activity.*


class ListByGroup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_grup_activity)

        var kanListesi : MutableList<Bagisci>
        kanListesi = mutableListOf()

        var fireDataGroup =FirebaseDatabase.getInstance().getReference("Bagiscilar")
        var gruplar = arrayOf("A Rh+","B Rh+","AB Rh+","0 Rh+","A Rh-" , "B Rh-","AB Rh-","0 Rh-")
        spinner_grup_ara.adapter = ArrayAdapter<String>(this@ListByGroup,android.R.layout.simple_list_item_1,gruplar)



        btnGrupAra.setOnClickListener {
            //Database sınıfından listByGroup fonksiyonunu çağrılıyor ve veriler listeleniyor.
            var dataBase = Database()
            dataBase.fireDatabase = fireDataGroup
            dataBase.listByGrup(kanListesi,spinner_grup_ara.selectedItem.toString(),grup_listview,this@ListByGroup)


        }



    }
}