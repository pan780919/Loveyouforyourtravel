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
import com.adbert.AdbertADView
import com.adbert.AdbertVideoBox
import com.adbert.AdbertVideoBoxListener
import com.clickforce.ad.Listener.PreRollViewLinstener
import com.clickforce.ad.PreRollAdView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.model.Placement
import com.ironsource.mediationsdk.sdk.RewardedVideoListener
import com.itheima.pulltorefreshlib.PullToRefreshListView
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import com.jackpan.specialstudy.oveyouforyourtravel.Data.CollectionData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleMapPlaceDetailsData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleResponseData
import java.util.*

class GetMoreCouponActivity : AppCompatActivity(), RewardedVideoAdListener, MfirebaeCallback, GoogleMapAPISerive.GetResponse {
    override fun getData(googleResponseData: GoogleResponseData?) {
    }

    override fun getDetailData(googleMapPlaceDetailsData: GoogleMapPlaceDetailsData?) {
        if (googleMapPlaceDetailsData != null) {


            mAllData.add(googleMapPlaceDetailsData)
            mAdapter!!.notifyDataSetChanged()
            Log.d("Jack", "getData")
            Log.d("Jack", mAllData.size.toString())




        }    }

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
    lateinit var mAdbertVideoBox :AdbertVideoBox
    var mMymRewardedVideoListener = MymRewardedVideoListener()
    override fun onRewarded(reward: RewardItem) {
        Toast.makeText(this, "onRewarded! currency: ${reward.type} amount: ${reward.amount}",
                Toast.LENGTH_SHORT).show()
        // Reward the user.
    }

    override fun onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdClosed() {
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {

        Toast.makeText(this, "onRewardedVideoAdFailedToLoad:"+errorCode, Toast.LENGTH_SHORT).show()
        mProgressDialog.dismiss()


    }

    override fun onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show()
        mProgressDialog.dismiss()
    }

    override fun onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show()
    }

    lateinit var mGetMoreButton : Button
    var mAdapter: MyAdapter? = null
    var mPreRollViewLinstener :myPreRollViewLinstener? = null
    var mAllData: ArrayList<GoogleMapPlaceDetailsData> = ArrayList()
    lateinit var mProgressDialog: ProgressDialog
    lateinit var mPullToRefreshListView: PullToRefreshListView
    lateinit var preRollAdView : PreRollAdView
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass = MfiebaselibsClass(this, this@GetMoreCouponActivity)

        setContentView(R.layout.activity_get_more_coupon)
//        preRollAdView = findViewById(R.id.preroll)
//        preRollAdView.setOnPreRollViewLoaded(mPreRollViewLinstener)

        getCouponList()
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("讀取中")
        mProgressDialog.setMessage("請稍候")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-7019441527375550/6135553828"
        mInterstitialAd.loadAd(AdRequest.Builder().addTestDevice("8303A350BC15927D2FCAB4ACA7FE50A7").build())
        mInterstitialAd.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d(javaClass.simpleName,"onAdLoaded")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                Log.d(javaClass.simpleName,"errorCode"+errorCode)

            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.d(javaClass.simpleName,"onAdOpened")

            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.d(javaClass.simpleName,"onAdLeftApplication")

            }

            override fun onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.d(javaClass.simpleName,"onAdClosed")

            }
        }
