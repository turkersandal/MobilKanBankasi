package com.example.lenovoz500.mobilkanbankasi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_olustur.view.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Firebase authenticaion nesnesi türetiliyor.
        var mAuth = FirebaseAuth.getInstance()
        //Login sınıfından nesne türetiliyor(Login olabilmek için)
        var login = Login()


        btnGiris.setOnClickListener {
            //Login sınıfından login metodu çağrılıyor
            login.login(edtMail.text.toString(),edtPassword.text.toString(),mAuth,this)

        }

        txtCreate.setOnClickListener {
            val Olustur = LayoutInflater.from(this).inflate(R.layout.dialog_olustur,null)
            val Builder = AlertDialog.Builder(this ).setView(Olustur).setTitle("Kullanıcı Oluştur")
            var sehirler = arrayOf(
                "ADANA","ADIYAMAN","AFYONKARAHİSAR","AĞRI","AMASYA","ANKARA","ANTALYA","ARTVİN","AYDIN", "BALIKESİR","BİLECİK",
                "BİNGÖL","BİTLİS","BOLU","BURDUR","BURSA","ÇANAKKALE","ÇANKIRI","ÇORUM","DENİZLİ","DİYARBAKIR","EDİRNE","ELAZIĞ",
                "ERZİNCAN","ERZURUM","ESKİŞEHİR","GAZİANTEP","GİRESUN","GÜMÜŞHANE","HAKKARİ","HATAY","ISPARTA","MERSİN","İSTANBUL",
                "İZMİR","KARS","KASTAMONU","KAYSERİ","KIRKLARELİ","KIRŞEHİR","KOCAELİ","KONYA","KÜTAHYA","MALATYA","MANİSA",
                "KAHRAMANMARAŞ","MARDİN","MUĞLA","MUŞ","NEVŞEHİR","NİĞDE","ORDU","RİZE","SAKARYA","SAMSUN","SİİRT","SİNOP","SİVAS",
                "TEKİRDAĞ","TOKAT","TRABZON","TUNCELİ","ŞANLIURFA","UŞAK","VAN","YOZGAT","ZONGULDAK","AKSARAY","BAYBURT","KARAMAN",
                "KIRIKKALE","BATMAN","ŞIRNAK","BARTIN","ARDAHAN","IĞDIR","YALOVA","KARABüK","KİLİS","OSMANİYE","DÜZCE")
            Olustur.spinner_sehirler.adapter= ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,sehirler)
            var gruplar = arrayOf("A Rh+","B Rh+","AB Rh+","0 Rh+","A Rh-" + "B Rh-","AB Rh-","0 Rh-")
            Olustur.spinner_grup.adapter = ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,gruplar)
            val AlertDialog = Builder.show()

            Olustur.btnAdd.setOnClickListener {

                //Login sınıfından kullanıcı oluşturma fonksiyonu çağrılıyor.
                login.createUser(Olustur.edtMail.text.toString(),Olustur.edtPass.text.toString(),Olustur.edtPass2.text.toString(),
                    Olustur.edtName.text.toString(),Olustur.spinner_grup.selectedItem.toString(),Olustur.edtPhone.text.toString(),Olustur.spinner_sehirler.selectedItem.toString(),
                    Olustur.edtAge.text.toString(),mAuth,this,AlertDialog)


            }
        }


    }
}
