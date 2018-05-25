package com.jackpan.specialstudy.oveyouforyourtravel

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback

class HomePageActivity : AppCompatActivity(), MfirebaeCallback {
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
        Log.d("getuseLoginId",p0)
        MySharedPrefernces.saveIsToken(this,p0)
    }

    override fun createUserState(p0: Boolean) {
    }

    override fun useLognState(p0: Boolean) {
    }

    override fun onResume() {

        super.onResume()
        mFirebselibClass.userLoginCheck()
    }

    override fun onStart() {
        super.onStart()
        mFirebselibClass.setAuthListener()

    }

    override fun onStop() {
        super.onStop()
        mFirebselibClass.removeAuthListener()
    }
    override fun getFirebaseStorageState(p0: Boolean) {
    }

    lateinit var mLoginBtn : Button
    lateinit var mMaPlayout : LinearLayout
    lateinit var mLoveLayout : LinearLayout
    lateinit var mFoodLayout : LinearLayout
    lateinit var mLevelLayout : LinearLayout
    lateinit var mFirebselibClass : MfiebaselibsClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass =  MfiebaselibsClass(this,this@HomePageActivity)

        setContentView(R.layout.activity_home_page)
        mFirebselibClass.userLoginCheck()

        mLoginBtn = findViewById(R.id.loginbtn)
        mMaPlayout = findViewById(R.id.maplayout)
        mLoveLayout = findViewById(R.id.lovelayout)
        mFoodLayout = findViewById(R.id.foodlayout)
        mLevelLayout = findViewById(R.id.levellayout)
        mLoginBtn.setOnClickListener{
            if(MySharedPrefernces.getIsToken(this).equals("")){

                var mIntnet = Intent()
                mIntnet.setClass(this,LoginActivity::class.java)
                startActivity(mIntnet)
            }else{
                var mAlertDilog =AlertDialog.Builder(this)
                mAlertDilog.setTitle("已經登入囉")
                mAlertDilog.setMessage("不需要再登入了")
                mAlertDilog.setPositiveButton("知道了！",null)
                mAlertDilog.show()
            }


        }
        mMaPlayout.setOnClickListener{
            var intent = Intent()
            intent.setClass(this,MapsActivity::class.java)
            startActivity(intent)


        }
        mLevelLayout.setOnClickListener{checkLoginState()}
        mFoodLayout.setOnClickListener { checkLoginState() }
        mLoveLayout.setOnClickListener {checkLoginState()  }
    }
    private fun checkLoginState(){
        if(MySharedPrefernces.getIsToken(this).equals("")){
            var mAlertDilog =AlertDialog.Builder(this)
            mAlertDilog.setTitle("尚未登入")
            mAlertDilog.setMessage("登入後才能使用喔！！")
            mAlertDilog.setPositiveButton("知道了！",null)
            mAlertDilog.show()
        }else{
            var mAlertDilog =AlertDialog.Builder(this)
            mAlertDilog.setTitle("已登入")
            mAlertDilog.setMessage("您已經可以使用此功能")
            mAlertDilog.setPositiveButton("知道了！",null)
            mAlertDilog.show()
        }

    }

}
