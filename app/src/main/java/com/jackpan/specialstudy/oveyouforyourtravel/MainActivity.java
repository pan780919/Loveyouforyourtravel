package com.jackpan.specialstudy.oveyouforyourtravel;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.clickforce.ad.Listener.PreRollViewLinstener;
import com.clickforce.ad.PreRollAdView;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideo;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private PreRollAdView preRollAdView;
    private MoPubRewardedVideoListener rewardedVideoListener;
    private Button btn1,btn2;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoPubRewardedVideos.loadRewardedVideo("207d9238cc834827b5e603d6b0b196dd");

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoPubRewardedVideos.showRewardedVideo("207d9238cc834827b5e603d6b0b196dd");

            }
        });

        preRollAdView = (PreRollAdView) this.findViewById(R.id.preroll);
        preRollAdView.setAdTagUrl(String.valueOf("8143"));
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

//        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder("207d9238cc834827b5e603d6b0b196dd")
//                .build();
//
//        MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
//        rewardedVideoListener = new MoPubRewardedVideoListener() {
//            @Override
//            public void onRewardedVideoLoadSuccess(String adUnitId) {
//                Log.d(TAG, "onRewardedVideoLoadSuccess: "+adUnitId);
//                // Called when the video for the given adUnitId has loaded. At this point you should be able to call MoPubRewardedVideos.showRewardedVideo(String) to show the video.
//            }
//            @Override
//            public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
//                Log.d(TAG, "onRewardedVideoLoadFailure: "+adUnitId);
//                Log.d(TAG, "onRewardedVideoLoadFailure: "+errorCode);
//                // Called when a video fails to load for the given adUnitId. The provided error code will provide more insight into the reason for the failure to load.
//            }
//
//            @Override
//            public void onRewardedVideoStarted(String adUnitId) {
//                // Called when a rewarded video starts playing.
//                Log.d(TAG, "onRewardedVideoStarted: "+adUnitId);
//            }
//
//            @Override
//            public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
//                //  Called when there is an error during video playback.
//                Log.d(TAG, "onRewardedVideoPlaybackError: "+adUnitId);
//                Log.d(TAG, "onRewardedVideoPlaybackError: "+errorCode);
//            }
//
//            @Override
//            public void onRewardedVideoClicked(@NonNull String adUnitId) {
//
//            }
//
//            @Override
//            public void onRewardedVideoClosed(String adUnitId) {
//                // Called when a rewarded video is closed. At this point your application should resume.
//            }
//
//            @Override
//            public void onRewardedVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MoPubReward reward) {
//                Log.d(TAG, "onRewardedVideoCompleted: ");
//            }
//
//        };
//
//        MoPubRewardedVideos.setRewardedVideoListener(rewardedVideoListener);

    }
    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
           /* MoPub SDK initialized.
           Check if you should show the consent dialog here, and make your ad requests. */
            }
        };
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        preRollAdView.fullScreen(this);

    }
}
