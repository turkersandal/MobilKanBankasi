package com.example.lenovoz500.mobilkanbankasi

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Login {

    fun login( username:String, password:String,mAuth:FirebaseAuth,ctx:Context){

        //Boşluk kontrolü
        if(!username.isEmpty() && !password.isEmpty()) {
            //Firebase in login olma metodu.
            mAuth.signInWithEmailAndPassword(username, password).addOnSuccessListener {
                Toast.makeText(ctx, "Başarıyla giriş yaptınız...", Toast.LENGTH_SHORT).show()
                //Login başarılı ise secim aktivitiye geçiş yapılıyor.
                var intent = Intent(ctx, SecimActivity::class.java)
                startActivity(ctx, intent, null)
            }.addOnFailureListener {
                //Login hatalı ise
                Toast.makeText(ctx, "Email adresiniz ve/veya şifreniz yanlış. ", Toast.LENGTH_SHORT).show()
            }
        }else
        {
            Toast.makeText(ctx,"Lütfen Tüm Alanları Doldurunuz!!!",Toast.LENGTH_SHORT).show()
        }

    }


    fun createUser(username:String, password: String, password2:String, nameSurname:String, grup:String, telno:String, sehir:String, yas:String,
        mAuth: FirebaseAuth,
        ctx: Context,
        alert: android.support.v7.app.AlertDialog
    ){
        //Boşluk kontrolü
        if(!username.isEmpty() && !password.isEmpty()
            && !password2.isEmpty() && !nameSurname.isEmpty() &&
            !telno.isEmpty() && !yas.toString().isEmpty())
        {
            if (password.length >= 6)
            {
                if(password == password2){

                    //Firebase ile kullanıcı oluşturma metodu.
                    mAuth.createUserWithEmailAndPassword(username,password).addOnSuccessListener {

                        //Kulanıcının verilerini database e kaydetme kısmı
                        var database = FirebaseDatabase.getInstance().getReference("Bagiscilar/${mAuth.currentUser!!.uid}")
                        //Bagisci sınıfından nesne oluşturuluyor
                        var bagisci = Bagisci(mAuth.currentUser!!.uid,nameSurname,grup,
                            sehir,telno,username,yas,false)
                        //Bagisci nesnesi firebase e kaydediliyor.
                        database.setValue(bagisci).addOnSuccessListener {
                            Toast.makeText(ctx,"Kullanıcı başarıyla oluşturulmuştur.",Toast.LENGTH_SHORT).show()
                          alert.cancel()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(ctx,"Email adresi yanlış veya Kullanıcı zaten var.",Toast.LENGTH_SHORT).show()
                    }

                }else{

                    Toast.makeText(ctx,"Parola alanları aynı olmalıdır.",Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(ctx , "Şifreniz en az 6 karakter olmalıdır.", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Toast.makeText(ctx ,"Lütfen bütün alanları eksiksiz bir şekilde doldurunuz.",Toast.LENGTH_SHORT).show()

        }
    }



}