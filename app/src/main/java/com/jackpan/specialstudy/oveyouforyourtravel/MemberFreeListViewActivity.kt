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
import com.adbert.AdbertVideoBox
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
            var gson = Gson()
            val reader = JsonReader(StringReader(p0.toString()))
            reader.setLenient(true)
            var mCollectionData = MapDetailResponData()

            mCollectionData = gson.fromJson(reader, MapDetailResponData::class.java)
            Log.d("getDatabaseData", "mCollectionData:" + mCollectionData.id)
            GoogleMapAPISerive.getPlaceDeatail(this, mCollectionData.id, this)
        }


    }

    override fun getuserLoginEmail(p0: String?) {
    }

    override fun getDeleteState(p0: Boolean, p1: String?, p2: Any?) {
        if (p0){

        }else{

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
            mAdapter!!.notifyDataSetChanged()
            Log.d("Jack", "getData")
            Log.d("Jack", mAllData.size.toString())


        }
        mProgressDialog.dismiss()


    }

    override fun getData(googleResponseData: GoogleResponseData?) {


    }

    lateinit var mPullToRefreshListView: PullToRefreshListView

    var mAdapter: MyAdapter? = null
    var mAllData: ArrayList<GoogleMapPlaceDetailsData> = ArrayList()
    lateinit var mProgressDialog: ProgressDialog
    lateinit var mTypeString: String
    lateinit var mLatLngString: String

    lateinit var mFirebselibClass: MfiebaselibsClass
    lateinit var mGetMoreFreeButton: Button
    lateinit var mAdbertVideoBox: AdbertVideoBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_free_list_view)
        mFirebselibClass = MfiebaselibsClass(this, this@MemberFreeListViewActivity)
        getList()
        mPullToRefreshListView = findViewById(R.id.pull_to_refresh_list_view)
        mAdapter = MyAdapter(mAllData)
        mPullToRefreshListView.setAdapter(mAdapter)
        mGetMoreFreeButton = findViewById(R.id.getmorebutton)
        mGetMoreFreeButton.setOnClickListener {
            var intent = Intent()
            intent.setClass(this@MemberFreeListViewActivity, GetMoreCouponActivity::class.java)
            startActivityForResult(intent,0)
        }


    }

    fun delete(id: String) {

        mFirebselibClass.deleteData(CollectionData.KEY_URL_FREE, id)

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
        pdCanceller.postDelayed(progressRunnable, 10000)
        if (!getUrl().equals("")) {
            mFirebselibClass.getFirebaseDatabase(getUrl() + "/" + MySharedPrefernces.getIsToken(this), MySharedPrefernces.getIsToken(this))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                Log.d(javaClass.simpleName, "OK")
                Log.d(javaClass.simpleName, data?.extras?.getString("id"))
                delete(data?.extras?.getString("id").toString())

            }
            999->{
                getList()
            }

        }


    }

    fun getUrl(): String {
        var mString: String = ""
        var bundle: Bundle = intent.extras
        mString = bundle.getString("url")
        return mString
    }

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
            val free = arrayOf("折扣10元", "折扣20元", "折扣50元")

            var mStoreNameText: TextView = convertView!!.findViewById(R.id.storename)
            var mFreeText: TextView = convertView!!.findViewById(R.id.listviewtext)
            var mUseText: TextView = convertView!!.findViewById(R.id.usetext)

            mStoreNameText.text = data.result.name
            val random = Random().nextInt(3)

            mFreeText.text = free.get(random)
            mUseText.text = "Use"
            mUseText.setOnClickListener {
                var intent = Intent()
                var mBundle = Bundle();
                mBundle.putString("id", data.result.place_id)
                intent.putExtras(mBundle)
                intent.setClass(this@MemberFreeListViewActivity, UseCouponActivity::class.java)
                startActivityForResult(intent, 0)
            }


            return convertView
        }

    }


}