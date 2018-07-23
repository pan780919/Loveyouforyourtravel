package com.jackpan.specialstudy.oveyouforyourtravel;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.clickforce.ad.Listener.PreRollViewLinstener;
import com.clickforce.ad.PreRollAdView;

public class MainActivity extends AppCompatActivity {
    private PreRollAdView preRollAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preRollAdView = (PreRollAdView) this.findViewById(R.id.preroll);
        preRollAdView.setAdTagUrl(String.valueOf("4397"));
        preRollAdView.setAdPlay(this);

        preRollAdView.setOnPreRollViewLoaded(new PreRollViewLinstener() {
            @Override
            public void onStartPlayVideo() {
                Log.d("CF","play");
            }

            @Override
            public void onFailedToPreRollAd() {

            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        preRollAdView.fullScreen(this);

    }
}
