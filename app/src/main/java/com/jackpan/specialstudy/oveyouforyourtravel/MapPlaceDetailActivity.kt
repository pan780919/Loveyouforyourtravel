package com.jackpan.specialstudy.oveyouforyourtravel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleMapPlaceDetailsData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleResponseData
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import android.content.Context
import android.support.v4.view.ViewPager
import android.text.format.DateFormat
import android.view.View
import android.widget.*
import com.hendraanggrian.pikasso.picasso
import java.util.*
import android.widget.AbsListView
import android.view.MotionEvent
import android.view.View.OnTouchListener

import java.text.SimpleDateFormat
import GoogleMapAPISerive.GetResponse
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
    lateinit var mListViewAdapter :MyAdapter
    lateinit var mReViewListView :LinearLayout
    lateinit var mFavoriteImg : ImageView
    lateinit var mNoFavoriteImg : ImageView
    lateinit var mFirebselibClass : MfiebaselibsClass
    lateinit var mTypeString :String

    var  mReViewData :ArrayList<GoogleMapPlaceDetailsData.Result.Reviews> = ArrayList()

    override fun getData(googleResponseData: GoogleResponseData?) {
    }

    override fun getDetailData(googleMapPlaceDetailsData: GoogleMapPlaceDetailsData?) {
        if (googleMapPlaceDetailsData!=null){
            setData(googleMapPlaceDetailsData.result)



            if(googleMapPlaceDetailsData.result.opening_hours!=null){
                Log.d("MapPlaceDetailActivity", googleMapPlaceDetailsData.result.opening_hours.open_now.toString())
                for (period in googleMapPlaceDetailsData.result.opening_hours.periods) {

                }


            }

            if(googleMapPlaceDetailsData.result.photos!=null){
                for (photos in googleMapPlaceDetailsData.result.photos) {
                    var  photoString:String = GoogleMapAPISerive.getPhotos(this@MapPlaceDetailActivity,photos.photo_reference)
                    mPhotoData.add(photoString)
                    Log.d("Jack",photoString)


                }
                mImagePagerAdapter.notifyDataSetChanged()
            }

            if(googleMapPlaceDetailsData.result.reviews!=null){
                for (review in googleMapPlaceDetailsData.result.reviews) {
                    Log.d("review",review.author_name)
                    Log.d("review",review.text)
                    Log.d("review",review.profile_photo_url)

                    Log.d("review",review.time.toString())
//                    mReViewData.add(review)
                    addnewLayout(review)


                }
//                Log.d("mReViewData",mReViewData.size.toString())
//                mListViewAdapter.notifyDataSetChanged()

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
//        setListViewHeightBasedOnChildren(mReViewListView)
//        mReViewListView.setOnTouchListener(OnTouchListener { v, event ->
//            // Setting on Touch Listener for handling the touch inside ScrollView
//// Disallow the touch request for parent scroll on touch of child view
//            v.parent.requestDisallowInterceptTouchEvent(true)
//            false
//        })

        if(!getData().equals("")){
            GoogleMapAPISerive.getPlaceDeatail(this,getData(),this)
        }
        mImagePagerAdapter = ImagePagerAdapter(this,mPhotoData)
        mViewPage.adapter = mImagePagerAdapter
//        mListViewAdapter = MyAdapter(mReViewData)
//        mReViewListView.adapter =mListViewAdapter


    }

    fun  setFavoriteView(result : GoogleMapPlaceDetailsData.Result){
        mNoFavoriteImg.setOnClickListener {
            mFavoriteImg.visibility = View.VISIBLE
            mNoFavoriteImg.visibility = View.GONE
            Toast.makeText(this,"收藏到最愛！",Toast.LENGTH_SHORT).show()
            setFavoriteToFirebase(result,"test12345",mTypeString)

        }
        mFavoriteImg.setOnClickListener {

            mFavoriteImg.visibility = View.GONE
            mNoFavoriteImg.visibility = View.VISIBLE
            Toast.makeText(this,"取消收藏！",Toast.LENGTH_SHORT).show()
//            mFirebselibClass.deleteData(CollectionData.KEY_URL,"test12345")

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
        mTypeString = bundle.getString(GoogleMapAPISerive.TYPE)
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
            Log.d("getView",position.toString())

            Log.d("getView",data.author_name)
            Log.d("getView",data.text)
            Log.d("getView",data.profile_photo_url)

            Log.d("getView",data.time.toString())
           if(data.profile_photo_url!=null) {
               picasso.load(data.profile_photo_url).error(R.mipmap.nolodingphoto).into(mReviewImg)
           }
            mReviewName.text = data.author_name
            mReviewText.text = data.text
            mReviewRating.rating = data.rating
//            mReviewTime.text = timestampToString(data.time)



            return convertView
        }

    }
//    fun timestampToString(times:Long):String{
//
////        val date = Date(times)
////        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////        return format.format(date)
//        val cal = Calendar.getInstance()
//        cal.timeZone = TimeZone.getTimeZone("UTC")
//        cal.timeInMillis = times
//        return (cal.get(Calendar.YEAR).toString() + " " + (cal.get(Calendar.MONTH) + 1) + " "
//                + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
//                + cal.get(Calendar.MINUTE))
//    }

    fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter ?: // pre-condition
                return

        var totalHeight = listView.paddingTop + listView.paddingBottom
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            (listItem as? ViewGroup)?.layoutParams = AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }

        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
        listView.layoutParams = params
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
//        mReviewTime.text = timestampToString(data.time)
        mReViewListView.addView(view)


    }
    lateinit var mUrl :String
    fun setFavoriteToFirebase(data: GoogleMapPlaceDetailsData.Result,userid:String,type :String){
        if(data==null){
            return
        }
        if (userid==null){
            return
        }
        Log.d("setFavoriteToFirebase",data.name)
        Log.d("setFavoriteToFirebase",data.id)

        var mHasMap = HashMap<String,String>()
        mHasMap.put(CollectionData.KEY_ID,data.id)
        mHasMap.put(CollectionData.KEY_NAME,data.name)
        mHasMap.put(CollectionData.KEY_PHOTO,mPhotoData.get(0))

        if(type.equals(GoogleMapAPISerive.TYPE_RESTAURANT)){
            mUrl = CollectionData.KEY_URL_FOOD

        }else if(type.equals(GoogleMapAPISerive.TYPE_PARK)){
            mUrl = CollectionData.KEY_URL_PARK
        }

        mFirebselibClass.setFireBaseDB(mUrl,userid,mHasMap)

    }
}
