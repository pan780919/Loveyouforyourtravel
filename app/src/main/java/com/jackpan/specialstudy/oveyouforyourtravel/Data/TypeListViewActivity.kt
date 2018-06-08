package com.jackpan.specialstudy.oveyouforyourtravel.Data

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.itheima.pulltorefreshlib.PullToRefreshListView
import com.jackpan.specialstudy.oveyouforyourtravel.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itheima.pulltorefreshlib.PullToRefreshBase
import android.util.Log
import android.widget.*
import com.hendraanggrian.pikasso.picasso
import com.jackpan.specialstudy.oveyouforyourtravel.MapPlaceDetailActivity


class TypeListViewActivity : AppCompatActivity(), GoogleMapAPISerive.GetResponse {
    override fun getDetailData(googleMapPlaceDetailsData: GoogleMapPlaceDetailsData?) {
    }

    override fun getData(googleResponseData: GoogleResponseData?) {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("讀取中")
        mProgressDialog.setMessage("請稍候")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
        if (googleResponseData != null) {
            mNextPage.clear()
            mNextPage.add(googleResponseData)
            for (result in googleResponseData.results) {
                Log.d("Jack", result.name)
                mAllData.add(result)

                mAdapter!!.notifyDataSetChanged()

            }
            Log.d("Jack","getData")
            Log.d("Jack",mAllData.size.toString())




        }
        mProgressDialog.dismiss()

    }

    lateinit var mPullToRefreshListView: PullToRefreshListView

    var mAdapter: MyAdapter? = null
    var mAllData: ArrayList<GoogleResponseData.Results> = ArrayList()
    lateinit var mProgressDialog:ProgressDialog
    var mNextPage :ArrayList<GoogleResponseData> = ArrayList()
    lateinit var mTypeString: String
    lateinit var mLatLngString: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list_view)

        mPullToRefreshListView = findViewById(R.id.pull_to_refresh_list_view)

        if(!getBundle()){
            Toast.makeText(this,"無法取得資料",Toast.LENGTH_SHORT).show()
            return
        }
        GoogleMapAPISerive.setPlaceForRestaurant(this@TypeListViewActivity, mLatLngString, mTypeString,this@TypeListViewActivity)
        mAdapter = MyAdapter(mAllData)
        mPullToRefreshListView.setAdapter(mAdapter)

        mPullToRefreshListView.setOnRefreshListener(mListViewOnRefreshListener2)
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mPullToRefreshListView.setOnItemClickListener(AdapterView.OnItemClickListener {
            parent, view, position, id ->
            var mIntent = Intent()
            var mBundle = Bundle()

            mBundle.putString(GoogleMapAPISerive.TYPE_PLACEID,mAdapter!!.mAllData!![parent.adapter.getItemId(position).toInt()].place_id)
            mIntent.putExtras(mBundle)
            mIntent.setClass(this,MapPlaceDetailActivity::class.java)
            Log.d("Jack", mAdapter!!.mAllData!![parent.adapter.getItemId(position).toInt()].place_id)
        })



    }

    override fun onResume() {
        super.onResume()

    }


    inner class MyAdapter(var mAllData: ArrayList<GoogleResponseData.Results>?) : BaseAdapter() {
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
                convertView = LayoutInflater.from(this@TypeListViewActivity).inflate(
                        R.layout.listview_layout, null)
            var mTittleText :TextView = convertView!!.findViewById(R.id.listviewtext)
            var photoimg :ImageView = convertView!!.findViewById(R.id.listview_img)
            mTittleText.text =data.name
            if(data.photos!=null){
                var  photoString:String = GoogleMapAPISerive.getPhotos(this@TypeListViewActivity,data.photos.get(0).photo_reference)
                picasso.load(photoString).into(photoimg)
            }else{
                picasso.load(R.mipmap.nolodingphoto).into(photoimg)

            }



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
                Log.d("Jack",mNextPage.size.toString())
                if(mNextPage[0].next_page_token==null){
                    Toast.makeText(this@TypeListViewActivity,"最後一筆資料囉！！",Toast.LENGTH_SHORT).show()
                    return@postDelayed
                }
                GoogleMapAPISerive.nextPage(this@TypeListViewActivity,mNextPage[0].next_page_token, this@TypeListViewActivity)
                mAdapter!!.notifyDataSetChanged()
                Toast.makeText(this@TypeListViewActivity,"列表刷新！！",Toast.LENGTH_SHORT).show()


            }, 3000)
        }
    }
    fun  getBundle():Boolean{
        var bundle:Bundle = intent.extras
        mTypeString = bundle.getString(GoogleMapAPISerive.TYPE)
        mLatLngString = bundle.getString(GoogleMapAPISerive.TYPE_LATLON)

        if(!mTypeString.equals("")||!mLatLngString.equals("")){
            return true
        }
        return false

    }
}