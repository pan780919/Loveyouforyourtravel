package com.jackpan.specialstudy.oveyouforyourtravel

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import com.jackpan.specialstudy.oveyouforyourtravel.Data.CollectionData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.TypeListViewActivity
import java.util.*

class MemberLoveActivity : AppCompatActivity(), MfirebaeCallback {
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

    lateinit var mFoodText :TextView
    lateinit var mParkText :TextView
    lateinit var mFreeText :TextView
    lateinit var mFirebselibClass : MfiebaselibsClass
    lateinit var mProgressDialog: ProgressDialog
    var mArrayString = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass =  MfiebaselibsClass(this,this@MemberLoveActivity)

        setContentView(R.layout.activity_member_love)
        mFoodText = findViewById(R.id.foodtext)
        mParkText = findViewById(R.id.parktext)
        mFreeText = findViewById(R.id.freetexttext)

        mFoodText.setOnClickListener {
            var intent = Intent()
            var mBundle = Bundle()
            mBundle.putString("url",CollectionData.KEY_URL_FOOD)
            intent.putExtras(mBundle)
            intent.setClass(this, MemberLoveListViewActivity::class.java)
            startActivity(intent)


        }

        mParkText.setOnClickListener {
            var intent = Intent()
            var mBundle = Bundle()
            mBundle.putString("url",CollectionData.KEY_URL_PARK)
            intent.putExtras(mBundle)
            intent.setClass(this, MemberLoveListViewActivity::class.java)
            startActivity(intent)


        }

        mFreeText.setOnClickListener {
            var intent = Intent()
            var mBundle = Bundle()
            mBundle.putString("url",CollectionData.KEY_URL_FREE)
            intent.putExtras(mBundle)
//            MySharedPrefernces.saveIsUrl(this,CollectionData.KEY_URL_FREE)
            intent.setClass(this, MemberFreeListViewActivity::class.java)
            startActivity(intent)

        }
    }
    fun setArray(){
        mArrayString.add("ChIJRU2X1EQEbjQR-eOVjNzERRQ")
        mArrayString.add("ChIJPXn4lkQEbjQRtInW0ADmQw8")
        mArrayString.add("ChIJJXvlCFoEbjQRqwnQcbOtSQI")
        mArrayString.add("ChIJU_XatFoEbjQRdgw_7DAcnYU")
        mArrayString.add("ChIJ_-qulFoEbjQR1XFPp7jiG3w")

    }
    fun setdb(){
        var mHasMap = HashMap<String, String>()


        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("讀取中")
        mProgressDialog.setMessage("請稍候")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()

        val random = Random().nextInt(3)
        val free = arrayOf("折扣10元", "折扣20元", "折扣50元")
        Log.d(javaClass.simpleName,free.get(random))
        mArrayString.forEach {
            Log.d("mArrayString",it)
            var mHasMap = HashMap<String, String>()

            mHasMap.put(CollectionData.KEY_ID, it)
            mHasMap.put(CollectionData.KEY_PRICE, free.get(random))

            mFirebselibClass.setFireBaseDB(CollectionData.KEY_URL_FREE + "/" + MySharedPrefernces.getIsToken(this@MemberLoveActivity), mHasMap.get(CollectionData.KEY_ID), mHasMap)

        }
        mProgressDialog.dismiss()
    }
}
