package com.jackpan.specialstudy.oveyouforyourtravel

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class HomePageActivity : AppCompatActivity() {
    lateinit var mLoginBtn : Button
    lateinit var mMaPlayout : LinearLayout
    lateinit var mLoveLayout : LinearLayout
    lateinit var mFoodLayout : LinearLayout
    lateinit var mLevelLayout : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        mLoginBtn = findViewById(R.id.loginbtn)
        mMaPlayout = findViewById(R.id.maplayout)
        mLoveLayout = findViewById(R.id.lovelayout)
        mFoodLayout = findViewById(R.id.foodlayout)
        mLevelLayout = findViewById(R.id.levellayout)
        mLoginBtn.setOnClickListener{
            var mIntnet = Intent()
            mIntnet.setClass(this,LoginActivity::class.java)
            startActivity(mIntnet)

        }
        mMaPlayout.setOnClickListener{ checkLoginState()}
        mLevelLayout.setOnClickListener{checkLoginState()}
        mFoodLayout.setOnClickListener { checkLoginState() }
        mLoveLayout.setOnClickListener {checkLoginState()  }
    }
    private fun checkLoginState(){

        var mAlertDilog =AlertDialog.Builder(this)
        mAlertDilog.setTitle("尚未登入")
        mAlertDilog.setMessage("登入後才能使用喔！！")
        mAlertDilog.setPositiveButton("知道了！",null)
        mAlertDilog.show()
    }
}
