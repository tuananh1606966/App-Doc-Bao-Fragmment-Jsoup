package com.nghiemtuananh.baitapappdocbaot3h

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.nghiemtuananh.baitapappdocbaot3h.databinding.RowTrangBaoBinding

class TrangBaoListAdapter(var inter: IDataAndClickTrangBao): RecyclerView.Adapter<TrangBaoListAdapter.TrangBaoListViewHolder>() {
    var lastItemChoose = 0
    lateinit var interOnClick: ITruyenDuLieu
    inner class TrangBaoListViewHolder(var binding: RowTrangBaoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrangBaoListViewHolder {
        var binding = RowTrangBaoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrangBaoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrangBaoListViewHolder, position: Int) {
        interOnClick = holder.itemView.context as ITruyenDuLieu
        var trangBao = inter.getItem(position)
        holder.binding.data = trangBao
        holder.itemView.setOnClickListener {
            interOnClick.onClick(inter.getItem(position).listBaiBao)
            for (i in 0..inter.getCount() - 1) {
                inter.getItem(i).isChoose = false
            }
            trangBao.isChoose = !trangBao.isChoose
            var copyLastItemChoose = lastItemChoose
            lastItemChoose = holder.adapterPosition
            notifyItemChanged(lastItemChoose)
            notifyItemChanged(copyLastItemChoose)
            var animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale_list)
            holder.itemView.startAnimation(animation)
        }
    }

    override fun getItemCount(): Int {
        return inter.getCount()
    }
}