//        setAdbertAd()
        SetRewardedVideoAd()
        mPullToRefreshListView = findViewById(R.id.pull_to_refresh_list_view)

        mAdapter = MyAdapter(mAllData)
        mPullToRefreshListView.setAdapter(mAdapter)
        mPullToRefreshListView.setOnItemClickListener { parent, view, position, id ->
            mInterstitialAd.show()

        }
    }
    class myPreRollViewLinstener : PreRollViewLinstener {
        override fun onStartPlayVideo() {
            Log.d(javaClass.simpleName,"onStartPlayVideo")
        }

        override fun onFailedToPreRollAd() {
            Log.d(javaClass.simpleName,"onFailedToPreRollAd")

        }

    }
    override fun onPause() {
        super.onPause()
        mRewardedVideoAd.pause(this)

    }

    override fun onResume() {
        super.onResume()
        mRewardedVideoAd.resume(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        mRewardedVideoAd.destroy(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        preRollAdView.fullScreen(this@GetMoreCouponActivity)


    }
    fun getCouponList(){
        var  mArray = arrayOf("ChIJO7jfCHkEbjQR67Rh2pNutMI","ChIJDZtbAXkEbjQR9NBmh-KSCcQ","ChIJxbX0zXkEbjQRZ16eV60lgk4","ChIJzfx7qXAEbjQR0Uy44Fen1gc","ChIJVVokUngEbjQR-a2eD4kbT70","ChIJwfoesmQEbjQRSewxKQGGl4Q")
        mArray.forEach {
            GoogleMapAPISerive.getPlaceDeatail(this, it, this)

        }

    }
    fun setCoupon(){
        var  mArray = arrayOf("ChIJO7jfCHkEbjQR67Rh2pNutMI","ChIJDZtbAXkEbjQR9NBmh-KSCcQ","ChIJxbX0zXkEbjQRZ16eV60lgk4","ChIJzfx7qXAEbjQR0Uy44Fen1gc","ChIJVVokUngEbjQR-a2eD4kbT70","ChIJwfoesmQEbjQRSewxKQGGl4Q")
        val random = Random().nextInt(mArray.size)
        var mHasMap = HashMap<String, String>()
        mHasMap.put(CollectionData.KEY_ID, mArray.get(random))
        mFirebselibClass.setFireBaseDB(CollectionData.KEY_URL_FREE + "/" + MySharedPrefernces.getIsToken(this@GetMoreCouponActivity), mHasMap.get(CollectionData.KEY_ID), mHasMap)

    }

    fun SetRewardedVideoAd(){
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this
        mRewardedVideoAd.loadAd("ca-app-pub-7019441527375550/1968113408",
                AdRequest.Builder().build())

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

            if (mRewardedVideoAd.isLoaded) {
                mUseText.text = "Get"
            }else{
                mUseText.setTextSize(12F)
                mUseText.text = "今日已被領取完畢"

            }
            mUseText.setOnClickListener {
//                mRewardedVideoAd.show()

                if(mRewardedVideoAd.isLoaded){
                    mRewardedVideoAd.show()

                }else{
                    var intent = Intent()
                    intent.setClass(this@GetMoreCouponActivity,MainActivity::class.java)
                    startActivity(intent)

                }
//            IronSource.showRewardedVideo()
            }


            return convertView
        }

    }
     var  myAdbertVideoBoxListener = mAdbertVideoBoxListener();
    fun setAdbertAd(){
        mAdbertVideoBox = findViewById(R.id.boxView)
        mAdbertVideoBox.setID("20170619000001", "90cebe8ef120c8bb6ac2ce529dcb99af")
        mAdbertVideoBox.setListener(myAdbertVideoBoxListener)
        mAdbertVideoBox.loadAD()



    }
    inner  class  mAdbertVideoBoxListener : AdbertVideoBoxListener(){
        override fun onReceived(p0: String?) {
            super.onReceived(p0)
            Log.d(javaClass.simpleName,"onReceived")
        }

        override fun onFailReceived(p0: String?) {
            super.onFailReceived(p0)
            Log.d(javaClass.simpleName,"onFailReceived:"+p0.toString())

        }

        override fun onCompletion() {
            super.onCompletion()
            Log.d(javaClass.simpleName,"onCompletion:")

        }

    }
    inner class  MymRewardedVideoListener : RewardedVideoListener {
        override fun onRewardedVideoAdClosed() {
            Log.d(javaClass.simpleName,"onRewardedVideoAdClosed")

        }

        override fun onRewardedVideoAdRewarded(p0: Placement?) {
            Log.d(javaClass.simpleName,"onRewardedVideoAdRewarded")

        }

        override fun onRewardedVideoAdClicked(p0: Placement?) {
            Log.d(javaClass.simpleName,"onRewardedVideoAdClicked")

        }

        override fun onRewardedVideoAdOpened() {
            Log.d(javaClass.simpleName,"onRewardedVideoAdOpened")

        }

        override fun onRewardedVideoAdShowFailed(p0: IronSourceError?) {
            Log.d(javaClass.simpleName,"onRewardedVideoAdShowFailed")

        }

        override fun onRewardedVideoAvailabilityChanged(p0: Boolean) {
            Log.d(javaClass.simpleName,"onRewardedVideoAvailabilityChanged")

        }

        override fun onRewardedVideoAdEnded() {
            Log.d(javaClass.simpleName,"onRewardedVideoAdEnded")

        }

        override fun onRewardedVideoAdStarted() {
            Log.d(javaClass.simpleName,"onRewardedVideoAdStarted")
        }

    }
}
