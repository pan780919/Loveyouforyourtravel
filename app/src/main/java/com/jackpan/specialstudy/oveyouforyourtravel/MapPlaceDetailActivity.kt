package com.jackpan.specialstudy.oveyouforyourtravel

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleMapPlaceDetailsData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleResponseData
import android.widget.LinearLayout
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import GoogleMapAPISerive
import android.content.Context
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.hendraanggrian.pikasso.picasso


class MapPlaceDetailActivity : AppCompatActivity(), GoogleMapAPISerive.GetResponse {
    lateinit var mViewPage :ViewPager
    lateinit var mNameText :TextView
    lateinit var mAddressText : TextView
    lateinit var mPhoneText :TextView
    lateinit var mOpenNowText : TextView
    lateinit var mOPenText : TextView
    lateinit var  mRatingBar : RatingBar
    lateinit var mImagePagerAdapter :ImagePagerAdapter
    var mPhotoData: ArrayList<String> = ArrayList()
    lateinit var  mString :String

    override fun getData(googleResponseData: GoogleResponseData?) {
    }

    override fun getDetailData(googleMapPlaceDetailsData: GoogleMapPlaceDetailsData?) {
        if (googleMapPlaceDetailsData!=null){

            setData(googleMapPlaceDetailsData.result)

            if(googleMapPlaceDetailsData.result.reviews!=null){
                for (review in googleMapPlaceDetailsData.result.reviews) {
                    Log.d("MapPlaceDetailActivity",review.text)

                }
            }
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


        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_place_detail)
        mViewPage = findViewById(R.id.viewpage)
        mNameText = findViewById(R.id.nametext)
        mAddressText = findViewById(R.id.addresstext)
        mPhoneText = findViewById(R.id.phonetext)
        mOpenNowText = findViewById(R.id.opennowtext)
        mOPenText = findViewById(R.id.opentext)
        mRatingBar = findViewById(R.id.rating)

        if(!getData().equals("")){
            GoogleMapAPISerive.getPlaceDeatail(this,getData(),this)
        }
        mImagePagerAdapter = ImagePagerAdapter(this,mPhotoData)
        mViewPage.adapter = mImagePagerAdapter

    }
    fun  setData(result : GoogleMapPlaceDetailsData.Result){
        mNameText.text = "名稱："+result.name
        mAddressText.text = "地址："+result.formatted_address
        mPhoneText.text = "聯絡電話："+result.formatted_phone_number
        if (result.opening_hours.open_now){
            mOpenNowText.text = "目前營業中!"
        }else{
            mOpenNowText.text = "目前沒有營業!"

        }
        mOPenText.text = "營業時間："+"\n"+result.opening_hours.weekday_text[0]+"\n"+result.opening_hours.weekday_text[1]+"\n"+result.opening_hours.weekday_text[2]+"\n"+
                result.opening_hours.weekday_text[3]+"\n"+result.opening_hours.weekday_text[4]+"\n"+result.opening_hours.weekday_text[5]+"\n"+result.opening_hours.weekday_text[6]
        mRatingBar.numStars = 5
        mRatingBar.rating =result.rating



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
}
