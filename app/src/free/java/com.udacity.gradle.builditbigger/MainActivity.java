package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String TEST_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    public static final String AD_SHOWN = "ad_shown";

    private InterstitialAd interstitialAd;
    private boolean adShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null ) {
            adShown = savedInstanceState.getBoolean(AD_SHOWN, false);
        }

        MobileAds.initialize(this);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(TEST_UNIT_ID);
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(adListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AD_SHOWN, false);
    }

    @Override
    public void tellJoke(View view) {
        Log.i(TAG, "adShown: " + adShown);
        if (interstitialAd.isLoaded() && ! adShown) {
            adShown = true;
            interstitialAd.show();
        } else {
            super.tellJoke(view);
        }
    }

    private AdListener adListener = new AdListener() {
        @Override
        public void onAdClosed() {
            fetchJoke();
        }
    };
}
