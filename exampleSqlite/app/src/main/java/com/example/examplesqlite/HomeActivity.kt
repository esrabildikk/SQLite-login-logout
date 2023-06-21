package com.example.examplesqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examplesqlite.databinding.ActivityHomeBinding
import com.example.examplesqlite.databinding.ActivityMainBinding

private lateinit var binding: ActivityHomeBinding
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val kullaniciAdlari = intent.getStringArrayListExtra("username")
        val sifreler = intent.getStringArrayListExtra("password")

// Verileri kullanırken null kontrolü yapmanız önemlidir
        if (kullaniciAdlari != null && sifreler != null) {
            for (i in kullaniciAdlari.indices) {
                val ad = kullaniciAdlari[i]
                val sifre = sifreler[i]
                // Verileri kullanın
                binding.textViewKullaniciadi.text = "${ad}"
                binding.textViewKullanicisifresi.text = "${sifre}"
            }
        }



    }
}