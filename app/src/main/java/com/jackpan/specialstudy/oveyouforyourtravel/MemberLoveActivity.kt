package com.jackpan.specialstudy.oveyouforyourtravel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.jackpan.specialstudy.oveyouforyourtravel.Data.CollectionData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.TypeListViewActivity

class MemberLoveActivity : AppCompatActivity() {
    lateinit var mFoodText :TextView
    lateinit var mParkText :TextView
    lateinit var mFreeText :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            intent.setClass(this, MemberLoveListViewActivity::class.java)
            startActivity(intent)

        }
    }
}
