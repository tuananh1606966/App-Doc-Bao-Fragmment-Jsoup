package com.nghiemtuananh.baitapappdocbaot3h

import android.annotation.SuppressLint
import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentBaiBaoList : Fragment(), IDataAndClickBaiBao {
    var arrBaiBao: ArrayList<BaiBao> = arrayListOf()
    var adapter: BaiBaoListAdapter? = null
    var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view = inflater.inflate(R.layout.fragment_bai_bao_list, container, false)
        recyclerView = view.findViewById(R.id.rcv_baibao_list) as RecyclerView
        var listBaiBaoTest =
            requireArguments().getSerializable("listBaiBao") as ArrayList<BaiBao>
        arrBaiBao = listBaiBaoTest
        initData(arrBaiBao)
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initData(listBaiBao: ArrayList<BaiBao>) {
        while (arrBaiBao.isEmpty()) {
            arrBaiBao = listBaiBao
        }
        adapter = BaiBaoListAdapter(this)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = GridLayoutManager(activity, 2)
        recyclerView?.adapter!!.notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return arrBaiBao.size
    }

    override fun getItem(position: Int): BaiBao {
        return arrBaiBao.get(position)
    }
}