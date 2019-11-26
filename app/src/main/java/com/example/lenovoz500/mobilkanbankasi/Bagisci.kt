package com.example.lenovoz500.mobilkanbankasi

//Model classımız
 class Bagisci(val id:String,val adSoyad:String,var grup:String,var sehir:String,var telefon:String,var email:String, var yas:String,var bagis_durum:Boolean) {
    constructor()
            :this("","","","","","", "",false)
}