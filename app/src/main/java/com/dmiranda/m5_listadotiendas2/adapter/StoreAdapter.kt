package com.dmiranda.m5_listadotiendas2.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmiranda.m5_listadotiendas2.databinding.ItemDetailBinding
import com.dmiranda.m5_listadotiendas2.R
import com.dmiranda.m5_listadotiendas2.model.Store

class StoreAdapter(
    private val storeList: List<Store>
): RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {
    lateinit var conexion0: ItemDetailBinding
    var onClickItem: ((Store)->Unit)?= null
    class StoreViewHolder(val conexion: ItemDetailBinding): RecyclerView.ViewHolder(conexion.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        conexion0 = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(conexion0)
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.conexion.textViewStoreName.text = storeList[position].name
        holder.conexion.textViewStoreAttention.text = storeList[position].officeHours
        Glide.with(holder.conexion.root.context)
            .load(storeList[position].photo)
            .centerCrop()
            .error(R.drawable.baseline_error_outline_24)
            .into(holder.conexion.imageViewStore)

        holder.conexion.constraintLayoutStore.setOnClickListener {
            onClickItem?.invoke(storeList[position])
        }

    }


}