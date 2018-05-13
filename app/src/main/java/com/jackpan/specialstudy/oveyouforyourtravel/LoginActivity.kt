package com.jackpan.specialstudy.oveyouforyourtravel

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    lateinit var  mLoginBtn : Button
    lateinit var  mSigupBtn : Button
    lateinit var  mEmailEdt : EditText
    lateinit var  mPasswordEdt : EditText
    lateinit var  mEmailStr :String
    lateinit var  mPasswordStr :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    }

    fun showDilog(message:String){
        var mAlertDilog = AlertDialog.Builder(this)
        mAlertDilog.setTitle("提示")
        mAlertDilog.setMessage(message)
        mAlertDilog.setPositiveButton("知道了!",null)
        mAlertDilog.show()
    }

}
