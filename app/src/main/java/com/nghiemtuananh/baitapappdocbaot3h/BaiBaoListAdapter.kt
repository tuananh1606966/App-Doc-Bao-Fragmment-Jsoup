package com.nghiemtuananh.baitapappdocbaot3h

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nghiemtuananh.baitapappdocbaot3h.databinding.RowBaiBaoBinding

class BaiBaoListAdapter(var inter: IDataAndClickBaiBao): RecyclerView.Adapter<BaiBaoListAdapter.BaiBaoListViewHolder>() {
    inner class BaiBaoListViewHolder: RecyclerView.ViewHolder {
        var binding: RowBaiBaoBinding
        constructor(binding: RowBaiBaoBinding): super(binding.root) {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaiBaoListViewHolder {
        var binding = RowBaiBaoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaiBaoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaiBaoListViewHolder, position: Int) {
        var baiBao = inter.getItem(position)
        holder.binding.data = baiBao
        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, baiBao.urlWeb, Toast.LENGTH_LONG).show()
            var intent = Intent(holder.itemView.context, WebActivity::class.java)
            intent.putExtra("url", baiBao.urlWeb)
            holder.itemView.context.startActivity(intent)
        }
        var animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.translate_alpha_list)
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int {
        return inter.getCount()
    }
}