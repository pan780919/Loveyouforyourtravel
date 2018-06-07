package com.jackpan.specialstudy.oveyouforyourtravel.Data

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.itheima.pulltorefreshlib.PullToRefreshListView
import com.jackpan.specialstudy.oveyouforyourtravel.R

import kotlinx.android.synthetic.main.activity_restaurant_list_view.*
import com.google.android.gms.ads.AdView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ArrayAdapter
import com.itheima.pulltorefreshlib.PullToRefreshBase
import android.text.method.TextKeyListener.clear
import android.widget.ListView
import android.widget.TextView


class RestaurantListViewActivity : AppCompatActivity() {
    lateinit var mPullToRefreshListView: PullToRefreshListView
    var mAdapter: MyAdapter? = null
    var mAllData: ArrayList<String> = ArrayList()
    val stockPriceDataList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list_view)
        mPullToRefreshListView = findViewById(R.id.pull_to_refresh_list_view)
        stockPriceDataList.add("1")
        stockPriceDataList.add("2")
        stockPriceDataList.add("3")
        stockPriceDataList.add("4")
        stockPriceDataList.add("5")
        stockPriceDataList.add("6")
        stockPriceDataList.add("7")

        mAdapter = MyAdapter(stockPriceDataList)
        mPullToRefreshListView.setAdapter(mAdapter)

        mPullToRefreshListView.setOnRefreshListener(mListViewOnRefreshListener2)
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);



    }

    inner class MyAdapter(private var stockPriceDataList: ArrayList<String>?) : BaseAdapter() {
        fun updateData(datas: ArrayList<String>) {
            stockPriceDataList = datas
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return stockPriceDataList!!.size
        }

        override fun getItem(position: Int): Any {
            return stockPriceDataList!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val data = stockPriceDataList!![position]
            if (convertView == null)
                convertView = LayoutInflater.from(this@RestaurantListViewActivity).inflate(
                        R.layout.listview_layout, null)
            var mTittleText :TextView = convertView!!.findViewById(R.id.listviewtext)
            mTittleText.text =data


            return convertView
        }

    }

    private val mListViewOnRefreshListener2 = object : PullToRefreshBase.OnRefreshListener2<ListView> {

        /**
         * 下拉刷新回调
         * @param refreshView
         */
        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>) {
            //模拟延时三秒刷新
            mPullToRefreshListView.postDelayed({

                mPullToRefreshListView.onRefreshComplete()//下拉刷新结束，下拉刷新头复位
            }, 3000)
        }

        /**
         * 上拉加载更多回调
         * @param refreshView
         */
        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>) {
            //模拟延时三秒加载更多数据
            mPullToRefreshListView.postDelayed({
                mPullToRefreshListView.onRefreshComplete()//上拉加载更多结束，上拉加载头复位
            }, 3000)
        }
    }
}