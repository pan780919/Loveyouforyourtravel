package com.jackpan.specialstudy.oveyouforyourtravel

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import com.jackpan.specialstudy.oveyouforyourtravel.Data.TypeListViewActivity

class HomePageActivity : AppCompatActivity(), MfirebaeCallback{
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
    private var locationManager: LocationManager? = null
    lateinit var mProgressDialog:ProgressDialog
    lateinit var latlon: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass =  MfiebaselibsClass(this,this@HomePageActivity)

        setContentView(R.layout.activity_home_page)
        mFirebselibClass.userLoginCheck()
        checkPermission()
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("讀取中")
        mProgressDialog.setMessage("請稍候")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch (ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available")
        }
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
        mFoodLayout.setOnClickListener {
            if(!latlon.equals("")){
                var intent = Intent()
                var mBundle = Bundle()
                mBundle.putString("type","")
                mBundle.putString("latlon",latlon)
                intent.putExtras(mBundle)
                intent.setClass(this, TypeListViewActivity::class.java)
                startActivity(intent)

            }

        }
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
    val MY_PERMISSIONS_REQUEST_LOCATION = 100

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_LOCATION)
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "需要定位功能,才能使用喔", Toast.LENGTH_SHORT).show()
                return
            }
        }
    }
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d("Location", location.latitude.toString())
            Log.d("Location", location.longitude.toString())
            latlon = location.latitude.toString() + "," + location.longitude.toString()
//            locationTextView.text = "${location.latitude} - ${location.longitude}"
            Log.d("Location", latlon)
            mProgressDialog.dismiss()



        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}
