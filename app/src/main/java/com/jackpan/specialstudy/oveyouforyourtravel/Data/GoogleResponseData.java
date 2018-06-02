package com.jackpan.specialstudy.oveyouforyourtravel.Data;

/**
 * Created by JackPan on 2018/6/3.
 */

public class GoogleResponseData {
    public  String next_page_token;
    public  String status;
    public Results[]results;

    public  class  Results{
        public Geometry geometry;
        public  String icon;
        public  String id;
        public  String name;
        public  Opening_hours opening_hours;
        public  Photos [] photos;
        public  String place_id;
        public  int price_level;
        public  float rating;
        public  String reference;
        public  String scope;
        public  String vicinity;
        public class  Geometry{
            public Location location;
            public  Viewport viewport;

            public  class  Location{
                public Double lat;
                public Double lng;

            }

            public  class  Viewport{

            }


        }
        public  class  Opening_hours{
            public  boolean open_now;



        }

        public  class Photos{

            public  String photo_reference;
        }


    }



}
