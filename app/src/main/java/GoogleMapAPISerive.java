import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by JackPan on 2018/5/26.
 */

public class GoogleMapAPISerive {
    private static final String TAG = "GoogleMapAPISerive";
    static  RequestQueue queue;

    public static  void setPlaceForRestaurant(Context context){
        queue = Volley.newRequestQueue(context);

        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyDeRZ8FEeGk0G9leGjbs316tbFUZu45J3I";

        StringRequest getRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d(TAG, "onResponse: "+s);

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d(TAG, "onErrorResponse: "+volleyError.getMessage());

                    }
                });
        queue.add(getRequest);
    }


}
