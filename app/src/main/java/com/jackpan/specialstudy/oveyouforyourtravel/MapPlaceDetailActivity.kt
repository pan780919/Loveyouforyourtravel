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
import android.view.View
import android.widget.ImageView
import com.hendraanggrian.pikasso.picasso


class MapPlaceDetailActivity : AppCompatActivity(), GoogleMapAPISerive.GetResponse {
    override fun getData(googleResponseData: GoogleResponseData?) {
    }

    override fun getDetailData(googleMapPlaceDetailsData: GoogleMapPlaceDetailsData?) {
        if (googleMapPlaceDetailsData!=null){
            Log.d("MapPlaceDetailActivity",googleMapPlaceDetailsData.result.name)
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
                    Log.d("Jack",photoString)


                }
            }


        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_place_detail)
        if(!getData().equals("")){
            GoogleMapAPISerive.getPlaceDeatail(this,getData(),this)
        }


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
            picasso.load(arrayList!![position]).into(imageView)
            container.addView(itemView)

            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as LinearLayout)
        }

    }
}
