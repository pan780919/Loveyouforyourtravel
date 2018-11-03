package com.jackpan.specialstudy.oveyouforyourtravel

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.hendraanggrian.pikasso.picasso
import com.itheima.pulltorefreshlib.PullToRefreshBase
import com.itheima.pulltorefreshlib.PullToRefreshListView
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import com.jackpan.specialstudy.oveyouforyourtravel.Data.CollectionData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleMapPlaceDetailsData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleResponseData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.MapDetailResponData
import java.io.StringReader
import android.R.string.cancel
import GoogleMapAPISerive
import GoogleMapAPISerive.GetResponse
import android.app.Activity
import android.os.Handler
import com.google.gson.JsonObject
import org.json.JSONObject
import java.util.*


class MemberFreeListViewActivity : AppCompatActivity(), GoogleMapAPISerive.GetResponse, MfirebaeCallback {
    override fun getUserLogoutState(p0: Boolean) {
    }

    override fun resetPassWordState(p0: Boolean) {
    }

    override fun getsSndPasswordResetEmailState(p0: Boolean) {
    }

    override fun getFirebaseStorageType(p0: String?, p1: String?) {
    }

    override fun getUpdateUserName(p0: Boolean) {
    }

    override fun getDatabaseData(p0: Any?) {
        Log.d("getDatabaseData", p0.toString())

        if (p0 != null) {
//            val jsonObj = JSONObject(p0.toString())
//
//            Log.d(javaClass.simpleName,jsonObj.getString("id"))
//
            var gson = Gson()
            val reader = JsonReader(StringReader(p0.toString()))
            reader.setLenient(true)
            var mCollectionData = MapDetailResponData()
            mCollectionData = gson.fromJson(reader, MapDetailResponData::class.java)
            mData.add(mCollectionData)
            Log.d(javaClass.simpleName, mCollectionData.id)
            val mString: List<String> = mCollectionData.id.split("+")
            Log.d(javaClass.simpleName, mString[0])
            GoogleMapAPISerive.getPlaceDeatail(this, mString[0], this)
        }


    }

    override fun getuserLoginEmail(p0: String?) {
    }

    override fun getDeleteState(p0: Boolean, p1: String?, p2: Any?) {
        if (p0) {
            Log.d("getDeleteState", p0.toString());
        } else {
            Log.d("getDeleteState", p0.toString());

        }
        getList()

    }

    override fun getFireBaseDBState(p0: Boolean, p1: String?) {
    }

    override fun getuseLoginId(p0: String?) {
    }

    override fun createUserState(p0: Boolean) {
    }

    override fun useLognState(p0: Boolean) {
    }

    override fun getFirebaseStorageState(p0: Boolean) {
    }

    override fun getDetailData(googleMapPlaceDetailsData: GoogleMapPlaceDetailsData?) {

        if (googleMapPlaceDetailsData != null) {

            mAllData.add(googleMapPlaceDetailsData)
        }
        mAdapter!!.notifyDataSetChanged()
        mProgressDialog.dismiss()


    }

    override fun getData(googleResponseData: GoogleResponseData?) {


    }

    lateinit var mPullToRefreshListView: PullToRefreshListView

    var mAdapter: MyAdapter? = null
    var mAllData: ArrayList<GoogleMapPlaceDetailsData> = ArrayList()
    var mData: ArrayList<MapDetailResponData> = ArrayList()

    lateinit var mProgressDialog: ProgressDialog
    lateinit var mTypeString: String
    lateinit var mLatLngString: String

    lateinit var mFirebselibClass: MfiebaselibsClass
    lateinit var mGetMoreFreeButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_free_list_view)
        mFirebselibClass = MfiebaselibsClass(this, this@MemberFreeListViewActivity)
