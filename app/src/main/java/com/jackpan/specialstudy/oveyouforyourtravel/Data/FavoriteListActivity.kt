package com.jackpan.specialstudy.oveyouforyourtravel.Data

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import com.jackpan.specialstudy.oveyouforyourtravel.R

class FavoriteListActivity : AppCompatActivity(),MfirebaeCallback {
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

    lateinit var mFirebselibClass : MfiebaselibsClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass = MfiebaselibsClass(this,this)
        setContentView(R.layout.activity_favorite_list)
    }
}
