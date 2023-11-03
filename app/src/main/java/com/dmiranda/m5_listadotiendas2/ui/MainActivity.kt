package com.dmiranda.m5_listadotiendas2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmiranda.m5_listadotiendas2.adapter.StoreAdapter
import com.dmiranda.m5_listadotiendas2.data.StoreRepository
import com.dmiranda.m5_listadotiendas2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isButtonClicked:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Para obtener el listado de tiendas debes ocupar la clase StoreRepository
        // la funcion getStoreList retorna un listado de sucursales
        // Por ejmeplo:

        val storeList = StoreRepository().getStoreList()

        binding.rvExample.layoutManager = LinearLayoutManager(this)
        val storeAdapter = StoreAdapter(storeList)
        binding.rvExample.adapter = storeAdapter

        storeAdapter.onClickItem = {
            if (!isButtonClicked) {
                isButtonClicked = true
                val intent = Intent(this, StoreDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable("store", it)
                intent.putExtra("bundle", bundle)
                startActivity(intent)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        isButtonClicked = false
    }
}