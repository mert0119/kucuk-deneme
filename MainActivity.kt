package com.example.borsakusu

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- ELEMANLARI TANIMLA ---
        val btnCompare = findViewById<Button>(R.id.btnCompare)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val inputMoney = findViewById<EditText>(R.id.inputMoney)

        // 10 Adet Hisse Kutusunu Tanıtıyoruz
        val txtThy = findViewById<TextView>(R.id.txtThyPrice)
        val txtAsels = findViewById<TextView>(R.id.txtAselsanPrice)
        val txtSasa = findViewById<TextView>(R.id.txtSasaPrice)
        val txtEregl = findViewById<TextView>(R.id.txtEregliPrice)
        val txtSise = findViewById<TextView>(R.id.txtSisePrice)
        val txtKchol = findViewById<TextView>(R.id.txtKcholPrice)
        val txtFroto = findViewById<TextView>(R.id.txtFrotoPrice)
        val txtBimas = findViewById<TextView>(R.id.txtBimasPrice)
        val txtAkbnk = findViewById<TextView>(R.id.txtAkbnkPrice)
        val txtTuprs = findViewById<TextView>(R.id.txtTuprasPrice)

        btnCompare.setOnClickListener {
            // Boş kutu kontrolü
            val paraString = inputMoney.text.toString()
            if (paraString.isEmpty()) {
                Toast.makeText(this, "Lütfen bir bakiye girin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Animasyon hissi ver
            txtResult.text = "🔄 BIST 100 Verileri Güncelleniyor..."
            txtResult.setTextColor(Color.DKGRAY)

            // İŞTE DÜZELTİLEN YER: Süreyi (800) en sona koyduk
            btnCompare.postDelayed({

                // --- 1. RASTGELE FİYATLAR (Gerçekçi Aralıklar) ---
                val pThy = Random.nextDouble(255.0, 265.0)
                val pAsels = Random.nextDouble(60.0, 65.0)
                val pSasa = Random.nextDouble(38.0, 42.0)
                val pEregl = Random.nextDouble(45.0, 50.0)
                val pSise = Random.nextDouble(48.0, 53.0)
                val pKchol = Random.nextDouble(170.0, 180.0)
                val pFroto = Random.nextDouble(1000.0, 1100.0)
                val pBimas = Random.nextDouble(390.0, 410.0)
                val pAkbnk = Random.nextDouble(55.0, 60.0)
                val pTuprs = Random.nextDouble(160.0, 175.0)

                // --- 2. LİSTEYİ DOLDUR VE RENKLENDİR ---
                renkliYaz(txtThy, "THYAO", pThy)
                renkliYaz(txtAsels, "ASELS", pAsels)
                renkliYaz(txtSasa, "SASA", pSasa)
                renkliYaz(txtEregl, "EREGL", pEregl)
                renkliYaz(txtSise, "SISE", pSise)
                renkliYaz(txtKchol, "KCHOL", pKchol)
                renkliYaz(txtFroto, "FROTO", pFroto)
                renkliYaz(txtBimas, "BIMAS", pBimas)
                renkliYaz(txtAkbnk, "AKBNK", pAkbnk)
                renkliYaz(txtTuprs, "TUPRS", pTuprs)

                // --- 3. HESAPLAMA (Örnek olarak THY ve ASELSAN) ---
                val anaPara = paraString.toDouble()
                val thyLot = (anaPara / pThy).toInt()
                val aselsLot = (anaPara / pAsels).toInt()

                txtResult.text = """
                    ✅ İŞLEM TAMAMLANDI
                    
                    ${anaPara.toInt()} TL ile alabileceklerin (Örnek):
                    ✈️ $thyLot Lot THY
                    🛡️ $aselsLot Lot ASELSAN
                    
                    Piyasa şu an aktif! 🟢
                """.trimIndent()
                txtResult.setTextColor(Color.parseColor("#1B5E20"))

            }, 800) // SÜRE BURADA (800 milisaniye)
        }
    }

    // Fiyatı yazdıran ve rastgele renk (Yeşil/Kırmızı) veren fonksiyon
    private fun renkliYaz(textView: TextView, isim: String, fiyat: Double) {
        val yukselis = Random.nextBoolean()
        val ok = if (yukselis) "▲" else "▼"
        val renk = if (yukselis) "#2E7D32" else "#C62828" // Yeşil veya Kırmızı

        textView.text = "$isim: %.2f TL  $ok".format(fiyat)
        textView.setTextColor(Color.parseColor(renk))
    }
}