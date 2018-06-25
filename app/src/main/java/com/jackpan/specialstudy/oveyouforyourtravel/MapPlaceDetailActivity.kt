package com.jackpan.specialstudy.oveyouforyourtravel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleMapPlaceDetailsData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleResponseData
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import android.content.Context
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.*
import com.hendraanggrian.pikasso.picasso
import java.util.*
import GoogleMapAPISerive
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import com.jackpan.specialstudy.oveyouforyourtravel.Data.CollectionData
import kotlin.collections.HashMap


class MapPlaceDetailActivity : AppCompatActivity(), GoogleMapAPISerive.GetResponse, MfirebaeCallback {
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

    lateinit var mViewPage :ViewPager
    lateinit var mNameText :TextView
    lateinit var mAddressText : TextView
    lateinit var mPhoneText :TextView
    lateinit var mOpenNowText : TextView
    lateinit var mOPenText : TextView
    lateinit var  mRatingBar : RatingBar
    lateinit var mImagePagerAdapter :ImagePagerAdapter
    var mPhotoData: ArrayList<String> = ArrayList()
    lateinit var mReViewListView :LinearLayout
    lateinit var mFavoriteImg : ImageView
    lateinit var mNoFavoriteImg : ImageView
    lateinit var mFirebselibClass : MfiebaselibsClass

    override fun getData(googleResponseData: GoogleResponseData?) {
    }

