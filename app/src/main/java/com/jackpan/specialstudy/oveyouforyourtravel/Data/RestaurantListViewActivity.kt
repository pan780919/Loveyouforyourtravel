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
import android.util.Log
import android.widget.ListView
import android.widget.TextView


class RestaurantListViewActivity : AppCompatActivity(), GoogleMapAPISerive.GetResponse {
    override fun getData(googleResponseData: GoogleResponseData?) {
        if (googleResponseData != null) {
            Log.d("Jack","in")
            for (result in googleResponseData.results) {

                mAllData.add(result)
                mAdapter = MyAdapter(mAllData)

                mAdapter!!.notifyDataSetChanged()
            }

        }
    }

    lateinit var mPullToRefreshListView: PullToRefreshListView
    var mAdapter: MyAdapter? = null
    var mAllData: ArrayList<GoogleResponseData.Results> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list_view)
        mPullToRefreshListView = findViewById(R.id.pull_to_refresh_list_view)
        GoogleMapAPISerive.setPlaceForRestaurant(this@RestaurantListViewActivity, "25.048630,%20121.544427", this@RestaurantListViewActivity)
        mPullToRefreshListView.setAdapter(mAdapter)

        mPullToRefreshListView.setOnRefreshListener(mListViewOnRefreshListener2)
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);



    }

    inner class MyAdapter(private var mAllData: ArrayList<GoogleResponseData.Results>?) : BaseAdapter() {
        fun updateData(datas: ArrayList<GoogleResponseData.Results>) {
            mAllData = datas
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return mAllData!!.size
        }

        override fun getItem(position: Int): Any {
            return mAllData!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val data = mAllData!![position]
            if (convertView == null)
                convertView = LayoutInflater.from(this@RestaurantListViewActivity).inflate(
                        R.layout.listview_layout, null)
            var mTittleText :TextView = convertView!!.findViewById(R.id.listviewtext)
            mTittleText.text =data.name



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