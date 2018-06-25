package com.jackpan.specialstudy.oveyouforyourtravel.Data

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hendraanggrian.pikasso.picasso
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import com.jackpan.specialstudy.oveyouforyourtravel.R
import java.util.ArrayList

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
    lateinit var mFavoriteList : ListView
    var mArrayList : ArrayList<CollectionData> = ArrayList()
    var mAdapter: MyAdapter? = null
    lateinit var mToken :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass = MfiebaselibsClass(this,this)
        setContentView(R.layout.activity_favorite_list)
        mFavoriteList = findViewById(R.id.favoritelist)
        mToken =MySharedPrefernces.getIsToken(this)
        if (!mToken.equals("")){
            mFirebselibClass.getFirebaseDatabase(CollectionData.KEY_URL+mToken,CollectionData.KEY_ID)

        }
    }

    inner class MyAdapter(var mAllData: ArrayList<GoogleResponseData.Results>?) : BaseAdapter() {
        fun updateData(datas: ArrayList<GoogleResponseData.Results>) {
            mAllData = datas
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return mAllData!!.size
        }

        override fun getItem(position: Int): Any {
            return mAllData!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val data = mAllData!![position]
            if (convertView == null)
                convertView = LayoutInflater.from(this@FavoriteListActivity).inflate(
                        R.layout.listview_layout, null)
            var mTittleText :TextView = convertView!!.findViewById(R.id.listviewtext)
            var photoimg :ImageView = convertView!!.findViewById(R.id.listview_img)
            mTittleText.text =data.name
            if(data.photos!=null){
//                var  photoString:String = GoogleMapAPISerive.getPhotos(this@TypeListViewActivity,data.photos.get(0).photo_reference)
                picasso.load("").into(photoimg)
            }else{
                picasso.load(R.mipmap.nolodingphoto).into(photoimg)

            }



            return convertView
        }

    }
}