//        mFirebselibClass.deleteData( CollectionData.KEY_URL_FREE+ "/" + MySharedPrefernces.getIsToken(this@MemberFreeListViewActivity), "id=ChIJxbX0zXkEbjQRZ16eV60lgk4,1533004646595")

        if (mAllData != null && mAllData.size > 0) {
            mAllData.clear()
        }
        getList()
        mPullToRefreshListView = findViewById(R.id.pull_to_refresh_list_view)
        mAdapter = MyAdapter(mAllData)
        mAdapter!!.notifyDataSetChanged()
        mPullToRefreshListView.setAdapter(mAdapter)
        mGetMoreFreeButton = findViewById(R.id.getmorebutton)
        mGetMoreFreeButton.setOnClickListener {
            var intent = Intent()
            intent.setClass(this@MemberFreeListViewActivity, GetMoreCouponActivity::class.java)
            startActivity(intent)
            finish()
        }

        mPullToRefreshListView.setOnRefreshListener(mListViewOnRefreshListener2)
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
    }

    fun getList() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("讀取中")
        mProgressDialog.setMessage("請稍候")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()

        val progressRunnable = Runnable {
            mProgressDialog.cancel()
        }

        val pdCanceller = Handler()
        pdCanceller.postDelayed(progressRunnable, 5000)
        if (!MySharedPrefernces.getIsToken(this).equals("")) {

            mFirebselibClass.getFirebaseDatabase(CollectionData.KEY_URL_FREE + "/" + MySharedPrefernces.getIsToken(this), MySharedPrefernces.getIsToken(this))

        } else {
            Toast.makeText(this@MemberFreeListViewActivity, "登入異常 請重新登入", Toast.LENGTH_SHORT).show();
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
//            Activity.RESULT_OK -> {
//
//
//                Log.d(javaClass.simpleName, "OK")
//                Log.d(javaClass.simpleName, data?.extras?.getString("id"))
//                delete(data?.extras?.getString("id").toString())
//
//            }
//            999->{
//                getList()
//            }

        }


    }

//    fun getUrl(): String {
//        var mString: String = ""
//        var bundle: Bundle = intent.extras
//        mString = bundle.getString("url")
//        MySharedPrefernces.saveIsUrl(this,mString)
//        return MySharedPrefernces.getIsUrl(this)
//    }

    inner class MyAdapter(var mAllData: ArrayList<GoogleMapPlaceDetailsData>?) : BaseAdapter() {
        fun updateData(datas: ArrayList<GoogleMapPlaceDetailsData>) {
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
                convertView = LayoutInflater.from(this@MemberFreeListViewActivity).inflate(
                        R.layout.freelistview_layout, null)

            var mStoreNameText: TextView = convertView!!.findViewById(R.id.storename)
            var mFreeText: TextView = convertView!!.findViewById(R.id.listviewtext)
            var mUseText: TextView = convertView!!.findViewById(R.id.usetext)

            mStoreNameText.text = data.result.name
//            val random = Random().nextInt(3)
//            val free = arrayOf("折扣10元", "折扣20元", "折扣50元")
            mData.forEach {
                Log.d(javaClass.simpleName, it.id)
                Log.d(javaClass.simpleName, data.result.place_id)

                if (it.id.split("+")[0].equals(data.result.place_id)) {
                    Log.d(javaClass.simpleName, "same")
                    mFreeText.text = it.price
                }
            }
            mUseText.text = "Use"
            mUseText.setOnClickListener {
                mData.forEach {
                    Log.d(javaClass.simpleName, it.id)
                    Log.d(javaClass.simpleName, data.result.place_id)

                    if (it.id.split("+")[0].equals(data.result.place_id)) {
                        Log.d(javaClass.simpleName, "same")

                        var intent = Intent()
                        var mBundle = Bundle()
                        mBundle.putString("id", it.id)
                        mBundle.putString("name",data.result.name)
                        mBundle.putString("price",it.price)

                        intent.putExtras(mBundle)
                        intent.setClass(this@MemberFreeListViewActivity, UseCouponActivity::class.java)
                        startActivity(intent)
                        finish()

                    }
                }

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
                mAllData.clear()
                mAdapter!!.notifyDataSetChanged()
                getList()
                mPullToRefreshListView.onRefreshComplete()//下拉刷新结束，下拉刷新头复位
            }, 3000)
        }

        /**
         * 上拉加载更多回调
         * @param refreshView
         */
        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>) {
            //模拟延时三秒加载更多数据
//            mPullToRefreshListView.postDelayed({
//                mPullToRefreshListView.onRefreshComplete()//上拉加载更多结束，上拉加载头复位
//                Log.d("Jack", mNextPage.size.toString())
//                if(mNextPage[0].next_page_token==null){
//                    Toast.makeText(this@MemberLoveListViewActivity, "最後一筆資料囉！！", Toast.LENGTH_SHORT).show()
//                    return@postDelayed
//                }
//                GoogleMapAPISerive.nextPage(this@MemberLoveListViewActivity,mNextPage[0].next_page_token, this@MemberLoveListViewActivity)
//                mAdapter!!.notifyDataSetChanged()
//                Toast.makeText(this@MemberLoveListViewActivity, "列表刷新！！", Toast.LENGTH_SHORT).show()
//
//
//            }, 3000)
        }
    }
}