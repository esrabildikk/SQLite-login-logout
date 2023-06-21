package com.example.examplesqlite

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.examplesqlite.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun signUp(view : View){
     try{
       //veritabani aç ve oluştur :
         val myDatabase = this.openOrCreateDatabase("Account", Context.MODE_PRIVATE,null)
         myDatabase.execSQL("CREATE TABLE IF NOT EXISTS account(username VARCHAR, password VARCHAR)")

         //textlerden bilgileri aliyoruz
         val username = binding.editTextUsername.text.toString()
         val password = binding.editTextPassword.text.toString()
//kayit islemi:
         myDatabase.execSQL("INSERT INTO account (username,password) VALUES ('${username}','${password}' )")
         val cursor = myDatabase.rawQuery("SELECT * FROM account", null)
         val kullaniciAdiIndex = cursor.getColumnIndex("username")
         val kullaniciSifresiIndex = cursor.getColumnIndex("password")

         val kullaniciAdlari = ArrayList<String>()
         val sifreler = ArrayList<String>()

         while (cursor.moveToNext()) {
             val ad = cursor.getString(kullaniciAdiIndex)
             val sifre = cursor.getString(kullaniciSifresiIndex)

             kullaniciAdlari.add(ad)
             sifreler.add(sifre)
         }

         cursor.close()
         myDatabase.close()

         val intent = Intent(this@MainActivity, HomeActivity::class.java)
         intent.putStringArrayListExtra("username", kullaniciAdlari)
         intent.putStringArrayListExtra("password", sifreler)
         startActivity(intent)

     }catch (e : Exception){
         e.printStackTrace()
     }
    }
}