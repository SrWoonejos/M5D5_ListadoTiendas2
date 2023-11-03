package com.dmiranda.m5_listadotiendas2.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dmiranda.m5_listadotiendas2.databinding.ActivityStoreDetailBinding
import com.dmiranda.m5_listadotiendas2.model.Store

class StoreDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoreDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras?.getBundle("bundle")
        bundle?.let {
            val store = it.getParcelable("store") as? Store
            binding.name.text = store?.name
            binding.direction.text =store?.address
            binding.history.text =store?.history
            Glide.with(this).load(store?.photo).centerCrop().into(binding.StoreImageView)

            binding.direction.setOnClickListener {
                val address = store?.address
                openMaps(address.orEmpty())
            }

            binding.shareButton.setOnClickListener {
                val storeName = store?.name
                shareStoreName(storeName.orEmpty())
            }
        }

    }

    private fun openMaps(address: String) {
        val uri = "geo:0,0?q=$address"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps") // Abre la aplicación de Google Maps
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // Si Google Maps no está instalado, puedes manejarlo aquí de otra manera
            Toast.makeText(this, "Google Maps no está instalado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareStoreName(storeName: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, storeName)

        if (shareIntent.resolveActivity(packageManager) != null) {
            startActivity(shareIntent)
        } else {
            // Si no hay aplicaciones para compartir disponibles
            Toast.makeText(this, "No se encontró una aplicación para compartir.", Toast.LENGTH_SHORT).show()
        }
    }
}