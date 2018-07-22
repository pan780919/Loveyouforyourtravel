package com.jackpan.specialstudy.oveyouforyourtravel

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import com.google.firebase.auth.FirebaseAuth
import com.jackpan.specialstudy.oveyouforyourtravel.Data.CollectionData


class SignUpActivity : AppCompatActivity(), MfirebaeCallback {
    lateinit var  mNaamEdt :EditText
    lateinit var mDatadedt :EditText
    lateinit var mPhoneedt :EditText
    lateinit var mEmailedt :EditText
    lateinit var mPasswordedt :EditText
    lateinit var mPasswordedt_2 :EditText
    lateinit var mSignupbtn :Button
    lateinit var mEmailStr :String
    lateinit var mPasswordStr :String
    lateinit var mFirebselibClass : MfiebaselibsClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass =  MfiebaselibsClass(this,this@SignUpActivity)
        setContentView(R.layout.activity_sign_up)
        mNaamEdt = findViewById(R.id.nameedt)
        mDatadedt =findViewById(R.id.datadedt)
        mPhoneedt = findViewById(R.id.phoneedt)
        mEmailedt = findViewById(R.id.emailedt)
        mPasswordedt = findViewById(R.id.passwordedt)
        mPasswordedt_2 = findViewById(R.id.passwordedt_2)
        mSignupbtn = findViewById(R.id.signupbtn)

        mSignupbtn.setOnClickListener {
            mEmailStr = mEmailedt.text.toString().trim()
            mPasswordStr = mPasswordedt.text.toString().trim()
            if(mNaamEdt.text.toString().trim().equals("")){
                Toast.makeText(this,"請輸入姓名",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                MySharedPrefernces.saveIsName(this,mNaamEdt.text.toString().trim())

            }
            if(mPhoneedt.text.toString().trim().equals("")){
                Toast.makeText(this,"請輸入手機",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                MySharedPrefernces.saveIsPhone(this,mPhoneedt.text.toString().trim())
            }
            if(!mDatadedt.text.toString().trim().equals("")){
                MySharedPrefernces.saveIsData(this,mDatadedt.text.toString().trim())
            }
            if(mEmailStr.equals("")){
                Toast.makeText(this,"請輸入Email",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mPasswordStr.equals("")){
                Toast.makeText(this,"請輸入密碼",Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
            if(mPasswordedt_2.text.toString().trim().equals("")){
                Toast.makeText(this,"請再次輸入密碼",Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
            if(!mPasswordStr.equals(mPasswordedt_2.text.toString().trim())){
                Toast.makeText(this,"密碼不一樣喔",Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
            sigup(mEmailStr,mPasswordStr)


        }



    }

    fun sigup(email:String,password:String){
        mFirebselibClass.createUser(email,password)


    }

    override fun onStart() {
        super.onStart()
        mFirebselibClass.setAuthListener()

    }

    override fun onStop() {
        super.onStop()
        mFirebselibClass.removeAuthListener()
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
        Log.d("getuserLoginEmail",p0)
    }

    override fun getDeleteState(p0: Boolean, p1: String?, p2: Any?) {
    }

    override fun getFireBaseDBState(p0: Boolean, p1: String?) {
    }

    override fun getuseLoginId(p0: String?) {

    }


    override fun createUserState(p0: Boolean) {
        Log.d("createUserState",""+p0)
        if(p0){

            var mAlertDialog = android.app.AlertDialog.Builder(this)
            mAlertDialog.setMessage("註冊成功！！,將跳轉到登入頁面")
            mAlertDialog.setPositiveButton("OK",DialogInterface.OnClickListener(){dialogInterface, i ->
                this.finish()

            })
            mAlertDialog.show()
        }else{
            var mAlertDialog = android.app.AlertDialog.Builder(this)
            mAlertDialog.setMessage("註冊失敗！！請檢查格式是否錯誤")
            mAlertDialog.setPositiveButton("OK",null)
            mAlertDialog.show()
        }

    }

    override fun useLognState(p0: Boolean) {
        Log.d("useLognState","Boolean:"+p0)


    }

    override fun getFirebaseStorageState(p0: Boolean) {
    }

    override fun getUserLogoutState(p0: Boolean) {
    }

}

