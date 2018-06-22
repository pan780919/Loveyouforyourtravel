package com.jackpan.specialstudy.oveyouforyourtravel

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleMapPlaceDetailsData
import com.jackpan.specialstudy.oveyouforyourtravel.Data.GoogleResponseData


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

}
