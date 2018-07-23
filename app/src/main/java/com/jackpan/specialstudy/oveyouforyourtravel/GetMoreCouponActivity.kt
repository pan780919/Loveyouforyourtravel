package com.jackpan.specialstudy.oveyouforyourtravel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

class GetMoreCouponActivity : AppCompatActivity(), RewardedVideoAdListener {
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
        mGetMoreButton.visibility = View.INVISIBLE

        Toast.makeText(this, "onRewardedVideoAdFailedToLoad:"+errorCode, Toast.LENGTH_SHORT).show()

    }

    override fun onRewardedVideoAdLoaded() {
        mGetMoreButton.visibility = View.VISIBLE
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show()
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

    private lateinit var mRewardedVideoAd: RewardedVideoAd
    lateinit var mGetMoreButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_more_coupon)
        mGetMoreButton = findViewById(R.id.getcoupon)
        SetRewardedVideoAd()

        mGetMoreButton.setOnClickListener {

            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            }
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
    fun getMoreCoupon(){
        var  mArray = arrayOf("ChIJO7jfCHkEbjQR67Rh2pNutMI","ChIJDZtbAXkEbjQR9NBmh-KSCcQ","ChIJxbX0zXkEbjQRZ16eV60lgk4","ChIJzfx7qXAEbjQR0Uy44Fen1gc","ChIJVVokUngEbjQR-a2eD4kbT70","ChIJwfoesmQEbjQRSewxKQGGl4Q")

    }
    fun SetRewardedVideoAd(){
        MobileAds.initialize(this, "ca-app-pub-7019441527375550~6063307093")

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this

        mRewardedVideoAd.loadAd("ca-app-pub-7019441527375550/3054000377",
                AdRequest.Builder().build())

    }
}
