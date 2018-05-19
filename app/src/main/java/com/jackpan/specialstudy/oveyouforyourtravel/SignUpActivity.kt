package com.jackpan.specialstudy.oveyouforyourtravel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback

class SignUpActivity : AppCompatActivity(), MfirebaeCallback {


    lateinit var mFirebselibClass : MfiebaselibsClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mFirebselibClass =  MfiebaselibsClass(this,this)



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