    override fun getDetailData(googleMapPlaceDetailsData: GoogleMapPlaceDetailsData?) {
        if (googleMapPlaceDetailsData!=null){
            setData(googleMapPlaceDetailsData.result)



            if(googleMapPlaceDetailsData.result.opening_hours!=null){
                for (period in googleMapPlaceDetailsData.result.opening_hours.periods) {

                }


            }

            if(googleMapPlaceDetailsData.result.photos!=null){
                for (photos in googleMapPlaceDetailsData.result.photos) {
                    var  photoString:String = GoogleMapAPISerive.getPhotos(this@MapPlaceDetailActivity,photos.photo_reference)
                    mPhotoData.add(photoString)


                }
                mImagePagerAdapter.notifyDataSetChanged()
            }

            if(googleMapPlaceDetailsData.result.reviews!=null){
                for (review in googleMapPlaceDetailsData.result.reviews) {
                    addnewLayout(review)


                }


            }


        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass =  MfiebaselibsClass(this,this@MapPlaceDetailActivity)
        setContentView(R.layout.activity_map_place_detail)
        mViewPage = findViewById(R.id.viewpage)
        mNameText = findViewById(R.id.nametext)
        mAddressText = findViewById(R.id.addresstext)
        mPhoneText = findViewById(R.id.phonetext)
        mOpenNowText = findViewById(R.id.opennowtext)
        mOPenText = findViewById(R.id.opentext)
        mRatingBar = findViewById(R.id.rating)
        mReViewListView = findViewById(R.id.reviewlistview)
        mNoFavoriteImg = findViewById(R.id.nofavoriteimg)
        mFavoriteImg = findViewById(R.id.favoriteimg)
        if(!getData().equals("")){
            GoogleMapAPISerive.getPlaceDeatail(this,getData(),this)
        }
        mImagePagerAdapter = ImagePagerAdapter(this,mPhotoData)
        mViewPage.adapter = mImagePagerAdapter



    }

    fun  setFavoriteView(result : GoogleMapPlaceDetailsData.Result){
        mNoFavoriteImg.setOnClickListener {
            mFavoriteImg.visibility = View.VISIBLE
            mNoFavoriteImg.visibility = View.GONE
            Toast.makeText(this,"收藏到最愛！",Toast.LENGTH_SHORT).show()
            setFavoriteToFirebase(result)

        }
        mFavoriteImg.setOnClickListener {

            mFavoriteImg.visibility = View.GONE
            mNoFavoriteImg.visibility = View.VISIBLE
            Toast.makeText(this,"取消收藏！",Toast.LENGTH_SHORT).show()

        }

    }
    fun  setData(result : GoogleMapPlaceDetailsData.Result){
        mNameText.text = "名稱："+result.name
        mAddressText.text = "地址："+result.formatted_address
        mPhoneText.text = "聯絡電話："+result.formatted_phone_number
        if (result.opening_hours!=null){
            if (result.opening_hours.open_now){
                mOpenNowText.text = "目前營業中!"
            }else{
                mOpenNowText.text = "目前沒有營業!"

            }
        }

        if(result.opening_hours!=null&&result.opening_hours.weekday_text!=null){
            mOPenText.text = "營業時間："+"\n"+result.opening_hours.weekday_text[0]+"\n"+result.opening_hours.weekday_text[1]+"\n"+result.opening_hours.weekday_text[2]+"\n"+
                    result.opening_hours.weekday_text[3]+"\n"+result.opening_hours.weekday_text[4]+"\n"+result.opening_hours.weekday_text[5]+"\n"+result.opening_hours.weekday_text[6]

        }
        mRatingBar.numStars = 5
        mRatingBar.rating =result.rating

        setFavoriteView(result)


    }
    fun getData():String{
        var mString:String =""
        var bundle:Bundle = intent.extras
        mString = bundle.getString(GoogleMapAPISerive.TYPE_PLACEID)
        return mString
    }


    inner class ImagePagerAdapter(internal var context: Context, internal var arrayList: ArrayList<String>?) : PagerAdapter() {
        internal var layoutInflater: LayoutInflater

        init {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        override fun getCount(): Int {
            return if (arrayList != null) {
                arrayList!!.size
            } else 0
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as LinearLayout
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val itemView = layoutInflater.inflate(R.layout.image_viewpager_layout, container, false)
            val imageView = itemView.findViewById<ImageView>(R.id.viewPagerItem_image1)
            picasso.load(arrayList!![position]).error(R.mipmap.nolodingphoto).into(imageView)
            container.addView(itemView)

            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as LinearLayout)
        }

    }

    inner class MyAdapter(var mAllData: ArrayList<GoogleMapPlaceDetailsData.Result.Reviews>?) : BaseAdapter() {
        fun updateData(datas: ArrayList<GoogleMapPlaceDetailsData.Result.Reviews>) {
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
                convertView = LayoutInflater.from(this@MapPlaceDetailActivity).inflate(
                        R.layout.review_layout, null)
            var mReviewImg : ImageView = convertView!!.findViewById(R.id.reviewimg)
            var mReviewName : TextView = convertView!!.findViewById(R.id.reviewname)
            var mReviewText : TextView = convertView!!.findViewById(R.id.reviewtext)
            var mReviewTime : TextView = convertView!!.findViewById(R.id.reviewtimetext)
            var mReviewRating : RatingBar = convertView!!.findViewById(R.id.rating)
           if(data.profile_photo_url!=null) {
               picasso.load(data.profile_photo_url).error(R.mipmap.nolodingphoto).into(mReviewImg)
           }
            mReviewName.text = data.author_name
            mReviewText.text = data.text
            mReviewRating.rating = data.rating



            return convertView
        }

    }
    fun addnewLayout(data:GoogleMapPlaceDetailsData.Result.Reviews){
        val view = getLayoutInflater().inflate(R.layout.review_layout, null)
        var mReviewImg : ImageView = view!!.findViewById(R.id.reviewimg)
        var mReviewName : TextView = view!!.findViewById(R.id.reviewname)
        var mReviewText : TextView = view!!.findViewById(R.id.reviewtext)
        var mReviewTime : TextView = view!!.findViewById(R.id.reviewtimetext)
        var mReviewRating : RatingBar = view!!.findViewById(R.id.rating)

        if(data.profile_photo_url!=null) {
            picasso.load(data.profile_photo_url).error(R.mipmap.nolodingphoto).into(mReviewImg)
        }
        mReviewName.text = data.author_name
        mReviewText.text = data.text
        mReviewRating.rating = data.rating
        mReViewListView.addView(view)


    }

    fun setFavoriteToFirebase(data: GoogleMapPlaceDetailsData.Result){
        if(data==null){
            return
        }
        var token :String = MySharedPrefernces.getIsToken(this)
        if(token.equals("")){
            return
        }
        var mHasMap = HashMap<String,String>()
        mHasMap.put(CollectionData.KEY_ID,data.id)
        mHasMap.put(CollectionData.KEY_NAME,data.name)
        mHasMap.put(CollectionData.KEY_PHOTO,mPhotoData.get(0))


        mFirebselibClass.setFireBaseDB(CollectionData.KEY_URL+"/"+token,token,mHasMap)

    }
}
