package com.jackpan.specialstudy.oveyouforyourtravel

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), MfirebaeCallback {
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
    }

    override fun createUserState(p0: Boolean) {
    }

    override fun useLognState(p0: Boolean) {
        if(p0){
            var mAlertDialog = android.app.AlertDialog.Builder(this)
            mAlertDialog.setMessage("登入成功！！,將跳轉到首頁")
            mAlertDialog.setPositiveButton("OK",DialogInterface.OnClickListener(){dialogInterface, i ->
                mFirebselibClass.userLoginCheck()
                this.finish()


            })
            mAlertDialog.show()
        }else{
            var mAlertDialog = android.app.AlertDialog.Builder(this)
            mAlertDialog.setMessage("登入失敗！！請檢查是否錯誤")
            mAlertDialog.setPositiveButton("OK",null)
            mAlertDialog.show()
        }
    }

    override fun getFirebaseStorageState(p0: Boolean) {
    }

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    lateinit var  mLoginBtn : Button
    lateinit var  mSigupBtn : Button
    lateinit var  mEmailEdt : EditText
    lateinit var  mPasswordEdt : EditText
    lateinit var  mEmailStr :String
    lateinit var  mPasswordStr :String
    lateinit var mFirebselibClass : MfiebaselibsClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass =  MfiebaselibsClass(this,this@LoginActivity)

        setContentView(R.layout.activity_login)
        mLoginBtn = findViewById(R.id.loginbtn)
        mSigupBtn = findViewById(R.id.sigupbtn)
        mEmailEdt = findViewById(R.id.emaidedt)
        mPasswordEdt = findViewById(R.id.passwordedt)

        mLoginBtn.setOnClickListener {
            mEmailStr = mEmailEdt.text.toString().trim()
            mPasswordStr = mPasswordEdt.text.toString().trim()
            login(mEmailStr,mPasswordStr)
        }
        mSigupBtn.setOnClickListener {
            var mSigupIntent = Intent()
            mSigupIntent.setClass(this,SignUpActivity::class.java)
            startActivity(mSigupIntent)
        }


        // Set up the login form
    }
    fun login(emaial :String,password:String){
        if(emaial.equals("")){
            showDilog("請輸入帳號！")
            return
        }
        if (password.equals("")){
            showDilog("請輸入密碼")

            return
        }
        mFirebselibClass.userLogin(emaial,password)


    }

    fun showDilog(message:String){
        var mAlertDilog = AlertDialog.Builder(this)
        mAlertDilog.setTitle("提示")
        mAlertDilog.setMessage(message)
        mAlertDilog.setPositiveButton("知道了!",null)
        mAlertDilog.show()
    }

}
