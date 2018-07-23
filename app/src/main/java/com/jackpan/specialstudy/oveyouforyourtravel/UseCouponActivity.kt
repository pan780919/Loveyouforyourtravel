package com.jackpan.specialstudy.oveyouforyourtravel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView


class UseCouponActivity : AppCompatActivity() {
    lateinit var mTimeText :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_coupon)
        mTimeText = findViewById(R.id.timetext)
        countdowntime()


    }

    fun countdowntime(){
        object : CountDownTimer(60000, 1000) {

            override fun onFinish() {
                Log.d(javaClass.simpleName,"onFinish")
            }

            override fun onTick(millisUntilFinished: Long) {
                Log.d(javaClass.simpleName, (millisUntilFinished / 1000).toString())
                mTimeText.text =(millisUntilFinished / 1000).toString()
            }

        }.start()


    }
}
