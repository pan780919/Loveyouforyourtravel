package com.jackpan.specialstudy.oveyouforyourtravel

import android.app.ProgressDialog
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

import com.itheima.pulltorefreshlib.PullToRefreshListView
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import com.jackpan.specialstudy.oveyouforyourtravel.Data.CollectionData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleMapPlaceDetailsData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleResponseData
import java.util.*

class GetMoreCouponActivity : AppCompatActivity(), MfirebaeCallback, GoogleMapAPISerive.GetResponse {
    override fun getData(googleResponseData: GoogleResponseData?) {
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
    }

    override fun getuserLoginEmail(p0: String?) {
    }

    override fun getDeleteState(p0: Boolean, p1: String?, p2: Any?) {
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

    lateinit var mFirebselibClass: MfiebaselibsClass
    private lateinit var mInterstitialAd: InterstitialAd
    lateinit var mGetMoreButton : Button
    var mAdapter: MyAdapter? = null
    var mAllData: ArrayList<GoogleMapPlaceDetailsData> = ArrayList()
    lateinit var mProgressDialog: ProgressDialog
    lateinit var mPullToRefreshListView: PullToRefreshListView
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass = MfiebaselibsClass(this, this@GetMoreCouponActivity)

        setContentView(R.layout.activity_get_more_coupon)
        getCouponList()
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("讀取中")
        mProgressDialog.setMessage("請稍候")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
        mPullToRefreshListView = findViewById(R.id.pull_to_refresh_list_view)

        mAdapter = MyAdapter(mAllData)
        mPullToRefreshListView.setAdapter(mAdapter)
        mPullToRefreshListView.setOnItemClickListener { parent, view, position, id ->
//            setCoupon(mAdapter!!.mAllData!!.get(position).result.place_id)

        }
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)


    }
    fun getCouponList(){
        var  mArray = arrayOf("ChIJO7jfCHkEbjQR67Rh2pNutMI","ChIJDZtbAXkEbjQR9NBmh-KSCcQ","ChIJxbX0zXkEbjQRZ16eV60lgk4","ChIJzfx7qXAEbjQR0Uy44Fen1gc","ChIJVVokUngEbjQR-a2eD4kbT70","ChIJwfoesmQEbjQRSewxKQGGl4Q")
        mArray.forEach {
            GoogleMapAPISerive.getPlaceDeatail(this, it, this)

        }

    }
    fun setCoupon(id:String){
//        var  mArray = arrayOf("ChIJO7jfCHkEbjQR67Rh2pNutMI","ChIJDZtbAXkEbjQR9NBmh-KSCcQ","ChIJxbX0zXkEbjQRZ16eV60lgk4","ChIJzfx7qXAEbjQR0Uy44Fen1gc","ChIJVVokUngEbjQR-a2eD4kbT70","ChIJwfoesmQEbjQRSewxKQGGl4Q")
//        val random = Random().nextInt(mArray.size)
        var mHasMap = HashMap<String, String>()
        mHasMap.put(CollectionData.KEY_ID,id)
        mFirebselibClass.setFireBaseDB(CollectionData.KEY_URL_FREE + "/" + MySharedPrefernces.getIsToken(this@GetMoreCouponActivity), mHasMap.get(CollectionData.KEY_ID), mHasMap)

        Toast.makeText(this@GetMoreCouponActivity,"領取成功！！",Toast.LENGTH_SHORT).show()
        var intent = Intent()
        intent.setClass(this@GetMoreCouponActivity,MemberFreeListViewActivity::class.java)
        startActivity(intent)
        finish()
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
                convertView = LayoutInflater.from(this@GetMoreCouponActivity).inflate(
                        R.layout.freelistview_layout, null)
            val free = arrayOf("折扣10元","折扣20元","折扣50元")

            var mStoreNameText : TextView = convertView!!.findViewById(R.id.storename)
            var mFreeText : TextView = convertView!!.findViewById(R.id.listviewtext)
            var mUseText : TextView = convertView!!.findViewById(R.id.usetext)

            mStoreNameText.text =data.result.name
            val random = Random().nextInt(3)

            mFreeText.text = free.get(random)
            mFreeText.visibility = View.GONE

//            if (mRewardedVideoAd.isLoaded) {
//                mUseText.text = "Get"
//            }else{
//                mUseText.setTextSize(12F)
//                mUseText.text = "今日已被領取完畢"
//
//            }
            mUseText.text = "Get"

            mUseText.setOnClickListener {
//                mRewardedVideoAd.show()

//                if(mRewardedVideoAd.isLoaded){
//                    mRewardedVideoAd.show()
//
//                }else{
//                    var intent = Intent()
//                    intent.setClass(this@GetMoreCouponActivity,MainActivity::class.java)
//                    startActivity(intent)
//
//                }
                setCoupon(data.result.place_id)
//            IronSource.showRewardedVideo()

            }


            return convertView
        }

    }
}
