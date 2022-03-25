package com.nghiemtuananh.baitapappdocbaot3h

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nghiemtuananh.baitapappdocbaot3h.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class MainActivity : AppCompatActivity(), IDataAndClickTrangBao, ITruyenDuLieu {
    var listTrangBao: ArrayList<TrangBao> = arrayListOf()
    var listBaiBaoVNE: ArrayList<BaiBao> = arrayListOf()
    var listBaiBao24H: ArrayList<BaiBao> = arrayListOf()
    lateinit var binding: ActivityMainBinding
    var fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        addListTrangBao()
        addListBaiBaoVNE()
        addListBaiBao24H()
        binding.rcvListTrangbao.adapter = TrangBaoListAdapter(this)
        binding.rcvListTrangbao.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rcvListTrangbao.addItemDecoration(DividerItemDecoration(this,
            DividerItemDecoration.HORIZONTAL))
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragmentBaiBaoList = FragmentBaiBaoList()
        var bundle = Bundle()
        bundle.putSerializable("listBaiBao", listBaiBaoVNE)
        fragmentBaiBaoList.arguments = bundle
        fragmentTransaction.add(R.id.frag_list_baibao, fragmentBaiBaoList)
        fragmentTransaction.commit()
    }

    override fun getCount(): Int {
        return listTrangBao.size
    }

    override fun getItem(position: Int): TrangBao {
        return listTrangBao.get(position)
    }

    override fun onClick(listBaiBao: ArrayList<BaiBao>) {
        var fragmentTransaction = fragmentManager.beginTransaction()
        var fragmentBaiBaoList = FragmentBaiBaoList()
        var bundle = Bundle()
        bundle.putSerializable("listBaiBao", listBaiBao)
        fragmentBaiBaoList.arguments = bundle
        fragmentTransaction.replace(R.id.frag_list_baibao, fragmentBaiBaoList)
        fragmentTransaction.commit()
//        Toast.makeText(this, listBaiBao.size.toString(), Toast.LENGTH_LONG).show()
    }

    @SuppressLint("CheckResult")
    private fun addListBaiBao24H() {
        var title = ""
        var content = ""
        var urlImage = ""
        var urlWeb = ""
        Observable.create<Void> {
            var doc: Document = Jsoup.connect("https://www.24h.com.vn/bong-da-c48.html").get()
            var elements = doc.select("article.cate-24h-foot-home-latest-list__box")
            for (element in elements) {
                var elementTitle = element.getElementsByTag("a").first()
                title = elementTitle!!.attr("title")
                urlWeb = elementTitle.attr("href")
                var elementImage = element.getElementsByTag("img").first()
                urlImage = elementImage!!.attr("data-original")
                var elementContent = element.getElementsByTag("div").first()
                content = elementContent!!.text()
                listBaiBao24H.add(BaiBao(title, content, urlImage, urlWeb))
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                },
                {},
                {}
            )
    }

    @SuppressLint("CheckResult")
    private fun addListBaiBaoVNE() {
        var title = ""
        var content = ""
        var urlImage = ""
        var urlWeb = ""
        Observable.create<Void> {
            var doc: Document = Jsoup.connect("https://vnexpress.net/giai-tri/thoi-trang").get()
            var elements: Elements = doc.select("article.item-news")
            for (i in 1..20) {
                if (i == 6 || i == 13 || i == 20) {
                    continue
                }
                if (i < 4) {
                    var elementTitle: Element = elements[i].getElementsByTag("a").get(1)
                    title = elementTitle.text()
                    var elementContent = elements[i].getElementsByTag("a").get(2)
                    content = elementContent.text()
                    var elementImage = elements[i].getElementsByTag("img").first()
                    urlImage = elementImage!!.attr("src")
                    var elementWeb: Element = elements[i].getElementsByTag("a").get(0)
                    urlWeb = elementWeb.attr("href")
                    listBaiBaoVNE.add(BaiBao(title, content, urlImage, urlWeb))
                } else {
                    var elementTitle: Element = elements[i].getElementsByTag("a").get(1)
                    title = elementTitle.text()
                    var elementContent = elements[i].getElementsByTag("a").get(2)
                    content = elementContent.text()
                    var elementImage = elements[i].getElementsByTag("img").first()
                    urlImage = elementImage!!.attr("data-src")
                    var elementWeb: Element = elements[i].getElementsByTag("a").get(0)
                    urlWeb = elementWeb.attr("href")
                    listBaiBaoVNE.add(BaiBao(title, content, urlImage, urlWeb))
                }
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                },
                {},
                {}
            )
    }


    private fun addListTrangBao() {
        listTrangBao.add(TrangBao(R.drawable.vn_express, listBaiBaoVNE, true))
        listTrangBao.add(TrangBao(R.drawable.bao_24h, listBaiBao24H))
        listTrangBao.add(TrangBao(R.drawable.vn_express, listBaiBaoVNE))
        listTrangBao.add(TrangBao(R.drawable.bao_24h, listBaiBao24H))
        listTrangBao.add(TrangBao(R.drawable.vn_express, listBaiBaoVNE))
        listTrangBao.add(TrangBao(R.drawable.bao_24h, listBaiBao24H))
        listTrangBao.add(TrangBao(R.drawable.vn_express, listBaiBaoVNE))
        listTrangBao.add(TrangBao(R.drawable.bao_24h, listBaiBao24H))
        listTrangBao.add(TrangBao(R.drawable.vn_express, listBaiBaoVNE))
        listTrangBao.add(TrangBao(R.drawable.bao_24h, listBaiBao24H))
    }
}