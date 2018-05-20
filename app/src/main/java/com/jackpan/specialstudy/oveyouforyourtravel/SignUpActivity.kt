package com.jackpan.specialstudy.oveyouforyourtravel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback

class SignUpActivity : AppCompatActivity(), MfirebaeCallback {
    lateinit var  mNaamEdt :EditText
    lateinit var mDatadedt :EditText
    lateinit var mPhoneedt :EditText
    lateinit var mEmailedt :EditText
    lateinit var mPasswordedt :EditText
    lateinit var mPasswordedt_2 :EditText
    lateinit var mSignupbtn :Button

    lateinit var mFirebselibClass : MfiebaselibsClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass =  MfiebaselibsClass(this,this@SignUpActivity)

        setContentView(R.layout.activity_sign_up)
        mNaamEdt = findViewById(R.id.nameedt)
        mDatadedt =findViewById(R.id.datadedt)
        mPhoneedt = findViewById(R.id.phoneedt)
        mEmailedt = findViewById(R.id.emaidedt)
        mPasswordedt = findViewById(R.id.passwordedt)
        mPasswordedt_2 = findViewById(R.id.passwordedt_2)
        mSignupbtn = findViewById(R.id.signupbtn)

        mSignupbtn.setOnClickListener {

            sigup()
        }



    }
    fun sigup(){

    }
    override fun resetPassWordState(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getsSndPasswordResetEmailState(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFirebaseStorageType(p0: String?, p1: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpdateUserName(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDatabaseData(p0: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getuserLoginEmail(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDeleteState(p0: Boolean, p1: String?, p2: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFireBaseDBState(p0: Boolean, p1: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getuseLoginId(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createUserState(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun useLognState(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFirebaseStorageState(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserLogoutState(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

