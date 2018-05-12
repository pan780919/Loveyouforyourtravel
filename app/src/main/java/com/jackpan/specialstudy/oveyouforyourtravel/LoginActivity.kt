package com.jackpan.specialstudy.oveyouforyourtravel

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    lateinit var  mLoginBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mLoginBtn = findViewById(R.id.loginbtn)

        // Set up the login form
    }
    fun login(emaial :String,password:String){
        if(emaial.equals("")){
            showDilog("請輸入帳號！")
            return
        }
        if (password.equals("請輸入密碼")){
            showDilog("")

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
